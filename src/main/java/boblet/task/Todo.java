package boblet.task;

/**
 * Represents a ToDo task in the Boblet task manager.
 * A ToDo task only contains a description and does not have a specific date or time.
 */
public class Todo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }
}
