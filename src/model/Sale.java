package model;

import java.sql.Timestamp;

public class Sale {
    public int id;
    public int cashierId;
    public double total;
    public Timestamp saleDate;
    public String paymentMethod;
    
    public Sale() {}
    
    public Sale(int id, int cashierId, double total, Timestamp saleDate) {
        this.id = id;
        this.cashierId = cashierId;
        this.total = total;
        this.saleDate = saleDate;
    }
    
    public Sale(int id, int cashierId, double total, Timestamp saleDate, String paymentMethod) {
        this.id = id;
        this.cashierId = cashierId;
        this.total = total;
        this.saleDate = saleDate;
        this.paymentMethod = paymentMethod;
    }
}
