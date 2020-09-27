package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class SetDoneCommand extends Command {

    private int selectedTaskIndex;

    public SetDoneCommand(TaskList taskList, Ui ui, int selectedTaskIndex) {
        super(taskList, ui);
        this.selectedTaskIndex = selectedTaskIndex;
    }

    @Override
    public void execute() throws DukeException {

        if (selectedTaskIndex < 0 || selectedTaskIndex > tasks.size()) {
            throw new DukeException("Index given is out of bounds");
        } else {
            tasks.setTaskDone(selectedTaskIndex);
            ui.printWithTemplate("set done: " + tasks.get(selectedTaskIndex).toString());
        }
    }

}
