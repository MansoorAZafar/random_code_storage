import java.util.Scanner;

public class CarryOn {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while(true){
            System.out.println("Shall we carry on?");
            String carryOn = scan.nextLine();
            if("no".equals(carryOn)){
                break;
            }
        }
        scan.close();
        scan = null;
    }
}
