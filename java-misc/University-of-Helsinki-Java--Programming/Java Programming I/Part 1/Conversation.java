import java.util.Scanner;
public class Conversation {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Write your program here
        System.out.println("Greetings! How are you doing?");
        String mood = scan.nextLine();
        System.out.println("Oh, how interesting. Tell me more!");
        String extraInfo = scan.nextLine();
        System.out.println("Thanks for sharing!");

    }
}
