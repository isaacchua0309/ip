package boblet.task;

/**
 * Represents the different types of tasks that can be managed in the Boblet task manager.
 * The task types include:
 * <ul>
 *     <li>{@code TODO} - A task without a specific date or time.</li>
 *     <li>{@code DEADLINE} - A task with a due date and time.</li>
 *     <li>{@code EVENT} - A task that occurs at a specific date and time.</li>
 * </ul>
 */
public enum TaskType {
    /** A task without a specific date or time. */
    TODO,

    /** A task with a deadline, requiring a specific date and time. */
    DEADLINE,

    /** A task that takes place at a specific date and time. */
    EVENT
}
