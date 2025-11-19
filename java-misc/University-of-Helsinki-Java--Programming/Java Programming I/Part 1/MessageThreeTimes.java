import java.util.Scanner;
public class MessageThreeTimes {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Write a message:");
        // Write your program here
        String str = scan.nextLine();
        System.out.println(str);
        System.out.println(str);
        System.out.println(str);

    }
}
