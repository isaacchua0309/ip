public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
