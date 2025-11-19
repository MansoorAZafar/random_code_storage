import java.util.Scanner;

public class AverageOfPositiveNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double input, sum = 0, numOfNumbers = 0;

        while ((input = scan.nextDouble()) != 0) {
            if (input < 0) {
                continue;
            }
            sum += input;
            ++numOfNumbers;
        }

        if (sum == 0) {
            System.out.println("Cannot calculate the average");
        } else {
            System.out.println((sum / numOfNumbers));
        }

        scan.close();
        scan = null;
    }
}
