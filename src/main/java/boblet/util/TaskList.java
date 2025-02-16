package boblet.util;

import java.util.ArrayList;
import java.util.stream.Collectors;

import boblet.exception.BobletException;
import boblet.task.Task;

/**
 * Represents a list of tasks. Provides methods to manage tasks such as adding, retrieving,
 * deleting, and searching tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList initialized with a list of tasks.
     *
     * @param tasks The initial list of tasks.
     * @throws IllegalArgumentException If the provided task list is null.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Task list should not be null";
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the list, ensuring no duplicate tasks are added.
     *
     * @param task The task to add.
     * @throws BobletException If the provided task is null or already exists in the list.
     */
    public void addTask(Task task) throws BobletException {
        assert task != null : "Task to be added should not be null";

        // Check if the exact task already exists in the list
        if (tasks.contains(task)) {
            throw new BobletException("Duplicate task detected! Task already exists in your list.");
        }

        tasks.add(task);
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of valid range.
     */
    public Task getTask(int index) {
        assert isValidIndex(index) : "Task index out of bounds: " + index;
        return tasks.get(index);
    }

    /**
     * Deletes and returns the task at the specified index.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     * @throws IndexOutOfBoundsException If the index is out of valid range.
     */
    public Task deleteTask(int index) {
        assert isValidIndex(index) : "Task index out of bounds: " + index;
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The total number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a copy of all tasks in the list.
     *
     * @return A list of all tasks in the task list.
     */
    public ArrayList<Task> getAllTasks() {
        assert tasks != null : "Task list should not be null";
        return new ArrayList<>(tasks);
    }

    /**
     * Finds and returns a list of tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of matching tasks.
     * @throws IllegalArgumentException If the keyword is null or empty.
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Search keyword should not be null or empty";

        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Checks if the given index is within the valid range of the task list.
     *
     * @param index The index to check.
     * @return True if the index is within the valid range, false otherwise.
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
