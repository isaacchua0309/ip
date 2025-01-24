import java.util.Scanner;

public class Boblet {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Boblet");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        String[] tasks = new String[100];
        int taskCount = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();

            // Exit the program if user types "bye"
            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            // Display all tasks if user types "list"
            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                // Add the task to the array
                tasks[taskCount] = userInput;
                taskCount++;

                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
