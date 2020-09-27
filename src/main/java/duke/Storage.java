package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public final Path FILE_PATH;
    protected Ui ui;

    public Storage(String filePath, Ui ui) {
        FILE_PATH = Paths.get("duke_data/store.txt");
        this.ui = ui;
    }

    public void saveFile(TaskList tasks) throws DukeException {

        try {

            FileWriter fw = new FileWriter(FILE_PATH.toString());

            String textOut = "";

            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                textOut = textOut.concat(t.encrypt());
                textOut = textOut.concat("\n");
            }

            fw.write(textOut);
            fw.close();

        } catch (IOException e) {
            throw new DukeException("IO exception");
        }
    }

    public void createFile() throws IOException {

        // Code referenced from
        // https://stackoverflow.com/questions/2833853/create-whole-path-automatically-when-writing-to-a-new-file
        Files.createDirectories(FILE_PATH.getParent());
        Files.createFile(FILE_PATH);
        ui.printWithTemplate("New storage file created.");
    }

    public TaskList readFile() throws DukeException {
        TaskList parsedTasks = new TaskList(100);

        try {
            File f = new File(FILE_PATH.toString());
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

                parsedTasks.add(t);
            }

            return parsedTasks;

        } catch (FileNotFoundException e) {
            try {
                createFile();
            } catch (IOException k) {
                throw new DukeException(k.toString() + "\nProblem with reading the storage file.");
            }
        }

        return null;
    }
}
