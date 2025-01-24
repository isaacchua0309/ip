import java.util.ArrayList;
import java.util.Scanner;

public class Boblet {

    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hey there! I'm Boblet, your friendly assistant!");
        System.out.println("I can track ToDos, Deadlines, and Events. Just tell me what to do!");
        System.out.println("Type 'list' to see your tasks, 'done <number>' to mark a task as done, 'delete <number>' to remove a task, or 'bye' to leave. Let's get started!");
        System.out.println("____________________________________________________________");

        ArrayList<Task> tasks = new ArrayList<>(); // Dynamic list for tasks
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            try {
                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Aww, you're leaving already! Well, take care!");
                    System.out.println("See you soon! Bye from Boblet!");
                    System.out.println("____________________________________________________________");
                    break;
                }

                if (userInput.equalsIgnoreCase("list")) {
                    handleListCommand(tasks);
                } else if (userInput.startsWith("done")) {
                    handleDoneCommand(userInput, tasks);
                } else if (userInput.startsWith("delete")) {
                    handleDeleteCommand(userInput, tasks);
                } else if (userInput.startsWith("todo")) {
                    handleTodoCommand(userInput, tasks);
                } else if (userInput.startsWith("deadline")) {
                    handleDeadlineCommand(userInput, tasks);
                } else if (userInput.startsWith("event")) {
                    handleEventCommand(userInput, tasks);
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

    private static void handleListCommand(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty! Add something to get started.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void handleDoneCommand(String userInput, ArrayList<Task> tasks) throws BobletException {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new BobletException("☹ OOPS!!! Invalid task number!");
            }
            tasks.get(taskNumber).markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(taskNumber));
            System.out.println("____________________________________________________________");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobletException("☹ OOPS!!! Invalid input format! Use: done <task number>");
        }
    }

    private static void handleDeleteCommand(String userInput, ArrayList<Task> tasks) throws BobletException {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new BobletException("☹ OOPS!!! Invalid task number!");
            }
            Task removedTask = tasks.remove(taskNumber); // Remove the task
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobletException("☹ OOPS!!! Invalid input format! Use: delete <task number>");
        }
    }

    private static void handleTodoCommand(String userInput, ArrayList<Task> tasks) throws BobletException {
        if (userInput.length() <= 5 || userInput.substring(5).trim().isEmpty()) {
            throw new BobletException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = userInput.substring(5).trim();
        tasks.add(new Todo(description)); // Add to the list
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void handleDeadlineCommand(String userInput, ArrayList<Task> tasks) throws BobletException {
        try {
            String[] parts = userInput.substring(9).split(" /by ");
            if (parts.length < 2) {
                throw new BobletException("☹ OOPS!!! The description or deadline of a deadline cannot be empty.");
            }
            tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            throw new BobletException("☹ OOPS!!! The description or deadline of a deadline cannot be empty.");
        }
    }

    private static void handleEventCommand(String userInput, ArrayList<Task> tasks) throws BobletException {
        try {
            String[] parts = userInput.substring(6).split(" /at ");
            if (parts.length < 2) {
                throw new BobletException("☹ OOPS!!! The description or time of an event cannot be empty.");
            }
            tasks.add(new Event(parts[0].trim(), parts[1].trim()));
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            throw new BobletException("☹ OOPS!!! The description or time of an event cannot be empty.");
        }
    }
}
