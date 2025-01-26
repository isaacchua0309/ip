package boblet.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testConstructorWithValidDate() {
        // Test constructor with valid date
        Event event = new Event("Team Meeting", "Feb 01 2025, 02:00 PM");
        assertEquals("Team Meeting", event.getDescription(), "Description should match.");
        assertEquals("Feb 01 2025, 02:00 PM", event.getAt(), "Date should be parsed correctly.");
    }

    @Test
    void testConstructorWithInvalidDate() {
        // Test constructor with invalid date
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Event("Team Meeting", "Invalid Date");
        });
        assertEquals("Invalid date/time format: Invalid Date", exception.getMessage(), "Invalid date should throw an exception.");
    }

    @Test
    void testGetAt() {
        // Test getAt for valid date format
        Event event = new Event("Team Meeting", "Feb 01 2025, 02:00 PM");
        assertEquals("Feb 01 2025, 02:00 PM", event.getAt(), "getAt() should return the formatted date.");
    }

    @Test
    void testToString() {
        // Test toString method
        Event event = new Event("Team Meeting", "Feb 01 2025, 02:00 PM");
        assertEquals("[EVENT][✗] Team Meeting (at: Feb 01 2025, 02:00 PM)", event.toString(), "String representation is incorrect.");

        event.markAsDone();
        assertEquals("[EVENT][✓] Team Meeting (at: Feb 01 2025, 02:00 PM)", event.toString(), "String representation after marking as done is incorrect.");
    }

    @Test
    void testIsOnDateTrue() {
        // Test isOnDate for a matching date
        Event event = new Event("Team Meeting", "Feb 01 2025, 02:00 PM");
        assertTrue(event.isOnDate(LocalDate.of(2025, 2, 1)), "isOnDate() should return true for a matching date.");
    }

    @Test
    void testIsOnDateFalse() {
        // Test isOnDate for a non-matching date
        Event event = new Event("Team Meeting", "Feb 01 2025, 02:00 PM");
        assertFalse(event.isOnDate(LocalDate.of(2025, 2, 2)), "isOnDate() should return false for a non-matching date.");
    }

    @Test
    void testParseDateTimeWithMultipleFormats() {
        // Test parsing with multiple valid date formats
        Event event1 = new Event("Event 1", "2025-02-01 14:00");
        assertEquals("Feb 01 2025, 02:00 PM", event1.getAt(), "Failed to parse 'yyyy-MM-dd HH:mm'.");

        Event event2 = new Event("Event 2", "2025-02-01");
        assertEquals("Feb 01 2025, 12:00 AM", event2.getAt(), "Failed to parse 'yyyy-MM-dd'.");

        Event event3 = new Event("Event 3", "01/02/2025 1400");
        assertEquals("Feb 01 2025, 02:00 PM", event3.getAt(), "Failed to parse 'd/M/yyyy HHmm'.");

        Event event4 = new Event("Event 4", "Feb 01 2025, 02:00 PM");
        assertEquals("Feb 01 2025, 02:00 PM", event4.getAt(), "Failed to parse 'MMM dd yyyy, hh:mm a'.");
    }
}
