package test_package.ADMIN;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import model.User;
import dao.UserDao;
import util.AppUI;

public class USER_MANAGEMENT_DASH extends javax.swing.JFrame {

    public USER_MANAGEMENT_DASH() {
        initComponents();
        AppUI.setupFrame(this, "User Management - Coffee Shop POS", true);
        loadAllUsers();
    }
    
    private void loadAllUsers() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) userTable.getModel();
            tableModel.setRowCount(0);
            
            List<User> users = UserDao.getAllUsers();
            for (User u : users) {
                String status = getApprovalStatus(u.id);
                tableModel.addRow(new Object[]{u.id, u.username, u.role, status});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading users: " + e.getMessage());
        }
    }
    
    private void loadPendingUsers() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) userTable.getModel();
            tableModel.setRowCount(0);
            
            List<User> users = UserDao.getPendingUsers();
            for (User u : users) {
                tableModel.addRow(new Object[]{u.id, u.username, u.role, "Pending"});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading pending users: " + e.getMessage());
        }
    }
    
    private String getApprovalStatus(int userId) {
        try {
            List<User> pendingUsers = UserDao.getPendingUsers();
            for (User u : pendingUsers) {
                if (u.id == userId) {
                    return "Pending";
                }
            }
            return "Approved";
        } catch (Exception e) {
            return "Unknown";
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        btnApprove = new javax.swing.JButton();
        btnReject = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnShowAll = new javax.swing.JButton();
        btnShowPending = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Username", "Role", "Status"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(userTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 850, 280));

        btnApprove.setBackground(new java.awt.Color(0, 128, 0));
        btnApprove.setFont(new java.awt.Font("Sitka Display", 1, 12));
        btnApprove.setForeground(new java.awt.Color(255, 255, 255));
        btnApprove.setText("APPROVE");
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });
        jPanel1.add(btnApprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 100, 35));

        btnReject.setBackground(new java.awt.Color(255, 102, 0));
        btnReject.setFont(new java.awt.Font("Sitka Display", 1, 12));
        btnReject.setForeground(new java.awt.Color(255, 255, 255));
        btnReject.setText("REJECT");
        btnReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectActionPerformed(evt);
            }
        });
        jPanel1.add(btnReject, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, 100, 35));

        btnDelete.setBackground(new java.awt.Color(204, 0, 0));
        btnDelete.setFont(new java.awt.Font("Sitka Display", 1, 12));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, 100, 35));

        btnRefresh.setBackground(new java.awt.Color(0, 0, 0));
        btnRefresh.setFont(new java.awt.Font("Sitka Display", 1, 12));
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 400, 100, 35));

        btnShowAll.setBackground(new java.awt.Color(0, 0, 0));
        btnShowAll.setFont(new java.awt.Font("Sitka Display", 1, 12));
        btnShowAll.setForeground(new java.awt.Color(255, 255, 255));
        btnShowAll.setText("SHOW ALL");
        btnShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAllActionPerformed(evt);
            }
        });
        jPanel1.add(btnShowAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, 100, 35));

        btnShowPending.setBackground(new java.awt.Color(0, 0, 0));
        btnShowPending.setFont(new java.awt.Font("Sitka Display", 1, 12));
        btnShowPending.setForeground(new java.awt.Color(255, 255, 255));
        btnShowPending.setText("PENDING ONLY");
        btnShowPending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowPendingActionPerformed(evt);
            }
        });
        jPanel1.add(btnShowPending, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 400, 120, 35));

        btnBack.setBackground(new java.awt.Color(0, 0, 0));
        btnBack.setFont(new java.awt.Font("Sitka Display", 1, 12));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 400, 100, 35));

        jLabel1.setFont(new java.awt.Font("Sitka Display", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("USER MANAGEMENT");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 280, 40));

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

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int row = userTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a user to approve");
                return;
            }
            
            int id = (int) userTable.getValueAt(row, 0);
            String status = (String) userTable.getValueAt(row, 3);
            
            if ("Approved".equals(status)) {
                JOptionPane.showMessageDialog(this, "User is already approved!");
                return;
            }
            
            UserDao.approve(id);
            JOptionPane.showMessageDialog(this, "User approved successfully!");
            loadAllUsers();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void btnRejectActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int row = userTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a user to reject");
                return;
            }
            
            int id = (int) userTable.getValueAt(row, 0);
            String username = (String) userTable.getValueAt(row, 1);
            String status = (String) userTable.getValueAt(row, 3);
            
            if ("Approved".equals(status)) {
                JOptionPane.showMessageDialog(this, "Cannot reject an already approved user! Use DELETE instead.");
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, "Reject user: " + username + "?");
            if (confirm == JOptionPane.YES_OPTION) {
                UserDao.deleteUser(id);
                JOptionPane.showMessageDialog(this, "User rejected!");
                loadAllUsers();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int row = userTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a user to delete");
                return;
            }
            
            int id = (int) userTable.getValueAt(row, 0);
            String username = (String) userTable.getValueAt(row, 1);
            
            if (username.equals("admin")) {
                JOptionPane.showMessageDialog(this, "Cannot delete admin user!");
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, "Delete user: " + username + "?\nThis action cannot be undone!");
            if (confirm == JOptionPane.YES_OPTION) {
                UserDao.deleteUser(id);
                JOptionPane.showMessageDialog(this, "User deleted successfully!");
                loadAllUsers();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {
        loadAllUsers();
    }

    private void btnShowAllActionPerformed(java.awt.event.ActionEvent evt) {
        loadAllUsers();
    }

    private void btnShowPendingActionPerformed(java.awt.event.ActionEvent evt) {
        loadPendingUsers();
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
            java.util.logging.Logger.getLogger(USER_MANAGEMENT_DASH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new USER_MANAGEMENT_DASH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnReject;
    private javax.swing.JButton btnShowAll;
    private javax.swing.JButton btnShowPending;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}