package boblet.command;

import boblet.exception.BobletException;
import boblet.util.Storage;
import boblet.util.TaskList;

/**
 * Represents an abstract command to be executed in the application.
 * Each specific command (e.g., AddCommand, DeleteCommand) must extend this class
 * and implement the `execute` method.
 */
public abstract class Command {

    /**
     * Executes the command using the provided task list, user interface, and storage.
     *
     * @param tasks   The task list to operate on.
     * @param storage The storage to persist changes.
     * @throws BobletException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws BobletException;

    /**
     * Indicates whether this command causes the program to exit.
     *
     * @return {@code true} if the command causes the program to exit, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
