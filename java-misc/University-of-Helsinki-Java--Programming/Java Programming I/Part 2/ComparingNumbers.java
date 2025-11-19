
import java.util.Scanner;

public class ComparingNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int first = Integer.valueOf(scan.nextLine());
        int second = Integer.valueOf(scan.nextLine());

        if(first > second){
            System.out.println(first + " is greater than " + second + ".");
        } else if (second > first) {
            System.out.println(first + " is smaller than " + second + ".");
        } else {
            System.out.println(first + " is equal to " + second + ".");
        }
        scan.close();
        scan = null;
    }
}
