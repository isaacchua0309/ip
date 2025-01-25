package boblet.command;

import boblet.util.Storage;
import boblet.util.TaskList;
import boblet.util.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye! See you soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
