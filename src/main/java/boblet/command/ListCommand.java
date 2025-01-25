package boblet.command;

import boblet.util.Storage;
import boblet.util.TaskList;
import boblet.util.Ui;

public class ListCommand extends Command {
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
