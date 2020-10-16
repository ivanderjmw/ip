package duke.command;

import duke.TaskList;
import duke.Ui;


public class FindCommand extends Command {

    private String keyword;

    /**
     * Finds a specific keyword through the task list.
     * @param tasks user taskList
     * @param ui user Ui
     * @param keyword search keyword
     */
    public FindCommand(TaskList tasks, Ui ui, String keyword) {
        super(tasks, ui);
        this.keyword = keyword;
    }

    /**
     * Execution of method
     */
    public void execute() {
        String text = "Here are matching tasks that contains [" + keyword + "]\n";
        ui.printWithTemplate(text + tasks.search(keyword).getMessage());
    }
}
