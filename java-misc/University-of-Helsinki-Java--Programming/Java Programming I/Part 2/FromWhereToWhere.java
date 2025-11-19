import java.util.Scanner;

public class FromWhereToWhere {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Write your program here
        System.out.print("Where to? ");
        int whereTo = scan.nextInt();
        System.out.print("Where from? ");
        int whereFrom = scan.nextInt();

        for(; whereFrom <= whereTo; ++whereFrom){
            System.out.println(whereFrom);
        }

        scan.close();
        scan = null;
    }
}
