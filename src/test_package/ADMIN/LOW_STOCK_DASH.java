package test_package.ADMIN;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import model.Product;
import dao.ProductDao;

public class LOW_STOCK_DASH extends javax.swing.JFrame {
    private int threshold = 10;

    public LOW_STOCK_DASH() {
        initComponents();
        loadLowStock();
    }

    private void searchById() {
        String text = txtSearchId.getText().trim();
        DefaultTableModel tableModel = (DefaultTableModel) stockTable.getModel();
        tableModel.setRowCount(0);
        try {
            List<Product> products = ProductDao.getAllProducts();
            for (Product p : products) {
                if (p.stock <= threshold && (text.isEmpty() || String.valueOf(p.id).contains(text))) {
                    tableModel.addRow(new Object[]{p.id, p.name, p.price, p.stock});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void loadLowStock() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) stockTable.getModel();
            tableModel.setRowCount(0);
            
            List<Product> products = ProductDao.getAllProducts();
            int lowStockCount = 0;
            
            for (Product p : products) {
                if (p.stock <= threshold) {
                    tableModel.addRow(new Object[]{p.id, p.name, p.price, p.stock});
                    lowStockCount++;
                }
            }
            
            lblLowStockCount.setText("Low Stock Items: " + lowStockCount);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading low stock: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();
        lblLowStockCount = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtThreshold = new javax.swing.JTextField();
        btnSetThreshold = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearchId = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lblSearch = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Product Name", "Price", "Stock"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(stockTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 750, 250));

        // Search bar
        lblSearch.setFont(new java.awt.Font("Sitka Display", 1, 14));
        lblSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblSearch.setText("Search Product ID:");
        jPanel1.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 65, 155, 30));

        txtSearchId.setFont(new java.awt.Font("Sitka Display", 0, 14));
        jPanel1.add(txtSearchId, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 65, 150, 30));

        btnSearch.setBackground(new java.awt.Color(0, 0, 0));
        btnSearch.setFont(new java.awt.Font("Sitka Display", 1, 13));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(evt -> searchById());
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 65, 100, 30));

        lblLowStockCount.setFont(new java.awt.Font("Sitka Display", 1, 16));
        lblLowStockCount.setForeground(new java.awt.Color(255, 255, 255));
        lblLowStockCount.setText("Low Stock Items: 0");
        jPanel1.add(lblLowStockCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 300, 30));

        jLabel3.setFont(new java.awt.Font("Sitka Display", 1, 12));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Threshold:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, 70, 30));

        txtThreshold.setText("10");
        jPanel1.add(txtThreshold, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 375, 60, -1));

        btnSetThreshold.setBackground(new java.awt.Color(0, 0, 0));
        btnSetThreshold.setFont(new java.awt.Font("Sitka Display", 1, 12));
        btnSetThreshold.setForeground(new java.awt.Color(255, 255, 255));
        btnSetThreshold.setText("SET");
        btnSetThreshold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetThresholdActionPerformed(evt);
            }
        });
        jPanel1.add(btnSetThreshold, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 370, 70, 30));

        btnRefresh.setBackground(new java.awt.Color(0, 0, 0));
        btnRefresh.setFont(new java.awt.Font("Sitka Display", 1, 14));
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 430, 120, 35));

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
        jLabel1.setText("LOW STOCK ALERT");
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

    private void btnSetThresholdActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            threshold = Integer.parseInt(txtThreshold.getText().trim());
            loadLowStock();
            JOptionPane.showMessageDialog(this, "Threshold updated to: " + threshold);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid threshold value");
        }
    }

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {
        loadLowStock();
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
            java.util.logging.Logger.getLogger(LOW_STOCK_DASH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LOW_STOCK_DASH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSetThreshold;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblLowStockCount;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable stockTable;
    private javax.swing.JTextField txtThreshold;
    private javax.swing.JTextField txtSearchId;
    // End of variables declaration//GEN-END:variables
}
