import java.util.Scanner;


public class Duke {
    private static Task[] tasks;
    private static int taskCount;

    /**
     * Prints a string using a specified template.
     * Here the template is set as a horizontal line before and after the output.
     *
     * @param text is the desired string to be printed.
     * */
    public static void printWithTemplate(String text) {
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = "Hello! I'm Duke\n" +
                "What can I do for you?";

        System.out.println("Hello from\n" + logo);

        printWithTemplate(welcomeMessage);
    }

    public static void printByeMessage() {
        String byeMessage = "Bye. Hope to see you again soon!";

        printWithTemplate(byeMessage);
    }

    public static void parseAndAddTask(String taskData) throws DukeException {

        // Checks the type of task in the prefix
        if (taskData.startsWith("todo")) {
            String description = taskData.replaceFirst("^todo", "").trim();
            tasks[taskCount] = new ToDo(description);

        } else if (taskData.startsWith("deadline")) {
            if (!taskData.contains("/by")) {
                throw new DukeException("Deadline needs to have /by attribute");
            }

            String by = taskData.substring(taskData.indexOf("/by") + "/by".length()).trim();
            String description = taskData.substring(0, taskData.indexOf("/by"))
                    .replaceFirst("^deadline", "").trim();

            tasks[taskCount] = new Deadline(description, by);

        } else if (taskData.startsWith("event")) {
            if (!taskData.contains("/at")) {
                throw new DukeException("Event needs to have /at attribute");
            }

            String at = taskData.substring(taskData.indexOf("/at") + "/at".length()).trim();
            String description = taskData.substring(0, taskData.indexOf("/at"))
                    .replaceFirst("^event", "").trim();

            tasks[taskCount] = new Event(description, at);
        } else {
            throw new DukeException("Command does not match any available task types. Try todo, event, or deadline.");
        }

        printWithTemplate("added: " + tasks[taskCount].toString());
        taskCount++;

    }

    public static void listTasks() {

        String taskList = "";

        if (taskCount == 0) {
            printWithTemplate("You have no tasks");
            return;
        }

        for (int i = 0; i < taskCount; i++) {
            taskList = taskList.concat((i + 1) + ". " +
                    tasks[i].toString() + "\n");
        }

        // removes last newline item
        taskList = taskList.substring(0, taskList.length() - 1);

        printWithTemplate(taskList);

    }

    public static void setTaskDone(String input) throws DukeException{

        // Parses the task index from the users input with format "done <index>"
        int taskIndex = Integer.parseInt(input) -  1;

        if (taskIndex > taskCount - 1) {
            throw new DukeException(
                    "Task number " + (taskIndex + 1)
                            + " doesn't exist.\nPlease enter a valid task index."
            );
        }

        Task selectedTask = tasks[taskIndex];

        selectedTask.setDone();
        printWithTemplate("Great! I have marked this task as done:\n" +
                selectedTask.getStatusIcon() + " " + selectedTask.description
        );
    }

    public static void parseCommand(String input) throws DukeException {
        String commandEntered = input.trim().split(" ")[0];
        String inputData = input.replaceFirst(commandEntered, "").trim();

        switch (commandEntered) {
        case "todo": case "deadline": case "event":
            parseAndAddTask(input);
            break;
        case "list":
            listTasks();
            break;
        case "done":
            setTaskDone(inputData);
            break;
        case "bye":
            printByeMessage();
            System.exit(0);
            break;
        default:
            printWithTemplate("Command not found.");
            break;
        }
    }

    public static void main(String[] args) {
        tasks = new Task[100];
        taskCount = 0;

        printWelcomeMessage();

        String input;
        Scanner in = new Scanner(System.in);

        input = in.nextLine();

        while (true) {
            try {
                // Checks cases for the command entered
                parseCommand(input);
            } catch (DukeException e) {
                printWithTemplate(e.toString());
            }

            // Gets the next command entered
            input = in.nextLine();
        }

    }
}
