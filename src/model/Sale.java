package model;

import java.util.Date;

public class Sale {
    public int id;
    public int cashierId;
    public double total;
    public Date saleDate;
    public String paymentMethod;

    public Sale() {}

    public Sale(int id, int cashierId, double total, Date saleDate) {
        this.id = id;
        this.cashierId = cashierId;
        this.total = total;
        this.saleDate = saleDate;
    }

    public Sale(int id, int cashierId, double total, Date saleDate, String paymentMethod) {
        this.id = id;
        this.cashierId = cashierId;
        this.total = total;
        this.saleDate = saleDate;
        this.paymentMethod = paymentMethod;
    }
}
