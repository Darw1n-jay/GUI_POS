package dao;

import db.DBConnection;
import model.Sale;
import model.SaleItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDao {
    
    public static int createSale(int cashierId, double total) throws Exception {
        String sql = "INSERT INTO sales(cashier_id, total) VALUES(?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cashierId);
            ps.setDouble(2, total);
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }
    
    public static void addSaleItem(int saleId, int productId, int qty, double price) throws Exception {
        String sql = "INSERT INTO sale_items(sale_id, product_id, qty, price) VALUES(?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, saleId);
            ps.setInt(2, productId);
            ps.setInt(3, qty);
            ps.setDouble(4, price);
            ps.executeUpdate();
        }
    }
    
    public static List<Sale> getAllSales() throws Exception {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sales ORDER BY sale_date DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Sale s = new Sale();
                s.id = rs.getInt("id");
                s.cashierId = rs.getInt("cashier_id");
                s.total = rs.getDouble("total");
                s.saleDate = rs.getTimestamp("sale_date");
                sales.add(s);
            }
        }
        return sales;
    }
    
    public static List<Sale> getSalesByCashier(int cashierId) throws Exception {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sales WHERE cashier_id=? ORDER BY sale_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cashierId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sale s = new Sale();
                    s.id = rs.getInt("id");
                    s.cashierId = rs.getInt("cashier_id");
                    s.total = rs.getDouble("total");
                    s.saleDate = rs.getTimestamp("sale_date");
                    sales.add(s);
                }
            }
        }
        return sales;
    }
    
    public static List<SaleItem> getSaleItems(int saleId) throws Exception {
        List<SaleItem> items = new ArrayList<>();
        String sql = "SELECT * FROM sale_items WHERE sale_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, saleId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SaleItem item = new SaleItem();
                    item.id = rs.getInt("id");
                    item.saleId = rs.getInt("sale_id");
                    item.productId = rs.getInt("product_id");
                    item.qty = rs.getInt("qty");
                    item.price = rs.getDouble("price");
                    items.add(item);
                }
            }
        }
        return items;
    }
}
