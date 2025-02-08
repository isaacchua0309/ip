package boblet.command;

import java.io.IOException;

import boblet.exception.BobletException;
import boblet.task.Task;
import boblet.util.Storage;
import boblet.util.TaskList;

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
        assert task != null : "Task should not be null";
        this.task = task;
    }

    /**
     * Executes the AddCommand, adding the task to the task list, updating storage,
     * and returning a response message.
     *
     * @param tasks   The current task list.
     * @param storage The storage to save the updated task list.
     * @return The response message.
     * @throws BobletException If saving to storage fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws BobletException {
        assert tasks != null : "TaskList should not be null before execution";
        assert storage != null : "Storage should not be null before execution";
        assert task != null : "Task to add should not be null";

        String response;

        try {
            tasks.addTask(task); // Add the task once
            storage.saveTasks(tasks.getAllTasks());

            response = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                    task, tasks.size());

        } catch (BobletException e) {
            response = e.getMessage(); // Return the duplicate task message
        } catch (IOException e) {
            response = "Failed to save tasks: " + e.getMessage();
        }

        return response;
    }

    /**
     * Retrieves the task associated with this AddCommand.
     *
     * @return The task to be added.
     */
    public Task getTask() {
        assert task != null : "Task should not be null when retrieved";
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
