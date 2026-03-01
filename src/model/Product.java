package model;

public class Product {
    public int id;
    public String name;
    public String category;
    public double price;
    public int stock;
    
    public Product() {}
    
    public Product(int id, String name, String category, double price, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }
}
