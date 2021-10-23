package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.ModelStub;
import seedu.address.model.order.Order;
import seedu.address.testutil.OrderBuilder;

public class AddOrderCommandTest {
    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(null));
    }

    @Test
    public void execute_orderAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingOrderAdded modelStub = new ModelStubAcceptingOrderAdded();
        Order validOrder = new OrderBuilder().build();

        CommandResult commandResult = new AddOrderCommand(validOrder).execute(modelStub);

        assertEquals(String.format(AddOrderCommand.MESSAGE_SUCCESS, validOrder), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validOrder), modelStub.ordersAdded);
    }

    @Test
    public void equals() {
        Order order1 = new OrderBuilder().withCustomer("Alice").build();
        Order order2 = new OrderBuilder().withCustomer("Bob").build();
        AddOrderCommand addOrder1Command = new AddOrderCommand(order1);
        AddOrderCommand addOrder2Command = new AddOrderCommand(order2);

        // same object -> returns true
        assertTrue(addOrder1Command.equals(addOrder1Command));

        // same values -> returns true
        AddOrderCommand addOrder1CommandCopy = new AddOrderCommand(order1);
        assertTrue(addOrder1Command.equals(addOrder1CommandCopy));

        // different types -> returns false
        assertFalse(addOrder1Command.equals(1));

        // null -> returns false
        assertFalse(addOrder1Command.equals(null));

        // different order -> returns false
        assertFalse(addOrder1Command.equals(addOrder2Command));
    }

    /**
     * A model stub for testing adding single order
     */
    private class ModelStubAcceptingOrderAdded extends ModelStub {
        private final ArrayList<Order> ordersAdded = new ArrayList<Order>();

        @Override
        public void addOrder(Order order) {
            requireNonNull(order);
            ordersAdded.add(order);
        }
    }
}
