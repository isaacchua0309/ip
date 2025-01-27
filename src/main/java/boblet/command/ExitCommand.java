package boblet.command;

import boblet.util.Storage;
import boblet.util.TaskList;
import boblet.util.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, displaying a farewell message to the user.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The UI to display messages to the user.
     * @param storage The storage to persist changes (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye! See you soon!");
    }

    /**
     * Indicates that this command signals the application to terminate.
     *
     * @return {@code true}, as this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
