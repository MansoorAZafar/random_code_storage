import java.util.Scanner;

public class NumberAndSumOfNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numOfNumbers = 0, sum = 0, input;

        System.out.println("Give a number:");
        while((input = scan.nextInt()) != 0){
            System.out.println("Give a number:");
            ++numOfNumbers;
            sum+=input;
        }
        System.out.println("Number of numbers: " + numOfNumbers 
            + "\nSum of the numbers: " + sum);
        
        scan.close();
        scan = null;

    }
}
