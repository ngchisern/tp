package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Lists all the completed tasks.
 */
public class ShowCompletedTasks extends Command {

    public static final String COMMAND_WORD = "completedtasks";

    public static final String MESSAGE_SUCCESS = "Listed all the completed tasks";

    public static final Predicate<Task> PREDICATE_SHOW_COMPLETED_TASKS = Task::getIsDone;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_COMPLETED_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
