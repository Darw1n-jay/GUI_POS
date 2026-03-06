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

        DASHBOARD_SUMMARY.setBackground(new java.awt.Color(0, 0, 0));
        DASHBOARD_SUMMARY.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        DASHBOARD_SUMMARY.setForeground(new java.awt.Color(255, 255, 255));
        DASHBOARD_SUMMARY.setText("OVERVIEW");
        DASHBOARD_SUMMARY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DASHBOARD_SUMMARYActionPerformed(evt);
            }
        });
        jPanel1.add(DASHBOARD_SUMMARY, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 160, 40));

        MANAGE_PRODUCT.setBackground(new java.awt.Color(0, 0, 0));
        MANAGE_PRODUCT.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        MANAGE_PRODUCT.setForeground(new java.awt.Color(255, 255, 255));
        MANAGE_PRODUCT.setText("MANAGE COFFEE PRODUCTS");
        MANAGE_PRODUCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MANAGE_PRODUCTActionPerformed(evt);
            }
        });
        jPanel1.add(MANAGE_PRODUCT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 160, 40));

        VIEW_INVENTORY.setBackground(new java.awt.Color(0, 0, 0));
        VIEW_INVENTORY.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        VIEW_INVENTORY.setForeground(new java.awt.Color(255, 255, 255));
        VIEW_INVENTORY.setText("VIEW INVENTORY");
        VIEW_INVENTORY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VIEW_INVENTORYActionPerformed(evt);
            }
        });
        jPanel1.add(VIEW_INVENTORY, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 160, 50));

        MAKE_SALE.setBackground(new java.awt.Color(0, 0, 0));
        MAKE_SALE.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        MAKE_SALE.setForeground(new java.awt.Color(255, 255, 255));
        MAKE_SALE.setText("MAKE SALE");
        MAKE_SALE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MAKE_SALEActionPerformed(evt);
            }
        });
        jPanel1.add(MAKE_SALE, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 160, 50));

        MANAGE_SALE.setBackground(new java.awt.Color(0, 0, 0));
        MANAGE_SALE.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        MANAGE_SALE.setForeground(new java.awt.Color(255, 255, 255));
        MANAGE_SALE.setText("MANAGE SALES");
        MANAGE_SALE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MANAGE_SALEActionPerformed(evt);
            }
        });
        jPanel1.add(MANAGE_SALE, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 160, 40));

        APPROVE_REGISTER.setBackground(new java.awt.Color(0, 0, 0));
        APPROVE_REGISTER.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        APPROVE_REGISTER.setForeground(new java.awt.Color(255, 255, 255));
        APPROVE_REGISTER.setText("APPROVE REGISTER");
        APPROVE_REGISTER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                APPROVE_REGISTERActionPerformed(evt);
            }
        });
        jPanel1.add(APPROVE_REGISTER, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 160, 50));

        MANAGE_USER.setBackground(new java.awt.Color(0, 0, 0));
        MANAGE_USER.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        MANAGE_USER.setForeground(new java.awt.Color(255, 255, 255));
        MANAGE_USER.setText("USER MANAGEMENT");
        MANAGE_USER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MANAGE_USERActionPerformed(evt);
            }
        });
        jPanel1.add(MANAGE_USER, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 160, 40));

        SALES_REPORT.setBackground(new java.awt.Color(0, 0, 0));
        SALES_REPORT.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        SALES_REPORT.setForeground(new java.awt.Color(255, 255, 255));
        SALES_REPORT.setText("SALES REPORT");
        SALES_REPORT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALES_REPORTActionPerformed(evt);
            }
        });
        jPanel1.add(SALES_REPORT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 160, 40));

        LOW_STOCK.setBackground(new java.awt.Color(0, 0, 0));
        LOW_STOCK.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        LOW_STOCK.setForeground(new java.awt.Color(255, 255, 255));
        LOW_STOCK.setText("LOW STOCK ALERT");
        LOW_STOCK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOW_STOCKActionPerformed(evt);
            }
        });
        jPanel1.add(LOW_STOCK, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 160, 40));

        SEARCH_PRODUCT.setBackground(new java.awt.Color(0, 0, 0));
        SEARCH_PRODUCT.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        SEARCH_PRODUCT.setForeground(new java.awt.Color(255, 255, 255));
        SEARCH_PRODUCT.setText("SEARCH COFFEE PRODUCT");
        SEARCH_PRODUCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEARCH_PRODUCTActionPerformed(evt);
            }
        });
        jPanel1.add(SEARCH_PRODUCT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 160, 40));

        LOGOUT.setBackground(new java.awt.Color(0, 0, 0));
        LOGOUT.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        LOGOUT.setForeground(new java.awt.Color(255, 255, 255));
        LOGOUT.setText("LOG OUT");
        LOGOUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGOUTActionPerformed(evt);
            }
        });
        jPanel1.add(LOGOUT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 400, 160, 40));

        jLabel1.setFont(new java.awt.Font("Sitka Display", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("WELCOME TO ADMIN DASH BOARD");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 400, 50));

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
        new test_package.ADMIN.SALES_REPORT_DASH().setVisible(true);
        this.dispose();
    }
    
    private void LOW_STOCKActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN.LOW_STOCK_DASH().setVisible(true);
        this.dispose();
    }
    
    private void DASHBOARD_SUMMARYActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN.DASHBOARD_SUMMARY().setVisible(true);
        this.dispose();
    }
    
    private void SEARCH_PRODUCTActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN.SEARCH_PRODUCT_DASH().setVisible(true);
        this.dispose();
    }

    private void VIEW_INVENTORYActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.ADMIN.VIEW_INVE_DASH().setVisible(true);
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
