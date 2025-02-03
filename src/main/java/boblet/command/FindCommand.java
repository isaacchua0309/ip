package boblet.command;

import java.util.ArrayList;

import boblet.task.Task;
import boblet.util.Storage;
import boblet.util.TaskList;
import boblet.util.Ui;

/**
 * The FindCommand class handles searching for tasks containing a specific keyword.
 * It executes the search and displays matching tasks from the TaskList.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the matching tasks in your list:");
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found.");
        } else {
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }
}
