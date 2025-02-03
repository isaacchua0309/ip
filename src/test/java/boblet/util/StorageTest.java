package boblet.util;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import boblet.task.Deadline;
import boblet.task.Task;

/**
 * Tests the functionality of the {@code Storage} class, ensuring proper loading and saving of tasks.
 */
class StorageTest {

    /**
     * Tests if tasks are correctly loaded from a file after being saved.
     *
     * @throws Exception If file operations fail.
     */
    @Test
    void testLoadTasks() throws Exception {
        // Create a temporary file for testing
        File tempFile = File.createTempFile("tasks", ".txt");
        tempFile.deleteOnExit();

        // Initialize storage with the temp file
        Storage storage = new Storage(tempFile.getAbsolutePath());
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Deadline("Submit Assignment", "Feb 01 2025, 06:00 PM"));

        // Save tasks to the file
        storage.saveTasks(tasks);

        // Load tasks back from the file
        ArrayList<Task> loadedTasks = storage.loadTasks();

        // Assertions
        Assertions.assertEquals(1, loadedTasks.size(),
                "Loaded task count is incorrect.");
        Assertions.assertEquals(
                "[DEADLINE][✗] Submit Assignment (by: Feb 01 2025, 06:00 PM)",
                loadedTasks.get(0).toString(),
                "Loaded task string is incorrect."
        );
    }
}
