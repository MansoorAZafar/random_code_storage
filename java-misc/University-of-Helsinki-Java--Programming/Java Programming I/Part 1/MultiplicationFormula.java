import java.util.Scanner;

public class MultiplicationFormula {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Write your program here
        System.out.println("Give the first number:");
        int firstN = scan.nextInt();
        System.out.println("Give the second number:");
        int secondN = scan.nextInt();
        System.out.println(firstN + " * " + secondN + " = " +
                            (firstN * secondN));
    }
}
