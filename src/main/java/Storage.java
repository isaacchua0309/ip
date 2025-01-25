import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file. Creates a new file if it doesn't exist.
     *
     * @return List of tasks loaded from the file.
     * @throws IOException      If there are issues with file I/O.
     * @throws BobletException  If the file content is corrupted or invalid.
     */
    public ArrayList<Task> loadTasks() throws IOException, BobletException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            createFile(file);
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    tasks.add(parseTask(line));
                }
            } catch (IOException | BobletException e) {
                throw new BobletException("Error loading tasks from file. File may be corrupted.");
            }
        }
        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks List of tasks to save.
     * @throws IOException If there are issues writing to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(serializeTask(task));
                writer.newLine();
            }
        }
    }

    /**
     * Parses a single line from the file into a Task object.
     *
     * @param line The line to parse.
     * @return Task object created from the line.
     * @throws BobletException If the line format is invalid.
     */
    private Task parseTask(String line) throws BobletException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new BobletException("Invalid task format.");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) todo.markAsDone();
                return todo;
            case "D":
                validateParts(parts, 4, "deadline");
                Deadline deadline = new Deadline(description, parts[3]);
                if (isDone) deadline.markAsDone();
                return deadline;
            case "E":
                validateParts(parts, 4, "event");
                Event event = new Event(description, parts[3]);
                if (isDone) event.markAsDone();
                return event;
            default:
                throw new BobletException("Unknown task type.");
        }
    }

    /**
     * Serializes a Task object into a String for saving to the file.
     *
     * @param task The task to serialize.
     * @return String representation of the task.
     */
    private String serializeTask(Task task) {
        String base = String.format("%s | %d | %s", task.getType().name().charAt(0), task.isDone ? 1 : 0, task.description);
        if (task instanceof Deadline) {
            return base + " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return base + " | " + ((Event) task).getAt();
        }
        return base;
    }

    /**
     * Validates that the parsed task line has the correct number of parts.
     *
     * @param parts      Array of parts from the task line.
     * @param required   Expected number of parts.
     * @param taskType   The type of task being validated.
     * @throws BobletException If the parts array does not meet the requirement.
     */
    private void validateParts(String[] parts, int required, String taskType) throws BobletException {
        if (parts.length != required) {
            throw new BobletException("Invalid " + taskType + " format.");
        }
    }

    /**
     * Creates the file and its parent directories if they do not exist.
     *
     * @param file The file to create.
     * @throws IOException If there are issues creating the file or directories.
     */
    private void createFile(File file) throws IOException {
        File parentDir = file.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw new IOException("Failed to create directory for file: " + filePath);
        }
        if (!file.createNewFile()) {
            throw new IOException("Failed to create file: " + filePath);
        }
    }
}
