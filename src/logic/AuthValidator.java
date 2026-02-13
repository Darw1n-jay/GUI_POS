package logic;

import java.sql.*;
import db.DBConnection;
import pos.model.Models.User;

public class AuthValidator {

    public static LoginResult login(String username, String password) {
        try {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

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
            return new LoginResult(false, "Database error", null);
        }
    }
}
