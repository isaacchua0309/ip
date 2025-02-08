package boblet.task;

/**
 * Represents an abstract base class for tasks.
 * A task has a description, a completion status, and a specific type.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;
    private final TaskType type;

    /**
     * Constructs a Task with the specified description and type.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     * @param type        The type of the task.
     */
    public Task(String description, TaskType type) {
        this.description = validateDescription(description);
        this.type = validateType(type);
        this.isDone = false;
    }

    /**
     * Validates the task description.
     *
     * @param description The task description.
     * @return The validated description.
     * @throws IllegalArgumentException If the description is null or empty.
     */
    private static String validateDescription(String description) {
        assert description != null && !description.trim().isEmpty() : "Task description should not be empty";
        return description.trim();
    }

    /**
     * Validates the task type.
     *
     * @param type The task type.
     * @return The validated task type.
     * @throws IllegalArgumentException If the task type is null.
     */
    private static TaskType validateType(TaskType type) {
        assert type != null : "Task type should not be null";
        return type;
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
