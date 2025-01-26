package boblet.command;
import java.io.IOException;

import boblet.exception.BobletException;
import boblet.task.Task;
import boblet.util.Storage;
import boblet.util.TaskList;
import boblet.util.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String index) throws BobletException {
        try {
            this.index = Integer.parseInt(index.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new BobletException("Invalid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobletException {
        if (index < 0 || index >= tasks.size()) {
            throw new BobletException("Task number out of range.");
        }

        Task task = tasks.deleteTask(index);
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage("  " + task);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");

        try {
            storage.saveTasks(tasks.getAllTasks());
        } catch (IOException e) {
            throw new BobletException("Failed to save tasks to storage: " + e.getMessage());
        }
    }

    public int getTaskIndex() {
        return this.index; // Expose the task index for testing and other operations
    }
}
