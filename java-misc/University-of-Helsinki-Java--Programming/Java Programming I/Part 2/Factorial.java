import java.util.Scanner;

public class Factorial {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int num = scan.nextInt();
        int factorial = 1;

        for(int current = 1; current <= num; ++current){
            factorial*=current;
        }

        System.out.println("The factorial of " + num + " is " + factorial);
        scan.close();
        scan = null;
    }
}
