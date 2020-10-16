package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ListCommand extends Command {

    /**
     * Lists a the tasklist.
     * @param tasks user tasks
     * @param ui user Ui
     */
    public ListCommand(TaskList tasks, Ui ui) {
        super(tasks, ui);
    }

    /**
     * Prints the tasklist.
     */
    public void execute() {
        ui.printWithTemplate(tasks.getMessage());
    }
}
