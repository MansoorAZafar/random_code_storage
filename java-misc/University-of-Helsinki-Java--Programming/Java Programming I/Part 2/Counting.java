import java.util.Scanner;

public class Counting {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int countTo = scan.nextInt();
        for(int currentCount = 0; currentCount <= countTo; ++currentCount){
            System.out.println(currentCount);
        }
        scan.close();
        scan = null;
    }
}
