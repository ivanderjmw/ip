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

    /**
     * A method that returns a SetDoneCommand with attributes parsed from a user's raw input.
     *
     * @param rawInput string of the user's input
     * @return a SetDoneCommand object
     */
    public Command setTaskDone(String rawInput) {
        String input = rawInput.replaceFirst("done", "").trim();

        // Parses the task index from the users input with format "done <index>"
        int taskIndex = Integer.parseInt(input) -  1;

        return new SetDoneCommand(tasks, ui, taskIndex);
    }

    /**
     * A method that returns a DeleteCommand with attributes parsed from a user's raw input.
     *
     * @param rawInput string of the user's input
     * @return a DeleteCommand object
     */
    public Command parseDeleteCommand(String rawInput) {
        String input = rawInput.replaceFirst("delete", "").trim();

        // Parses the task index from the users input with format "delete <index>"
        int taskIndex = Integer.parseInt(input) - 1;

        return new DeleteCommand(tasks, ui, taskIndex);
    }

    /**
     * A method that returns a FindCommand object with attributes parsed from a user's raw input.
     *
     * @param rawInput string of the user's input
     * @return a FindCommand object
     */
    public Command parseFindCommand(String rawInput) {
        String keyword = rawInput.replaceFirst("find", "").trim();

        return new FindCommand(tasks, ui, keyword);
    }

    /**
     * A command parser that parses the user input and calls the respective command methods.
     * Simplifies the method redirection.
     *
     * @param rawInput the raw user input
     * @return a command object
     * @throws DukeException
     */
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
            throw new DukeException("Command not found. Try refer to Duke's user guide in README.md");
        }

        return parsedCommand;

    }

    /**
     * Parses the user's raw input and returns an AddCommand object according to the details.
     *
     * @param rawUserInput the raw input from a user
     * @return an AddCommand object
     * @throws DukeException
     */
    public Command parseAddCommand(String rawUserInput) throws DukeException {

        Task newTask;

        // Checks the type of task in the prefix
        if (rawUserInput.startsWith("todo")) {
            String description = rawUserInput.replaceFirst("^todo", "").trim();
            newTask = new ToDo(description);

        } else if (rawUserInput.startsWith("deadline")) {
            if (!rawUserInput.contains("/by")) {
                throw new DukeException("duke.task.Deadline needs to have /by attribute");
            }

            String by = rawUserInput.substring(rawUserInput.indexOf("/by") + "/by".length()).trim();
            String description = rawUserInput.substring(0, rawUserInput.indexOf("/by"))
                    .replaceFirst("^deadline", "").trim();

            newTask = new Deadline(description, by);

        } else if (rawUserInput.startsWith("event")) {
            if (!rawUserInput.contains("/at")) {
                throw new DukeException("duke.task.Event needs to have /at attribute");
            }

            String at = rawUserInput.substring(rawUserInput.indexOf("/at") + "/at".length()).trim();
            String description = rawUserInput.substring(0, rawUserInput.indexOf("/at"))
                    .replaceFirst("^event", "").trim();

            newTask = new Event(description, at);
        } else {
            throw new DukeException("Command does not match any available task types. Try todo, event, or deadline.");
        }

        return new AddCommand(tasks, ui, newTask);
    }
}
