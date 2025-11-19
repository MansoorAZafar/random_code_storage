import java.util.Scanner;
public class Story {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Write your program here
        System.out.println("I will tell you a story, but I need some information first." + 
        "\nWhat is the main character called?");
        String mcName = scan.nextLine();
        System.out.println("What is their job?");
        String job = scan.nextLine();
        System.out.println("Here is the story:\n" + 
        "Once upon a time there was " + mcName + ", who was " + job + 
        ".\nOn the way to work, " + mcName + " reflected on life.\n" + 
        "Perhaps " + mcName + " will not be " + job + " forever.");
    }
}
