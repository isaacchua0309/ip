package boblet.command;

import java.io.IOException;

import boblet.exception.BobletException;
import boblet.task.Task;
import boblet.util.Storage;
import boblet.util.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand.
     *
     * @param index The index of the task to delete (1-based index as input).
     * @throws BobletException If the provided index is invalid or not a number.
     */
    public DeleteCommand(String index) throws BobletException {
        assert index != null : "Index string should not be null";
        assert !index.trim().isEmpty() : "Index string should not be empty";

        try {
            this.index = Integer.parseInt(index.trim()) - 1; // Convert to 0-based index
        } catch (NumberFormatException e) {
            throw new BobletException("Invalid task number.");
        }

        assert this.index >= 0 : "Parsed index should not be negative";
    }

    /**
     * Executes the delete command by removing the task at the specified index from the task list.
     *
     * @param tasks   The task list to operate on.
     * @param storage The storage to persist changes.
     * @return A response message after deleting the task.
     * @throws BobletException If the index is out of range or an error occurs during file I/O.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws BobletException {
        assert tasks != null : "Task list should not be null before execution";
        assert storage != null : "Storage should not be null before execution";
        assert index >= 0 && index < tasks.size() : "Index should be within valid task list bounds";

        if (index < 0 || index >= tasks.size()) {
            throw new BobletException("Task number out of range.");
        }

        Task task = tasks.deleteTask(index);
        assert task != null : "Deleted task should not be null";

        String response = String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                task, tasks.size());

        try {
            storage.saveTasks(tasks.getAllTasks());
        } catch (IOException e) {
            throw new BobletException("Failed to save tasks to storage: " + e.getMessage());
        }

        return response;
    }

    /**
     * Retrieves the task index to be deleted.
     *
     * @return The 0-based index of the task.
     */
    public int getTaskIndex() {
        assert index >= 0 : "Task index should always be non-negative";
        return this.index;
    }

    /**
     * Returns false since deleting a task does not exit the application.
     *
     * @return False, since the command does not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false; // No assertion needed as this will always be false
    }
}
