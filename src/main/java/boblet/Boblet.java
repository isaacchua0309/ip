package boblet;

import java.io.IOException;

import boblet.command.Command;
import boblet.exception.BobletException;
import boblet.util.Parser;
import boblet.util.Storage;
import boblet.util.TaskList;
import boblet.util.Ui;

/**
 * The main class of the Boblet application.
 * Handles the initialization of components and the main program flow.
 */
public class Boblet {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Boblet instance and initializes its components.
     *
     * @param filePath The file path to store and load tasks.
     */
    public Boblet(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException | BobletException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program loop.
     * Handles user commands until the program is terminated.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(); // Reads the user's input
                Command command = Parser.parse(fullCommand); // Parses the command
                command.execute(tasks, ui, storage); // Executes the parsed command
                isExit = command.isExit(); // Checks if the command signals program termination
            } catch (BobletException e) {
                ui.showError(e.getMessage()); // Displays an error message for invalid commands
            } finally {
                ui.showLine(); // Displays a separator line for clarity
            }
        }
    }

    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Boblet("data/tasks.txt").run(); // Creates and runs a new instance of Boblet
    }
}
