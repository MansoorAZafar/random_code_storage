import java.util.Scanner;

public class CountingToHundred {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Can assume this is always < 100
        int currentCount = scan.nextInt();
        //because countTo is never used again, modifying it doesn't matter
        while(currentCount <= 100){
            System.out.println(currentCount);
            ++currentCount;
        }

        scan.close();
        scan = null;
    }
}
