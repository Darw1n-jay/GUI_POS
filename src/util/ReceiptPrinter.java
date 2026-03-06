package util;

import model.*;
import dao.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;

public class ReceiptPrinter {
    
    public static String generateReceipt(int saleId) {
        StringBuilder receipt = new StringBuilder();
        
        try {
            receipt.append("========================================\n");
            receipt.append("           POS SYSTEM RECEIPT           \n");
            receipt.append("========================================\n\n");
            
            // Get sale info with error handling
            Sale sale = null;
            try {
                sale = getSaleById(saleId);
            } catch (Exception e) {
                return "Error: Unable to retrieve sale information.\nSale ID: " + saleId + "\nError: " + e.getMessage();
            }
            
            if (sale == null) {
                return "Sale not found! Sale ID: " + saleId;
            }
            
            receipt.append("Sale ID: ").append(sale.id).append("\n");
            
            // Handle timestamp with multiple fallbacks
            String dateStr = "N/A";
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (sale.saleDate != null) {
                    dateStr = sdf.format(sale.saleDate);
                } else {
                    dateStr = sdf.format(new Date());
                }
            } catch (Exception dateEx) {
                try {
                    // Try a simpler date format
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    dateStr = sdf.format(new Date());
                } catch (Exception dateEx2) {
                    dateStr = new Date().toString();
                }
            }
            receipt.append("Date: ").append(dateStr).append("\n");
            
            receipt.append("Cashier ID: ").append(sale.cashierId).append("\n");
            
            // Handle payment method safely
            String paymentMethod = "Cash";
            try {
                if (sale.paymentMethod != null && !sale.paymentMethod.trim().isEmpty()) {
                    paymentMethod = sale.paymentMethod;
                }
            } catch (Exception e) {
                // Use default
            }
            receipt.append("Payment Method: ").append(paymentMethod).append("\n\n");
            
            receipt.append("----------------------------------------\n");
            receipt.append(String.format("%-20s %5s %8s %10s\n", "Product", "Qty", "Price", "Subtotal"));
            receipt.append("----------------------------------------\n");
            
            // Get sale items with error handling
            List<SaleItem> items = null;
            try {
                items = SaleDao.getSaleItems(saleId);
            } catch (Exception e) {
                receipt.append("Error loading sale items: ").append(e.getMessage()).append("\n");
                items = new ArrayList<>();
            }
            
            if (items != null && !items.isEmpty()) {
                for (SaleItem item : items) {
                    try {
                        Product product = null;
                        String productName = "Product #" + item.productId;
                        
                        try {
                            product = ProductDao.getProductById(item.productId);
                            if (product != null && product.name != null) {
                                productName = product.name;
                                if (productName.length() > 20) {
                                    productName = productName.substring(0, 17) + "...";
                                }
                            }
                        } catch (Exception prodEx) {
                            // Use default product name
                        }
                        
                        double subtotal = item.qty * item.price;
                        receipt.append(String.format("%-20s %5d ₱%7.2f ₱%9.2f\n", 
                            productName, 
                            item.qty, 
                            item.price, 
                            subtotal));
                            
                    } catch (Exception itemEx) {
                        receipt.append(String.format("%-20s %5s %8s %10s\n", 
                            "Error loading item", "N/A", "N/A", "N/A"));
                    }
                }
            } else {
                receipt.append("No items found for this sale.\n");
            }
            
            receipt.append("----------------------------------------\n");
            receipt.append(String.format("%-35s ₱%9.2f\n", "TOTAL:", sale.total));
            receipt.append("========================================\n");
            receipt.append("       Thank you for your purchase!     \n");
            receipt.append("========================================\n");
            
            return receipt.toString();
            
        } catch (Exception e) {
            // Final fallback - create a minimal receipt
            receipt.setLength(0);
            receipt.append("========================================\n");
            receipt.append("           POS SYSTEM RECEIPT           \n");
            receipt.append("========================================\n\n");
            receipt.append("Sale ID: ").append(saleId).append("\n");
            receipt.append("Date: ").append(new Date().toString()).append("\n");
            receipt.append("Status: Error occurred during receipt generation\n");
            receipt.append("Error: ").append(e.getMessage()).append("\n\n");
            receipt.append("Please contact support for assistance.\n");
            receipt.append("========================================\n");
            return receipt.toString();
        }
    }
    
    // Helper method to get a single sale by ID more efficiently
    private static Sale getSaleById(int saleId) throws Exception {
        return SaleDao.getSaleById(saleId);
    }
}
