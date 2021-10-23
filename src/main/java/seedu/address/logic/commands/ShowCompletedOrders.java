package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.order.Order;


/**
 * Lists all the completed orders.
 */
public class ShowCompletedOrders extends Command {

    public static final String COMMAND_WORD = "completedorders";

    public static final String MESSAGE_SUCCESS = "Listed all the completed orders";

    public static final Predicate<Order> PREDICATE_SHOW_COMPLETED_ORDERS = Order::getIsComplete;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(PREDICATE_SHOW_COMPLETED_ORDERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
