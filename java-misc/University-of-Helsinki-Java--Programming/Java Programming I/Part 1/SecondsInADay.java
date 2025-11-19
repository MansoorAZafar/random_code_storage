
import java.util.Scanner;

public class SecondsInADay {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Write your program here
        System.out.println("How many days would you like to convert to seconds?");
        int days = Integer.valueOf(scan.nextLine());
        final int DAY_TO_HRS = 24;
        final int HRS_TO_MIN = 60;
        final int MIN_TO_SEC = 60;
        int seconds = days * DAY_TO_HRS * HRS_TO_MIN * MIN_TO_SEC;
        System.out.println(seconds);
    }
}
