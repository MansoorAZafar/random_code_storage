
import java.util.ArrayList;
import java.util.Scanner;

public class AverageOfAList {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // implement here a program, that first reads user input
        // adding them on a list until user gives -1.
        // Then it computes the average of the numbers on the list
        // and prints it.
        ArrayList<Double> list = new ArrayList<>();
        double avg = 0;
        while(true){
            double input = scan.nextDouble();
            if(input == -1){
                break;
            }
            avg+= input;
            list.add(input);
        }
        System.out.println("Average: " + (avg / list.size() * 1.0));
        scan.close();
        
    }
}
