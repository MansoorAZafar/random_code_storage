
import java.util.Scanner;

public class DifferentTypesOfInput {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Write your program here
        System.out.println("Give a string:");
        String str = scan.nextLine();

        System.out.println("Give an integer:");
        int nInt = Integer.valueOf(scan.nextLine());

        System.out.println("Give a double:");
        double nDouble = Double.valueOf(scan.nextLine());

        System.out.println("Give a boolean:");
        boolean flag = Boolean.valueOf(scan.nextLine());

        System.out.println("You gave the string " + str + "\n"
        + "You gave the integer " + nInt + "\n" +
        "You gave the double " + nDouble + "\n" + 
        "You gave the boolean " + flag);
    }
}
