package boblet.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@code Event} class.
 */
class EventTest {

    /**
     * Tests if the constructor correctly initializes an event with a valid date.
     */
    @Test
    void testConstructorWithValidDate() {
        Event event = new Event("Team Meeting", "Feb 01 2025, 02:00 PM");
        assertEquals("Team Meeting", event.getDescription(),
                "Description should match.");
        assertEquals("Feb 01 2025, 02:00 PM", event.getAt(),
                "Date should be parsed correctly.");
    }

    /**
     * Tests if the constructor throws an exception for an invalid date.
     */
    @Test
    void testConstructorWithInvalidDate() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Event("Team Meeting", "Invalid Date");
        });
        assertEquals("Invalid date/time format: Invalid Date",
                exception.getMessage(), "Invalid date should throw an exception.");
    }

    /**
     * Tests if {@code getAt} returns the correct formatted date.
     */
    @Test
    void testGetAt() {
        Event event = new Event("Team Meeting", "Feb 01 2025, 02:00 PM");
        assertEquals("Feb 01 2025, 02:00 PM", event.getAt(),
                "getAt() should return the formatted date.");
    }

    /**
     * Tests if {@code toString} correctly formats the event description.
     */
    @Test
    void testToString() {
        Event event = new Event("Team Meeting", "Feb 01 2025, 02:00 PM");
        assertEquals("[EVENT][✗] Team Meeting (at: Feb 01 2025, 02:00 PM)",
                event.toString(), "String representation is incorrect.");

        event.markAsDone();
        assertEquals("[EVENT][✓] Team Meeting (at: Feb 01 2025, 02:00 PM)",
                event.toString(), "String representation after marking as done is incorrect.");
    }

    /**
     * Tests if {@code isOnDate} returns true when the event date matches the given date.
     */
    @Test
    void testIsOnDateTrue() {
        Event event = new Event("Team Meeting", "Feb 01 2025, 02:00 PM");
        assertTrue(event.isOnDate(LocalDate.of(2025, 2, 1)),
                "isOnDate() should return true for a matching date.");
    }

    /**
     * Tests if {@code isOnDate} returns false when the event date does not match the given date.
     */
    @Test
    void testIsOnDateFalse() {
        Event event = new Event("Team Meeting", "Feb 01 2025, 02:00 PM");
        assertFalse(event.isOnDate(LocalDate.of(2025, 2, 2)),
                "isOnDate() should return false for a non-matching date.");
    }

    /**
     * Tests if the constructor correctly parses multiple valid date formats.
     */
    @Test
    void testParseDateTimeWithMultipleFormats() {
        Event event1 = new Event("Event 1", "2025-02-01 14:00");
        assertEquals("Feb 01 2025, 02:00 PM", event1.getAt(),
                "Failed to parse 'yyyy-MM-dd HH:mm'.");

        Event event2 = new Event("Event 2", "2025-02-01");
        assertEquals("Feb 01 2025, 12:00 AM", event2.getAt(),
                "Failed to parse 'yyyy-MM-dd'.");

        Event event3 = new Event("Event 3", "01/02/2025 1400");
        assertEquals("Feb 01 2025, 02:00 PM", event3.getAt(),
                "Failed to parse 'd/M/yyyy HHmm'.");

        Event event4 = new Event("Event 4", "Feb 01 2025, 02:00 PM");
        assertEquals("Feb 01 2025, 02:00 PM", event4.getAt(),
                "Failed to parse 'MMM dd yyyy, hh:mm a'.");
    }
}
