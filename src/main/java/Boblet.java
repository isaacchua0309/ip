import java.util.Scanner;

public class Boblet {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hey there! I'm Boblet, your friendly assistant!");
        System.out.println("I can keep track of your tasks. Just tell me what to do!");
        System.out.println("Type 'list' to see your tasks or 'bye' to leave. Let's get started!");
        System.out.println("____________________________________________________________");

        String[] tasks = new String[100];
        int taskCount = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("You: "); // Prompt to show user input
            String userInput = scanner.nextLine();

            // Exit the program if user types "bye"
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Aww, you're leaving already? Well, take care!");
                System.out.println("See you soon! Bye from Boblet!");
                System.out.println("____________________________________________________________");
                break;
            }

            // Display all tasks if user types "list"
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                if (taskCount == 0) {
                    System.out.println("Your task list is empty! Add something to get started.");
                } else {
                    System.out.println("Here's what you've got so far:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
                System.out.println("____________________________________________________________");
            } else {
                // Add the task to the array
                tasks[taskCount] = userInput;
                taskCount++;

                System.out.println("____________________________________________________________");
                System.out.println("Got it! I've added this to your list:");
                System.out.println("  " + userInput);
                System.out.println("You now have " + taskCount + " task(s) in your list. Keep it up!");
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
