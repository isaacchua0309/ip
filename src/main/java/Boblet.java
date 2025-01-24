import java.util.Scanner;

public class Boblet {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hey there! I'm Boblet, your friendly assistant!");
        System.out.println("I can track ToDos, Deadlines, and Events. Just tell me what to do!");
        System.out.println("Type 'list' to see your tasks, 'done <number>' to mark a task as done, or 'bye' to leave. Let's get started!");
        System.out.println("____________________________________________________________");

        Task[] tasks = new Task[100]; // Array to store tasks
        int taskCount = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            try {
                // Exit the program
                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Aww, you're leaving already? Well, take care!");
                    System.out.println("See you soon! Bye from Boblet!");
                    System.out.println("____________________________________________________________");
                    break;
                }

                // List all tasks
                if (userInput.equalsIgnoreCase("list")) {
                    System.out.println("____________________________________________________________");
                    if (taskCount == 0) {
                        System.out.println("Your task list is empty! Add something to get started.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println((i + 1) + "." + tasks[i]);
                        }
                    }
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("done")) {
                    handleDoneCommand(userInput, tasks, taskCount);
                } else if (userInput.startsWith("todo")) {
                    handleTodoCommand(userInput, tasks, taskCount++);
                } else if (userInput.startsWith("deadline")) {
                    handleDeadlineCommand(userInput, tasks, taskCount++);
                } else if (userInput.startsWith("event")) {
                    handleEventCommand(userInput, tasks, taskCount++);
                } else {
                    throw new BobletException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (BobletException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }

    private static void handleDoneCommand(String userInput, Task[] tasks, int taskCount) throws BobletException {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskNumber < 0 || taskNumber >= taskCount) {
                throw new BobletException("☹ OOPS!!! Invalid task number!");
            }
            tasks[taskNumber].markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[taskNumber]);
            System.out.println("____________________________________________________________");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobletException("☹ OOPS!!! Invalid input format! Use: done <task number>");
        }
    }

    private static void handleTodoCommand(String userInput, Task[] tasks, int taskIndex) throws BobletException {
        // Validate the command length
        if (userInput.length() <= 5 || userInput.substring(5).trim().isEmpty()) {
            throw new BobletException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = userInput.substring(5).trim();
        tasks[taskIndex] = new Todo(description);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskIndex]);
        System.out.println("Now you have " + (taskIndex + 1) + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    

    private static void handleDeadlineCommand(String userInput, Task[] tasks, int taskIndex) throws BobletException {
        try {
            String[] parts = userInput.substring(9).split(" /by ");
            if (parts.length < 2) {
                throw new BobletException("☹ OOPS!!! The description or deadline of a deadline cannot be empty.");
            }
            tasks[taskIndex] = new Deadline(parts[0].trim(), parts[1].trim());
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks[taskIndex]);
            System.out.println("Now you have " + (taskIndex + 1) + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            throw new BobletException("☹ OOPS!!! The description or deadline of a deadline cannot be empty.");
        }
    }

    private static void handleEventCommand(String userInput, Task[] tasks, int taskIndex) throws BobletException {
        try {
            String[] parts = userInput.substring(6).split(" /at ");
            if (parts.length < 2) {
                throw new BobletException("☹ OOPS!!! The description or time of an event cannot be empty.");
            }
            tasks[taskIndex] = new Event(parts[0].trim(), parts[1].trim());
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks[taskIndex]);
            System.out.println("Now you have " + (taskIndex + 1) + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            throw new BobletException("☹ OOPS!!! The description or time of an event cannot be empty.");
        }
    }
}
