package boblet.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import boblet.exception.BobletException;
import boblet.task.Deadline;
import boblet.task.Event;
import boblet.task.Task;
import boblet.util.Storage;
import boblet.util.TaskList;

/**
 * Represents a command to display tasks scheduled for a specific date.
 */
public class ShowDateCommand extends Command {
    private final LocalDate date;

    /**
     * Constructs a ShowDateCommand with the specified date input.
     *
     * @param dateInput The date input in the format "yyyy-MM-dd".
     * @throws BobletException If the date input is in an invalid format.
     */
    public ShowDateCommand(String dateInput) throws BobletException {
        assert dateInput != null && !dateInput.trim().isEmpty() : "Date input should not be null or empty";

        try {
            this.date = LocalDate.parse(dateInput.trim());
        } catch (DateTimeParseException e) {
            throw new BobletException("Invalid date format. Please use yyyy-MM-dd.");
        }

        assert this.date != null : "Parsed date should not be null";
    }

    /**
     * Executes the show date command, returning all tasks scheduled for the specified date.
     * If no tasks are found for the date, a message indicating this is returned.
     *
     * @param tasks   The task list containing all tasks.
     * @param storage The storage to persist changes (not used in this command).
     * @return A response listing tasks for the specified date or indicating none were found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";

        List<Task> matchingTasks = tasks.getAllTasks().stream()
                .peek(task -> {
                    assert task != null : "Task in TaskList should not be null";
                })
                .filter(task -> (task instanceof Deadline && ((Deadline) task).isOnDate(date))
                        || (task instanceof Event && ((Event) task).isOnDate(date)))
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            return "No tasks found for this date.";
        }

        String taskList = matchingTasks.stream()
                .map(task -> (matchingTasks.indexOf(task) + 1) + ". " + task)
                .collect(Collectors.joining("\n"));

        return String.format("Tasks scheduled for %s:\n%s", 
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), taskList);
    }

    /**
     * Returns the date associated with this command.
     *
     * @return The date as a LocalDate object.
     */
    public LocalDate getDate() {
        assert this.date != null : "Date should not be null";
        return this.date;
    }

    /**
     * Returns false since displaying tasks for a date does not exit the application.
     *
     * @return False, since the command does not terminate the program.
     */
    @Override
    public boolean isExit() {
        assert true : "ShowDateCommand should always return false for isExit()";
        return false;
    }
}
