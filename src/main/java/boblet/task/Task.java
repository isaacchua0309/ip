package boblet.task;

/**
 * Represents an abstract base class for tasks.
 * A task has a description, a completion status, and a specific type.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a Task with the specified description and type.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     * @param type        The type of the task.
     */
    public Task(String description, TaskType type) {
        assert description != null && !description.trim().isEmpty() : "Task description should not be empty";
        assert type != null : "Task type should not be null";

        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        assert description != null : "Task description should never be null";
        return description;
    }

    /**
     * Checks whether the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Retrieves the type of the task.
     *
     * @return The task type.
     */
    public TaskType getType() {
        assert type != null : "Task type should never be null";
        return type;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        assert !isDone : "Task should not already be marked as done";
        this.isDone = true;
    }

    /**
     * Converts the task to a string representation.
     * The format is: [TYPE][✓/✗] description
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        assert description != null : "Task description should never be null in toString";
        assert type != null : "Task type should never be null in toString";
        return "[" + type + "][" + (isDone ? "✓" : "✗") + "] " + description;
    }
}
