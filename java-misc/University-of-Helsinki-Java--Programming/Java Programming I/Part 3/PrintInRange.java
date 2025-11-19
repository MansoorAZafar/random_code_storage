
import java.util.ArrayList;

public class PrintInRange {

    public static void main(String[] args) {
        // Try your method here
    }

    public static void printNumbersInRange(ArrayList<Integer> list, int low, int high) {
        System.out.println("The numbers in the range [" + low + ", " + high + "]");
        for(final int val : list){
            if(val < low || val > high){
                continue;
            }
            System.out.println(val);
        }
    }
}
