import java.util.Scanner;

public class AverageOfNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double sum = 0, numOfInputs = 0, input;
        
        System.out.println("Give a number:");
        while((input = scan.nextDouble()) != 0){
            System.out.println("Give a number:");
            sum += input;
            ++numOfInputs;
        }

        System.out.println("Average of the numbers: " + (sum / numOfInputs));

        scan.close();
        scan = null;
    }
}
