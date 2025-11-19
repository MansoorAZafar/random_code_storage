import java.util.Scanner;
public class LineByLine {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] words;
        while(true){
            String input = scan.nextLine();
            if("".equals(input)){
                break;
            }
            words = input.split(" ");
            for(int i = 0; i < words.length; ++i){
                System.out.println(words[i]);
            }
        }
        scan.close();
    }
}
