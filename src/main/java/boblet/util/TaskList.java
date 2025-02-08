package boblet.util;

import java.util.ArrayList;

import boblet.task.Task;

/**
 * Represents a list of tasks. Provides methods to manage tasks such as adding, retrieving,
 * deleting, and getting the total number of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList initialized with a list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Task list should not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        assert task != null : "Task to be added should not be null";
        tasks.add(task);
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Task index out of bounds";
        return tasks.get(index);
    }

    /**
     * Deletes and returns the task at the specified index.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Task index out of bounds";
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks in the list as an ArrayList.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        assert tasks != null : "Task list should not be null";
        return tasks;
    }

    /**
     * Finds and returns a list of tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of matching tasks.
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Search keyword should not be null or empty";

        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            assert task != null : "Task in list should not be null";
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
