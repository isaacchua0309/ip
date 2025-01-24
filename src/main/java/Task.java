public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✗");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public TaskType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[" + getType().name() + "][" + getStatusIcon() + "] " + description;
    }
}
