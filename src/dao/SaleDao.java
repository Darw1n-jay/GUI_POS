package dao;

import db.DBConnection;
import model.Sale;
import model.SaleItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SaleDao {

    // Safely parse sale_date from SQLite (stored as text)
    private static Date parseSaleDate(ResultSet rs) {
        String[] formats = {
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss.SSS",
            "yyyy-MM-dd"
        };
        try {
            String raw = rs.getString("sale_date");
            if (raw == null || raw.isEmpty()) return new Date();
            for (String fmt : formats) {
                try { return new SimpleDateFormat(fmt).parse(raw); } catch (Exception ignored) {}
            }
        } catch (Exception ignored) {}
        return new Date();
    }

    public static int createSaleWithItems(int cashierId, double total, List<SaleItemData> items) throws Exception {
        final int[] saleId = {-1};

        DBConnection.executeTransaction(conn -> {
            String saleSql = "INSERT INTO sales(cashier_id, total) VALUES(?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(saleSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, cashierId);
                ps.setDouble(2, total);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) saleId[0] = rs.getInt(1);
                }
            }

            String itemSql = "INSERT INTO sale_items(sale_id, product_id, qty, price) VALUES(?, ?, ?, ?)";
            String stockSql = "UPDATE products SET stock = stock - ? WHERE id = ?";
            try (PreparedStatement itemPs = conn.prepareStatement(itemSql);
                 PreparedStatement stockPs = conn.prepareStatement(stockSql)) {
                for (SaleItemData item : items) {
                    itemPs.setInt(1, saleId[0]);
                    itemPs.setInt(2, item.productId);
                    itemPs.setInt(3, item.qty);
                    itemPs.setDouble(4, item.price);
                    itemPs.executeUpdate();
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
        String sql = "INSERT INTO sales(cashier_id, total, payment_method_id) "
                   + "VALUES(?, ?, (SELECT id FROM payment_methods WHERE name=?))";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cashierId);
            ps.setDouble(2, total);
            ps.setString(3, paymentMethod);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

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
        String sql = "SELECT s.*, pm.name AS payment_method_name FROM sales s "
                   + "LEFT JOIN payment_methods pm ON s.payment_method_id = pm.id "
                   + "ORDER BY s.sale_date DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Sale s = new Sale();
                s.id = rs.getInt("id");
                s.cashierId = rs.getInt("cashier_id");
                s.total = rs.getDouble("total");
                s.saleDate = parseSaleDate(rs);
                s.paymentMethod = rs.getString("payment_method_name");
                if (s.paymentMethod == null) s.paymentMethod = "Cash";
                sales.add(s);
            }
        }
        return sales;
    }

    public static List<Sale> getSalesByCashier(int cashierId) throws Exception {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT s.*, pm.name AS payment_method_name FROM sales s "
                   + "LEFT JOIN payment_methods pm ON s.payment_method_id = pm.id "
                   + "WHERE s.cashier_id=? ORDER BY s.sale_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cashierId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sale s = new Sale();
                    s.id = rs.getInt("id");
                    s.cashierId = rs.getInt("cashier_id");
                    s.total = rs.getDouble("total");
                    s.saleDate = parseSaleDate(rs);
                    s.paymentMethod = rs.getString("payment_method_name");
                    if (s.paymentMethod == null) s.paymentMethod = "Cash";
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
        String sql = "SELECT s.*, pm.name AS payment_method_name FROM sales s "
                   + "LEFT JOIN payment_methods pm ON s.payment_method_id = pm.id "
                   + "WHERE s.id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, saleId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Sale s = new Sale();
                    s.id = rs.getInt("id");
                    s.cashierId = rs.getInt("cashier_id");
                    s.total = rs.getDouble("total");
                    s.saleDate = parseSaleDate(rs);
                    s.paymentMethod = rs.getString("payment_method_name");
                    if (s.paymentMethod == null) s.paymentMethod = "Cash";
                    return s;
                }
            }
        }
        return null;
    }
}
