package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

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
