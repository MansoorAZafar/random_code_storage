import java.util.Scanner;
public class AgeOfTheOldest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        String[] words;
        int oldest = Integer.MIN_VALUE;
        while(true){
            input = scan.nextLine();
            if("".equals(input)){
                break;
            }
            words = input.split(",");
            oldest = oldest > Integer.valueOf(words[1]) ? oldest : Integer.valueOf(words[1]);
        }
        System.out.println("Age of the oldest: " + oldest);
        scan.close();
    }
}
