import java.util.Scanner;
public class FirstWords {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        while(true){
            input = scan.nextLine();
            if("".equals(input)){
                break;
            }
            input = input.split(" ")[0];
            System.out.println(input);
        }
        scan.close();
    }
}
