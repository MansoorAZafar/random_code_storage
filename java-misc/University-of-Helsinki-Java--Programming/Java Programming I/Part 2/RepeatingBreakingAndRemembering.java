import java.util.Scanner;

public class RepeatingBreakingAndRemembering {

    public static void main(String[] args) {
        
        // This exercise is worth five exercise points, and it is 
        // gradually extended part by part.
        
        // If you want, you can send this exercise to the server
        // when it's just partially done. In that case the server will complain about 
        // the parts you haven't done, but you'll get points for the finished parts.
        
        Scanner scan = new Scanner(System.in);

        int sum = 0, numbers = 0, odd = 0, even = 0;
        double average = 0.0;

        System.out.println("Give numbers:");
        while(true){
            int input = scan.nextInt();
            
            if(input == -1){
                break;
            }

            if(input % 2 == 0){
                ++even;
            } else {
                ++odd;
            }

            sum+= input;
            ++numbers;
        }

        average = sum / (double)numbers;

        System.out.println("Thx! Bye!");
        System.out.println("Sum: " + sum);
        System.out.println("Numbers: " + numbers);
        System.out.println("Average: " + average);
        System.out.println("Even: " + even);
        System.out.println("Odd: " + odd);

        scan.close();
        scan = null;

    }
}
