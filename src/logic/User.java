package pos.model.Models;

public class User {
    public int id;
    public String username;
    public Role role;

    public enum Role {
        ADMIN, CASHIER
    }

    public User(int id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }
}
