package boblet.util;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
 * Unit tests for the {@code Parser} class.
 */
class ParserTest {

    /**
     * Tests if the "bye" command is parsed correctly.
     *
     * @throws BobletException If parsing fails.
     */
    @Test
    void testParseExitCommand() throws BobletException {
        Command command = Parser.parse("bye");
        Assertions.assertTrue(command instanceof ExitCommand,
                "Should return an ExitCommand.");
    }

    /**
     * Tests if the "list" command is parsed correctly.
     *
     * @throws BobletException If parsing fails.
     */
    @Test
    void testParseListCommand() throws BobletException {
        Command command = Parser.parse("list");
        Assertions.assertTrue(command instanceof ListCommand,
                "Should return a ListCommand.");
    }

    /**
     * Tests if the "done" command is parsed correctly.
     *
     * @throws BobletException If parsing fails.
     */
    @Test
    void testParseDoneCommand() throws BobletException {
        Command command = Parser.parse("done 1");
        Assertions.assertTrue(command instanceof DoneCommand,
                "Should return a DoneCommand.");
        Assertions.assertEquals(0, ((DoneCommand) command).getTaskIndex(),
                "Task index should match.");
    }

    /**
     * Tests if the "delete" command is parsed correctly.
     *
     * @throws BobletException If parsing fails.
     */
    @Test
    void testParseDeleteCommand() throws BobletException {
        Command command = Parser.parse("delete 2");
        Assertions.assertTrue(command instanceof DeleteCommand,
                "Should return a DeleteCommand.");
        Assertions.assertEquals(1, ((DeleteCommand) command).getTaskIndex(),
                "Task index should match.");
    }

    /**
     * Tests if the "todo" command is parsed correctly.
     *
     * @throws BobletException If parsing fails.
     */
    @Test
    void testParseTodoCommand() throws BobletException {
        Command command = Parser.parse("todo Buy groceries");
        Assertions.assertTrue(command instanceof AddCommand,
                "Should return an AddCommand.");
        Todo todo = (Todo) ((AddCommand) command).getTask();
        Assertions.assertEquals("Buy groceries", todo.getDescription(),
                "Todo description should match.");
    }

    /**
     * Tests if the "deadline" command is parsed correctly.
     *
     * @throws BobletException If parsing fails.
     */
    @Test
    void testParseDeadlineCommand() throws BobletException {
        Command command = Parser.parse("deadline Submit report /by Feb 01 2025, 06:00 PM");
        Assertions.assertTrue(command instanceof AddCommand,
                "Should return an AddCommand.");
        Deadline deadline = (Deadline) ((AddCommand) command).getTask();
        Assertions.assertEquals("Submit report", deadline.getDescription(),
                "Deadline description should match.");
        Assertions.assertEquals("Feb 01 2025, 06:00 PM", deadline.getBy(),
                "Deadline date/time should match.");
    }

    /**
     * Tests if the "event" command is parsed correctly.
     *
     * @throws BobletException If parsing fails.
     */
    @Test
    void testParseEventCommand() throws BobletException {
        Command command = Parser.parse("event Team meeting /at Feb 01 2025, 02:00 PM");
        Assertions.assertTrue(command instanceof AddCommand,
                "Should return an AddCommand.");
        Event event = (Event) ((AddCommand) command).getTask();
        Assertions.assertEquals("Team meeting", event.getDescription(),
                "Event description should match.");
        Assertions.assertEquals("Feb 01 2025, 02:00 PM", event.getAt(),
                "Event date/time should match.");
    }

    /**
     * Tests if the "showdate" command is parsed correctly.
     *
     * @throws BobletException If parsing fails.
     */
    @Test
    void testParseShowDateCommand() throws BobletException {
        Command command = Parser.parse("showdate 2025-02-01");
        Assertions.assertTrue(command instanceof ShowDateCommand,
                "Should return a ShowDateCommand.");
        Assertions.assertEquals(LocalDate.of(2025, 2, 1), ((ShowDateCommand) command).getDate(), "Date should match.");
    }

    /**
     * Tests if an invalid command throws an exception.
     */
    @Test
    void testParseInvalidCommand() {
        Exception exception = Assertions.assertThrows(BobletException.class, () -> {
            Parser.parse("unknownCommand");
        });
        Assertions.assertEquals("Unknown command: unknownCommand",
                exception.getMessage(), "Invalid command should throw exception with correct message.");
    }

    /**
     * Tests if an empty "todo" description throws an exception.
     */
    @Test
    void testParseTodoCommandEmptyDescription() {
        Exception exception = Assertions.assertThrows(BobletException.class, () -> {
            Parser.parse("todo ");
        });
        Assertions.assertEquals("The description of a todo cannot be empty.",
                exception.getMessage(), "Empty todo description should throw exception.");
    }

    /**
     * Tests if a missing "/by" in the deadline command throws an exception.
     */
    @Test
    void testParseDeadlineCommandMissingDate() {
        Exception exception = Assertions.assertThrows(BobletException.class, () -> {
            Parser.parse("deadline Submit report");
        });
        Assertions.assertEquals("The deadline must specify a date/time using the '/by' keyword.",
                exception.getMessage(), "Missing '/by' in deadline should throw exception.");
    }

    /**
     * Tests if a missing "/at" in the event command throws an exception.
     */
    @Test
    void testParseEventCommandMissingDate() {
        Exception exception = Assertions.assertThrows(BobletException.class, () -> {
            Parser.parse("event Team meeting");
        });
        Assertions.assertEquals("The event must specify a date/time using the '/at' keyword.",
                exception.getMessage(), "Missing '/at' in event should throw exception.");
    }

    /**
     * Tests if the "find" command is parsed correctly.
     *
     * @throws BobletException If parsing fails.
     */
    @Test
    void testParseFindCommand() throws BobletException {
        Command command = Parser.parse("find book");
        Assertions.assertTrue(command instanceof FindCommand,
                "Should return a FindCommand.");
    }
}
