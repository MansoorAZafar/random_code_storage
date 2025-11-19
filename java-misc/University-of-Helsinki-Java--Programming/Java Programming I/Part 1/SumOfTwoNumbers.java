
import java.util.Scanner;

public class SumOfTwoNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Write your program here
        System.out.println("Give the first number:");
        int firstN = Integer.valueOf(scan.nextLine());
        System.out.println("Give the second number:");
        int secondN = Integer.valueOf(scan.nextLine());
        System.out.println("The sum of the numbers is " + (firstN + secondN));
    }
}
