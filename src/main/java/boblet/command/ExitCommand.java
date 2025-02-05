package boblet.command;

import boblet.util.Storage;
import boblet.util.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, returning a farewell message to the user.
     *
     * @param tasks   The task list (not used in this command).
     * @param storage The storage to persist changes (not used in this command).
     * @return A farewell message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye! See you soon!";
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
