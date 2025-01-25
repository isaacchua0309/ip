package boblet.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = parseDateTime(at);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        String normalizedDateTime = dateTime.trim(); // Trim any leading/trailing spaces

        List<DateTimeFormatter> formats = new ArrayList<>();
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM d yyyy h:mma", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a", Locale.ENGLISH)); // e.g., Feb 01 2025, 02:00 PM

        for (DateTimeFormatter formatter : formats) {
            try {
                return LocalDateTime.parse(normalizedDateTime, formatter);
            } catch (DateTimeParseException ignored) {
                // Try the next format
            }
        }

        throw new IllegalArgumentException("Invalid date/time format: " + dateTime);
    }

    public String getAt() {
        return at.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a").withLocale(Locale.ENGLISH));
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + getAt() + ")";
    }

    public boolean isOnDate(LocalDate date) {
        return this.at.toLocalDate().equals(date);
    }
}
