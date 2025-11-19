import java.util.Scanner;
public class AVClub {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] words;
        String input;
        while(true){
            input = scan.nextLine();
            if("".equals(input)){
                break;
            }
            words = input.split(" ");
            for(int i = 0; i < words.length; ++i){
                if(words[i].contains("av")){
                    System.out.println(words[i]);
                }
            }
        }
        scan.close();
    }
}
