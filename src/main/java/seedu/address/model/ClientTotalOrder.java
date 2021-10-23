package seedu.address.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Represents the total orders of a client ({@code Person}).
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ClientTotalOrder {
    private final SimpleStringProperty clientName;
    private final SimpleDoubleProperty totalOrder;

    /**
     * Constructs a {@code ClientTotalOrder}
     *
     * @param clientName the name of the client
     * @param totalOrder the total amount of orders by this client.
     */
    public ClientTotalOrder(String clientName, double totalOrder) {
        this.clientName = new SimpleStringProperty(clientName);
        this.totalOrder = new SimpleDoubleProperty(totalOrder);
    }

    public String getClientName() {
        return clientName.getValue();
    }

    public Double getTotalOrder() {
        return totalOrder.getValue();
    }
}
