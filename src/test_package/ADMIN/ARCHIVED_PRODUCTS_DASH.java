package test_package.ADMIN;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import model.Product;
import dao.ProductDao;
import logic.User;
import util.AppUI;

public class ARCHIVED_PRODUCTS_DASH extends javax.swing.JFrame {

    private User currentUser;

    public ARCHIVED_PRODUCTS_DASH(User user) {
        this.currentUser = user;
        initComponents();
        AppUI.setupFrame(this, "Archived Products", true);
        AppUI.styleTable(archiveTable);
        loadArchived();
    }

    private void loadArchived() {
        try {
            DefaultTableModel model = (DefaultTableModel) archiveTable.getModel();
            model.setRowCount(0);
            List<Product> products = ProductDao.getArchivedProducts();
            for (Product p : products) {
                model.addRow(new Object[]{p.id, p.name, p.category, p.price, p.stock});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        archiveTable = new javax.swing.JTable();
        btnRestore = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 30, 30));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        archiveTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Name", "Category", "Price", "Stock"}
        ) {
            public boolean isCellEditable(int row, int col) { return false; }
        });
        jScrollPane1.setViewportView(archiveTable);
        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 880, 350));

        jLabel1.setFont(new java.awt.Font("Sitka Display", 1, 22));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ARCHIVED PRODUCTS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 320, 40));

        btnRestore.setBackground(new java.awt.Color(0, 153, 76));
        btnRestore.setFont(new java.awt.Font("Sitka Display", 1, 13));
        btnRestore.setForeground(new java.awt.Color(255, 255, 255));
        btnRestore.setText("RESTORE");
        btnRestore.addActionListener(evt -> btnRestoreActionPerformed());
        jPanel1.add(btnRestore, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 460, 120, 35));

        btnBack.setBackground(new java.awt.Color(80, 80, 80));
        btnBack.setFont(new java.awt.Font("Sitka Display", 1, 13));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("BACK");
        btnBack.addActionListener(evt -> btnBackActionPerformed());
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 460, 100, 35));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        pack();
    }

    private void btnRestoreActionPerformed() {
        try {
            int row = archiveTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a product to restore.");
                return;
            }
            int id = (int) archiveTable.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Restore this product?");
            if (confirm == JOptionPane.YES_OPTION) {
                ProductDao.restoreProduct(id);
                JOptionPane.showMessageDialog(this, "Product restored successfully!");
                loadArchived();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void btnBackActionPerformed() {
        new test_package.ADMIN_DASH(currentUser).setVisible(true);
        this.dispose();
    }

    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable archiveTable;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
}
