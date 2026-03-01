package dao;

import db.DBConnection;
import java.sql.*;
import model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static void register(String u, String p) throws Exception {
        String sql = "INSERT INTO users(username,password,role,approved) VALUES(?,?, 'CASHIER', false)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u);
            ps.setString(2, p);
            ps.executeUpdate();
        }
    }

    public static User login(String u, String p) throws Exception {
        String sql = "SELECT * FROM users WHERE username=? AND password=? AND approved=true";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u);
            ps.setString(2, p);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                User user = new User();
                user.id = rs.getInt("id");
                user.username = rs.getString("username");
                user.role = rs.getString("role");
                return user;
            }
        }
    }

    public static void approve(int id) throws Exception {
        String sql = "UPDATE users SET approved=true WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    
    public static List<User> getPendingUsers() throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE approved=false";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                User u = new User();
                u.id = rs.getInt("id");
                u.username = rs.getString("username");
                u.role = rs.getString("role");
                users.add(u);
            }
        }
        return users;
    }
    
    public static List<User> getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                User u = new User();
                u.id = rs.getInt("id");
                u.username = rs.getString("username");
                u.role = rs.getString("role");
                users.add(u);
            }
        }
        return users;
    }
    
    public static void deleteUser(int id) throws Exception {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
