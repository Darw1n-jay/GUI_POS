package test_package.ADMIN;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.text.SimpleDateFormat;
import model.Sale;
import model.SaleItem;
import model.Product;
import dao.SaleDao;
import dao.ProductDao;

public class MANAGE_SALES_DASH extends javax.swing.JFrame {

    public MANAGE_SALES_DASH() {
        initComponents();
        loadSales();
    }

    private void searchBySaleId() {
        String text = txtSearchId.getText().trim();
        DefaultTableModel model = (DefaultTableModel) salesTable.getModel();
        model.setRowCount(0);
        try {
            List<Sale> sales = SaleDao.getAllSales();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Sale s : sales) {
                if (text.isEmpty() || String.valueOf(s.id).contains(text)) {
                    model.addRow(new Object[]{
                        s.id,
                        s.cashierId,
                        String.format("₱%.2f", s.total),
                        s.paymentMethod != null ? s.paymentMethod : "Cash",
                        sdf.format(s.saleDate)
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error searching: " + e.getMessage());
        }
    }

    private void loadSales() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) salesTable.getModel();
            tableModel.setRowCount(0);
            
            List<Sale> sales = SaleDao.getAllSales();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            for (Sale s : sales) {
                tableModel.addRow(new Object[]{
                    s.id, 
                    s.cashierId, 
                    String.format("₱%.2f", s.total),
                    s.paymentMethod != null ? s.paymentMethod : "Cash",
                    sdf.format(s.saleDate)
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading sales: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        salesTable = new javax.swing.JTable();
        btnViewDetails = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearchId = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lblSearch = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        salesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Sale ID", "Cashier ID", "Total", "Payment Method", "Date"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(salesTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 850, 280));

        // Search bar
        lblSearch.setFont(new java.awt.Font("Sitka Display", 1, 14));
        lblSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblSearch.setText("Search Sale ID:");
        jPanel1.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 65, 130, 30));

        txtSearchId.setFont(new java.awt.Font("Sitka Display", 0, 14));
        jPanel1.add(txtSearchId, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 65, 150, 30));

        btnSearch.setBackground(new java.awt.Color(0, 0, 0));
        btnSearch.setFont(new java.awt.Font("Sitka Display", 1, 13));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(evt -> searchBySaleId());
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 65, 100, 30));

        btnViewDetails.setBackground(new java.awt.Color(0, 0, 0));
        btnViewDetails.setFont(new java.awt.Font("Sitka Display", 1, 14));
        btnViewDetails.setForeground(new java.awt.Color(255, 255, 255));
        btnViewDetails.setText("VIEW DETAILS");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });
        jPanel1.add(btnViewDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 140, 35));

        btnRefresh.setBackground(new java.awt.Color(0, 0, 0));
        btnRefresh.setFont(new java.awt.Font("Sitka Display", 1, 14));
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 120, 35));

        btnBack.setBackground(new java.awt.Color(0, 0, 0));
        btnBack.setFont(new java.awt.Font("Sitka Display", 1, 14));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 400, 120, 35));

        jLabel1.setFont(new java.awt.Font("Sitka Display", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SALES MANAGEMENT");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 300, 40));

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

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int row = salesTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a sale to view details");
                return;
            }
            
            int saleId = (int) salesTable.getValueAt(row, 0);
            Sale sale = SaleDao.getSaleById(saleId);
            List<SaleItem> items = SaleDao.getSaleItems(saleId);
            
            StringBuilder details = new StringBuilder();
            details.append("=== SALE DETAILS ===\n\n");
            details.append("Sale ID: ").append(sale.id).append("\n");
            details.append("Cashier ID: ").append(sale.cashierId).append("\n");
            details.append("Payment Method: ").append(sale.paymentMethod != null ? sale.paymentMethod : "Cash").append("\n");
            details.append("Total: ₱").append(String.format("%.2f", sale.total)).append("\n");
            details.append("Date: ").append(sale.saleDate).append("\n\n");
            
            details.append("=== ITEMS ===\n\n");
            double totalCheck = 0.0;
            for (SaleItem item : items) {
                Product product = ProductDao.getProductById(item.productId);
                double subtotal = item.qty * item.price;
                totalCheck += subtotal;
                details.append(String.format("• %s\n", product.name));
                details.append(String.format("  Qty: %d × ₱%.2f = ₱%.2f\n\n", 
                    item.qty, item.price, subtotal));
            }
            
            details.append("===================\n");
            details.append(String.format("Total: ₱%.2f", totalCheck));
            
            JTextArea textArea = new JTextArea(details.toString());
            textArea.setEditable(false);
            textArea.setFont(new java.awt.Font("Monospaced", 0, 12));
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(400, 350));
            
            JOptionPane.showMessageDialog(this, scrollPane, "Sale Details - #" + saleId, JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {
        loadSales();
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
            java.util.logging.Logger.getLogger(MANAGE_SALES_DASH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MANAGE_SALES_DASH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable salesTable;
    private javax.swing.JTextField txtSearchId;
    // End of variables declaration//GEN-END:variables
}
