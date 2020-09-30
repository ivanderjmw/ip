package duke;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Ui {

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        in = new Scanner(System.in);
        out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    }

    /**
     * Referenced from addressbook-level2
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the command back to the user.
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        out.print("Enter command: ");
        String fullInputLine = in.nextLine();

        return fullInputLine;
    }

    /**
     * Prints a string using a specified template.
     * Here the template is set as a horizontal line before and after the output.
     *
     * @param text is the desired string to be printed.
     * */
    public void printWithTemplate(String text) {
        String line = "____________________________________________________________";

        out.println(line);
        out.println(text);
        out.println(line);
    }

    /**
     * Prints the welcome message to the user. Includes a DUKE logo.
     */
    public void printWelcomeMessage() {
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

    /**
     * Prints a goodbye message to the user.
     */
    public void printByeMessage() {
        String byeMessage = "Bye. Hope to see you again soon!";

        printWithTemplate(byeMessage);
    }
}
