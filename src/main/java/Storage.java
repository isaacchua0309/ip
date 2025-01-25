import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws IOException, BobletException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.createNewFile();
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

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(serializeTask(task));
                writer.newLine();
            }
        }
    }

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
                if (parts.length != 4) {
                    throw new BobletException("Invalid deadline format.");
                }
                Deadline deadline = new Deadline(description, parts[3]);
                if (isDone) deadline.markAsDone();
                return deadline;
            case "E":
                if (parts.length != 4) {
                    throw new BobletException("Invalid event format.");
                }
                Event event = new Event(description, parts[3]);
                if (isDone) event.markAsDone();
                return event;
            default:
                throw new BobletException("Unknown task type.");
        }
    }

    private String serializeTask(Task task) {
        String base = String.format("%s | %d | %s", task.getType().name().charAt(0), task.isDone ? 1 : 0, task.description);
        if (task instanceof Deadline) {
            return base + " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return base + " | " + ((Event) task).getAt();
        }
        return base;
    }
}
