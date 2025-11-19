import java.util.Scanner;

public class SumOfASequence {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Last number? ");
        int lastNumber = scan.nextInt();
        int sum = 0;
        
        for(int current = 0; current <= lastNumber; ++current){
            sum += current;
        }

        System.out.println("The sum is " + sum);
        scan.close();
        scan = null;
    }
}
