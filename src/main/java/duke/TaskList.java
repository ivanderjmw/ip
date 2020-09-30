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

    /**
     * Removes a task with the specified index from the tasklist. Throws an exception if
     * given task is out of bounds
     *
     * @param index the index of the task to be removed
     * @return the removed Task
     * @throws DukeException
     */
    public Task remove(int index) throws DukeException {

        if (index < 0 || index > list.size() - 1) {
            throw new DukeException(
                    "duke.task.Task number " + (list.size() + 1)
                            + " doesn't exist.\nPlease enter a valid task index."
            );
        }

        return list.remove(index);
    }

    /**
     * Sets a task to done, throws an exception if given index is out of bounds.
     *
     * @param index the index of the task to be set to done
     * @throws DukeException
     */
    public void setTaskDone(int index) throws DukeException{
        if (index < 0 || index > list.size() - 1) {
            throw new DukeException(
                    "Task with index " + (index + 1)
                            + " doesn't exist.\nPlease enter a valid task index."
            );
        }

        list.get(index).setDone();

    }

    /**
     * Returns a message intended to be shown to users. Lists all the tasks inside TaskList.
     * Uses enumeration from 1 to n-1, where n is the number of tasks.
     *
     * @return a string of the task list
     */
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

    /**
     * Searches the descriptions of tasks if it contains the specified keyword.
     * Returns a tasklist of search results, ordered by increasing task index.
     *
     * @param keyword a string keyword
     * @return a tasklist containing search results.
     */
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
