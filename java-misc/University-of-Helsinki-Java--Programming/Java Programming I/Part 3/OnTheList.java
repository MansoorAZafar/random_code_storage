
import java.util.ArrayList;
import java.util.Scanner;

public class OnTheList {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String input = scan.nextLine();
            if (input.equals("")) {
                break;
            }

            list.add(input);
        }
        System.out.print("Search for? ");
        String input = scan.nextLine();

        if(list.contains(input)){
            System.out.println(input + " was found!");
        }else{
            System.out.println(input + " was not found!");
        }

        scan.close();
    }
}
