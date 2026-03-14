package dao;

import db.DBConnection;
import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    // Resolve category name to id, inserting if new
    private static int getOrCreateCategoryId(Connection conn, String category) throws SQLException {
        if (category == null || category.trim().isEmpty()) return 0;
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT OR IGNORE INTO categories(name) VALUES(?)")) {
            ps.setString(1, category);
            ps.executeUpdate();
        }
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT id FROM categories WHERE name=?")) {
            ps.setString(1, category);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    private static Product mapProduct(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.id = rs.getInt("id");
        p.name = rs.getString("name");
        p.category = rs.getString("category_name");
        p.price = rs.getDouble("price");
        p.stock = rs.getInt("stock");
        try { p.isAvailable = rs.getInt("is_available") == 1; } catch (SQLException e) { p.isAvailable = true; }
        return p;
    }

    private static final String SELECT_SQL =
        "SELECT p.*, c.name AS category_name FROM products p "
        + "LEFT JOIN categories c ON p.category_id = c.id "
        + "WHERE p.is_archived = 0";

    public static void addProduct(String name, String category, double price, int stock) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            int catId = getOrCreateCategoryId(conn, category);
            String sql = "INSERT INTO products(name, category_id, price, stock) VALUES(?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setInt(2, catId);
                ps.setDouble(3, price);
                ps.setInt(4, stock);
                ps.executeUpdate();
            }
        }
    }

    public static void updateProduct(int id, String name, String category, double price, int stock) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            int catId = getOrCreateCategoryId(conn, category);
            String sql = "UPDATE products SET name=?, category_id=?, price=?, stock=? WHERE id=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setInt(2, catId);
                ps.setDouble(3, price);
                ps.setInt(4, stock);
                ps.setInt(5, id);
                ps.executeUpdate();
            }
        }
    }

    public static void deleteProduct(int id) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            // Copy to archived_products
            String archive = "INSERT OR IGNORE INTO archived_products(id, name, category_id, price, stock) "
                           + "SELECT id, name, category_id, price, stock FROM products WHERE id=?";
            try (PreparedStatement ps = conn.prepareStatement(archive)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            // Remove from active products
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE id=?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
    }

    public static List<Product> getAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        String sql = SELECT_SQL + " ORDER BY c.name, p.name";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) products.add(mapProduct(rs));
        }
        return products;
    }

    public static Product getProductById(int id) throws Exception {
        String sql = SELECT_SQL + " AND p.id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapProduct(rs);
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
        String sql = "SELECT name FROM categories ORDER BY name";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) categories.add(rs.getString("name"));
        }
        return categories;
    }

    public static List<Product> getProductsByCategory(String category) throws Exception {
        List<Product> products = new ArrayList<>();
        String sql = SELECT_SQL + " AND c.name=? ORDER BY p.name";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) products.add(mapProduct(rs));
            }
        }
        return products;
    }

    public static List<Product> getArchivedProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT ap.*, c.name AS category_name FROM archived_products ap "
                   + "LEFT JOIN categories c ON ap.category_id = c.id "
                   + "ORDER BY ap.archived_at DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product p = new Product();
                p.id = rs.getInt("id");
                p.name = rs.getString("name");
                p.category = rs.getString("category_name");
                p.price = rs.getDouble("price");
                p.stock = rs.getInt("stock");
                products.add(p);
            }
        }
        return products;
    }

    public static void toggleAvailability(int id) throws Exception {
        String sql = "UPDATE products SET is_available = CASE WHEN is_available=1 THEN 0 ELSE 1 END WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static List<Product> getAvailableProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        String sql = SELECT_SQL + " AND (p.is_available IS NULL OR p.is_available=1) ORDER BY c.name, p.name";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) products.add(mapProduct(rs));
        }
        return products;
    }

    public static void restoreProduct(int id) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            String restore = "INSERT OR IGNORE INTO products(id, name, category_id, price, stock) "
                           + "SELECT id, name, category_id, price, stock FROM archived_products WHERE id=?";
            try (PreparedStatement ps = conn.prepareStatement(restore)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM archived_products WHERE id=?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
    }
}
