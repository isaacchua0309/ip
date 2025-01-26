package boblet.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testConstructorWithValidDate() {
        // Test constructor with valid date
        Deadline deadline = new Deadline("Submit Assignment", "Feb 01 2025, 06:00 PM");
        assertEquals("Submit Assignment", deadline.getDescription(), "Description should match.");
        assertEquals("Feb 01 2025, 06:00 PM", deadline.getBy(), "Date should be parsed correctly.");
    }

    @Test
    void testConstructorWithInvalidDate() {
        // Test constructor with invalid date
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("Submit Assignment", "Invalid Date");
        });
        assertEquals("Invalid date/time format: Invalid Date", exception.getMessage(), "Invalid date should throw an exception.");
    }

    @Test
    void testGetBy() {
        // Test getBy for valid date format
        Deadline deadline = new Deadline("Submit Assignment", "Feb 01 2025, 06:00 PM");
        assertEquals("Feb 01 2025, 06:00 PM", deadline.getBy(), "getBy() should return the formatted date.");
    }

    @Test
    void testToString() {
        // Test toString method
        Deadline deadline = new Deadline("Submit Assignment", "Feb 01 2025, 06:00 PM");
        assertEquals("[DEADLINE][✗] Submit Assignment (by: Feb 01 2025, 06:00 PM)", deadline.toString(), "String representation is incorrect.");

        deadline.markAsDone();
        assertEquals("[DEADLINE][✓] Submit Assignment (by: Feb 01 2025, 06:00 PM)", deadline.toString(), "String representation after marking as done is incorrect.");
    }

    @Test
    void testIsOnDateTrue() {
        // Test isOnDate for a matching date
        Deadline deadline = new Deadline("Submit Assignment", "Feb 01 2025, 06:00 PM");
        assertTrue(deadline.isOnDate(LocalDate.of(2025, 2, 1)), "isOnDate() should return true for a matching date.");
    }

    @Test
    void testIsOnDateFalse() {
        // Test isOnDate for a non-matching date
        Deadline deadline = new Deadline("Submit Assignment", "Feb 01 2025, 06:00 PM");
        assertFalse(deadline.isOnDate(LocalDate.of(2025, 2, 2)), "isOnDate() should return false for a non-matching date.");
    }

    @Test
    void testParseDateTimeWithMultipleFormats() {
        // Test parsing with multiple valid date formats
        Deadline deadline1 = new Deadline("Task 1", "2025-02-01 18:00");
        assertEquals("Feb 01 2025, 06:00 PM", deadline1.getBy(), "Failed to parse 'yyyy-MM-dd HH:mm'.");

        Deadline deadline2 = new Deadline("Task 2", "2025-02-01");
        assertEquals("Feb 01 2025, 12:00 AM", deadline2.getBy(), "Failed to parse 'yyyy-MM-dd'.");

        Deadline deadline3 = new Deadline("Task 3", "01/02/2025 1800");
        assertEquals("Feb 01 2025, 06:00 PM", deadline3.getBy(), "Failed to parse 'd/M/yyyy HHmm'.");

        Deadline deadline4 = new Deadline("Task 4", "Feb 01 2025, 06:00 PM");
        assertEquals("Feb 01 2025, 06:00 PM", deadline4.getBy(), "Failed to parse 'MMM dd yyyy, hh:mm a'.");
    }
}
