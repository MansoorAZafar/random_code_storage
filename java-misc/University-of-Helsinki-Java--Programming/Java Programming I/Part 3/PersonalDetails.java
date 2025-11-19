
import java.util.ArrayList;
import java.util.Scanner;

public class PersonalDetails {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double avgBirth = 0.0;
        int longestName = 0;
        String name = "";
        int total = 0;
        while(true)
        {
            String input = scan.nextLine();
            if("".equals(input)){
                break;
            }
            String[] words = input.split(",");
            ++total;
            avgBirth += Integer.valueOf(words[1]); 
            if(longestName < words[0].length()){
                longestName = words[0].length();
                name = words[0];
            }
        }
        avgBirth /= total;
        System.out.println("Longest name: " + name);
        System.out.println("Average of the birth years: " + avgBirth);
        scan.close();
    }
}
