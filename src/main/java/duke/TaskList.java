package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList (int size) {
        list = new ArrayList<>(100);
    }

    public TaskList (TaskList taskList) {
        list = (ArrayList<Task>) taskList.list.clone();
    }

    public int size() {
        return list.size();
    }

    public Task get (int index) {
        return list.get(index);
    }

    public void add (Task t) {
        list.add(t);
    }

    public Task remove (int index) throws DukeException {

        if (index > list.size() - 1) {
            throw new DukeException(
                    "duke.task.Task number " + (list.size() + 1)
                            + " doesn't exist.\nPlease enter a valid task index."
            );
        }

        return list.remove(index);
    }

    public Task remove (Task t) {
        return list.remove(list.indexOf(t));
    }

    public void setTaskDone(int index) throws DukeException{
        if (index < 1 || index > list.size()) {
            throw new DukeException(
                    "duke.task.Task number " + (index + 1)
                            + " doesn't exist.\nPlease enter a valid task index."
            );
        }

        list.get(index).setDone();

    }

    // Returns a string object
    public String getMessage() {

        String message = "";

        if (list.size() == 0) {
            message = "Task List is empty.";
        } else {
            // concatenates each task into message
            for (int i = 0; i < list.size(); i++) {
                message = message.concat((i + 1) + ". " + list.get(i).toString() + "\n");
            }

            // removes last newline item
            message = message.substring(0, message.length() - 1);
        }

        return message;

    }

    public TaskList search(String keyword) {
        TaskList searchResults = new TaskList();

        for (Task t : list) {
            if (t.contains(keyword)) {
                searchResults.add(t);
            }
        }

        return searchResults;
    }
}
