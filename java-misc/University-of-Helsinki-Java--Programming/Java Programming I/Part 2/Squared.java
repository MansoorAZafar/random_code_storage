import java.util.Scanner;

public class Squared {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int value = Integer.valueOf(scan.nextLine());
        System.out.println(value*value);

        scan.close();
        scan = null;
    }
}
