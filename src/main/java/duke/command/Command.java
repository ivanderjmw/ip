package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    protected TaskList tasks;
    protected Ui ui;
    private int targetIndex = -1;

    public Command(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void execute() throws DukeException {
        throw new DukeException("Unspecified Method");
    }

    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }


}
