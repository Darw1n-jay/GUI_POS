package dao;
import db.DBConnection;
import java.sql.*;
import model.User;

public class UserDAO {

    public static void register(String u, String p) throws Exception {
        String sql =
          "INSERT INTO users(username,password,role,approved) VALUES(?,?, 'CASHIER', false)";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, u);
        ps.setString(2, p);
        ps.executeUpdate();
    }

    public static User login(String u, String p) throws Exception {
        String sql =
          "SELECT * FROM users WHERE username=? AND password=? AND approved=true";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, u);
        ps.setString(2, p);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) return null;

        User user = new User();
        user.id = rs.getInt("id");
        user.username = rs.getString("username");
        user.role = rs.getString("role");
        return user;
    }

    public static void approve(int id) throws Exception {
        PreparedStatement ps =
            DBConnection.getConnection().prepareStatement(
                "UPDATE users SET approved=true WHERE id=?"
            );
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
