package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * An abstract command Class for all commands.
 */
public abstract class Command {
    protected TaskList tasks;
    protected Ui ui;
    private int targetIndex = -1;

    /**
     * Command constructor
     * @param tasks user tasklist
     * @param ui user Ui
     */
    public Command(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Command execution
     * @throws DukeException exception if abstract class is executed
     */
    public void execute() throws DukeException {
        throw new DukeException("Unspecified Method");
    }

    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }


}
