package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Lists all the incomplete tasks.
 */
public class ShowIncompleteTasks extends Command {

    public static final String COMMAND_WORD = "incompletetasks";

    public static final String MESSAGE_SUCCESS = "Listed all the incomplete tasks";

    public static final Predicate<Task> PREDICATE_SHOW_INCOMPLETE_TASKS = t -> !t.getIsDone();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_INCOMPLETE_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
