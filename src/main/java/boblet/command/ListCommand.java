package boblet.command;

import boblet.util.Storage;
import boblet.util.TaskList;
import boblet.util.Ui;

/**
 * Represents a command to display all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list.
     * If the task list is empty, a message indicating this is displayed.
     *
     * @param tasks   The task list containing the tasks to display.
     * @param ui      The UI to display messages to the user.
     * @param storage The storage to persist changes (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        if (tasks.size() == 0) {
            ui.showMessage("Your task list is empty!");
        } else {
            ui.showMessage("Here are your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.getTask(i));
            }
        }
        ui.showLine();
    }
}
