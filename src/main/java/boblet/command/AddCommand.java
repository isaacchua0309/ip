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
     * and returning a response message.
     *
     * @param tasks   The current task list.
     * @param ui      The UI to display messages.
     * @param storage The storage to save the updated task list.
     * @return The response message.
     * @throws BobletException If saving to storage fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobletException {
        tasks.addTask(task);
        String response = "Got it. I've added this task:\n"
                        + "  " + task + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.";

        try {
            storage.saveTasks(tasks.getAllTasks());
        } catch (IOException e) {
            throw new BobletException("Failed to save tasks to storage: " + e.getMessage());
        }

        return response;
    }

    /**
     * Retrieves the task associated with this AddCommand.
     *
     * @return The task to be added.
     */
    public Task getTask() {
        return this.task;
    }

    /**
     * Returns false since adding a task does not exit the application.
     *
     * @return False, since the command does not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
