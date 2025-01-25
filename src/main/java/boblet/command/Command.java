package boblet.command;

import boblet.exception.BobletException;
import boblet.util.Storage;
import boblet.util.TaskList;
import boblet.util.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BobletException;

    public boolean isExit() {
        return false;
    }
}
