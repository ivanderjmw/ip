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

## Developer Guide
Seting up in IntteliJ Idea

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/duke/Duke.java` file, right-click it, and choose `Run duke.Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
