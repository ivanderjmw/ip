package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Parser {
    protected TaskList tasks;
    protected Ui ui;
    protected Storage storage;

    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    public Command setTaskDone(String rawInput) {
        String input = rawInput.replaceFirst("done", "").trim();

        // Parses the task index from the users input with format "done <index>"
        int taskIndex = Integer.parseInt(input) -  1;

        return new SetDoneCommand(tasks, ui, taskIndex);
    }

    public Command parseDeleteCommand(String rawInput) {
        String input = rawInput.replaceFirst("delete", "").trim();

        // Parses the task index from the users input with format "delete <index>"
        int taskIndex = Integer.parseInt(input) - 1;

        return new DeleteCommand(tasks, ui, taskIndex);
    }

    public Command parseFindCommand(String rawInput) {
        String keyword = rawInput.replaceFirst("find", "").trim();

        return new FindCommand(tasks, ui, keyword);
    }

    public Command parseCommand(String rawInput) throws DukeException {
        String commandEntered = rawInput.trim().split(" ")[0];
        Command parsedCommand = null;

        switch (commandEntered) {
        case "todo": case "deadline": case "event":
            parsedCommand = parseAddCommand(rawInput);
            break;
        case "list":
            parsedCommand = new ListCommand(tasks, ui);
            break;
        case "done":
            parsedCommand = setTaskDone(rawInput);
            break;
        case "delete":
            parsedCommand = parseDeleteCommand(rawInput);
            break;
        case "find":
            parsedCommand = parseFindCommand(rawInput);
            break;
        case "bye":
            ui.printByeMessage();
            System.exit(0);
            break;
        default:
            ui.printWithTemplate("Command not found.");
            break;
        }

        return parsedCommand;

    }

    public Command parseAddCommand(String taskData) throws DukeException {

        Task newTask;

        // Checks the type of task in the prefix
        if (taskData.startsWith("todo")) {
            String description = taskData.replaceFirst("^todo", "").trim();
            newTask = new ToDo(description);

        } else if (taskData.startsWith("deadline")) {
            if (!taskData.contains("/by")) {
                throw new DukeException("duke.task.Deadline needs to have /by attribute");
            }

            String by = taskData.substring(taskData.indexOf("/by") + "/by".length()).trim();
            String description = taskData.substring(0, taskData.indexOf("/by"))
                    .replaceFirst("^deadline", "").trim();

            newTask = new Deadline(description, by);

        } else if (taskData.startsWith("event")) {
            if (!taskData.contains("/at")) {
                throw new DukeException("duke.task.Event needs to have /at attribute");
            }

            String at = taskData.substring(taskData.indexOf("/at") + "/at".length()).trim();
            String description = taskData.substring(0, taskData.indexOf("/at"))
                    .replaceFirst("^event", "").trim();

            newTask = new Event(description, at);
        } else {
            throw new DukeException("Command does not match any available task types. Try todo, event, or deadline.");
        }

        return new AddCommand(tasks, ui, newTask);
    }
}
