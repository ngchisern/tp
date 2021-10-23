package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 * Sorts and lists all orders in the address book by their amount in descending order.
 */
public class SortOrdersByAmountCommand extends Command {

    public static final String COMMAND_WORD = "sortorders";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all orders by their amount "
            + "in descending order and displays them as a list with index numbers.\n";

    public static final String MESSAGE_SUCCESS = "Sorted all orders";

    //Order of parameters reversed to sort in descending order
    public static final Comparator<Order> COMPARATOR = (o1, o2) -> o2.getAmount().compareTo(o1.getAmount());

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortOrderList(COMPARATOR);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortOrdersByAmountCommand); // instanceof handles nulls
    }
}

