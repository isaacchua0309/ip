package boblet.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Represents an event task with a specific date and time.
 * Extends the Task class and adds an event date/time field.
 */
public class Event extends Task {
    private LocalDateTime at;

    /**
     * Constructs an Event task with the given description and event date/time.
     *
     * @param description Description of the event task.
     * @param at          The event date/time in a supported format.
     * @throws IllegalArgumentException If the provided date/time format is invalid.
     */
    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = parseDateTime(at);
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
                // Try parsing as LocalDateTime
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
     * Retrieves the event date/time in a formatted string.
     * The format is "MMM dd yyyy, hh:mm a" (e.g., "Feb 01 2025, 06:00 PM").
     *
     * @return A formatted string representation of the event date/time.
     */
    public String getAt() {
        return at.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a", Locale.ENGLISH));
    }

    /**
     * Converts the Event to a string representation, including the event date/time.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + getAt() + ")";
    }

    /**
     * Checks if the event occurs on a specific date.
     *
     * @param date The date to check against.
     * @return True if the event occurs on the specified date, false otherwise.
     */
    public boolean isOnDate(LocalDate date) {
        return this.at.toLocalDate().equals(date);
    }
}
