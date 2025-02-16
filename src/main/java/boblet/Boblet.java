package boblet;

import java.io.IOException;

import boblet.command.Command;
import boblet.exception.BobletException;
import boblet.util.Parser;
import boblet.util.Storage;
import boblet.util.TaskList;

/**
 * The main class of the Boblet application.
 * Handles the initialization of components and the main program flow.
 */
public class Boblet {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new Boblet instance and initializes its components.
     *
     * @param filePath The file path to store and load tasks.
     */
    public Boblet(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path should not be null or empty";

        this.storage = new Storage(filePath);
        assert this.storage != null : "Storage should never be null";

        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (IOException | BobletException e) {
            this.tasks = new TaskList();
        }

        assert this.tasks != null : "TaskList should never be null";
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The user's input command.
     * @return The response generated by executing the command.
     */
    public String getResponse(String input) {
        assert input != null && !input.trim().isEmpty() : "User input should not be null or empty";

        try {
            Command command = Parser.parse(input);
            assert command != null : "Parsed command should never be null";
            return command.execute(tasks, storage);
        } catch (BobletException e) {
            return e.getMessage();
        }
    }
}
