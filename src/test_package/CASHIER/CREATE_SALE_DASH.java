package test_package.CASHIER;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import logic.User;
import model.Product;
import dao.ProductDao;
import dao.SaleDao;

public class CREATE_SALE_DASH extends javax.swing.JFrame {
    private User currentUser;
    private DefaultTableModel cartModel;
    private double totalAmount = 0.0;

    public CREATE_SALE_DASH(User user) {
        this.currentUser = user;
        initComponents();
        loadProducts();
        setupCart();
    }
    
    private void setupCart() {
        cartModel = (DefaultTableModel) cartTable.getModel();
    }
    
    private void loadProducts() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) productTable.getModel();
            tableModel.setRowCount(0);
            
            List<Product> products = ProductDao.getAllProducts();
            for (Product p : products) {
                if (p.stock > 0) {
                    tableModel.addRow(new Object[]{p.id, p.name, p.price, p.stock});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        btnAddToCart = new javax.swing.JButton();
        btnRemoveFromCart = new javax.swing.JButton();
        btnCompleteSale = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Product", "Price", "Stock"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(productTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 400, 200));

        cartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Product ID", "Product", "Qty", "Price", "Subtotal"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(cartTable);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 440, 200));

        jLabel1.setFont(new java.awt.Font("Sitka Display", 1, 14));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Available Products");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel2.setFont(new java.awt.Font("Sitka Display", 1, 14));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Shopping Cart");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Sitka Display", 1, 12));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Quantity:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        txtQty.setText("1");
        jPanel1.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 80, -1));

        btnAddToCart.setBackground(new java.awt.Color(0, 0, 0));
        btnAddToCart.setFont(new java.awt.Font("Sitka Display", 1, 12));
        btnAddToCart.setForeground(new java.awt.Color(255, 255, 255));
        btnAddToCart.setText("ADD TO CART");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 295, 130, 30));

        btnRemoveFromCart.setBackground(new java.awt.Color(0, 0, 0));
        btnRemoveFromCart.setFont(new java.awt.Font("Sitka Display", 1, 12));
        btnRemoveFromCart.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoveFromCart.setText("REMOVE");
        btnRemoveFromCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFromCartActionPerformed(evt);
            }
        });
        jPanel1.add(btnRemoveFromCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 295, 120, 30));

        btnCompleteSale.setBackground(new java.awt.Color(0, 0, 0));
        btnCompleteSale.setFont(new java.awt.Font("Sitka Display", 1, 14));
        btnCompleteSale.setForeground(new java.awt.Color(255, 255, 255));
        btnCompleteSale.setText("COMPLETE SALE");
        btnCompleteSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteSaleActionPerformed(evt);
            }
        });
        jPanel1.add(btnCompleteSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 380, 160, 40));

        btnBack.setBackground(new java.awt.Color(0, 0, 0));
        btnBack.setFont(new java.awt.Font("Sitka Display", 1, 12));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 100, 30));

        lblTotal.setFont(new java.awt.Font("Sitka Display", 1, 18));
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setText("Total: $0.00");
        jPanel1.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 340, 200, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1.png")));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 530));

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

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int row = productTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a product");
                return;
            }
            
            int productId = (int) productTable.getValueAt(row, 0);
            String productName = (String) productTable.getValueAt(row, 1);
            double price = (double) productTable.getValueAt(row, 2);
            int stock = (int) productTable.getValueAt(row, 3);
            int qty = Integer.parseInt(txtQty.getText().trim());
            
            if (qty <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than 0");
                return;
            }
            
            if (qty > stock) {
                JOptionPane.showMessageDialog(this, "Insufficient stock!");
                return;
            }
            
            double subtotal = price * qty;
            cartModel.addRow(new Object[]{productId, productName, qty, price, subtotal});
            
            totalAmount += subtotal;
            lblTotal.setText(String.format("Total: $%.2f", totalAmount));
            txtQty.setText("1");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void btnRemoveFromCartActionPerformed(java.awt.event.ActionEvent evt) {
        int row = cartTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to remove");
            return;
        }
        
        double subtotal = (double) cartModel.getValueAt(row, 4);
        totalAmount -= subtotal;
        lblTotal.setText(String.format("Total: $%.2f", totalAmount));
        cartModel.removeRow(row);
    }

    private void btnCompleteSaleActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (cartModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Cart is empty!");
                return;
            }
            
            int saleId = SaleDao.createSale(currentUser.id, totalAmount);
            
            for (int i = 0; i < cartModel.getRowCount(); i++) {
                int productId = (int) cartModel.getValueAt(i, 0);
                int qty = (int) cartModel.getValueAt(i, 2);
                double price = (double) cartModel.getValueAt(i, 3);
                
                SaleDao.addSaleItem(saleId, productId, qty, price);
                
                Product product = ProductDao.getProductById(productId);
                ProductDao.updateStock(productId, product.stock - qty);
            }
            
            // Generate and show receipt
            String receipt = util.ReceiptPrinter.generateReceipt(saleId);
            JTextArea textArea = new JTextArea(receipt);
            textArea.setEditable(false);
            textArea.setFont(new java.awt.Font("Monospaced", 0, 12));
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(450, 400));
            
            JOptionPane.showMessageDialog(this, scrollPane, "Receipt - Sale #" + saleId, JOptionPane.INFORMATION_MESSAGE);
            
            cartModel.setRowCount(0);
            totalAmount = 0.0;
            lblTotal.setText("Total: $0.00");
            loadProducts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        new test_package.CASHIER_DASH(currentUser).setVisible(true);
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
            java.util.logging.Logger.getLogger(CREATE_SALE_DASH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CREATE_SALE_DASH(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCompleteSale;
    private javax.swing.JButton btnRemoveFromCart;
    private javax.swing.JTable cartTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable productTable;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables
}
