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
        String sql = "SELECT * FROM users WHERE username=? AND password=? AND approved=1 AND is_archived=0";
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
        String sql = "SELECT * FROM users WHERE approved=0 AND is_archived=0";
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
        String sql = "SELECT * FROM users WHERE is_archived=0";
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
        try (Connection conn = DBConnection.getConnection()) {
            String archive = "INSERT OR IGNORE INTO archived_users(id, username, password, role, approved) "
                           + "SELECT id, username, password, role, approved FROM users WHERE id=?";
            try (PreparedStatement ps = conn.prepareStatement(archive)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id=?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
    }

    public static List<User> getArchivedUsers() throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM archived_users ORDER BY archived_at DESC";
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

    public static void restoreUser(int id) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            String restore = "INSERT OR IGNORE INTO users(id, username, password, role, approved) "
                           + "SELECT id, username, password, role, approved FROM archived_users WHERE id=?";
            try (PreparedStatement ps = conn.prepareStatement(restore)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM archived_users WHERE id=?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
    }
}
