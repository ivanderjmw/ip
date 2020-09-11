public class Event extends Task{
    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        if (at.isBlank()) {
            throw new DukeException("Event needs to have a non-blank attribute /at.");
        }
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
