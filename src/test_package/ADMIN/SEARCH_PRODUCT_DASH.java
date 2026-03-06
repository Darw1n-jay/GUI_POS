package test_package.ADMIN;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import model.Product;
import dao.ProductDao;

public class SEARCH_PRODUCT_DASH extends javax.swing.JFrame {

    public SEARCH_PRODUCT_DASH() {
        initComponents();
    }
    
    private void searchProducts(String keyword) {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) productTable.getModel();
            tableModel.setRowCount(0);
            
            List<Product> products = ProductDao.getAllProducts();
            int count = 0;
            
            for (Product p : products) {
                if (p.name.toLowerCase().contains(keyword.toLowerCase())) {
                    tableModel.addRow(new Object[]{p.id, p.name, p.price, p.stock});
                    count++;
                }
            }
            
            lblResults.setText("Results found: " + count);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error searching: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lblResults = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Product Name", "Price", "Stock"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(productTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 750, 250));

        jLabel3.setFont(new java.awt.Font("Sitka Display", 1, 14));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Search Product:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 120, 30));

        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 300, 30));

        btnSearch.setBackground(new java.awt.Color(0, 0, 0));
        btnSearch.setFont(new java.awt.Font("Sitka Display", 1, 14));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 120, 30));

        lblResults.setFont(new java.awt.Font("Sitka Display", 1, 14));
        lblResults.setForeground(new java.awt.Color(255, 255, 255));
        lblResults.setText("Results found: 0");
        jPanel1.add(lblResults, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 300, 30));

        btnBack.setBackground(new java.awt.Color(0, 0, 0));
        btnBack.setFont(new java.awt.Font("Sitka Display", 1, 14));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, 120, 35));

        jLabel1.setFont(new java.awt.Font("Sitka Display", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SEARCH COFFEE PRODUCTS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 250, 40));

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

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search keyword");
            return;
        }
        searchProducts(keyword);
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
            java.util.logging.Logger.getLogger(SEARCH_PRODUCT_DASH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SEARCH_PRODUCT_DASH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblResults;
    private javax.swing.JTable productTable;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
