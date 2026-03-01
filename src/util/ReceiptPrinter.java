package util;

import model.*;
import dao.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReceiptPrinter {
    
    public static String generateReceipt(int saleId) {
        try {
            StringBuilder receipt = new StringBuilder();
            receipt.append("========================================\n");
            receipt.append("           POS SYSTEM RECEIPT           \n");
            receipt.append("========================================\n\n");
            
            // Get sale info
            List<Sale> sales = SaleDao.getAllSales();
            Sale sale = null;
            for (Sale s : sales) {
                if (s.id == saleId) {
                    sale = s;
                    break;
                }
            }
            
            if (sale == null) {
                return "Sale not found!";
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            receipt.append("Sale ID: ").append(sale.id).append("\n");
            receipt.append("Date: ").append(sdf.format(sale.saleDate)).append("\n");
            receipt.append("Cashier ID: ").append(sale.cashierId).append("\n\n");
            
            receipt.append("----------------------------------------\n");
            receipt.append(String.format("%-20s %5s %8s %10s\n", "Product", "Qty", "Price", "Subtotal"));
            receipt.append("----------------------------------------\n");
            
            // Get sale items
            List<SaleItem> items = SaleDao.getSaleItems(saleId);
            for (SaleItem item : items) {
                Product product = ProductDao.getProductById(item.productId);
                double subtotal = item.qty * item.price;
                receipt.append(String.format("%-20s %5d $%7.2f $%9.2f\n", 
                    product.name.substring(0, Math.min(20, product.name.length())), 
                    item.qty, 
                    item.price, 
                    subtotal));
            }
            
            receipt.append("----------------------------------------\n");
            receipt.append(String.format("%-35s $%9.2f\n", "TOTAL:", sale.total));
            receipt.append("========================================\n");
            receipt.append("       Thank you for your purchase!     \n");
            receipt.append("========================================\n");
            
            return receipt.toString();
            
        } catch (Exception e) {
            return "Error generating receipt: " + e.getMessage();
        }
    }
}
