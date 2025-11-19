import java.util.Scanner;

public class SquareRootOfSum {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int value1 = Integer.valueOf(scan.nextLine());
        int value2 = Integer.valueOf(scan.nextLine());

        int sum = value1 + value2;
        System.out.println(Math.sqrt(sum));

    }
}
