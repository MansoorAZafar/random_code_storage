import java.util.Scanner;

public class NumberOfNegativeNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numOfNegatives = 0;
        int n;
        System.out.println("Give a number:");
        while((n = scan.nextInt()) != 0){
            if(n < 0){
                ++numOfNegatives;
            }
            System.out.println("Give a number:");
        }
        System.out.println("Number of negative numbers: " + numOfNegatives);
        scan.close();
        scan = null;
    }
}
