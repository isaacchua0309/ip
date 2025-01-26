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

public class ShowDateCommand extends Command {
    private final LocalDate date;

    public ShowDateCommand(String dateInput) throws BobletException {
        try {
            this.date = LocalDate.parse(dateInput.trim());
        } catch (DateTimeParseException e) {
            throw new BobletException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

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

    public LocalDate getDate(){
        return this.date;
    }
}
