package logic;

import java.sql.*;
import db.DBConnection;

public class AuthValidator {

    public static LoginResult login(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (!rs.next()) {
                return new LoginResult(false, "Invalid credentials", null);
            }

            if (!rs.getBoolean("approved")) {
                return new LoginResult(false, "Account not approved yet", null);
            }

            User user = new User(
                rs.getInt("id"),
                rs.getString("username"),
                User.Role.valueOf(rs.getString("role"))
            );

            return new LoginResult(true, "Success", user);

        } catch (Exception e) {
            e.printStackTrace();
            return new LoginResult(false, "Database error: " + e.getMessage(), null);
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ex) {}
            try { if (ps != null) ps.close(); } catch (Exception ex) {}
            try { if (conn != null) conn.close(); } catch (Exception ex) {}
        }
    }
}
