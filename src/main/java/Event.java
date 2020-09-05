public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getStatusIcon() {
        return "E - "+ (isDone ? "\u2713" : "\u2718") + " (at:" + at + ")"; //return tick or X symbols
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }

    public void setDone() {
        this.isDone = true;
    }

}
