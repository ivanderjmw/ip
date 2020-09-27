package duke.task;

import duke.DukeException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Description cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }

    public String encrypt() {
        String SPLITTER = " | ";
        String isDoneCode = (this.isDone) ? "1" : "0";

        return "T" + SPLITTER + isDoneCode + SPLITTER + this.description;
    }

    public boolean contains(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }
}