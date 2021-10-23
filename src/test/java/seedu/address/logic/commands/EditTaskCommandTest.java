package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.EditTaskCommand.MESSAGE_DUPLICATE_TASK;
import static seedu.address.logic.commands.EditTaskCommand.MESSAGE_NO_CHANGES_MADE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TypicalTasks;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditTaskCommand.
 */
public class EditTaskCommandTest {

    private AddressBook typicalPersonsAddressBookWithTask = TypicalTasks
            .addTypicalTasksToAddressBook(new AddressBook());
    private Model model = new ModelManager(typicalPersonsAddressBookWithTask, new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Task editedTask = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        Task lastTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task editedTask = taskInList.withLabel(VALID_LABEL_ORDER).build();

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withLabel(VALID_LABEL_ORDER).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setTask(lastTask, editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTaskUnfilteredList_failure() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(firstTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_SECOND_TASK, descriptor);

        assertCommandFailure(editTaskCommand, model, MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_noChangesMadeUnfilteredList_failure() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(firstTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        assertCommandFailure(editTaskCommand, model, MESSAGE_NO_CHANGES_MADE);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withLabel(VALID_LABEL_ORDER).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditTaskCommand standardCommand = new EditTaskCommand(INDEX_FIRST_TASK, DESC_ORDER);

        // same values -> returns true
        EditTaskDescriptor copyDescriptor = new EditTaskDescriptor(DESC_ORDER);
        EditTaskCommand commandWithSameValues = new EditTaskCommand(INDEX_FIRST_TASK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_SECOND_TASK, DESC_ORDER)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_FIRST_TASK, DESC_SEW)));
    }
}
