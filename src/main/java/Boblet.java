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
                // Mark a task as done
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < taskCount) {
                        tasks[taskNumber].markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks[taskNumber]);
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Invalid task number! Please try again.");
                        System.out.println("____________________________________________________________");
                    }
                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Invalid input format! Use: done <task number>");
                    System.out.println("____________________________________________________________");
                }
            } else if (userInput.startsWith("todo")) {
                // Add a Todo task
                String description = userInput.substring(5).trim();
                tasks[taskCount] = new Todo(description);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("deadline")) {
                // Add a Deadline task
                String[] parts = userInput.substring(9).split(" /by ");
                tasks[taskCount] = new Deadline(parts[0].trim(), parts[1].trim());
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("event")) {
                // Add an Event task
                String[] parts = userInput.substring(6).split(" /at ");
                tasks[taskCount] = new Event(parts[0].trim(), parts[1].trim());
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("I'm sorry, I don't understand that command.");
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
