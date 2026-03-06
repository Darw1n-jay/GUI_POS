package dao;

import db.DBConnection;
import model.Sale;
import model.SaleItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDao {
    
    public static int createSaleWithItems(int cashierId, double total, List<SaleItemData> items) throws Exception {
        final int[] saleId = {-1};
        
        DBConnection.executeTransaction(conn -> {
            // Create the sale
            String saleSql = "INSERT INTO sales(cashier_id, total) VALUES(?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(saleSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, cashierId);
                ps.setDouble(2, total);
                ps.executeUpdate();
                
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        saleId[0] = rs.getInt(1);
                    }
                }
            }
            
            // Add sale items and update stock
            String itemSql = "INSERT INTO sale_items(sale_id, product_id, qty, price) VALUES(?, ?, ?, ?)";
            String stockSql = "UPDATE products SET stock = stock - ? WHERE id = ?";
            
            try (PreparedStatement itemPs = conn.prepareStatement(itemSql);
                 PreparedStatement stockPs = conn.prepareStatement(stockSql)) {
                
                for (SaleItemData item : items) {
                    // Add sale item
                    itemPs.setInt(1, saleId[0]);
                    itemPs.setInt(2, item.productId);
                    itemPs.setInt(3, item.qty);
                    itemPs.setDouble(4, item.price);
                    itemPs.executeUpdate();
                    
                    // Update stock
                    stockPs.setInt(1, item.qty);
                    stockPs.setInt(2, item.productId);
                    stockPs.executeUpdate();
                }
            }
        });
        
        return saleId[0];
    }
    
    public static class SaleItemData {
        public int productId;
        public int qty;
        public double price;
        
        public SaleItemData(int productId, int qty, double price) {
            this.productId = productId;
            this.qty = qty;
            this.price = price;
        }
    }
    
    public static int createSale(int cashierId, double total, String paymentMethod) throws Exception {
        String sql = "INSERT INTO sales(cashier_id, total, payment_method) VALUES(?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cashierId);
            ps.setDouble(2, total);
            ps.setString(3, paymentMethod);
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }
    
    // Keep original method for backward compatibility
    public static int createSale(int cashierId, double total) throws Exception {
        return createSale(cashierId, total, "Cash");
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
                
                // Handle payment_method column that might not exist in older databases
                try {
                    s.paymentMethod = rs.getString("payment_method");
                    if (s.paymentMethod == null) s.paymentMethod = "Cash";
                } catch (SQLException e) {
                    // Column doesn't exist, use default
                    s.paymentMethod = "Cash";
                }
                
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
                    
                    // Handle payment_method column that might not exist in older databases
                    try {
                        s.paymentMethod = rs.getString("payment_method");
                        if (s.paymentMethod == null) s.paymentMethod = "Cash";
                    } catch (SQLException e) {
                        // Column doesn't exist, use default
                        s.paymentMethod = "Cash";
                    }
                    
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
    
    public static Sale getSaleById(int saleId) throws Exception {
        String sql = "SELECT * FROM sales WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, saleId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Sale s = new Sale();
                    s.id = rs.getInt("id");
                    s.cashierId = rs.getInt("cashier_id");
                    s.total = rs.getDouble("total");
                    s.saleDate = rs.getTimestamp("sale_date");
                    
                    // Handle payment_method column that might not exist in older databases
                    try {
                        s.paymentMethod = rs.getString("payment_method");
                        if (s.paymentMethod == null) s.paymentMethod = "Cash";
                    } catch (SQLException e) {
                        // Column doesn't exist, use default
                        s.paymentMethod = "Cash";
                    }
                    
                    return s;
                }
            }
        }
        return null;
    }
}
