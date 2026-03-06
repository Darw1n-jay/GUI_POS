package util;

import javax.swing.*;
import java.awt.*;

public class ButtonStyler {
    
    // Standard button colors
    public static final Color BUTTON_BG = new Color(0, 0, 0);
    public static final Color BUTTON_FG = new Color(255, 255, 255);
    public static final Color BUTTON_HOVER_BG = new Color(50, 50, 50);
    public static final Font BUTTON_FONT = new Font("Sitka Display", Font.BOLD, 14);
    
    /**
     * Apply standard styling to a button
     */
    public static void styleButton(JButton button) {
        button.setBackground(BUTTON_BG);
        button.setForeground(BUTTON_FG);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setOpaque(true);
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_BG);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_BG);
            }
        });
    }
    
    /**
     * Create a styled button with text
     */
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        styleButton(button);
        return button;
    }
    
    /**
     * Create a styled button with text and preferred size
     */
    public static JButton createStyledButton(String text, int width, int height) {
        JButton button = createStyledButton(text);
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }
}