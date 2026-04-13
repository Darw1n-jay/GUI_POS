package util;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

public final class AppUI {
    private static volatile boolean initialized = false;

    // Brand colors
    public static final Color HEADER_BG_LEFT  = new Color(20, 12, 8);
    public static final Color HEADER_BG_RIGHT = new Color(60, 35, 15);
    public static final Color HEADER_ACCENT   = new Color(180, 120, 50);
    public static final Color HEADER_TEXT     = new Color(255, 255, 255);
    public static final Color HEADER_SUB_TEXT = new Color(210, 175, 120);

    private AppUI() {}

    // ── Look & Feel ──────────────────────────────────────────────────────────

    public static void initLookAndFeelOnce() {
        if (initialized) return;
        synchronized (AppUI.class) {
            if (initialized) return;
            try { FlatLightLaf.setup(); } catch (Exception ignored) {}
            UIManager.put("Component.arc", 12);
            UIManager.put("Button.arc", 12);
            UIManager.put("TextComponent.arc", 12);
            UIManager.put("ScrollBar.thumbArc", 999);
            UIManager.put("ScrollBar.thumbInsets", new java.awt.Insets(2, 2, 2, 2));
            UIManager.put("Component.focusWidth", 1);
            UIManager.put("Component.innerFocusWidth", 0);
            Font f = UIManager.getFont("defaultFont");
            if (f != null)
                UIManager.put("defaultFont", f.deriveFont(Font.PLAIN, Math.max(13f, f.getSize2D())));
            initialized = true;
        }
    }

    // ── Header ───────────────────────────────────────────────────────────────

    /**
     * Creates a branded header (950 × 60) with hover animation and
     * custom window-control buttons (minimize / maximize / close).
     *
     * @param pageTitle page label shown in the centre
     * @param username  logged-in user, or null
     * @param role      "ADMIN" / "CASHIER", or null
     * @param frame     the owning JFrame — needed for window controls
     */
    public static JPanel createHeader(String pageTitle, String username,
                                      String role, JFrame frame) {

        final float[] hoverAlpha = {0f};
        final boolean[] hovering = {false};

        // ── gradient panel ────────────────────────────────────────────────
        JPanel header = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                Color bgL = blend(HEADER_BG_LEFT,  new Color(45, 28, 10),  hoverAlpha[0]);
                Color bgR = blend(HEADER_BG_RIGHT, new Color(100, 62, 20), hoverAlpha[0]);
                g2.setPaint(new GradientPaint(0, 0, bgL, getWidth(), 0, bgR));
                g2.fillRect(0, 0, getWidth(), getHeight());
                // accent line
                Color accent = blend(HEADER_ACCENT, new Color(255, 200, 80), hoverAlpha[0]);
                int lineH = (int)(3 + hoverAlpha[0] * 2);
                g2.setColor(accent);
                g2.fillRect(0, getHeight() - lineH, getWidth(), lineH);
                // top shimmer
                if (hoverAlpha[0] > 0) {
                    g2.setColor(new Color(255, 255, 255, (int)(18 * hoverAlpha[0])));
                    g2.fillRect(0, 0, getWidth(), 2);
                }
                g2.dispose();
            }
        };
        header.setPreferredSize(new java.awt.Dimension(950, 60));
        header.setBounds(0, 0, 950, 60);
        header.setOpaque(false);

        // ── hover animation ───────────────────────────────────────────────
        Timer anim = new Timer(16, null);
        anim.addActionListener(e -> {
            float target = hovering[0] ? 1f : 0f;
            hoverAlpha[0] += (target - hoverAlpha[0]) * 0.08f;
            if (Math.abs(hoverAlpha[0] - target) < 0.005f) hoverAlpha[0] = target;
            header.repaint();
        });
        anim.start();

        MouseAdapter hoverListener = new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { hovering[0] = true; }
            @Override public void mouseExited(MouseEvent e) {
                java.awt.Point p = SwingUtilities.convertPoint(
                        e.getComponent(), e.getPoint(), header);
                if (p.x < 0 || p.y < 0
                        || p.x >= header.getWidth()
                        || p.y >= header.getHeight()) {
                    hovering[0] = false;
                }
            }
        };
        header.addMouseListener(hoverListener);

        // ── shop name (left) ──────────────────────────────────────────────
        JLabel lblShop = new JLabel("☕ BREW & GO POS");
        lblShop.setFont(new Font("Sitka Display", Font.BOLD, 15));
        lblShop.setForeground(HEADER_ACCENT);
        lblShop.setBounds(15, 8, 220, 20);
        lblShop.addMouseListener(hoverListener);
        header.add(lblShop);

        JLabel lblTagline = new JLabel("Coffee Shop Management");
        lblTagline.setFont(new Font("Sitka Display", Font.PLAIN, 10));
        lblTagline.setForeground(HEADER_SUB_TEXT);
        lblTagline.setBounds(15, 30, 220, 16);
        lblTagline.addMouseListener(hoverListener);
        header.add(lblTagline);

        // ── page title (centre, with glow on hover) ───────────────────────
        JLabel lblPage = new JLabel(
                pageTitle != null ? pageTitle.toUpperCase() : "",
                SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                if (hoverAlpha[0] > 0.05f) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    g2.setColor(new Color(255, 200, 80, (int)(60 * hoverAlpha[0])));
                    g2.setFont(getFont());
                    java.awt.FontMetrics fm = g2.getFontMetrics();
                    int tx = (getWidth()  - fm.stringWidth(getText())) / 2;
                    int ty = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                    for (int dx = -1; dx <= 1; dx++)
                        for (int dy = -1; dy <= 1; dy++)
                            g2.drawString(getText(), tx + dx, ty + dy);
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };
        lblPage.setFont(new Font("Sitka Display", Font.BOLD, 16));
        lblPage.setForeground(HEADER_TEXT);
        lblPage.setBounds(250, 12, 450, 36);
        lblPage.addMouseListener(hoverListener);
        header.add(lblPage);

        // ── user info ─────────────────────────────────────────────────────
        if (username != null) {
            String roleTag = (role != null) ? " [" + role + "]" : "";
            JLabel lblUser = new JLabel(
                    "👤 " + username.toUpperCase() + roleTag, SwingConstants.RIGHT);
            lblUser.setFont(new Font("Sitka Display", Font.BOLD, 11));
            lblUser.setForeground(HEADER_TEXT);
            lblUser.setBounds(580, 8, 200, 18);
            lblUser.addMouseListener(hoverListener);
            header.add(lblUser);
        }

        // ── live clock ────────────────────────────────────────────────────
        JLabel lblClock = new JLabel("", SwingConstants.RIGHT);
        lblClock.setFont(new Font("Sitka Display", Font.PLAIN, 10));
        lblClock.setForeground(HEADER_SUB_TEXT);
        lblClock.setBounds(580, 30, 200, 16);
        lblClock.addMouseListener(hoverListener);
        header.add(lblClock);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd, yyyy  hh:mm:ss a");
        Timer clock = new Timer(1000, e -> lblClock.setText(LocalDateTime.now().format(fmt)));
        clock.setInitialDelay(0);
        clock.start();

        // ── window control buttons ────────────────────────────────────────
        // Minimize
        JButton btnMin = makeWinBtn("─", new Color(80, 80, 80), new Color(120, 120, 120));
        btnMin.setBounds(794, 10, 38, 38);
        btnMin.addActionListener(e -> {
            if (frame != null) frame.setState(Frame.ICONIFIED);
        });
        header.add(btnMin);

        // Maximize / Restore
        JButton btnMax = makeWinBtn("□", new Color(60, 100, 60), new Color(80, 150, 80));
        btnMax.setBounds(836, 10, 38, 38);
        btnMax.addActionListener(e -> {
            if (frame == null) return;
            if (frame.getExtendedState() == Frame.MAXIMIZED_BOTH) {
                frame.setExtendedState(Frame.NORMAL);
                btnMax.setText("□");
            } else {
                frame.setExtendedState(Frame.MAXIMIZED_BOTH);
                btnMax.setText("❐");
            }
        });
        header.add(btnMax);

        // Close
        JButton btnClose = makeWinBtn("✕", new Color(140, 30, 30), new Color(200, 50, 50));
        btnClose.setBounds(878, 10, 38, 38);
        btnClose.addActionListener(e -> {
            if (frame != null) frame.dispose();
        });
        header.add(btnClose);

        return header;
    }

    /** Convenience — no user info, no frame (window controls hidden). */
    public static JPanel createHeader(String pageTitle) {
        return createHeader(pageTitle, null, null, null);
    }

    /** Convenience — user info but no frame. */
    public static JPanel createHeader(String pageTitle, String username, String role) {
        return createHeader(pageTitle, username, role, null);
    }

    // ── Window control button factory ─────────────────────────────────────

    private static JButton makeWinBtn(String symbol, Color idle, Color hover) {
        JButton btn = new JButton(symbol) {
            private Color current = idle;
            private final float[] alpha = {0f};
            {
                Timer t = new Timer(16, null);
                t.addActionListener(e -> {
                    float target = getModel().isRollover() ? 1f : 0f;
                    alpha[0] += (target - alpha[0]) * 0.12f;
                    if (Math.abs(alpha[0] - target) < 0.005f) alpha[0] = target;
                    current = blend(idle, hover, alpha[0]);
                    repaint();
                });
                t.start();
            }
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                // circle background
                g2.setColor(current);
                g2.fillOval(2, 2, getWidth() - 4, getHeight() - 4);
                // subtle border
                g2.setColor(new Color(255, 255, 255, 40));
                g2.drawOval(2, 2, getWidth() - 5, getHeight() - 5);
                // symbol
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Segoe UI Symbol", Font.BOLD, 13));
                java.awt.FontMetrics fm = g2.getFontMetrics();
                int tx = (getWidth()  - fm.stringWidth(getText())) / 2;
                int ty = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), tx, ty);
                g2.dispose();
            }
            // expose getText for the paint
            @Override public String getText() { return super.getText(); }
        };
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setToolTipText(symbol.equals("─") ? "Minimize"
                         : symbol.equals("□") ? "Maximize" : "Close");
        return btn;
    }

    // ── Helpers ───────────────────────────────────────────────────────────

    /** Linear color blend: t=0 → a, t=1 → b */
    static Color blend(Color a, Color b, float t) {
        t = Math.max(0f, Math.min(1f, t));
        return new Color(
            (int)(a.getRed()   + (b.getRed()   - a.getRed())   * t),
            (int)(a.getGreen() + (b.getGreen() - a.getGreen()) * t),
            (int)(a.getBlue()  + (b.getBlue()  - a.getBlue())  * t)
        );
    }

    // ── Frame setup ───────────────────────────────────────────────────────

    public static void setupFrame(JFrame frame, String title, boolean fixedSize) {
        initLookAndFeelOnce();
        if (title != null && !title.isEmpty()) frame.setTitle(title);
        frame.setLocationRelativeTo(null);
        frame.setResizable(!fixedSize);
    }

    // ── Field helpers ─────────────────────────────────────────────────────

    public static void setPlaceholder(JTextField field, String placeholder) {
        field.putClientProperty("JTextField.placeholderText", placeholder);
        field.putClientProperty("JTextField.showClearButton", true);
    }

    public static void setPasswordFieldFeatures(JPasswordField field, String placeholder) {
        field.putClientProperty("JTextField.placeholderText", placeholder);
        field.putClientProperty("JPasswordField.showRevealButton", true);
    }

    // ── Button helpers ────────────────────────────────────────────────────

    public static void makePrimary(AbstractButton button) {
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

    // ── Table helper ──────────────────────────────────────────────────────

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
