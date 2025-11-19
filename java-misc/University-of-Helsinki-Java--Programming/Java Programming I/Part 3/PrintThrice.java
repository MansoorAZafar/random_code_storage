
import java.util.Scanner;

public class PrintThrice {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Write your program here
        System.out.print("Give a word: ");
        String word = scan.nextLine();
        word += word+word;
        System.out.println(word);

        scan.close();
    }
}
