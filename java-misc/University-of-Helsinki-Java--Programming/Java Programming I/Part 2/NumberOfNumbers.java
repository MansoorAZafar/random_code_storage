import java.util.Scanner;

public class NumberOfNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numOfNumbers = 0;
        System.out.println("Give a number:");
        while((scan.nextInt()) != 0){
            ++numOfNumbers;
            System.out.println("Give a number:");
        }
        System.out.println("Number of numbers: " + numOfNumbers);
        scan.close();
        scan = null;
    }
}
