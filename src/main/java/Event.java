import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = parseDateTime(at);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        List<DateTimeFormatter> formats = new ArrayList<>();
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")); // e.g., 2023-11-05 18:00
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // e.g., 2023-11-05
        formats.add(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")); // e.g., Nov 5 2023 6:00PM
        formats.add(DateTimeFormatter.ofPattern("MMM d yyyy")); // e.g., Nov 5 2023
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")); // e.g., 5/11/2023 1800
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy")); // e.g., 5/11/2023

        for (DateTimeFormatter formatter : formats) {
            try {
                return LocalDateTime.parse(dateTime, formatter);
            } catch (DateTimeParseException ignored) {
                // Try the next format
            }
        }

        throw new IllegalArgumentException("Invalid date/time format: " + dateTime);
    }

    public String getAt() {
        return at.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a"));
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + getAt() + ")";
    }

    public boolean isOnDate(LocalDate date) {
    return this.at.toLocalDate().equals(date);
}

}
