package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 * Lists all the incomplete orders.
 */
public class ShowIncompleteOrders extends Command {

    public static final String COMMAND_WORD = "incompleteorders";

    public static final String MESSAGE_SUCCESS = "Listed all the incomplete orders";

    public static final Predicate<Order> PREDICATE_SHOW_INCOMPLETE_ORDERS = o -> !o.getIsComplete();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(PREDICATE_SHOW_INCOMPLETE_ORDERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
