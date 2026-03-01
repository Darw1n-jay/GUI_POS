# POS System - Quick Start Guide

## Prerequisites

1. **Java Development Kit (JDK)** - Version 8 or higher
2. **SQLite JDBC Driver** - Already included in `src/db/sqlite-jdbc-3.30.1.jar`
3. **NetBeans IDE** (recommended) or any Java IDE
4. **Required Libraries**:
   - SQLite JDBC (already included)
   - FlatLaf (for modern UI)

## Setup Instructions

### Step 1: Database Setup (Automatic)

The database is automatically created when you run the application for the first time!

- Database file: `pos.db` (created in project root)
- Tables are auto-created on first run
- Default admin user is auto-inserted

**No manual database setup required!**

### Step 2: Verify SQLite Driver

The SQLite JDBC driver is already included at:
```
src/db/sqlite-jdbc-3.30.1.jar
```

Make sure this JAR is in your project's classpath.

### Step 3: Add Required Libraries

#### Using NetBeans:
1. Right-click on project → Properties
2. Go to Libraries → Add JAR/Folder
3. Add:
   - `src/db/sqlite-jdbc-3.30.1.jar` (SQLite driver)
   - FlatLaf JAR (download from GitHub: JFormDesigner/FlatLaf)

#### Manual Setup:
The SQLite driver is already in the project. Just add FlatLaf if you want the modern UI.

### Step 4: Build the Project

#### Using NetBeans:
1. Open the project in NetBeans
2. Right-click on project name
3. Select "Clean and Build"
4. Wait for build to complete

#### Using Command Line:
```bash
cd GUI_POS
javac -cp "src/db/sqlite-jdbc-3.30.1.jar" -d build/classes src/**/*.java
```

### Step 5: Run the Application

#### Using NetBeans:
1. Right-click on project
2. Select "Run"
3. Login screen should appear
4. Database will be auto-created on first run

#### Using Command Line:
```bash
java -cp "build/classes:src/db/sqlite-jdbc-3.30.1.jar" app.Main
```

On Windows:
```bash
java -cp "build/classes;src/db/sqlite-jdbc-3.30.1.jar" app.Main
```

## First Login

### Default Admin Account
- **Username**: `admin`
- **Password**: `admin123`
- **Role**: ADMIN

The admin account is automatically created on first run!

### Admin Dashboard Features
1. Dashboard Summary - View KPIs and statistics
2. Manage Products - Add, edit, delete products
3. View Inventory - Monitor stock levels
4. Make Sale - Process sales transactions
5. Manage Sales - View all sales history
6. Approve Registrations - Approve new cashier accounts
7. Manage Users - User administration
8. Sales Report - Daily sales analytics
9. Low Stock Alert - Products below threshold
10. Search Product - Find products by keyword
11. Logout

## Creating a Cashier Account

### As Admin:
1. New users register through the registration screen
2. Admin approves them via "Approve Registrations" module
3. Once approved, cashiers can login

### As New User:
1. Click "REGISTER" on login screen
2. Enter username and password
3. Wait for admin approval
4. Login after approval

## Cashier Dashboard Features
1. Create Sale - Process customer transactions
2. My Sales - View personal sales history
3. My Products - View available products
4. Logout

## Common Operations

### Adding a Product (Admin)
1. Login as admin
2. Click "MANAGE PRODUCT"
3. Fill in product details:
   - Name
   - Price
   - Stock quantity
4. Click "Add Product"

### Making a Sale (Admin/Cashier)
1. Click "CREATE SALE" or "MAKE SALE"
2. Select products from the list
3. Enter quantity
4. Click "Add to Cart"
5. Review cart items
6. Click "Complete Sale"
7. Print receipt (optional)

### Viewing Sales Report (Admin)
1. Click "SALES REPORT"
2. Select date range
3. View total sales, revenue, and transactions
4. Export or print report

### Approving New Users (Admin)
1. Click "APPROVE REGISTER"
2. View pending registrations
3. Select user to approve
4. Click "Approve"

## Database Information

### Database Type: SQLite
- **File**: `pos.db` (in project root)
- **Auto-created**: Yes, on first run
- **Portable**: Yes, just copy the .db file

### Tables:
1. **users** - User accounts (admin, cashiers)
2. **products** - Product inventory
3. **sales** - Sales transactions
4. **sale_items** - Individual items in each sale

### Database Location:
The `pos.db` file is created in the same directory as your application.

## Troubleshooting

### Database Connection Error
- Verify `sqlite-jdbc-3.30.1.jar` is in classpath
- Check if `pos.db` file has write permissions
- Ensure no other application is locking the database

### Login Failed
- Check if user is approved (for cashiers)
- Verify username and password
- For first run, use admin/admin123

### GUI Not Displaying Properly
- Ensure FlatLaf library is in classpath
- Check Java version (should be 8+)
- Try running with default look and feel

### Build Errors
- Clean and rebuild project
- Verify all source files are present
- Check for missing libraries
- Ensure Java version compatibility

### Database File Not Created
- Check write permissions in project directory
- Verify SQLite JDBC driver is in classpath
- Check console output for error messages

## System Requirements

### Minimum:
- Java 8
- 2GB RAM
- 50MB disk space
- SQLite JDBC driver (included)

### Recommended:
- Java 11 or higher
- 4GB RAM
- 100MB disk space
- SSD for better database performance

## Database Backup

To backup your data:
1. Close the application
2. Copy the `pos.db` file to a safe location
3. To restore, replace `pos.db` with your backup

## Resetting the Database

To start fresh:
1. Close the application
2. Delete the `pos.db` file
3. Run the application again
4. A new database will be created with default admin

## Support

For issues or questions:
1. Check `FIXES_APPLIED.md` for recent changes
2. Review `FEATURES.md` for feature documentation
3. Check `README.md` for project overview

## Security Notes

⚠️ **Important**: This system stores passwords in plain text. For production use:
1. Implement password hashing (BCrypt)
2. Use HTTPS for any web interface
3. Implement session management
4. Add audit logging
5. Regular database backups

## Next Steps

After successful setup:
1. Login with admin account
2. Add initial product inventory
3. Create cashier accounts
4. Configure receipt printer (if available)
5. Set low stock thresholds
6. Test all features thoroughly

## Advantages of SQLite

✅ **No server setup required**
✅ **Portable database file**
✅ **Zero configuration**
✅ **Fast and reliable**
✅ **Perfect for desktop applications**
✅ **Automatic database creation**

---

**System Status**: ✅ Ready to Use
**Database**: SQLite (Auto-configured)
**Last Updated**: 2026-03-01

