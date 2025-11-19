
import java.util.Scanner;
public class Login {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        final String[] users = {"emma", "alex"};
        final String[] pass  = {"haskell", "sunshine"};
        System.out.print("Enter username: ");
        String username = scan.nextLine();
        System.out.print("Enter password: ");
        String password = scan.nextLine();

        if((users[0].equals(username) && pass[0].equals(password)) || (users[1].equals(username) && pass[1].equals(password)) ){
            System.out.println("You have successfully logged in!");
        }else{
            System.out.println("Incorrect username or password!");
        }
        scan.close();
    }
}
