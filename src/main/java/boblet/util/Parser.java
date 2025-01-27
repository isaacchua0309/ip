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

public class Parser {
    public static Command parse(String input) throws BobletException {
        // Step 1: Split the input
        String[] words = input.split(" ", 2);
        
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
                return new DoneCommand(words[1]);
            case "delete":
                return new DeleteCommand(words[1]);
            case "todo":
                return parseTodoCommand(words);
            case "deadline":
                return parseDeadlineCommand(words);
            case "event":
                return parseEventCommand(words);
            case "showdate":
                return new ShowDateCommand(words[1]);
            case "find":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new BobletException("The keyword for the find command cannot be empty.");
                }
                return new FindCommand(words[1].trim());

            default:
                // Step 2: Throw with the original command word, not lowercased
                throw new BobletException("Unknown command: " + rawAction);
        }
    }

    private static Command parseTodoCommand(String[] words) throws BobletException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new BobletException("The description of a todo cannot be empty.");
        }
        return new AddCommand(new Todo(words[1].trim()));
    }

    private static Command parseDeadlineCommand(String[] words) throws BobletException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new BobletException("The description or deadline of a deadline cannot be empty.");
        }
        String[] parts = words[1].split(" /by ", 2);
        if (parts.length < 2) {
            throw new BobletException("The deadline must specify a date/time using the '/by' keyword.");
        }
        return new AddCommand(new Deadline(parts[0].trim(), parts[1].trim()));
    }

    private static Command parseEventCommand(String[] words) throws BobletException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new BobletException("The description or time of an event cannot be empty.");
        }
        String[] parts = words[1].split(" /at ", 2);
        if (parts.length < 2) {
            throw new BobletException("The event must specify a date/time using the '/at' keyword.");
        }
        return new AddCommand(new Event(parts[0].trim(), parts[1].trim()));
    }
}
