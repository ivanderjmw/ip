package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ListCommand extends Command {

    public ListCommand(TaskList tasks, Ui ui) {
        super(tasks, ui);
    }

    public void execute() {
        ui.printWithTemplate(tasks.getMessage());
    }
}
