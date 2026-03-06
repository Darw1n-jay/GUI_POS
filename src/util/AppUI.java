package util;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

public final class AppUI {
    private static volatile boolean initialized = false;

    private AppUI() {}

    public static void initLookAndFeelOnce() {
        if (initialized) return;
        synchronized (AppUI.class) {
            if (initialized) return;

            try {
                // Prefer a consistent modern look across Windows/Linux.
                FlatLightLaf.setup();
            } catch (Exception ignored) {
                // fall back to system L&F
            }

            // Gentle modern defaults (FlatLaf reads these keys)
            UIManager.put("Component.arc", 12);
            UIManager.put("Button.arc", 12);
            UIManager.put("TextComponent.arc", 12);
            UIManager.put("ScrollBar.thumbArc", 999);
            UIManager.put("ScrollBar.thumbInsets", new java.awt.Insets(2, 2, 2, 2));
            UIManager.put("Component.focusWidth", 1);
            UIManager.put("Component.innerFocusWidth", 0);

            // Slightly nicer default font, if available
            Font f = UIManager.getFont("defaultFont");
            if (f != null) {
                UIManager.put("defaultFont", f.deriveFont(Font.PLAIN, Math.max(13f, f.getSize2D())));
            }

            initialized = true;
        }
    }

    public static void setupFrame(JFrame frame, String title, boolean fixedSize) {
        initLookAndFeelOnce();
        if (title != null && !title.isEmpty()) frame.setTitle(title);
        frame.setLocationRelativeTo(null);
        frame.setResizable(!fixedSize);
    }

    public static void setPlaceholder(JTextField field, String placeholder) {
        // FlatLaf client property (safe even if L&F changes)
        field.putClientProperty("JTextField.placeholderText", placeholder);
        field.putClientProperty("JTextField.showClearButton", true);
    }

    public static void setPasswordFieldFeatures(JPasswordField field, String placeholder) {
        field.putClientProperty("JTextField.placeholderText", placeholder);
        // FlatLaf: built-in reveal button for password fields
        field.putClientProperty("JPasswordField.showRevealButton", true);
    }

    public static void makePrimary(AbstractButton button) {
        // FlatLaf: emphasize default action
        button.putClientProperty("JButton.buttonType", "default");
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void makeSecondary(AbstractButton button) {
        button.putClientProperty("JButton.buttonType", "toolBarButton");
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void setupDefaultButton(JFrame frame, JButton button) {
        SwingUtilities.invokeLater(() -> frame.getRootPane().setDefaultButton(button));
    }

    public static void styleTable(JTable table) {
        table.setRowHeight(Math.max(24, table.getRowHeight()));
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = table.getTableHeader();
        if (header != null) header.setReorderingAllowed(false);
    }
}

