package boblet.util;

import boblet.task.Deadline;
import boblet.task.Task;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void testLoadTasks() throws Exception {
        // Test loading tasks from a file
        File tempFile = File.createTempFile("tasks", ".txt");
        tempFile.deleteOnExit();

        Storage storage = new Storage(tempFile.getAbsolutePath());
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Deadline("Submit Assignment", "Feb 01 2025, 06:00 PM"));

        storage.saveTasks(tasks);

        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertEquals(1, loadedTasks.size(), "Loaded task count is incorrect.");
        assertEquals("[DEADLINE][✗] Submit Assignment (by: Feb 01 2025, 06:00 PM)", loadedTasks.get(0).toString(), "Loaded task string is incorrect.");
    }
}
