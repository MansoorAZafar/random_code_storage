import java.util.Scanner;

public class SimpleCalculator {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Write your program here
        System.out.println("Give the first number:");
        int firstN = Integer.valueOf(scan.nextLine());
        System.out.println("Give the second number:");
        int secondN = Integer.valueOf(scan.nextLine());

        int sum = firstN + secondN;
        int difference = firstN - secondN;
        int product = firstN * secondN; 
        double quotient = (double)firstN / secondN;

        System.out.println(firstN + " + " + secondN + " = " + sum);
        System.out.println(firstN + " - " + secondN + " = " + difference);
        System.out.println(firstN + " * " + secondN + " = " + product);
        System.out.println(firstN + " / " + secondN + " = " + quotient);
    }
}
