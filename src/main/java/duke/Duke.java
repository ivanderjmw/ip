package duke;

import duke.command.Command;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;


public class Duke extends Throwable {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);

        try {
            tasks = new TaskList(storage.readFile());
        } catch (DukeException e) {
            ui.printWithTemplate(e.toString());
            tasks = new TaskList();
        }

        parser = new Parser(tasks, ui, storage);
    }

    public void run() {
        ui.printWelcomeMessage();

        String input;

        input = ui.getUserCommand();

        while (true) {
            try {
                // Checks cases for the command entered
                Command userCommand = parser.parseCommand(input);
                userCommand.execute();
                storage.saveFile(tasks);
            } catch (DukeException e) {
                ui.printWithTemplate(e.getMessage());
            }

            // Gets the next command entered
            input = ui.getUserCommand();
        }
    }

    public static void main(String[] args) {
        new Duke("duke_data/store.txt").run();
    }
}
