package duke.task;

import duke.DukeException;

public class Deadline extends Task{
    protected String by;

    /**
     * Constructor for deadline object with a "by" attribute.
     * @param description deadline description
     * @param by by attribute
     * @throws DukeException exception if description is blank
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (by.isBlank()) {
            throw new DukeException("Deadline needs to have a non-blank attribute /by.");
        }
        this.by = by;
    }

    /**
     * Get status icon and attribute.
     * @return status icon
     */
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

    public String encode() {
        String SPLITTER = " | ";
        String isDoneCode = (this.isDone) ? "1" : "0";

        return "D" + SPLITTER + isDoneCode + SPLITTER + this.description + SPLITTER + this.by;
    }
}
