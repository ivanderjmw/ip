package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class AddCommand extends Command {

    private Task newTask;

    public AddCommand(TaskList taskList, Ui ui, Task task) {
        super(taskList, ui);
        this.newTask = task;
    }

    @Override
    public void execute() throws DukeException {

        tasks.add(newTask);
        ui.printWithTemplate("added: " + newTask.toString());

    }
}
