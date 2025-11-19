import java.util.Scanner;

public class Same {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Write your program here. 
        System.out.println("Enter the first string:");
        String txt1 = scan.nextLine();
        String txt2 = scan.nextLine();

        if(txt1.equals(txt2)){
            System.out.println("Same");
        } else {
            System.out.println("Different");
        }

        scan.close();
    }
}
