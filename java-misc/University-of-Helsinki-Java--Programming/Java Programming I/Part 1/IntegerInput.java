
import java.util.Scanner;

public class IntegerInput {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // write your program here
        System.out.println("Give a number:");
        int num = Integer.valueOf(scan.nextLine());
        System.out.println("You gave the number " + num);
    }
}
