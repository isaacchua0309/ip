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
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword should not be null or empty";
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the find command by searching for tasks that contain the keyword.
     *
     * @param tasks   The task list to search within.
     * @param storage The storage (not used in this command).
     * @return A response message listing matching tasks or indicating none were found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";

        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        assert matchingTasks != null : "Matching tasks list should not be null";

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
        assert true : "FindCommand should always return false for isExit()";
        return false;
    }
}
