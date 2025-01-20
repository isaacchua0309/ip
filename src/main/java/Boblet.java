public class Boblet {
    public static void main(String[] args) {
        try {
            System.out.println("____________________________________________________________\n");
            Thread.sleep(1000);
            System.out.println("Hello! I'm Boblet\n");
            Thread.sleep(1000);
            System.out.println("What can I do for you?\n");
            System.out.println("\n");
            Thread.sleep(1000);
            System.out.println("____________________________________________________________\n");
            Thread.sleep(1000);
            System.out.println("Bye. Hope to see you again soon!\n");
            System.out.println("____________________________________________________________\n");
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted!");
        }
    }
}
