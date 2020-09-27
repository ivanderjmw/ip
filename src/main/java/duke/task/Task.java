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

    /**
     * Returns a Cross or a Check symbol that shows the done status of the task.
     *
     * @return the status icon of the specified task
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Sets the task as done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns one line of String that is an encoded form of the specified task.
     * Human readable. Works in compliment with readFile() in Duke.Storage
     *
     * @return the task encoded as a UTF-8 string
     */
    public String encode() {
        String SPLITTER = " | ";
        String isDoneCode = (this.isDone) ? "1" : "0";

        return "T" + SPLITTER + isDoneCode + SPLITTER + this.description;
    }

    /**
     * Checks whether a keyword exists in the task's description.
     * Not case-sensitive.
     *
     * @param keyword the keyword to be searched
     * @return true if the task's description contains the specified keyword
     */
    public boolean contains(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }
}