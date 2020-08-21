public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = "Hello! I'm Duke\n" +
                "What can I do for you?";
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println("Hello from\n" + logo);

        System.out.println(line);
        System.out.println(welcomeMessage);
        System.out.println(line);

        System.out.println(byeMessage);
        System.out.println(line);

    }
}
