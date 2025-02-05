package boblet.command;

import java.util.ArrayList;

import boblet.task.Task;
import boblet.util.Storage;
import boblet.util.TaskList;

/**
 * Represents a command to find tasks containing a given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the find command by searching for tasks that contain the keyword.
     *
     * @param tasks   The task list to search within.
     * @param ui      The UI to display messages.
     * @param storage The storage (not used in this command).
     * @return A response message listing matching tasks or indicating none were found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        }

        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            response.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
        }

        return response.toString().trim();
    }

    /**
     * Returns false since finding tasks does not exit the application.
     *
     * @return False, since the command does not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
