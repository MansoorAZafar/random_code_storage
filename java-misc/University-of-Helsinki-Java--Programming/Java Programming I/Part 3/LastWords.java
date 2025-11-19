import java.util.Scanner;
public class LastWords {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        String[] words;
        while(true){
            input = scan.nextLine();
            if("".equals(input)){
                break;
            }
            words = input.split(" ");
            input = words[words.length - 1];
            words = null;
            System.out.println(input);
        }
        scan.close();
    }
}
