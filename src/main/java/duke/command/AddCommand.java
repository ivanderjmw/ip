package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class AddCommand extends Command {

    private Task newTask;

    /**
     * Adds a task to the tasklist
     *
     * @param taskList user's taskList
     * @param ui user interface
     * @param task task to be added
     */
    public AddCommand(TaskList taskList, Ui ui, Task task) {
        super(taskList, ui);
        this.newTask = task;
    }

    /**
     * Execute the command
     * @throws DukeException if any
     */
    @Override
    public void execute() throws DukeException {

        tasks.add(newTask);
        ui.printWithTemplate("added: " + newTask.toString());

    }
}
