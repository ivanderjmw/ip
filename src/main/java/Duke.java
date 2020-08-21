import java.util.Scanner;

public class Duke {

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

    public static void echoInput(String input) {

        printOutputFormat(input);

    }

    public static void main(String[] args) {

        printWelcomeMessage();

        String input;
        Scanner in = new Scanner(System.in);

        input = in.nextLine();

        while(!input.equals("bye")) {
            echoInput(input);
            input = in.nextLine();
        }

        printByeMessage();

    }
}
