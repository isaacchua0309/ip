package boblet.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Represents a task with a deadline. Extends the Task class and includes a specific deadline date and time.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a Deadline task with the given description and deadline date/time.
     *
     * @param description Description of the task.
     * @param by          The deadline for the task in a supported date/time format.
     * @throws IllegalArgumentException If the date/time format is invalid.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = parseDateTime(by);
    }

    /**
     * Parses a string representation of a date/time into a LocalDateTime object.
     * Supports multiple formats and defaults to midnight for dates without time.
     *
     * @param dateTime The string representation of the date/time.
     * @return A LocalDateTime object parsed from the input.
     * @throws IllegalArgumentException If the input does not match any supported format.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        String normalizedDateTime = dateTime.trim();

        // List of supported date/time formats
        List<DateTimeFormatter> formats = new ArrayList<>();
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM d yyyy h:mma", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH));

        // Attempt to parse with each formatter
        for (DateTimeFormatter formatter : formats) {
            try {
                // Try parsing as a LocalDateTime
                return LocalDateTime.parse(normalizedDateTime, formatter);
            } catch (DateTimeParseException e1) {
                // If LocalDateTime fails, try LocalDate
                try {
                    LocalDate date = LocalDate.parse(normalizedDateTime, formatter);
                    return date.atStartOfDay(); // Defaults to 00:00 for LocalDate
                } catch (DateTimeParseException e2) {
                    // Continue trying the next format
                }
            }
        }

        // If no format matches, throw an exception
        throw new IllegalArgumentException("Invalid date/time format: " + dateTime);
    }

    /**
     * Retrieves the deadline date/time in a formatted string.
     * The format is "MMM dd yyyy, hh:mm a" (e.g., "Feb 01 2025, 06:00 PM").
     *
     * @return A formatted string representation of the deadline.
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a", Locale.ENGLISH));
    }

    /**
     * Converts the Deadline to a string representation, including the deadline date/time.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + getBy() + ")";
    }

    /**
     * Checks if the deadline is on a specific date.
     *
     * @param date The date to check against.
     * @return True if the deadline falls on the specified date, false otherwise.
     */
    public boolean isOnDate(LocalDate date) {
        return this.by.toLocalDate().equals(date);
    }
}
