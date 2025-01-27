package boblet.command;

import java.io.IOException;

import boblet.exception.BobletException;
import boblet.task.Task;
import boblet.util.Storage;
import boblet.util.TaskList;
import boblet.util.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task to be added.
     *
     * @param task The task to add to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the AddCommand, adding the task to the task list, updating storage,
     * and notifying the user.
     *
     * @param tasks   The current task list.
     * @param ui      The UI to display messages to the user.
     * @param storage The storage to save the updated task list.
     * @throws BobletException If saving to storage fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobletException {
        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + task);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");

        try {
            storage.saveTasks(tasks.getAllTasks());
        } catch (IOException e) {
            throw new BobletException("Failed to save tasks to storage: " + e.getMessage());
        }
    }

    /**
     * Retrieves the task associated with this AddCommand.
     *
     * @return The task to be added.
     */
    public Task getTask() {
        return this.task;
    }
}
