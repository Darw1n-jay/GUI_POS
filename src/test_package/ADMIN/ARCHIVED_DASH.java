package test_package.ADMIN;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import dao.ProductDao;
import dao.UserDao;
import logic.User;
import util.AppUI;

public class ARCHIVED_DASH extends javax.swing.JFrame {

    private User currentUser;

    public ARCHIVED_DASH(User user) {
        this.currentUser = user;
        initComponents();
        AppUI.setupFrame(this, "Archives", true);
        AppUI.styleTable(productTable);
        AppUI.styleTable(userTable);
        loadArchivedProducts();
        loadArchivedUsers();
    }

    private void loadArchivedProducts() {
        filterProducts("");
    }

    private void loadArchivedUsers() {
        filterUsers("");
    }

    private void filterProducts(String text) {
        DefaultTableModel m = (DefaultTableModel) productTable.getModel();
        m.setRowCount(0);
        try {
            for (model.Product p : ProductDao.getArchivedProducts()) {
                if (text.isEmpty() || String.valueOf(p.id).contains(text))
                    m.addRow(new Object[]{p.id, p.name, p.category, p.price, p.stock});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void filterUsers(String text) {
        DefaultTableModel m = (DefaultTableModel) userTable.getModel();
        m.setRowCount(0);
        try {
            for (model.User u : UserDao.getArchivedUsers()) {
                if (text.isEmpty() || String.valueOf(u.id).contains(text))
                    m.addRow(new Object[]{u.id, u.username, u.role});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void initComponents() {
        jPanel1          = new JPanel();
        tabbedPane       = new JTabbedPane();
        productPanel     = new JPanel();
        jScrollPane1     = new JScrollPane();
        productTable     = new JTable();
        btnRestoreProduct = new JButton();
        txtProductSearch = new JTextField();
        btnProductSearch = new JButton();
        userPanel        = new JPanel();
        jScrollPane2     = new JScrollPane();
        userTable        = new JTable();
        btnRestoreUser   = new JButton();
        txtUserSearch    = new JTextField();
        btnUserSearch    = new JButton();
        btnBack          = new JButton();
        jLabel1          = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Main panel — coffee brown background
        jPanel1.setBackground(new java.awt.Color(101, 67, 33));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Title
        jLabel1.setFont(new java.awt.Font("Sitka Display", java.awt.Font.BOLD, 22));
        jLabel1.setForeground(java.awt.Color.WHITE);
        jLabel1.setText("ARCHIVES");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 15, 150, 35));

        // ── Products tab ──────────────────────────────────────
        productTable.setModel(new DefaultTableModel(
            new Object[][]{}, new String[]{"ID", "Name", "Category", "Price", "Stock"}
        ) { public boolean isCellEditable(int r, int c) { return false; } });
        jScrollPane1.setViewportView(productTable);

        productPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabel lblPS = new JLabel("Search ID:");
        lblPS.setFont(new java.awt.Font("Sitka Display", java.awt.Font.BOLD, 13));
        productPanel.add(lblPS,            new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 85, 25));
        productPanel.add(txtProductSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 140, 25));

        btnProductSearch.setBackground(java.awt.Color.BLACK);
        btnProductSearch.setForeground(java.awt.Color.WHITE);
        btnProductSearch.setFont(new java.awt.Font("Sitka Display", java.awt.Font.BOLD, 12));
        btnProductSearch.setText("SEARCH");
        btnProductSearch.addActionListener(evt -> filterProducts(txtProductSearch.getText().trim()));
        productPanel.add(btnProductSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 90, 25));

        productPanel.add(jScrollPane1,     new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 870, 280));

        btnRestoreProduct.setBackground(new java.awt.Color(0, 153, 76));
        btnRestoreProduct.setFont(new java.awt.Font("Sitka Display", java.awt.Font.BOLD, 13));
        btnRestoreProduct.setForeground(java.awt.Color.WHITE);
        btnRestoreProduct.setText("RESTORE PRODUCT");
        btnRestoreProduct.addActionListener(evt -> restoreProduct());
        productPanel.add(btnRestoreProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 335, 180, 35));

        // ── Users tab ─────────────────────────────────────────
        userTable.setModel(new DefaultTableModel(
            new Object[][]{}, new String[]{"ID", "Username", "Role"}
        ) { public boolean isCellEditable(int r, int c) { return false; } });
        jScrollPane2.setViewportView(userTable);

        userPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabel lblUS = new JLabel("Search ID:");
        lblUS.setFont(new java.awt.Font("Sitka Display", java.awt.Font.BOLD, 13));
        userPanel.add(lblUS,          new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 85, 25));
        userPanel.add(txtUserSearch,  new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 140, 25));

        btnUserSearch.setBackground(java.awt.Color.BLACK);
        btnUserSearch.setForeground(java.awt.Color.WHITE);
        btnUserSearch.setFont(new java.awt.Font("Sitka Display", java.awt.Font.BOLD, 12));
        btnUserSearch.setText("SEARCH");
        btnUserSearch.addActionListener(evt -> filterUsers(txtUserSearch.getText().trim()));
        userPanel.add(btnUserSearch,  new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 90, 25));

        userPanel.add(jScrollPane2,   new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 870, 280));

        btnRestoreUser.setBackground(new java.awt.Color(0, 153, 76));
        btnRestoreUser.setFont(new java.awt.Font("Sitka Display", java.awt.Font.BOLD, 13));
        btnRestoreUser.setForeground(java.awt.Color.WHITE);
        btnRestoreUser.setText("RESTORE USER");
        btnRestoreUser.addActionListener(evt -> restoreUser());
        userPanel.add(btnRestoreUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 335, 160, 35));

        // ── Tabbed pane ───────────────────────────────────────
        tabbedPane.addTab("Archived Products", productPanel);
        tabbedPane.addTab("Archived Users",    userPanel);
        jPanel1.add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 920, 400));

        // Back button
        btnBack.setBackground(new java.awt.Color(80, 80, 80));
        btnBack.setFont(new java.awt.Font("Sitka Display", java.awt.Font.BOLD, 13));
        btnBack.setForeground(java.awt.Color.WHITE);
        btnBack.setText("BACK");
        btnBack.addActionListener(evt -> btnBackActionPerformed());
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 100, 35));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        setSize(960, 540);
        setLocationRelativeTo(null);
    }

    private void restoreProduct() {
        try {
            int row = productTable.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(this, "Select a product to restore."); return; }
            int id = (int) productTable.getValueAt(row, 0);
            if (JOptionPane.showConfirmDialog(this, "Restore this product?") == JOptionPane.YES_OPTION) {
                ProductDao.restoreProduct(id);
                JOptionPane.showMessageDialog(this, "Product restored!");
                loadArchivedProducts();
            }
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
    }

    private void restoreUser() {
        try {
            int row = userTable.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(this, "Select a user to restore."); return; }
            int id = (int) userTable.getValueAt(row, 0);
            if (JOptionPane.showConfirmDialog(this, "Restore this user?") == JOptionPane.YES_OPTION) {
                UserDao.restoreUser(id);
                JOptionPane.showMessageDialog(this, "User restored!");
                loadArchivedUsers();
            }
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
    }

    private void btnBackActionPerformed() {
        new test_package.ADMIN_DASH(currentUser).setVisible(true);
        this.dispose();
    }

    private JPanel jPanel1, productPanel, userPanel;
    private JTabbedPane tabbedPane;
    private JScrollPane jScrollPane1, jScrollPane2;
    private JTable productTable, userTable;
    private JButton btnRestoreProduct, btnRestoreUser, btnBack;
    private JButton btnProductSearch, btnUserSearch;
    private JTextField txtProductSearch, txtUserSearch;
    private JLabel jLabel1;
}
