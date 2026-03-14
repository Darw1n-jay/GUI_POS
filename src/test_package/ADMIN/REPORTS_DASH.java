package test_package.ADMIN;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import db.DBConnection;
import logic.User;
import util.AppUI;

public class REPORTS_DASH extends javax.swing.JFrame {

    private User currentUser;

    public REPORTS_DASH(User user) {
        this.currentUser = user;
        initComponents();
        AppUI.setupFrame(this, "Reports", true);
        AppUI.styleTable(reportTable);
        loadOverview();
        loadSalesReport();
    }

    private void loadOverview() {
        try (Connection conn = DBConnection.getConnection()) {
            try (Statement s = conn.createStatement();
                 ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM products WHERE is_archived=0 OR is_archived IS NULL")) {
                if (rs.next()) lblTotalProducts.setText("Total Products: " + rs.getInt(1));
            }
            try (Statement s = conn.createStatement();
                 ResultSet rs = s.executeQuery(
                     "SELECT COUNT(*), COALESCE(SUM(total),0) FROM sales WHERE DATE(sale_date)=DATE('now')")) {
                if (rs.next()) {
                    lblTodaySales.setText("Today's Sales: " + rs.getInt(1));
                    lblTodayRevenue.setText(String.format("Today's Revenue: \u20b1%.2f", rs.getDouble(2)));
                }
            }
            try (Statement s = conn.createStatement();
                 ResultSet rs = s.executeQuery("SELECT COALESCE(SUM(total),0) FROM sales")) {
                if (rs.next()) lblTotalRevenue.setText(String.format("Total Revenue: \u20b1%.2f", rs.getDouble(1)));
            }
            try (Statement s = conn.createStatement();
                 ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM products WHERE stock<=10 AND (is_archived=0 OR is_archived IS NULL)")) {
                if (rs.next()) lblLowStock.setText("Low Stock Items: " + rs.getInt(1));
            }
            try (Statement s = conn.createStatement();
                 ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM users WHERE approved=0 AND (is_archived=0 OR is_archived IS NULL)")) {
                if (rs.next()) lblPendingUsers.setText("Pending Approvals: " + rs.getInt(1));
            }
            try (Statement s = conn.createStatement();
                 ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM users WHERE is_archived=0 OR is_archived IS NULL")) {
                if (rs.next()) lblTotalUsers.setText("Total Users: " + rs.getInt(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading overview: " + e.getMessage());
        }
    }

    private void loadSalesReport() {
        searchSalesReport("");
    }

    private void searchSalesReport(String keyword) {
        try {
            DefaultTableModel model = (DefaultTableModel) reportTable.getModel();
            model.setRowCount(0);
            String sql = "SELECT DATE(sale_date) as date, COUNT(*) as txn, SUM(total) as rev "
                       + "FROM sales GROUP BY DATE(sale_date) ORDER BY date DESC";
            try (Connection conn = DBConnection.getConnection();
                 Statement s = conn.createStatement();
                 ResultSet rs = s.executeQuery(sql)) {
                double totalRev = 0; int totalTxn = 0;
                while (rs.next()) {
                    double rev = rs.getDouble("rev");
                    int txn = rs.getInt("txn");
                    String date = rs.getString("date");
                    if (keyword.isEmpty() || date.contains(keyword)) {
                        totalRev += rev; totalTxn += txn;
                        model.addRow(new Object[]{date, txn, String.format("\u20b1%.2f", rev)});
                    }
                }
                lblSalesTotalRevenue.setText(String.format("Total Revenue: \u20b1%.2f", totalRev));
                lblSalesTotalTxn.setText("Total Transactions: " + totalTxn);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading sales report: " + e.getMessage());
        }
    }

    private void initComponents() {
        jPanel1       = new javax.swing.JPanel();
        tabbedPane    = new javax.swing.JTabbedPane();
        overviewPanel = new javax.swing.JPanel();
        salesPanel    = new javax.swing.JPanel();
        jScrollPane1  = new javax.swing.JScrollPane();
        reportTable   = new javax.swing.JTable();

        lblTotalProducts = new javax.swing.JLabel("Total Products: 0");
        lblTodaySales    = new javax.swing.JLabel("Today's Sales: 0");
        lblTodayRevenue  = new javax.swing.JLabel("Today's Revenue: \u20b10.00");
        lblTotalRevenue  = new javax.swing.JLabel("Total Revenue: \u20b10.00");
        lblLowStock      = new javax.swing.JLabel("Low Stock Items: 0");
        lblPendingUsers  = new javax.swing.JLabel("Pending Approvals: 0");
        lblTotalUsers    = new javax.swing.JLabel("Total Users: 0");
        lblSalesTotalRevenue = new javax.swing.JLabel("Total Revenue: \u20b10.00");
        lblSalesTotalTxn     = new javax.swing.JLabel("Total Transactions: 0");

        btnRefresh = new javax.swing.JButton();
        btnBack    = new javax.swing.JButton();
        jLabel1    = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(101, 67, 33));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Sitka Display", 1, 22));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REPORTS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 15, 120, 35));

        // Overview tab
        overviewPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        java.awt.Font ovFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 15);
        java.awt.Color white = new java.awt.Color(255, 255, 255);
        java.awt.Color ovColor = new java.awt.Color(0, 0, 0); // black for overview labels
        for (javax.swing.JLabel lbl : new javax.swing.JLabel[]{
                lblTotalProducts, lblTodaySales, lblTodayRevenue, lblTotalRevenue,
                lblLowStock, lblPendingUsers, lblTotalUsers}) {
            lbl.setFont(ovFont); lbl.setForeground(ovColor);
        }
        overviewPanel.add(lblTotalProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 350, 35));
        overviewPanel.add(lblTodaySales,    new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 350, 35));
        overviewPanel.add(lblTodayRevenue,  new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 350, 35));
        overviewPanel.add(lblTotalRevenue,  new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 350, 35));
        overviewPanel.add(lblLowStock,      new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 350, 35));
        overviewPanel.add(lblPendingUsers,  new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 350, 35));
        overviewPanel.add(lblTotalUsers,    new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 350, 35));

        // Sales Report tab
        reportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{}, new String[]{"Date", "Transactions", "Revenue"}
        ) { public boolean isCellEditable(int r, int c) { return false; } });
        jScrollPane1.setViewportView(reportTable);

        txtSalesSearch = new javax.swing.JTextField();
        btnSalesSearch = new javax.swing.JButton();
        javax.swing.JLabel lblSalesSearch = new javax.swing.JLabel("Search Date:");
        lblSalesSearch.setFont(new java.awt.Font("Sitka Display", 1, 13));
        lblSalesSearch.setForeground(new java.awt.Color(0, 0, 0));

        salesPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        salesPanel.add(lblSalesSearch,  new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 25));
        salesPanel.add(txtSalesSearch,  new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 10, 160, 25));
        btnSalesSearch.setBackground(new java.awt.Color(0, 0, 0));
        btnSalesSearch.setFont(new java.awt.Font("Sitka Display", 1, 12));
        btnSalesSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSalesSearch.setText("SEARCH");
        btnSalesSearch.addActionListener(evt -> searchSalesReport(txtSalesSearch.getText().trim()));
        salesPanel.add(btnSalesSearch,  new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 10, 90, 25));
        salesPanel.add(jScrollPane1,    new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 870, 230));
        lblSalesTotalRevenue.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 15)); lblSalesTotalRevenue.setForeground(ovColor);
        lblSalesTotalTxn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 15));     lblSalesTotalTxn.setForeground(ovColor);
        salesPanel.add(lblSalesTotalRevenue, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 285, 350, 30));
        salesPanel.add(lblSalesTotalTxn,     new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 285, 350, 30));

        tabbedPane.addTab("Overview", overviewPanel);
        tabbedPane.addTab("Sales Report", salesPanel);
        jPanel1.add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 910, 390));

        btnRefresh.setBackground(new java.awt.Color(0, 0, 0));
        btnRefresh.setFont(new java.awt.Font("Sitka Display", 1, 13));
        btnRefresh.setForeground(white);
        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(evt -> { loadOverview(); loadSalesReport(); });
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 465, 120, 35));

        btnBack.setBackground(new java.awt.Color(80, 80, 80));
        btnBack.setFont(new java.awt.Font("Sitka Display", 1, 13));
        btnBack.setForeground(white);
        btnBack.setText("BACK");
        btnBack.addActionListener(evt -> { new test_package.ADMIN_DASH(currentUser).setVisible(true); dispose(); });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 465, 100, 35));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        setSize(960, 540);
        setLocationRelativeTo(null);
    }

    private javax.swing.JPanel jPanel1, overviewPanel, salesPanel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable reportTable;
    private javax.swing.JLabel jLabel1, lblTotalProducts, lblTodaySales, lblTodayRevenue,
            lblTotalRevenue, lblLowStock, lblPendingUsers, lblTotalUsers,
            lblSalesTotalRevenue, lblSalesTotalTxn;
    private javax.swing.JButton btnRefresh, btnBack, btnSalesSearch;
    private javax.swing.JTextField txtSalesSearch;
}
