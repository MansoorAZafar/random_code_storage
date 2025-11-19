import java.util.Scanner;

public class LargerThanOrEqualTo {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Give the first number:");
        int firstN = Integer.valueOf(scan.nextLine());
        System.out.println("Give the second number:");
        int secondN = Integer.valueOf(scan.nextLine());
        if(firstN > secondN) {
            System.out.println("Greater number is: " + firstN);
        } else if (firstN < secondN) {
            System.out.println("Greater number is: " + secondN);
        } else {
            System.out.println("The numbers are equal!");
        }

    }
}
