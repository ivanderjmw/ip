package duke;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    private static Task[] tasks;
    private static int taskCount;
    private static final Path FILE_PATH = Paths.get("/Users/ivanderjmw/CS2113T/ip/store.txt");

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

    public static void saveFile() throws DukeException {

        try {

            FileWriter fw = new FileWriter(FILE_PATH.toString());

            String textOut = "";

            System.out.println("File stored in: " + FILE_PATH.toString());

            for (int i = 0; i < taskCount; i++) {
                Task t = tasks[i];
                textOut = textOut.concat(t.encrypt());
                textOut = textOut.concat("\n");
            }

            fw.write(textOut);
            fw.close();

        } catch (IOException e) {
            throw new DukeException("IO exception");
        }
    }

    public static void readFile() throws DukeException {
        Task[] parsedTasks = new Task[100];
        int parsedTaskCount = 0;

        try {
            File f = new File(FILE_PATH.toString());

            if (f.createNewFile()) {
                printWithTemplate("New storage file created.");
            } else {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    String[] inputArray = line.split(" [ | ] ");
                    Task t;

                    /*
                      type | isDone | description | attribute
                      The input array splits the encrypted input into separate String values
                     */

                    switch (inputArray[0]) {
                    case "T":
                        t = new ToDo(inputArray[2]);
                        break;
                    case "D":
                        t = new Deadline(inputArray[2], inputArray[3]);
                        break;
                    case "E":
                        t = new Event(inputArray[2], inputArray[3]);
                        break;
                    default:
                        throw new DukeException("Parse Error");
                    }

                    if (inputArray[1].equals("1")) {
                        t.setDone();
                    }

                    parsedTasks[parsedTaskCount] = t;
                    parsedTaskCount++;
                }

                tasks = parsedTasks.clone();
                taskCount = parsedTaskCount;
            }
        } catch (IOException e) {
            throw new DukeException("An error occurred while reading from file.");
        }

    }

    public static void parseAndAddTask(String taskData) throws DukeException {

        // Checks the type of task in the prefix
        if (taskData.startsWith("todo")) {
            String description = taskData.replaceFirst("^todo", "").trim();
            tasks[taskCount] = new ToDo(description);

        } else if (taskData.startsWith("deadline")) {
            if (!taskData.contains("/by")) {
                throw new DukeException("duke.task.Deadline needs to have /by attribute");
            }

            String by = taskData.substring(taskData.indexOf("/by") + "/by".length()).trim();
            String description = taskData.substring(0, taskData.indexOf("/by"))
                    .replaceFirst("^deadline", "").trim();

            tasks[taskCount] = new Deadline(description, by);

        } else if (taskData.startsWith("event")) {
            if (!taskData.contains("/at")) {
                throw new DukeException("duke.task.Event needs to have /at attribute");
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
                    "duke.task.Task number " + (taskIndex + 1)
                            + " doesn't exist.\nPlease enter a valid task index."
            );
        }

        Task selectedTask = tasks[taskIndex];

        selectedTask.setDone();
        printWithTemplate("Great! I have marked this task as done:\n" + selectedTask.toString());
    }

    public static void parseCommand(String input) throws DukeException {
        String commandEntered = input.trim().split(" ")[0];
        String inputData = input.replaceFirst(commandEntered, "").trim();

        switch (commandEntered) {
        case "todo": case "deadline": case "event":
            parseAndAddTask(input);
            saveFile();
            break;
        case "list":
            listTasks();
            break;
        case "done":
            setTaskDone(inputData);
            saveFile();
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
        try {
            readFile();
        } catch (DukeException e) {
            printWithTemplate(e.toString());
        }
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
