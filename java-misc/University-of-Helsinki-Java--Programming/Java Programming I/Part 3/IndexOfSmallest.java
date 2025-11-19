
import java.util.ArrayList;
import java.util.Scanner;

public class IndexOfSmallest {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // implement here a program that reads user input
        // until the user enters 9999
        ArrayList<Integer> list = new ArrayList<>();
        while(true){
            int input = scan.nextInt();
            if(input == 9999){
                break;
            }
            list.add(input);
        }

        int smallest = list.get(0);
        for(int i = 1; i < list.size(); ++i){
            smallest = smallest < list.get(i) ? smallest : list.get(i);
        }

        // after that, the program prints the smallest number
        // and its index -- the smallest number
        // might appear multiple times
        System.out.println("Smallest number: " + smallest);
        for(int i = 0; i < list.size(); ++i){
            if(list.get(i) == smallest){
                System.out.println("Found at index: " + i);
            }
        }
        
        scan.close();
    }   
}
