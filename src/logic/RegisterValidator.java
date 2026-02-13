package logic;

import java.sql.*;
import db.DBConnection; // your existing DBConnection class

public class RegisterValidator {

    // Register a cashier account (pending approval)
    public static String registerCashier(String username, String password) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            if (usernameExists(username)) {
                return "Username already exists!";
            }

            conn = DBConnection.getConnection();
            String sql = "INSERT INTO users (username, password, role, approved) VALUES (?, ?, 'CASHIER', 0)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password); // For production, hash passwords!

            int inserted = pst.executeUpdate();
            if (inserted > 0) {
                return "Cashier registered successfully. Awaiting admin approval.";
            } else {
                return "Registration failed.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } finally {
            try { if (pst != null) pst.close(); } catch (Exception ex) {}
            try { if (conn != null) conn.close(); } catch (Exception ex) {}
        }
    }

    // Check if username exists
    private static boolean usernameExists(String username) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT id FROM users WHERE username=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            return rs.next();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ex) {}
            try { if (pst != null) pst.close(); } catch (Exception ex) {}
            try { if (conn != null) conn.close(); } catch (Exception ex) {}
        }
    }
}
