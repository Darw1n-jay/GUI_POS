/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test_package;

        import javax.swing.JOptionPane;
        import java.sql.*;
        import db.DBConnection;
import logic.RegisterValidator;

public class REGISTER_DASH extends javax.swing.JFrame {

    public REGISTER_DASH() {
        initComponents();
        setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        USER_NAME = new javax.swing.JTextField();
        LOGIN = new javax.swing.JButton();
        CREATE = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        PASSWORD = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        USER_NAME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                USER_NAMEActionPerformed(evt);
            }
        });
        jPanel1.add(USER_NAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 170, 29));

        LOGIN.setBackground(new java.awt.Color(0, 0, 0));
        LOGIN.setFont(new java.awt.Font("Sitka Display", 1, 12)); // NOI18N
        LOGIN.setForeground(new java.awt.Color(255, 255, 255));
        LOGIN.setText("LOG IN");
        LOGIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGINActionPerformed(evt);
            }
        });
        jPanel1.add(LOGIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(389, 450, 80, -1));

        CREATE.setBackground(new java.awt.Color(0, 0, 0));
        CREATE.setFont(new java.awt.Font("Sitka Display", 1, 12)); // NOI18N
        CREATE.setForeground(new java.awt.Color(255, 255, 255));
        CREATE.setText("CREATE");
        CREATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CREATEActionPerformed(evt);
            }
        });
        jPanel1.add(CREATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 450, -1, -1));

        jLabel1.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ENTER NAME");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, 90, 30));

        jLabel2.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ENETER PASSWORD");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 130, 30));

        jLabel4.setFont(new java.awt.Font("Sitka Display", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CREATE AN ACCOUNT");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 230, 50));
        jPanel1.add(PASSWORD, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 410, 170, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/3.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 500));

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
    }// </editor-fold>//GEN-END:initComponents

    private void CREATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CREATEActionPerformed
      String username = USER_NAME.getText().trim();
    String password = new String(PASSWORD.getPassword()).trim();

    // ---- VALIDATION ----
    if (username.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Username is required");
        USER_NAME.requestFocus();
        return;
    }

    if (password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Password is required");
        PASSWORD.requestFocus();
        return;
    }

    if (password.length() < 6) {
        JOptionPane.showMessageDialog(this, "Password must be at least 6 characters");
        PASSWORD.requestFocus();
        return;
    }

    // ---- REGISTER CASHIER ----
    String msg = RegisterValidator.registerCashier(username, password);
    JOptionPane.showMessageDialog(this, msg);

    // ---- CLEAR FIELDS ON SUCCESS ----
    if (msg.toLowerCase().contains("success")) {
        USER_NAME.setText("");
        PASSWORD.setText("");
    }

    }//GEN-LAST:event_CREATEActionPerformed

    private void LOGINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGINActionPerformed
       LOG_IN LoginDash = new LOG_IN();
         LoginDash.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_LOGINActionPerformed

    private void USER_NAMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_USER_NAMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_USER_NAMEActionPerformed

    private void backToLogin() {
        new LOG_IN().setVisible(true);
        dispose();
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new REGISTER_DASH().setVisible(true);
        });
    
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
            java.util.logging.Logger.getLogger(REGISTER_DASH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(REGISTER_DASH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(REGISTER_DASH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(REGISTER_DASH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new REGISTER_DASH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CREATE;
    private javax.swing.JButton LOGIN;
    private javax.swing.JPasswordField PASSWORD;
    private javax.swing.JTextField USER_NAME;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
