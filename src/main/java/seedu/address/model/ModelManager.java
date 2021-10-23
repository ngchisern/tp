package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Order> filteredOrders;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTasks = new FilteredList<>(this.addressBook.getTaskList());
        filteredOrders = new FilteredList<>(this.addressBook.getOrderList());

    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Task Management ==================================================================================

    /**
     * Checks if tasklist has this task.
     */
    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return addressBook.hasTask(task);
    }

    /**
     * Adds a task to tasklist.
     */
    public void addTask(Task toAdd) {
        addressBook.addTask(toAdd);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    /**
     * Deletes a task from tasklist.
     */
    public void deleteTask(Task toDelete) {
        addressBook.deleteTask(toDelete);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);
        addressBook.setTask(target, editedTask);
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    public void markTask(Task task) {
        addressBook.markTask(task);
    }


    //=========== Order Management ==================================================================================

    /**
     * Checks if orderlist has this order.
     */
    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return addressBook.hasOrder(order);
    }

    @Override
    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);
        addressBook.setOrder(target, editedOrder);
    }

    /**
     * Adds an order to orderlist.
     */
    public void addOrder(Order toAdd) {
        addressBook.addOrder(toAdd);
        resetOrderView();
    }

    /**
     * Deletes an order from orderlist.
     */
    public void deleteOrder(Order toDelete) {
        addressBook.deleteOrder(toDelete);
    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return filteredOrders;
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        requireNonNull(predicate);
        filteredOrders.setPredicate(predicate);
    }

    /**
     * Marks an order as completed
     */
    public void markOrder(Order order) {
        addressBook.markOrder(order);
    }

    /**
     * For each person, finds orders associated with the person, and adds up the amount.
     * Creates a ClientTotalOrder for each person.
     *
     * @return an ObservableList of {@code ClientTotalOrder}.
     */
    @Override
    public ObservableList<ClientTotalOrder> getClientTotalOrders() {
        ObservableList<ClientTotalOrder> clientTotalOrders = FXCollections.observableArrayList();
        for (Person client : addressBook.getPersonList()) {
            clientTotalOrders.add(getClientTotalOrder(client));
        }
        return clientTotalOrders;
    }

    private ClientTotalOrder getClientTotalOrder(Person client) {
        String clientName = client.getName().toString();
        Predicate<Order> correctClient = (order) -> order.getCustomer().toString().equals(clientName);
        double totalOrder = addressBook.getOrderList().stream()
                .filter(correctClient)
                .mapToDouble(Order::getAmountAsDouble)
                .sum();
        return new ClientTotalOrder(clientName, totalOrder);
    }


    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    @Override
    public void sortOrderList(Comparator<Order> comparator) {
        addressBook.sortOrders(comparator);
        filteredOrders.setPredicate(PREDICATE_SHOW_ALL_ORDERS);
    }

    @Override
    public void resetOrderView() {
        Comparator<Order> defaultComparator = Order::compareTo;
        addressBook.sortOrders(defaultComparator);
        updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
    }

}
