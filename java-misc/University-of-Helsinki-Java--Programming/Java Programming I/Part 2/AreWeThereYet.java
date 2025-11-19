import java.util.Scanner;

public class AreWeThereYet {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Give a number:");
        while((Integer.valueOf(scan.nextLine())) != 4){
            System.out.println("Give a number:");
        }
        scan.close();
        scan = null;
    }
}
