import java.util.Scanner;


public class Duke {
    private static Task[] tasks;
    private static int taskCount;

    public static void printOutputFormat(String output) {
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println(output);
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

        printOutputFormat(welcomeMessage);
    }

    public static void printByeMessage() {
        String byeMessage = "Bye. Hope to see you again soon!";

        printOutputFormat(byeMessage);
    }

    public static void addTask(String task) {

        tasks[taskCount] = new Task(task);
        taskCount++;

        printOutputFormat("added: " + task);

    }

    public static void listTasks() {

        String taskList = "";

        for(int i = 0; i < taskCount; i++) {
            taskList = taskList.concat((i + 1) + ". " +
                    tasks[i].getStatusIcon() + " " +
                    tasks[i].description + "\n");
        }

        // removes last newline item
        taskList = taskList.substring(0, taskList.length() - 1);

        printOutputFormat(taskList);

    }

    public static void setTaskDone(String input) {
        int taskIndex = Integer.parseInt(input.replace("done ", "")) -  1;

        if (taskIndex > taskCount - 1) {
            printOutputFormat("Task number " + (taskIndex + 1) + " doesn't exist.\nPlease enter a valid task index.");
            return;
        }

        Task selectedTask = tasks[taskIndex];

        selectedTask.setDone();
        printOutputFormat("Great! I have marked this task as done:\n" +
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
            if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("done")){
                setTaskDone(input);
            } else {
                addTask(input);
            }
            input = in.nextLine();
        }

        printByeMessage();

    }
}
