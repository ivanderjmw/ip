# Duke 
Duke is a simple task management Command Line interface (CLI) software. Available features include different task types, autosaving, and search.

## User Guide
Welcome to Duke. This user guide will help you to get started, or just to find further references of the various Duke commands.

1. Getting Started
2. Adding a task
3. Listing tasks
4. Marking a task as done
5. Removing a task
6. Searching a task
7. Saving your data

### Getting Started
To get started right away, download Java 11 and the latest Duke .jar package.
Double click the package to open the command line, and try to enter some Duke commands, 
like `todo Watch netflix`, `list`, and exit by entering `bye`.

### Adding a Task
There are three available different task types in Duke. 
The most basic task is called a `TODO`, with it you can set the task as done.
You also can use `EVENT` to include an `/at` attribute or `DEADLINE` to include a `/by` attribute. 

To add a task, you can enter the following formats.

```todo TODO_DESCRIPTION```

```event EVENT_DESCRIPTION /at EVENT_DATE```

```deadline DEADLINE_DESCRIPTION /by DEADLINE_DATE```
 
Added tasks will automatically be stored and you do not need to worry about losing data.
 
### Listing Tasks
You can list all of your stored tasks. Enter the following command:

```list```

Below is an example output of the list command.

```
Enter command: list
____________________________________________________________
1. E - ✓ (at:1am 15 September) Apple WWDC 2020
2. T - ✘ Finish Week 6 ip
3. T - ✓ Buy boba koi
4. E - ✓ (at:27 Sept 11am) Go to Church
____________________________________________________________
```

### Marking a task as done
To mark a task as done, you need to refer to the task index (numbering) from the `list` command.
Then we could enter the following:

```done TASK_INDEX```


### Removing a task
You can remove a task permanently from your task list.
Similar with `done`, you need to refer to the task index (numbering) from the `list` command.
Then enter the following:

```delete TASK_INDEX```

### Searching a task
To search through your task descriptions, you could use this command, and find your tasks
that contain a specific keyword.
Enter the following into Duke:

```search SEARCH_KEYWORD```

### Saving Your Data
Duke stores your user data inside a folder named `duke_data`. 
Your tasks are stored in that folder inside a file named `store.txt`.

Duke stores your data every time your commands are executed with no errors.
