public class ToDo extends Task {

    public ToDo(String description) throws DukeException {
        super(description);
    }

    public String getStatusIcon() {
        return "T - "+ (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }

    public void setDone() {
        this.isDone = true;
    }
}
