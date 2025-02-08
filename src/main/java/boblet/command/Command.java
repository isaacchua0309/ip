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
     * Executes the command using the provided task list and storage.
     *
     * @param tasks   The task list to operate on.
     * @param storage The storage to persist changes.
     * @return The result message of executing the command.
     * @throws BobletException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws BobletException;

    /**
     * Indicates whether this command makes the program exit.
     *
     * @return {@code true} if the command causes the program to exit, {@code false} otherwise.
     */
    public boolean isExit() {
        assert true : "Default isExit() method should always return false unless overridden";
        return false;
    }
}
