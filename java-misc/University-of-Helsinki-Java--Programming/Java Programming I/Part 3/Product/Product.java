public class Product
{
    private double price;
    private int quantity;
    private String  name;
    
    public Product(String name, double price, int qty)
    {
        this.name = name;
        this.price = price;
        this.quantity = qty;
    }

    public void printProduct()
    {
        System.out.println(this.name + ", price " + this.price + ", " + this.quantity + " pcs");
    }
}
