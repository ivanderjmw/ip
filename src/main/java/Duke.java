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

    public static void addTask(String task) {

        if (task.startsWith("task")) {
            tasks[taskCount] = new ToDo(task.substring("task".length()));
        } else if (task.startsWith("deadline")) {
            tasks[taskCount] = new Deadline(task.substring("deadline".length(), task.indexOf("/by")), task.substring(task.indexOf("/by") + "/by".length()));
        } else if (task.startsWith("event")) {
            tasks[taskCount] = new Event(task.substring("event".length(), task.indexOf("/at")), task.substring(task.indexOf("/at") + "/at".length()));
        }


        printWithTemplate("added: " + tasks[taskCount].toString());
        taskCount++;

    }

    public static void listTasks() {

        String taskList = "";

        for (int i = 0; i < taskCount; i++) {
            taskList = taskList.concat((i + 1) + ". " +
                    tasks[i].getStatusIcon() + " " +
                    tasks[i].description + "\n");
        }

        // removes last newline item
        taskList = taskList.substring(0, taskList.length() - 1);

        printWithTemplate(taskList);

    }

    public static void setTaskDone(String input) {

        // Parses the task index from the users input with format "done <index>"
        int taskIndex = Integer.parseInt(input.replace("done ", "")) -  1;

        if (taskIndex > taskCount - 1) {
            printWithTemplate("Task number " + (taskIndex + 1) + " doesn't exist.\nPlease enter a valid task index.");
            return;
        }

        Task selectedTask = tasks[taskIndex];

        selectedTask.setDone();
        printWithTemplate("Great! I have marked this task as done:\n" +
                selectedTask.getStatusIcon() + " " + selectedTask.description
        );
    }

    public static void main(String[] args) {
        tasks = new Task[100];
        taskCount = 0;

        printWelcomeMessage();

        String input;
        Scanner in = new Scanner(System.in);

        input = in.nextLine();

        while (!input.equals("bye")) {

            // Checks cases for the command entered
            if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("done")) {
                setTaskDone(input);
            } else {
                addTask(input);
            }

            // Gets the next command entered
            input = in.nextLine();
        }

        printByeMessage();

    }
}
