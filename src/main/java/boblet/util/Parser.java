package boblet.util;

import boblet.command.AddCommand;
import boblet.command.Command;
import boblet.command.DeleteCommand;
import boblet.command.DoneCommand;
import boblet.command.ExitCommand;
import boblet.command.FindCommand;
import boblet.command.ListCommand;
import boblet.command.ShowDateCommand;
import boblet.exception.BobletException;
import boblet.task.Deadline;
import boblet.task.Event;
import boblet.task.Todo;

/**
 * The Parser class is responsible for interpreting user input and converting
 * it into executable commands for the Boblet application.
 */
public class Parser {

    /**
     * Parses a user input string into a Command object.
     *
     * @param input The raw input string from the user.
     * @return The corresponding Command object.
     * @throws BobletException If the input command is unknown or invalid.
     */
    public static Command parse(String input) throws BobletException {
        assert input != null : "User input should not be null";

        // Step 1: Split the input
        String[] words = input.split(" ", 2);
        assert words.length > 0 : "Input split should contain at least one element";

        // Keep the original for error messages
        String rawAction = words[0];
        // Lowercase for matching known commands
        String action = rawAction.toLowerCase();

        switch (action) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            assert words.length > 1 : "Done command must have a task index";
            return new DoneCommand(words[1]);
        case "delete":
            assert words.length > 1 : "Delete command must have a task index";
            return new DeleteCommand(words[1]);
        case "todo":
            return parseTodoCommand(words);
        case "deadline":
            return parseDeadlineCommand(words);
        case "event":
            return parseEventCommand(words);
        case "showdate":
            assert words.length > 1 : "ShowDate command must have a date argument";
            return new ShowDateCommand(words[1]);
        case "find":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new BobletException("The keyword for the find command cannot be empty.");
            }
            return new FindCommand(words[1].trim());
        default:
            throw new BobletException("Unknown command: " + rawAction);
        }
    }

    /**
     * Parses a "todo" command.
     *
     * @param words The split input string for the command.
     * @return The corresponding AddCommand for the Todo task.
     * @throws BobletException If the description is missing or empty.
     */
    private static Command parseTodoCommand(String[] words) throws BobletException {
        assert words != null : "words array should not be null";
        assert words.length > 0 : "words array should have at least one element";

        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new BobletException("The description of a todo cannot be empty.");
        }
        return new AddCommand(new Todo(words[1].trim()));
    }

    /**
     * Parses a "deadline" command.
     *
     * @param words The split input string for the command.
     * @return The corresponding AddCommand for the Deadline task.
     * @throws BobletException If the description or date/time is missing or invalid.
     */
    private static Command parseDeadlineCommand(String[] words) throws BobletException {
        assert words != null : "words array should not be null";
        assert words.length > 0 : "words array should have at least one element";

        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new BobletException("The description or deadline of a deadline cannot be empty.");
        }
        String[] parts = words[1].split(" /by ", 2);
        assert parts.length > 0 : "Deadline command should have at least one part";

        if (parts.length < 2) {
            throw new BobletException("The deadline must specify a date/time using the '/by' keyword.");
        }
        return new AddCommand(new Deadline(parts[0].trim(), parts[1].trim()));
    }

    /**
     * Parses an "event" command.
     *
     * @param words The split input string for the command.
     * @return The corresponding AddCommand for the Event task.
     * @throws BobletException If the description or date/time is missing or invalid.
     */
    private static Command parseEventCommand(String[] words) throws BobletException {
        assert words != null : "words array should not be null";
        assert words.length > 0 : "words array should have at least one element";

        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new BobletException("The description or time of an event cannot be empty.");
        }
        String[] parts = words[1].split(" /at ", 2);
        assert parts.length > 0 : "Event command should have at least one part";

        if (parts.length < 2) {
            throw new BobletException("The event must specify a date/time using the '/at' keyword.");
        }
        return new AddCommand(new Event(parts[0].trim(), parts[1].trim()));
    }
}
