package boblet.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import boblet.exception.BobletException;
import boblet.task.Deadline;
import boblet.task.Event;
import boblet.task.Task;
import boblet.util.Storage;
import boblet.util.TaskList;
import boblet.util.Ui;

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
     * Executes the show date command, displaying all tasks scheduled for the specified date.
     * If no tasks are found for the date, a message indicating this is displayed.
     *
     * @param tasks   The task list containing all tasks.
     * @param ui      The UI to display messages to the user.
     * @param storage The storage to persist changes (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Tasks scheduled for " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        boolean found = false;

        for (Task task : tasks.getAllTasks()) {
            if (task instanceof Deadline && ((Deadline) task).isOnDate(date)) {
                ui.showMessage(task.toString());
                found = true;
            } else if (task instanceof Event && ((Event) task).isOnDate(date)) {
                ui.showMessage(task.toString());
                found = true;
            }
        }

        if (!found) {
            ui.showMessage("No tasks found for this date.");
        }
    }

    /**
     * Returns the date associated with this command.
     *
     * @return The date as a LocalDate object.
     */
    public LocalDate getDate() {
        return this.date;
    }
}
