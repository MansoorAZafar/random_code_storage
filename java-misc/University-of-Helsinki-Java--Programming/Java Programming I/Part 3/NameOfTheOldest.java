import java.util.Scanner;
public class NameOfTheOldest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int oldest = Integer.MIN_VALUE;
        String oldestName = "";
        while(true){
            String input = scan.nextLine();
            if("".equals(input)){
                break;
            }
            String[] words = input.split(",");
            if(Integer.valueOf(words[1]) > oldest){
                oldestName = words[0];
                oldest = Integer.valueOf(words[1]);
            }
        }
        System.out.println("Name of the oldest: " + oldestName);
        scan.close();
    }
}
