package boblet.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
        try {
            this.date = LocalDate.parse(dateInput.trim());
        } catch (DateTimeParseException e) {
            throw new BobletException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    /**
     * Executes the show date command, returning all tasks scheduled for the specified date.
     * If no tasks are found for the date, a message indicating this is returned.
     *
     * @param tasks   The task list containing all tasks.
     * @param ui      The UI to display messages.
     * @param storage The storage to persist changes (not used in this command).
     * @return A response listing tasks for the specified date or indicating none were found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder response = new StringBuilder("Tasks scheduled for ")
                .append(date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))).append(":\n");

        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks.getAllTasks()) {
            if ((task instanceof Deadline && ((Deadline) task).isOnDate(date)) ||
                (task instanceof Event && ((Event) task).isOnDate(date))) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "No tasks found for this date.";
        }

        for (int i = 0; i < matchingTasks.size(); i++) {
            response.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
        }

        return response.toString().trim();
    }

    /**
     * Returns the date associated with this command.
     *
     * @return The date as a LocalDate object.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns false since displaying tasks for a date does not exit the application.
     *
     * @return False, since the command does not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
