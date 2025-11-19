import java.util.Scanner;

public class SumOfASequenceTheSequel {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("First number? ");
        final int firstNum = scan.nextInt();
        
        System.out.print("Last number? ");
        final int lastNum = scan.nextInt();
        
        int sum = 0;  
        for(int current = firstNum; current <= lastNum; ++current){
            sum+=current;
        }
        System.out.println("The sum is " + sum);

        scan.close();
        scan = null;
    }
}
