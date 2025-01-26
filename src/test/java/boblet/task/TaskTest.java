package boblet.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testConstructor() {
        // Test constructor initialization
        Task task = new Todo("Sample Task");
        assertEquals("Sample Task", task.getDescription(), "Description should match.");
        assertFalse(task.isDone(), "Task should not be marked as done initially.");
        assertEquals(TaskType.TODO, task.getType(), "Task type should match.");
    }

    @Test
    void testMarkAsDone() {
        // Test marking a task as done
        Task task = new Todo("Sample Task");
        assertFalse(task.isDone(), "Task should not be marked as done initially.");
        
        task.markAsDone();
        assertTrue(task.isDone(), "Task should be marked as done after calling markAsDone.");
    }

    @Test
    void testToString() {
        // Test the string representation of a task
        Task task = new Todo("Sample Task");
        assertEquals("[TODO][✗] Sample Task", task.toString(), "String format for incomplete task is incorrect.");
        
        task.markAsDone();
        assertEquals("[TODO][✓] Sample Task", task.toString(), "String format for completed task is incorrect.");
    }
}
