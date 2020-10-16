package duke.task;

import duke.DukeException;

public class Event extends Task {
    protected String at;

    /**
     * A constructor of an event with "at" attribute
     * @param description description of event
     * @param at at attribute of event
     * @throws DukeException description is blank
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        if (at.isBlank()) {
            throw new DukeException("Event needs to have a non-blank attribute /at.");
        }
        this.at = at;
    }

    /**
     * Get the status icon and at attribute for event.
     * @return status icon
     */
    public String getStatusIcon() {
        return "E - "+ (isDone ? "\u2713" : "\u2718") + " (at:" + at + ")"; //return tick or X symbols
    }

    /**
     * Override to String.
     * @return string
     */
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

        return "E" + SPLITTER + isDoneCode + SPLITTER + this.description + SPLITTER + this.at;
    }

}
