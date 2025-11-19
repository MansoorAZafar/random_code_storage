import java.util.Scanner;

public class OnlyPositives {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int number = 0;
        while(true){
            System.out.println("Give a number:");
            number = scan.nextInt();
            if(number == 0){
                break;
            }
            if(number < 0){
                System.out.println("Unsuitable number");
                continue;
            }
            System.out.println(Math.pow(number, 2));
        }
        scan.close();
        scan = null;
    }
}
