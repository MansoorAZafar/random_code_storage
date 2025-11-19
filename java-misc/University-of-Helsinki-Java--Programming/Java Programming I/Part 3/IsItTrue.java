
import java.util.Scanner;

public class IsItTrue {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Give a string: ");
        String input = scan.nextLine();
        if("true".equals(input)){
            System.out.println("You got it right!");
        }else{
            System.out.println("Try again!");
        }
        scan.close();
    }
}
