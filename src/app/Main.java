package app;
import test_package.LOG_IN;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import db.DBConnection;

public class Main {
    public static void main(String[] args) {
        // Initialize database
        DBConnection.initialize();
        
        // Set look and feel
        try { 
            UIManager.setLookAndFeel(new FlatLightLaf()); 
        } catch (Exception e) {
            System.err.println("Failed to set look and feel: " + e.getMessage());
        }

        // Launch login screen
        java.awt.EventQueue.invokeLater(() -> {
            new LOG_IN().setVisible(true);
        });
    }
}
