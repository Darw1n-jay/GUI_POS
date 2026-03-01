package model;

public class SaleItem {
    public int id;
    public int saleId;
    public int productId;
    public int qty;
    public double price;
    
    public SaleItem() {}
    
    public SaleItem(int id, int saleId, int productId, int qty, double price) {
        this.id = id;
        this.saleId = saleId;
        this.productId = productId;
        this.qty = qty;
        this.price = price;
    }
}
