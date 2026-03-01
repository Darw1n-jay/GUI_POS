package dao;

import db.DBConnection;
import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    
    public static void addProduct(String name, String category, double price, int stock) throws Exception {
        String sql = "INSERT INTO products(name, category, price, stock) VALUES(?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setDouble(3, price);
            ps.setInt(4, stock);
            ps.executeUpdate();
        }
    }
    
    public static void updateProduct(int id, String name, String category, double price, int stock) throws Exception {
        String sql = "UPDATE products SET name=?, category=?, price=?, stock=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setDouble(3, price);
            ps.setInt(4, stock);
            ps.setInt(5, id);
            ps.executeUpdate();
        }
    }
    
    public static void deleteProduct(int id) throws Exception {
        String sql = "DELETE FROM products WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    
    public static List<Product> getAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY category, name";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Product p = new Product();
                p.id = rs.getInt("id");
                p.name = rs.getString("name");
                p.category = rs.getString("category");
                p.price = rs.getDouble("price");
                p.stock = rs.getInt("stock");
                products.add(p);
            }
        }
        return products;
    }
    
    public static Product getProductById(int id) throws Exception {
        String sql = "SELECT * FROM products WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product p = new Product();
                    p.id = rs.getInt("id");
                    p.name = rs.getString("name");
                    p.category = rs.getString("category");
                    p.price = rs.getDouble("price");
                    p.stock = rs.getInt("stock");
                    return p;
                }
            }
        }
        return null;
    }
    
    public static void updateStock(int productId, int newStock) throws Exception {
        String sql = "UPDATE products SET stock=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newStock);
            ps.setInt(2, productId);
            ps.executeUpdate();
        }
    }
    
    public static List<String> getAllCategories() throws Exception {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT category FROM products WHERE category IS NOT NULL ORDER BY category";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                categories.add(rs.getString("category"));
            }
        }
        return categories;
    }
    
    public static List<Product> getProductsByCategory(String category) throws Exception {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category=? ORDER BY name";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product();
                    p.id = rs.getInt("id");
                    p.name = rs.getString("name");
                    p.category = rs.getString("category");
                    p.price = rs.getDouble("price");
                    p.stock = rs.getInt("stock");
                    products.add(p);
                }
            }
        }
        return products;
    }
}
