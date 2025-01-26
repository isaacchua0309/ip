package boblet.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        String normalizedDateTime = dateTime.trim();

        List<DateTimeFormatter> formats = new ArrayList<>();
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM d yyyy h:mma", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH));

        for (DateTimeFormatter formatter : formats) {
            try {
                // Try parsing as a LocalDateTime
                return LocalDateTime.parse(normalizedDateTime, formatter);
            } catch (DateTimeParseException e1) {
                // If that fails, try parsing as LocalDate -> atStartOfDay()
                try {
                    LocalDate date = LocalDate.parse(normalizedDateTime, formatter);
                    return date.atStartOfDay(); // e.g. 2025-02-01 -> 2025-02-01T00:00
                } catch (DateTimeParseException e2) {
                    // Continue trying the next format
                }
            }
        }

        // If no format matched, throw exception
        throw new IllegalArgumentException("Invalid date/time format: " + dateTime);
    }

    /**
     * Formats the LocalDateTime for display.
     * E.g., "Feb 01 2025, 06:00 PM"
     */
    public String getBy() {
        // "MMM dd yyyy, hh:mm a" -> e.g., "Feb 01 2025, 12:00 AM"
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a", Locale.ENGLISH));
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getBy() + ")";
    }

    /**
     * Checks if this Deadline is on the specified date.
     */
    public boolean isOnDate(LocalDate date) {
        return this.by.toLocalDate().equals(date);
    }
}
