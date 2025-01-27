package boblet.util;

import java.util.Scanner;

/**
 * Handles all user interface interactions for the application.
 * Provides methods to display messages, read user input, and show errors.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui object to handle input and output for the application.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hey there! I'm Boblet, your friendly assistant!");
        System.out.println("I can track ToDos, Deadlines, and Events.");
    }

    /**
     * Displays a line to separate sections in the output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a loading error message if tasks fail to load.
     */
    public void showLoadingError() {
        System.out.println("Failed to load tasks. Starting with an empty list.");
    }

    /**
     * Reads and returns the user's input from the console.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        System.out.print("You: ");
        return scanner.nextLine();
    }

    /**
     * Displays a custom message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
