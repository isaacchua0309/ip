package boblet.command;

import java.io.IOException;

import boblet.exception.BobletException;
import boblet.task.Task;
import boblet.util.Storage;
import boblet.util.TaskList;
import boblet.util.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class DoneCommand extends Command {
    private final int index;

    /**
     * Constructs a DoneCommand.
     *
     * @param index The index of the task to mark as done (1-based index as input).
     * @throws BobletException If the provided index is invalid or not a number.
     */
    public DoneCommand(String index) throws BobletException {
        try {
            this.index = Integer.parseInt(index.trim()) - 1; // Convert to 0-based index
        } catch (NumberFormatException e) {
            throw new BobletException("Invalid task number.");
        }
    }

    /**
     * Executes the done command by marking the task at the specified index as done.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The UI to display messages to the user.
     * @param storage The storage to persist changes.
     * @throws BobletException If the index is out of range or an error occurs during file I/O.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobletException {
        if (index < 0 || index >= tasks.size()) {
            throw new BobletException("Task number out of range.");
        }

        Task task = tasks.getTask(index);
        task.markAsDone();
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage("  " + task);

        try {
            storage.saveTasks(tasks.getAllTasks());
        } catch (IOException e) {
            throw new BobletException("Failed to save tasks to storage: " + e.getMessage());
        }
    }

    /**
     * Retrieves the task index to be marked as done.
     *
     * @return The 0-based index of the task.
     */
    public int getTaskIndex() {
        return this.index;
    }
}
