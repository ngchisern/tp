package seedu.address.testutil;

import seedu.address.model.Date;
import seedu.address.model.order.Amount;
import seedu.address.model.order.Customer;
import seedu.address.model.order.Order;

public class OrderBuilder {

    public static final String DEFAULT_CUSTOMER = "Johnson";
    public static final String DEFAULT_AMOUNT = "99.99";
    public static final String DEFAULT_DATE = "20th October 2021";
    public static final boolean DEFAULT_IS_COMPLETE = false;
    public static final long DEFAULT_ID = 2021;

    private Customer customer;
    private Amount amount;
    private Date date;
    private boolean isComplete;
    private long id;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        customer = new Customer(DEFAULT_CUSTOMER);
        amount = new Amount(DEFAULT_AMOUNT);
        date = new Date(DEFAULT_DATE);
        isComplete = DEFAULT_IS_COMPLETE;
        id = DEFAULT_ID;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        customer = orderToCopy.getCustomer();
        amount = orderToCopy.getAmount();
        date = orderToCopy.getDate();
        isComplete = orderToCopy.getIsComplete();
        id = orderToCopy.getId();
    }

    /**
     * Sets the {@code Customer} of the {@code Order} that we are building.
     */
    public OrderBuilder withCustomer(String customer) {
        this.customer = new Customer(customer);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Order} that we are building.
     */
    public OrderBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Order} that we are building.
     */
    public OrderBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code isComplete} of the {@code Order} that we are building.
     */
    public OrderBuilder withIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
        return this;
    }

    /**
     * builds the Order.
     */
    public Order build() {
        Order order = new Order(customer, date, amount);
        order.setIsComplete(isComplete);
        order.setId(this.id);
        return order;
    }
}
