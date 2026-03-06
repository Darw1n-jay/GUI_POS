package test_package.ADMIN;

import javax.swing.*;
import java.sql.*;
import db.DBConnection;

public class DASHBOARD_SUMMARY extends javax.swing.JFrame {

    public DASHBOARD_SUMMARY() {
        initComponents();
        loadSummary();
    }
    
    private void loadSummary() {
        try (Connection conn = DBConnection.getConnection()) {
            
            // Total Products
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM products")) {
                if (rs.next()) {
                    lblTotalProducts.setText("Total Products: " + rs.getInt("count"));
                }
            }
            
            // Total Sales Today (SQLite syntax)
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(
                     "SELECT COUNT(*) as count, COALESCE(SUM(total), 0) as revenue " +
                     "FROM sales WHERE DATE(sale_date) = DATE('now')")) {
                if (rs.next()) {
                    lblTodaySales.setText("Today's Sales: " + rs.getInt("count"));
                    lblTodayRevenue.setText(String.format("Today's Revenue: ₱%.2f", rs.getDouble("revenue")));
                }
            }
            
            // Total Revenue
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COALESCE(SUM(total), 0) as total FROM sales")) {
                if (rs.next()) {
                    lblTotalRevenue.setText(String.format("Total Revenue: ₱%.2f", rs.getDouble("total")));
                }
            }
            
            // Low Stock Count
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM products WHERE stock <= 10")) {
                if (rs.next()) {
                    lblLowStock.setText("Low Stock Items: " + rs.getInt("count"));
                }
            }
            
            // Pending Approvals
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM users WHERE approved = 0")) {
                if (rs.next()) {
                    lblPendingUsers.setText("Pending Approvals: " + rs.getInt("count"));
                }
            }
            
            // Total Users
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM users")) {
                if (rs.next()) {
                    lblTotalUsers.setText("Total Users: " + rs.getInt("count"));
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading summary: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTotalProducts = new javax.swing.JLabel();
        lblTodaySales = new javax.swing.JLabel();
        lblTodayRevenue = new javax.swing.JLabel();
        lblTotalRevenue = new javax.swing.JLabel();
        lblLowStock = new javax.swing.JLabel();
        lblPendingUsers = new javax.swing.JLabel();
        lblTotalUsers = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Sitka Display", 1, 18));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DASHBOARD SUMMARY");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 300, 40));

        lblTotalProducts.setFont(new java.awt.Font("Sitka Display", 1, 16));
        lblTotalProducts.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalProducts.setText("Total Products: 0");
        jPanel1.add(lblTotalProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 300, 40));

        lblTodaySales.setFont(new java.awt.Font("Sitka Display", 1, 16));
        lblTodaySales.setForeground(new java.awt.Color(255, 255, 255));
        lblTodaySales.setText("Today's Sales: 0");
        jPanel1.add(lblTodaySales, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 300, 40));

        lblTodayRevenue.setFont(new java.awt.Font("Sitka Display", 1, 16));
        lblTodayRevenue.setForeground(new java.awt.Color(255, 255, 255));
        lblTodayRevenue.setText("Today's Revenue: ₱0.00");
        jPanel1.add(lblTodayRevenue, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 300, 40));

        lblTotalRevenue.setFont(new java.awt.Font("Sitka Display", 1, 16));
        lblTotalRevenue.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalRevenue.setText("Total Revenue: ₱0.00");
        jPanel1.add(lblTotalRevenue, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 300, 40));

        lblLowStock.setFont(new java.awt.Font("Sitka Display", 1, 16));
        lblLowStock.setForeground(new java.awt.Color(255, 255, 255));
        lblLowStock.setText("Low Stock Items: 0");
        jPanel1.add(lblLowStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 300, 40));

        lblPendingUsers.setFont(new java.awt.Font("Sitka Display", 1, 16));
        lblPendingUsers.setForeground(new java.awt.Color(255, 255, 255));
        lblPendingUsers.setText("Pending Approvals: 0");
        jPanel1.add(lblPendingUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 300, 40));

        lblTotalUsers.setFont(new java.awt.Font("Sitka Display", 1, 16));
        lblTotalUsers.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalUsers.setText("Total Users: 0");
        jPanel1.add(lblTotalUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, 300, 40));

        btnRefresh.setBackground(new java.awt.Color(0, 0, 0));
        btnRefresh.setFont(new java.awt.Font("Sitka Display", 1, 12));
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, 150, 40));

        btnBack.setBackground(new java.awt.Color(0, 0, 0));
        btnBack.setFont(new java.awt.Font("Sitka Display", 1, 12));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 100, 35));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1.png")));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {
        loadSummary();
        JOptionPane.showMessageDialog(this, "Dashboard refreshed!");
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN_DASH(null).setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DASHBOARD_SUMMARY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DASHBOARD_SUMMARY().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLowStock;
    private javax.swing.JLabel lblPendingUsers;
    private javax.swing.JLabel lblTodayRevenue;
    private javax.swing.JLabel lblTodaySales;
    private javax.swing.JLabel lblTotalProducts;
    private javax.swing.JLabel lblTotalRevenue;
    private javax.swing.JLabel lblTotalUsers;
    // End of variables declaration//GEN-END:variables
}
