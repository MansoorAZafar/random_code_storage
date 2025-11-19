import java.util.Scanner;

public class GiftTax {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int taxLowerLimit[] = {100, 1700, 4700, 22100, 142100};
        int minValue[] = {5000, 25000, 55000, 200_000, 1_000_000};
        int taxRate[] = {8,10,12,15,17};
        
        int taxIndex = 0;
        double tax = 0;
        //Formula: (tax_lower_limit + ([value] - min_value) * % of tax_rate)

        System.out.println("Value of the gift?");
        int value = Integer.valueOf(scan.nextLine());

        if(value < 5_000){
            System.out.println("No tax!");
        } else if (value < 25_000) {
            taxIndex = 0;
            tax = taxLowerLimit[taxIndex] + (value - minValue[taxIndex]) * (taxRate[taxIndex] / 100.0);
            System.out.println("Tax: " + tax);
        } else if (value < 55_000) {
            taxIndex = 1;
            tax = taxLowerLimit[taxIndex] + (value - minValue[taxIndex]) * (taxRate[taxIndex] / 100.0);
            System.out.println("Tax: " + tax);
        } else if (value < 200_000) {
            taxIndex = 2;
            tax = taxLowerLimit[taxIndex] + (value - minValue[taxIndex]) * (taxRate[taxIndex] / 100.0);
            System.out.println("Tax: " + tax);
        } else if (value < 1_000_000) {
            taxIndex = 3;
            tax = taxLowerLimit[taxIndex] + (value - minValue[taxIndex]) * (taxRate[taxIndex] / 100.0);
            System.out.println("Tax: " + tax);
        } else {
            taxIndex = 4;
            tax = taxLowerLimit[taxIndex] + (value - minValue[taxIndex]) * (taxRate[taxIndex] / 100.0);
            System.out.println("Tax: " + tax);
        }

        scan.close();
    }
}
