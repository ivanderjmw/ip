package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    private int selectedTaskIndex;

    /**
     * Deletes a particular task with a corresponding index.
     * @param taskList user tasklist
     * @param ui user Ui
     * @param selectedTaskIndex index of task to delete
     */
    public DeleteCommand(TaskList taskList, Ui ui, int selectedTaskIndex) {
        super(taskList, ui);
        this.selectedTaskIndex = selectedTaskIndex;
    }

    /**
     * Command execution
     * @throws DukeException index out of bounds
     */
    @Override
    public void execute() throws DukeException {

        if (selectedTaskIndex < 0 || selectedTaskIndex > tasks.size()) {
            throw new DukeException("Index given is out of bounds");
        } else {
            String removedTaskString = tasks.remove(selectedTaskIndex).toString();
            ui.printWithTemplate("deleted: " + removedTaskString);
        }
    }
}
