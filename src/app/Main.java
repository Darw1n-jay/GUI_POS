package app;
import test_package.LOG_IN;
import db.DBConnection;
import util.AppUI;

public class Main {
    public static void main(String[] args) {
        // Initialize database
        DBConnection.initialize();
        
        // Set look and feel (FlatLaf)
        AppUI.initLookAndFeelOnce();

        // Launch login screen
        java.awt.EventQueue.invokeLater(() -> {
            new LOG_IN().setVisible(true);
        });
    }
}
