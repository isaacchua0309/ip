package boblet.command;
import java.io.IOException;

import boblet.exception.BobletException;
import boblet.task.Task;
import boblet.util.Storage;
import boblet.util.TaskList;
import boblet.util.Ui;

public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(String index) throws BobletException {
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

    public int getTaskIndex() {
        return this.index; // Expose the task index for testing and other operations
    }
}
