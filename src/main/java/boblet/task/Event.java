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
    private final LocalDateTime at;

    /**
     * Constructs an Event task with the given description and event date/time.
     *
     * @param description Description of the event task.
     * @param at          The event date/time in a supported format.
     * @throws IllegalArgumentException If the provided date/time format is invalid.
     */
    public Event(String description, String at) {
        super(validateDescription(description), TaskType.EVENT);
        this.at = parseDateTime(validateDateTime(at));
        assert this.at != null : "Parsed event date/time should not be null";
    }

    /**
     * Validates the description input.
     *
     * @param description The task description.
     * @return The validated description.
     * @throws IllegalArgumentException If the description is null or empty.
     */
    private static String validateDescription(String description) {
        assert description != null && !description.trim().isEmpty() : "Task description should not be null or empty";
        return description.trim();
    }

    /**
     * Validates the date/time input.
     *
     * @param dateTime The event date/time.
     * @return The validated date/time string.
     * @throws IllegalArgumentException If the date/time input is null or empty.
     */
    private static String validateDateTime(String dateTime) {
        assert dateTime != null && !dateTime.trim().isEmpty() : "Event date/time should not be null or empty";
        return dateTime.trim();
    }

    /**
     * Parses a string representation of a date/time into a LocalDateTime object.
     * Supports multiple formats and defaults to midnight for dates without time.
     *
     * @param dateTime The string representation of the date/time.
     * @return A LocalDateTime object parsed from the input.
     * @throws IllegalArgumentException If the input does not match any supported format.
     */
    private static LocalDateTime parseDateTime(String dateTime) {
        assert dateTime != null && !dateTime.isEmpty() : "Input date/time string should not be null or empty";

        List<DateTimeFormatter> formats = getSupportedFormats();

        for (DateTimeFormatter formatter : formats) {
            try {
                return LocalDateTime.parse(dateTime, formatter);
            } catch (DateTimeParseException e1) {
                try {
                    LocalDate date = LocalDate.parse(dateTime, formatter);
                    return date.atStartOfDay();
                } catch (DateTimeParseException e2) {
                    // Continue trying the next format
                }
            }
        }

        throw new IllegalArgumentException("Invalid date/time format: " + dateTime);
    }

    /**
     * Retrieves the list of supported date/time formats.
     *
     * @return A list of DateTimeFormatter objects.
     */
    private static List<DateTimeFormatter> getSupportedFormats() {
        List<DateTimeFormatter> formats = new ArrayList<>();
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM d yyyy h:mma", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH));
        return formats;
    }

    /**
     * Retrieves the event date/time in a formatted string.
     * The format is "MMM dd yyyy, hh:mm a" (e.g., "Feb 01 2025, 06:00 PM").
     *
     * @return A formatted string representation of the event date/time.
     */
    public String getAt() {
        assert this.at != null : "Event date/time should not be null before formatting";
        return at.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a", Locale.ENGLISH));
    }

    /**
     * Converts the Event to a string representation, including the event date/time.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        assert this.at != null : "Event date/time should not be null before converting to string";
        return super.toString() + " (at: " + getAt() + ")";
    }

    /**
     * Checks if the event occurs on a specific date.
     *
     * @param date The date to check against.
     * @return True if the event occurs on the specified date, false otherwise.
     */
    public boolean isOnDate(LocalDate date) {
        assert date != null : "Input date should not be null";
        assert this.at != null : "Event date/time should not be null when checking the date";
        return this.at.toLocalDate().equals(date);
    }
}
