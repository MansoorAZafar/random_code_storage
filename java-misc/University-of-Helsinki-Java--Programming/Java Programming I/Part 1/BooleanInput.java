
import java.util.Scanner;

public class BooleanInput {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // write your program here
        System.out.println("Write something:");
        boolean flag = Boolean.valueOf(scan.nextLine());
        System.out.println("True or false? " + flag);
    }
}
