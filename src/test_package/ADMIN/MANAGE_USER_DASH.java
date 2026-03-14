package test_package.ADMIN;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import model.User;
import dao.UserDao;

public class MANAGE_USER_DASH extends javax.swing.JFrame {

    public MANAGE_USER_DASH() {
        initComponents();
        loadUsers();
    }

    private void searchById() {
        String text = txtSearchId.getText().trim();
        DefaultTableModel tableModel = (DefaultTableModel) userTable.getModel();
        tableModel.setRowCount(0);
        try {
            List<User> users = UserDao.getAllUsers();
            for (User u : users) {
                if (text.isEmpty() || String.valueOf(u.id).contains(text)) {
                    tableModel.addRow(new Object[]{u.id, u.username, u.role});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void loadUsers() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) userTable.getModel();
            tableModel.setRowCount(0);
            
            List<User> users = UserDao.getAllUsers();
            for (User u : users) {
                tableModel.addRow(new Object[]{u.id, u.username, u.role});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading users: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearchId = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lblSearch = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Username", "Role"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(userTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 750, 300));

        // Search bar
        lblSearch.setFont(new java.awt.Font("Sitka Display", 1, 14));
        lblSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblSearch.setText("Search User ID:");
        jPanel1.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 65, 130, 30));

        txtSearchId.setFont(new java.awt.Font("Sitka Display", 0, 14));
        jPanel1.add(txtSearchId, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 65, 150, 30));

        btnSearch.setBackground(new java.awt.Color(0, 0, 0));
        btnSearch.setFont(new java.awt.Font("Sitka Display", 1, 13));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(evt -> searchById());
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 65, 100, 30));

        btnDelete.setBackground(new java.awt.Color(0, 0, 0));
        btnDelete.setFont(new java.awt.Font("Sitka Display", 1, 14));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("ARCHIVE USER");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 430, 140, 35));

        btnRefresh.setBackground(new java.awt.Color(0, 0, 0));
        btnRefresh.setFont(new java.awt.Font("Sitka Display", 1, 14));
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 430, 120, 35));

        btnBack.setBackground(new java.awt.Color(0, 0, 0));
        btnBack.setFont(new java.awt.Font("Sitka Display", 1, 14));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 120, 35));

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

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int row = userTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a user to archive");
                return;
            }
            
            int id = (int) userTable.getValueAt(row, 0);
            String username = (String) userTable.getValueAt(row, 1);
            
            if (username.equals("admin")) {
                JOptionPane.showMessageDialog(this, "Cannot archive admin user!");
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, "Archive user: " + username + "?");
            if (confirm == JOptionPane.YES_OPTION) {
                UserDao.deleteUser(id);
                JOptionPane.showMessageDialog(this, "User archived successfully!");
                loadUsers();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {
        loadUsers();
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
            java.util.logging.Logger.getLogger(MANAGE_USER_DASH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MANAGE_USER_DASH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable userTable;
    private javax.swing.JTextField txtSearchId;
    // End of variables declaration//GEN-END:variables
}
