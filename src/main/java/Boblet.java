import java.util.Scanner;

public class Boblet {
    public static void main(String[] args) {
        // Print greeting
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Boblet");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        // Create a scanner to read input
        Scanner scanner = new Scanner(System.in);

        // Keep reading commands until "bye" is entered
        while (true) {
            String userInput = scanner.nextLine();

            // Exit if the user types "bye"
            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            // Echo the user's input
            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");
        }

        // Close the scanner
        scanner.close();
    }
}