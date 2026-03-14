/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_package;

import javax.swing.*;
import logic.User;
import test_package.ADMIN.MANAGE_PRODUCT_DASH;
import util.AppUI;

       public class ADMIN_DASH extends javax.swing.JFrame {
           private User currentUser;
         
         public ADMIN_DASH(User user) {
            initComponents();
           this.currentUser = user;
           AppUI.setupFrame(this, "Coffee Shop POS - Admin Dashboard", true);
           AppUI.makeSecondary(DASHBOARD_SUMMARY);
           AppUI.makeSecondary(MANAGE_PRODUCT);
           AppUI.makeSecondary(VIEW_INVENTORY);
           AppUI.makeSecondary(MAKE_SALE);
           AppUI.makeSecondary(MANAGE_SALE);
           AppUI.makeSecondary(APPROVE_REGISTER);
           AppUI.makeSecondary(MANAGE_USER);
           AppUI.makeSecondary(SALES_REPORT);
           AppUI.makeSecondary(LOW_STOCK);
           AppUI.makeSecondary(SEARCH_PRODUCT);
           AppUI.makeSecondary(LOGOUT);
           // Hide admin "MAKE SALE" entry point; sales are created from Cashier only
           MAKE_SALE.setVisible(false);
           MAKE_SALE.setEnabled(false);
           // Hide redundant plain inventory button (admin uses Manage Products / Reports)
           VIEW_INVENTORY.setVisible(false);
           VIEW_INVENTORY.setEnabled(false);
           // Hide APPROVE_REGISTER button since functionality is now combined in USER_MANAGEMENT
           APPROVE_REGISTER.setVisible(false);
           APPROVE_REGISTER.setEnabled(false);
           // Search is now inside Manage Products
           SEARCH_PRODUCT.setVisible(false);
           SEARCH_PRODUCT.setEnabled(false);
           // DASHBOARD_SUMMARY is now merged into REPORTS
           DASHBOARD_SUMMARY.setVisible(false);
           DASHBOARD_SUMMARY.setEnabled(false);
           // Rename buttons
           SALES_REPORT.setText("REPORTS");
           if (user != null && user.username != null) {
               jLabel1.setText("WELCOME, " + user.username.toUpperCase());
           }
         }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        DASHBOARD_SUMMARY = new javax.swing.JButton();
        MANAGE_PRODUCT = new javax.swing.JButton();
        VIEW_INVENTORY = new javax.swing.JButton();
        MAKE_SALE = new javax.swing.JButton();
        MANAGE_SALE = new javax.swing.JButton();
        APPROVE_REGISTER = new javax.swing.JButton();
        MANAGE_USER = new javax.swing.JButton();
        SALES_REPORT = new javax.swing.JButton();
        LOW_STOCK = new javax.swing.JButton();
        SEARCH_PRODUCT = new javax.swing.JButton();
        LOGOUT = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Dashboard - Coffee Shop POS");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // shared style vars
        java.awt.Font btnFont  = new java.awt.Font("Sitka Display", 1, 14);
        java.awt.Color white   = new java.awt.Color(255, 255, 255);
        java.awt.Color black   = new java.awt.Color(0, 0, 0);
        int bx = 100, bw = 160, bh = 50, by = 50, gap = 60;

        // 1. MANAGE PRODUCTS
        MANAGE_PRODUCT.setBackground(black); MANAGE_PRODUCT.setFont(btnFont); MANAGE_PRODUCT.setForeground(white);
        MANAGE_PRODUCT.setText("MANAGE PRODUCTS");
        MANAGE_PRODUCT.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { MANAGE_PRODUCTActionPerformed(evt); } });
        jPanel1.add(MANAGE_PRODUCT, new org.netbeans.lib.awtextra.AbsoluteConstraints(bx, by, bw, bh));

        // 2. MANAGE SALES
        MANAGE_SALE.setBackground(black); MANAGE_SALE.setFont(btnFont); MANAGE_SALE.setForeground(white);
        MANAGE_SALE.setText("MANAGE SALES");
        MANAGE_SALE.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { MANAGE_SALEActionPerformed(evt); } });
        jPanel1.add(MANAGE_SALE, new org.netbeans.lib.awtextra.AbsoluteConstraints(bx, by + gap, bw, bh));

        // 3. USER MANAGEMENT
        MANAGE_USER.setBackground(black); MANAGE_USER.setFont(btnFont); MANAGE_USER.setForeground(white);
        MANAGE_USER.setText("USER MANAGEMENT");
        MANAGE_USER.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { MANAGE_USERActionPerformed(evt); } });
        jPanel1.add(MANAGE_USER, new org.netbeans.lib.awtextra.AbsoluteConstraints(bx, by + gap*2, bw, bh));

        // 4. LOW STOCK
        LOW_STOCK.setBackground(black); LOW_STOCK.setFont(btnFont); LOW_STOCK.setForeground(white);
        LOW_STOCK.setText("LOW STOCK ALERT");
        LOW_STOCK.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { LOW_STOCKActionPerformed(evt); } });
        jPanel1.add(LOW_STOCK, new org.netbeans.lib.awtextra.AbsoluteConstraints(bx, by + gap*3, bw, bh));

        // 5. REPORTS
        SALES_REPORT.setBackground(black); SALES_REPORT.setFont(btnFont); SALES_REPORT.setForeground(white);
        SALES_REPORT.setText("REPORTS");
        SALES_REPORT.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { SALES_REPORTActionPerformed(evt); } });
        jPanel1.add(SALES_REPORT, new org.netbeans.lib.awtextra.AbsoluteConstraints(bx, by + gap*4, bw, bh));

        // 6. ARCHIVED
        ARCHIVED_PRODUCTS = new javax.swing.JButton();
        ARCHIVED_PRODUCTS.setBackground(black); ARCHIVED_PRODUCTS.setFont(btnFont); ARCHIVED_PRODUCTS.setForeground(white);
        ARCHIVED_PRODUCTS.setText("ARCHIVED");
        ARCHIVED_PRODUCTS.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { ARCHIVED_PRODUCTSActionPerformed(evt); } });
        jPanel1.add(ARCHIVED_PRODUCTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(bx, by + gap*5, bw, bh));

        // 7. LOGOUT — last
        LOGOUT.setBackground(black); LOGOUT.setFont(btnFont); LOGOUT.setForeground(white);
        LOGOUT.setText("LOG OUT");
        LOGOUT.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { LOGOUTActionPerformed(evt); } });
        jPanel1.add(LOGOUT, new org.netbeans.lib.awtextra.AbsoluteConstraints(bx, by + gap*6, bw, bh));

        // Hidden (wiring only)
        DASHBOARD_SUMMARY.setVisible(false); DASHBOARD_SUMMARY.setEnabled(false);
        DASHBOARD_SUMMARY.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { DASHBOARD_SUMMARYActionPerformed(evt); } });
        jPanel1.add(DASHBOARD_SUMMARY, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));
        VIEW_INVENTORY.setVisible(false); VIEW_INVENTORY.setEnabled(false);
        VIEW_INVENTORY.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { VIEW_INVENTORYActionPerformed(evt); } });
        jPanel1.add(VIEW_INVENTORY, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));
        MAKE_SALE.setVisible(false); MAKE_SALE.setEnabled(false);
        MAKE_SALE.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { MAKE_SALEActionPerformed(evt); } });
        jPanel1.add(MAKE_SALE, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));
        APPROVE_REGISTER.setVisible(false); APPROVE_REGISTER.setEnabled(false);
        APPROVE_REGISTER.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { APPROVE_REGISTERActionPerformed(evt); } });
        jPanel1.add(APPROVE_REGISTER, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));
        SEARCH_PRODUCT.setVisible(false); SEARCH_PRODUCT.setEnabled(false);
        SEARCH_PRODUCT.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { SEARCH_PRODUCTActionPerformed(evt); } });
        jPanel1.add(SEARCH_PRODUCT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));
        ARCHIVED_USERS = new javax.swing.JButton();
        ARCHIVED_USERS.setVisible(false); ARCHIVED_USERS.setEnabled(false);
        jPanel1.add(ARCHIVED_USERS, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Sitka Display", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("WELCOME TO ADMIN DASH BOARD");
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 500, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/6.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 530));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   

   

    private void MANAGE_PRODUCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MANAGE_PRODUCTActionPerformed
       MANAGE_PRODUCT_DASH manageProduct = new MANAGE_PRODUCT_DASH(currentUser);
    manageProduct.setVisible(true);
    this.dispose(); // Close the current ADMIN_DASH window

    }//GEN-LAST:event_MANAGE_PRODUCTActionPerformed

    private void LOGOUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGOUTActionPerformed
        LOG_IN login = new LOG_IN();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LOGOUTActionPerformed
    
    private void MAKE_SALEActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.CASHIER.CREATE_SALE_DASH(currentUser).setVisible(true);
        this.dispose();
    }
    
    private void MANAGE_SALEActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN.MANAGE_SALES_DASH().setVisible(true);
        this.dispose();
    }
    
    private void APPROVE_REGISTERActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN.APPROVE_DASH().setVisible(true);
        this.dispose();
    }
    
    private void MANAGE_USERActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN.USER_MANAGEMENT_DASH().setVisible(true);
        this.dispose();
    }
    
    private void SALES_REPORTActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN.REPORTS_DASH(currentUser).setVisible(true);
        this.dispose();
    }
    
    private void LOW_STOCKActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN.LOW_STOCK_DASH().setVisible(true);
        this.dispose();
    }
    
    private void DASHBOARD_SUMMARYActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN.REPORTS_DASH(currentUser).setVisible(true);
        this.dispose();
    }
    
    private void SEARCH_PRODUCTActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN.MANAGE_PRODUCT_DASH(currentUser).setVisible(true);
        this.dispose();
    }

    private void VIEW_INVENTORYActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN.VIEW_INVE_DASH().setVisible(true);
        this.dispose();
    }

    private void ARCHIVED_PRODUCTSActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN.ARCHIVED_DASH(currentUser).setVisible(true);
        this.dispose();
    }

    private void ARCHIVED_USERSActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN.ARCHIVED_DASH(currentUser).setVisible(true);
        this.dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        AppUI.initLookAndFeelOnce();
        java.awt.EventQueue.invokeLater(() -> {
            User demo = new User(0, "admin", User.Role.ADMIN);
            new ADMIN_DASH(demo).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton APPROVE_REGISTER;
    private javax.swing.JButton ARCHIVED_PRODUCTS;
    private javax.swing.JButton ARCHIVED_USERS;
    private javax.swing.JButton DASHBOARD_SUMMARY;
    private javax.swing.JButton LOGOUT;
    private javax.swing.JButton LOW_STOCK;
    private javax.swing.JButton MAKE_SALE;
    private javax.swing.JButton MANAGE_PRODUCT;
    private javax.swing.JButton MANAGE_SALE;
    private javax.swing.JButton MANAGE_USER;
    private javax.swing.JButton SALES_REPORT;
    private javax.swing.JButton SEARCH_PRODUCT;
    private javax.swing.JButton VIEW_INVENTORY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
