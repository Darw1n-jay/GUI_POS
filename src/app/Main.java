package app;
import test_package.LOG_IN;
import com.formdev.flatlaf.FlatLightLaf
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(new FlatLightLaf()); }
        catch (Exception e) {}

        new LOG_IN().setVisible(true);
    }
}
