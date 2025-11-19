import java.util.Scanner;

public class SumOfNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //Initalize the sum of the numbers that will be inputted
        int sum = 0;
        
        while(true){
            System.out.println("Give a number:");
            int input = scan.nextInt();
            if(input == 0){
                break; // if the input is 0 break out of the loop
            }
            sum+=input; // add on the sum
        }

        System.out.println("Sum of the numbers: " + sum);
        scan.close(); // close the scammer
        scan = null; // set scan ready to go to the garbage collection
    }
}
