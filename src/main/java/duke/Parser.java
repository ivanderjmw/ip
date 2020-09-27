package duke;

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

    public Parser() {

    }

    public void setTaskDone(String rawInput) throws DukeException{
        String input = rawInput.replaceFirst("done", "").trim();

        // Parses the task index from the users input with format "done <index>"
        int taskIndex = Integer.parseInt(input) -  1;


        tasks.setTaskDone(taskIndex);
        String selectedTaskText = tasks.get(taskIndex).toString();

        ui.printWithTemplate("Great! I have marked this task as done:\n" + selectedTaskText);

        storage.saveFile(tasks);
    }

    public void deleteTask(String rawInput) throws DukeException {
        String input = rawInput.replaceFirst("delete", "").trim();

        // Parses the task index from the users input with format "delete <index>"
        int taskIndex = Integer.parseInt(input) - 1;

        // Remove the task with the corresponding index
        String selectedTaskText = tasks.get(taskIndex).toString();
        tasks.remove(taskIndex);

        ui.printWithTemplate("The following task was deleted:\n" + selectedTaskText);

        storage.saveFile(tasks);
    }

    public void parseCommand(String rawInput) throws DukeException {
        String commandEntered = rawInput.trim().split(" ")[0];


        switch (commandEntered) {
        case "todo": case "deadline": case "event":
            parseAndAddTask(rawInput);
            break;
        case "list":
            ui.printWithTemplate(tasks.getMessage());
            break;
        case "done":
            setTaskDone(rawInput);
            break;
        case "delete":
            deleteTask(rawInput);
            break;
        case "bye":
            ui.printByeMessage();
            System.exit(0);
            break;
        default:
            ui.printWithTemplate("Command not found.");
            break;
        }


    }

    public void parseAndAddTask(String taskData) throws DukeException {

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

        tasks.add(newTask);
        ui.printWithTemplate("added: " + newTask.toString());

        storage.saveFile(tasks);
    }
}
