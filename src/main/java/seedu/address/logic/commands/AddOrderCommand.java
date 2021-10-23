package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

public class AddOrderCommand extends Command {

    public static final String COMMAND_WORD = "addorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an order to the application. "
            + "Parameters: "
            + PREFIX_CUSTOMER + "CUSTOMER "
            + PREFIX_AMOUNT + "AMOUNT "
            + PREFIX_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CUSTOMER + "Alice "
            + PREFIX_AMOUNT + "10.90 "
            + PREFIX_DATE + "20th August 2021";

    public static final String MESSAGE_SUCCESS = "New order added: %1$s";

    private final Order toAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddOrderCommand(Order order) {
        requireNonNull(order);
        toAdd = order;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addOrder(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddOrderCommand
                && toAdd.isSameOrder(((AddOrderCommand) other).toAdd));
    }

}
