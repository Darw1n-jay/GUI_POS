/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
       package test_package;

      import javax.swing.*;
      import pos.model.Models.User;
import test_package.ADMIN.MANAGE_PRODUCT_DASH;

       public class ADMIN_DASH extends javax.swing.JFrame {
           private User currentUser;
         
         public ADMIN_DASH(User user) {
            initComponents();
           this.currentUser = user;
         }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        MANAGE_PRODUCT = new javax.swing.JButton();
        VIEW_INVENTORY = new javax.swing.JButton();
        MAKE_SALE = new javax.swing.JButton();
        MANAGE_SALE = new javax.swing.JButton();
        APPROVE_REGISTER = new javax.swing.JButton();
        LOGOUT = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MANAGE_PRODUCT.setBackground(new java.awt.Color(0, 0, 0));
        MANAGE_PRODUCT.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        MANAGE_PRODUCT.setForeground(new java.awt.Color(255, 255, 255));
        MANAGE_PRODUCT.setText("MANAGE PRODUCT");
        MANAGE_PRODUCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MANAGE_PRODUCTActionPerformed(evt);
            }
        });
        jPanel1.add(MANAGE_PRODUCT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 160, 40));

        VIEW_INVENTORY.setBackground(new java.awt.Color(0, 0, 0));
        VIEW_INVENTORY.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        VIEW_INVENTORY.setForeground(new java.awt.Color(255, 255, 255));
        VIEW_INVENTORY.setText("VIEW INVENTORY");
        VIEW_INVENTORY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VIEW_INVENTORYActionPerformed(evt);
            }
        });
        jPanel1.add(VIEW_INVENTORY, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 160, 40));

        MAKE_SALE.setBackground(new java.awt.Color(0, 0, 0));
        MAKE_SALE.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        MAKE_SALE.setForeground(new java.awt.Color(255, 255, 255));
        MAKE_SALE.setText("MAKE SALE");
        jPanel1.add(MAKE_SALE, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 160, 40));

        MANAGE_SALE.setBackground(new java.awt.Color(0, 0, 0));
        MANAGE_SALE.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        MANAGE_SALE.setForeground(new java.awt.Color(255, 255, 255));
        MANAGE_SALE.setText("MANAGE SALES");
        jPanel1.add(MANAGE_SALE, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 160, 40));

        APPROVE_REGISTER.setBackground(new java.awt.Color(0, 0, 0));
        APPROVE_REGISTER.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        APPROVE_REGISTER.setForeground(new java.awt.Color(255, 255, 255));
        APPROVE_REGISTER.setText("APPROVE REGISTER");
        jPanel1.add(APPROVE_REGISTER, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 160, 40));

        LOGOUT.setBackground(new java.awt.Color(0, 0, 0));
        LOGOUT.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        LOGOUT.setForeground(new java.awt.Color(255, 255, 255));
        LOGOUT.setText("LOG OUT");
        LOGOUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGOUTActionPerformed(evt);
            }
        });
        jPanel1.add(LOGOUT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 160, 40));

        jLabel1.setFont(new java.awt.Font("Sitka Display", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("WELCOME TO ADMIN DASH BOARD");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, 380, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/6.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 530));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents
   

   

    private void MANAGE_PRODUCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MANAGE_PRODUCTActionPerformed
       MANAGE_PRODUCT_DASH manageProduct = new MANAGE_PRODUCT_DASH();
    manageProduct.setVisible(true);
    this.dispose(); // Close the current ADMIN_DASH window

    }//GEN-LAST:event_MANAGE_PRODUCTActionPerformed

    private void LOGOUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGOUTActionPerformed
                   LOG_IN login = new LOG_IN();
    login.setVisible(true);
              // TODO add your handling code here:
    }//GEN-LAST:event_LOGOUTActionPerformed

    private void VIEW_INVENTORYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VIEW_INVENTORYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VIEW_INVENTORYActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ADMIN_DASH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ADMIN_DASH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ADMIN_DASH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ADMIN_DASH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                User user = null;
               new ADMIN_DASH(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton APPROVE_REGISTER;
    private javax.swing.JButton LOGOUT;
    private javax.swing.JButton MAKE_SALE;
    private javax.swing.JButton MANAGE_PRODUCT;
    private javax.swing.JButton MANAGE_SALE;
    private javax.swing.JButton VIEW_INVENTORY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
