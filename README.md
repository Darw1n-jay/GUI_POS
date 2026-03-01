# Point of Sale (POS) System

A complete Java Swing-based Point of Sale system with admin and cashier roles, featuring comprehensive inventory management, sales tracking, reporting, and user management.

## Complete Features

### Admin Features
- **Dashboard Summary** - View key metrics (total products, today's sales, revenue, low stock alerts, pending approvals)
- **Manage Products** - Add, Update, Delete products with real-time inventory tracking
- **View Inventory** - Complete inventory overview with stock levels
- **Make Sale** - Create sales transactions (admin can also act as cashier)
- **Manage Sales** - View all sales with detailed transaction history
- **Sales Report** - Daily sales reports with revenue analytics
- **Approve Registrations** - Approve or reject pending cashier registrations
- **Manage Users** - View and delete users (with admin protection)
- **Low Stock Alert** - Monitor products below threshold with customizable alerts
- **Search Products** - Quick product search functionality

### Cashier Features
- **Create Sales** - Full POS interface with shopping cart
  - Add products to cart with quantity selection
  - Real-time total calculation
  - Stock validation
  - Automatic receipt generation
  - Stock deduction on sale completion
- **View My Sales History** - Personal sales records with details
- **View Product List** - Browse available products with prices and stock
- **Receipt Printing** - Formatted receipts for each transaction

## Advanced Features

### Receipt System
- Automatic receipt generation after each sale
- Formatted display with:
  - Sale ID and timestamp
  - Cashier information
  - Itemized product list with quantities and prices
  - Subtotals and grand total
  - Professional layout

### Inventory Management
- Real-time stock updates
- Low stock alerts with customizable threshold
- Product search functionality
- Complete CRUD operations

### Sales Analytics
- Daily sales reports
- Revenue tracking
- Transaction history
- Sale detail views with itemized breakdowns

### User Management
- Role-based access (Admin/Cashier)
- Registration approval workflow
- User deletion with admin protection
- Secure login system

## Database Setup

1. Import the fixed database schema:
   ```bash
   mysql -u root -p < pos_db_fixed.sql
   ```

2. Or manually create the database using the SQL file `pos_db_fixed.sql`

3. Default admin credentials:
   - Username: `admin`
   - Password: `admin123`

## Database Configuration

Update the database connection settings in `src/db/DBConnection.java`:
```java
private static final String URL = "jdbc:mysql://localhost:3306/pos_db?useSSL=false";
private static final String USER = "root"; // your DB username
private static final String PASSWORD = ""; // your DB password
```

## Project Structure

```
src/
├── dao/              # Data Access Objects
│   ├── ProductDao.java
│   ├── SaleDao.java
│   └── UserDao.java
├── db/               # Database Connection
│   └── DBConnection.java
├── logic/            # Business Logic
│   ├── AuthValidator.java
│   ├── LoginResult.java
│   ├── RegisterValidator.java
│   └── User.java
├── model/            # Data Models
│   ├── Product.java
│   ├── Sale.java
│   ├── SaleItem.java
│   └── User.java
├── util/             # Utilities
│   └── ReceiptPrinter.java
└── test_package/     # GUI Components
    ├── ADMIN/
    │   ├── APPROVE_DASH.java
    │   ├── DASHBOARD_SUMMARY.java
    │   ├── LOW_STOCK_DASH.java
    │   ├── MANAGE_PRODUCT_DASH.java
    │   ├── MANAGE_SALES_DASH.java
    │   ├── MANAGE_USER_DASH.java
    │   ├── SALES_REPORT_DASH.java
    │   ├── SEARCH_PRODUCT_DASH.java
    │   └── VIEW_INVE_DASH.java
    ├── CASHIER/
    │   ├── CREATE_SALE_DASH.java
    │   ├── MY_PRODUCTS_DASH.java
    │   └── MY_SALES_DASH.java
    ├── ADMIN_DASH.java
    ├── CASHIER_DASH.java
    ├── LOG_IN.java
    └── REGISTER_DASH.java
```

## How to Run

1. Ensure MySQL is running
2. Import the database schema
3. Update database credentials in DBConnection.java
4. Build and run the project from NetBeans or your IDE
5. Main class: `test_package.LOG_IN`

## User Workflow

### For New Cashiers:
1. Click "REGISTER" on login screen
2. Enter username and password
3. Wait for admin approval
4. Login after approval

### For Admin:
1. Login with admin credentials
2. Approve pending registrations
3. Manage products, sales, and users
4. Can also create sales

### For Cashiers:
1. Login with approved credentials
2. Create sales by adding products to cart
3. View sales history
4. View available products

## GUI Design

The system uses a consistent design pattern:
- Black buttons with white text
- Sitka Display font
- Background images (3.png for login/register, 6.png for dashboards)
- Absolute layout positioning

## Complete Feature List

### Admin Dashboard (8 Main Features + Dashboard Summary)
1. **Dashboard Summary** - Real-time business metrics
2. **Manage Products** - Full CRUD operations
3. **View Inventory** - Stock monitoring
4. **Make Sale** - POS functionality
5. **Manage Sales** - Transaction management
6. **Approve Registrations** - User approval workflow
7. **Manage Users** - User administration
8. **Sales Report** - Analytics and reporting
9. **Low Stock Alert** - Inventory warnings
10. **Search Products** - Quick product lookup

### Cashier Dashboard (3 Main Features)
1. **Create Sale** - Complete POS with cart and receipt
2. **My Sales History** - Personal transaction records
3. **Product List** - Available inventory view

### System Features
- User authentication with role-based access
- Automatic stock management
- Receipt generation and printing
- Real-time inventory updates
- Sales analytics and reporting
- Low stock monitoring
- User approval workflow
- Secure login system
- Data validation
- Error handling

## Technologies Used

- Java Swing for GUI
- MySQL for database
- JDBC for database connectivity
- NetBeans IDE for development

## Notes

- The original database schema had a typo ("passwrod" instead of "password") which has been fixed in `pos_db_fixed.sql`
- All passwords are stored in plain text (for production, implement proper password hashing)
- Stock is automatically updated when sales are completed
- Only approved users can login
