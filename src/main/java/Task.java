public class Task {
    protected String description;
    protected boolean isDone;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Method to get the status icon
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // Return ✓ for done, ✗ for not done
    }

    // Method to mark the task as done
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
