public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    public String getStatusIcon() {
        return "D - "+ (isDone ? "\u2713" : "\u2718") + " (by:" + by + ")"; //return tick or X symbols
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }

    public void setDone() {
        this.isDone = true;
    }
}
