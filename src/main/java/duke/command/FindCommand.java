package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * FindCommand handles the command to find tasks that contains a specified keyword
 */
public class FindCommand extends Command {

    private String keyword;


    public FindCommand(TaskList tasks, Ui ui, String keyword) {
        super(tasks, ui);
        this.keyword = keyword;
    }

    public void execute() {
        String text = "Here are matching tasks that contains [" + keyword + "]\n";
        ui.printWithTemplate(text + tasks.search(keyword).getMessage());
    }
}
