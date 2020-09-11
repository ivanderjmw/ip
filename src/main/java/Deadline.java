public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (by.isBlank()) {
            throw new DukeException("Deadline needs to have a non-blank attribute /by.");
        }
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
