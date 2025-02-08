package boblet.command;

import boblet.util.Storage;
import boblet.util.TaskList;

/**
 * Represents a command to display all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, returning all tasks in the task list.
     * If the task list is empty, a message indicating this is returned.
     *
     * @param tasks   The task list containing the tasks to display.
     * @param storage The storage to persist changes (not used in this command).
     * @return A response listing all tasks or a message if the task list is empty.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";

        return tasks.size() == 0 ? "Your task list is empty!" : formatTaskList(tasks);
    }

    /**
     * Formats the task list into a readable string.
     *
     * @param tasks The task list containing all tasks.
     * @return A formatted string of tasks.
     */
    private String formatTaskList(TaskList tasks) {
        StringBuilder response = new StringBuilder("Here are your tasks:\n");

        for (int i = 0; i < tasks.size(); i++) {
            assert tasks.getTask(i) != null : "Task at index " + i + " should not be null";
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
        assert true : "ListCommand should always return false for isExit()";
        return false;
    }
}
