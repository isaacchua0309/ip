package boblet.task;

import java.util.Objects;

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
        return "[" + type + "][" + (isDone ? "✓" : "✗") + "] " + description;
    }

    /**
     * Defines equality for tasks: Two tasks are considered equal if they have the same type and description.
     * This ensures that a deleted task does not interfere with the duplicate detection logic.
     *
     * @param obj The object to compare against.
     * @return True if the tasks are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Same memory reference
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return Objects.equals(description, task.description) && type == task.type;
    }

    /**
     * Ensures the hashcode is consistent with equals() to avoid issues in collections.
     *
     * @return Hashcode representation of the task.
     */
    @Override
    public int hashCode() {
        return Objects.hash(description, type);
    }
}
