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
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM d yyyy h:mma", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH));
        formats.add(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a", Locale.ENGLISH));
    
        for (DateTimeFormatter formatter : formats) {
            try {
                return LocalDateTime.parse(normalizedDateTime, formatter);
            } catch (DateTimeParseException ignored) {
                // Try the next format
            }
        }
    
        throw new IllegalArgumentException("Invalid date/time format: " + dateTime);
    }
    
    

    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a").withLocale(java.util.Locale.ENGLISH));
    }    

    @Override
    public String toString() {
        return super.toString() + " (by: " + getBy() + ")";
    }

    public boolean isOnDate(LocalDate date) {
    return this.by.toLocalDate().equals(date);
}

}
