package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.ClientTotalOrder;

/**
 * Controller for a total orders page
 */
public class TotalOrdersWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(TotalOrdersWindow.class);
    private static final String FXML = "TotalOrdersWindow.fxml";

    private final Logic logic;

    @FXML
    private TableView<ClientTotalOrder> table;
    @FXML
    private TableColumn<ClientTotalOrder, String> clientCol;
    @FXML
    private TableColumn<ClientTotalOrder, Double> totalCol;

    /**
     * Creates a {@code TotalOrdersWindow} with the given {@code Logic}.
     */
    public TotalOrdersWindow(Logic logic) {
        super(FXML, new Stage());
        this.logic = logic;
    }

    /**
     * Shows the total orders window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing total orders.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the total orders window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the total orders window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the total orders window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Reloads ClientTotalOrders items from AddressBook.
     */
    public void reloadData() {
        table.setItems(logic.getClientTotalOrders());
        clientCol.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalOrder"));
    }
}
