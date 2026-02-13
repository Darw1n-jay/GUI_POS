package logic;

import pos.model.Models.User;

public class LoginResult {
    public boolean success;
    public String message;
    public User user;

    public LoginResult(boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }
}
