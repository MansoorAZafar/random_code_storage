//Cannot use Math.abs();
import java.util.Scanner;

public class AbsoluteValue {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int input = Integer.valueOf(scan.nextLine());
        if(input < 0) {
            System.out.println((input * (-1)));
        } else {
            System.out.println(input);
        }
        scan.close();
        scan = null;
    }
}
