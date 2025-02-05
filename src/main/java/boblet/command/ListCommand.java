package boblet.command;

import boblet.util.Storage;
import boblet.util.TaskList;
import boblet.util.Ui;

/**
 * Represents a command to display all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, returning all tasks in the task list.
     * If the task list is empty, a message indicating this is returned.
     *
     * @param tasks   The task list containing the tasks to display.
     * @param ui      The UI to display messages.
     * @param storage The storage to persist changes (not used in this command).
     * @return A response listing all tasks or a message if the task list is empty.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            return "Your task list is empty!";
        }

        StringBuilder response = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
        }

        return response.toString().trim();
    }

    /**
     * Returns false since listing tasks does not exit the application.
     *
     * @return False, since the command does not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
