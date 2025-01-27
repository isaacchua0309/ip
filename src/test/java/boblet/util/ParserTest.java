package boblet.util;

import boblet.command.*;
import boblet.exception.BobletException;
import boblet.task.Deadline;
import boblet.task.Event;
import boblet.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class ParserTest {

    @Test
    void testParseExitCommand() throws BobletException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand, "Should return an ExitCommand.");
    }

    @Test
    void testParseListCommand() throws BobletException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand, "Should return a ListCommand.");
    }

    @Test
    void testParseDoneCommand() throws BobletException {
        Command command = Parser.parse("done 1");
        assertTrue(command instanceof DoneCommand, "Should return a DoneCommand.");
        assertEquals(0, ((DoneCommand) command).getTaskIndex(), "Task index should match.");
    }

    @Test
    void testParseDeleteCommand() throws BobletException {
        Command command = Parser.parse("delete 2");
        assertTrue(command instanceof DeleteCommand, "Should return a DeleteCommand.");
        assertEquals(1, ((DeleteCommand) command).getTaskIndex(), "Task index should match.");
    }

    @Test
    void testParseTodoCommand() throws BobletException {
        Command command = Parser.parse("todo Buy groceries");
        assertTrue(command instanceof AddCommand, "Should return an AddCommand.");
        Todo todo = (Todo) ((AddCommand) command).getTask();
        assertEquals("Buy groceries", todo.getDescription(), "Todo description should match.");
    }

    @Test
    void testParseDeadlineCommand() throws BobletException {
        Command command = Parser.parse("deadline Submit report /by Feb 01 2025, 06:00 PM");
        assertTrue(command instanceof AddCommand, "Should return an AddCommand.");
        Deadline deadline = (Deadline) ((AddCommand) command).getTask();
        assertEquals("Submit report", deadline.getDescription(), "Deadline description should match.");
        assertEquals("Feb 01 2025, 06:00 PM", deadline.getBy(), "Deadline date/time should match.");
    }

    @Test
    void testParseEventCommand() throws BobletException {
        Command command = Parser.parse("event Team meeting /at Feb 01 2025, 02:00 PM");
        assertTrue(command instanceof AddCommand, "Should return an AddCommand.");
        Event event = (Event) ((AddCommand) command).getTask();
        assertEquals("Team meeting", event.getDescription(), "Event description should match.");
        assertEquals("Feb 01 2025, 02:00 PM", event.getAt(), "Event date/time should match.");
    }

    @Test
    void testParseShowDateCommand() throws BobletException {
        Command command = Parser.parse("showdate 2025-02-01");
        assertTrue(command instanceof ShowDateCommand, "Should return a ShowDateCommand.");
        assertEquals(LocalDate.of(2025, 2, 1), ((ShowDateCommand) command).getDate(), "Date should match.");
    }

    @Test
    void testParseInvalidCommand() {
        Exception exception = assertThrows(BobletException.class, () -> {
            Parser.parse("unknownCommand");
        });
        assertEquals("Unknown command: unknownCommand", exception.getMessage(), "Invalid command should throw exception with correct message.");
    }

    @Test
    void testParseTodoCommandEmptyDescription() {
        Exception exception = assertThrows(BobletException.class, () -> {
            Parser.parse("todo ");
        });
        assertEquals("The description of a todo cannot be empty.", exception.getMessage(), "Empty todo description should throw exception.");
    }

    @Test
    void testParseDeadlineCommandMissingDate() {
        Exception exception = assertThrows(BobletException.class, () -> {
            Parser.parse("deadline Submit report");
        });
        assertEquals("The deadline must specify a date/time using the '/by' keyword.", exception.getMessage(), "Missing '/by' in deadline should throw exception.");
    }

    @Test
    void testParseEventCommandMissingDate() {
        Exception exception = assertThrows(BobletException.class, () -> {
            Parser.parse("event Team meeting");
        });
        assertEquals("The event must specify a date/time using the '/at' keyword.", exception.getMessage(), "Missing '/at' in event should throw exception.");
    }

    @Test
    void testParseFindCommand() throws BobletException {
        Command command = Parser.parse("find book");
        assertTrue(command instanceof FindCommand, "Should return a FindCommand.");
}

}
