
import java.util.Scanner;

public class DoubleInput {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // write your program here
        double num;
        System.out.println("Give a number:");
        num = Double.valueOf(scan.nextLine());
        System.out.println("You gave the number " + num);
    }
}
