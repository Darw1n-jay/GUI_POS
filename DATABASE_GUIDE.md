# POS System - Database Guide

## Database Overview

Your POS system uses **SQLite** - a lightweight, serverless database that's perfect for desktop applications.

### Why SQLite?

✅ **Zero Configuration** - No database server to install or configure
✅ **Automatic Setup** - Database and tables created automatically on first run
✅ **Portable** - Single file database (pos.db) that you can backup/move easily
✅ **Fast** - Excellent performance for desktop applications
✅ **Reliable** - ACID-compliant, production-ready database
✅ **No Dependencies** - Only requires the JDBC driver (already included)

## Database File

**Location**: `pos.db` (in project root directory)
**Type**: SQLite 3
**Size**: Starts small (~20KB), grows with data
**Auto-created**: Yes, on first application run

## Database Schema

### 1. Users Table
Stores user accounts (admin and cashiers)

```sql
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    role TEXT CHECK(role IN ('ADMIN', 'CASHIER')) DEFAULT 'CASHIER',
    approved INTEGER DEFAULT 0
);
```

**Fields:**
- `id` - Unique user identifier (auto-increment)
- `username` - Login username (must be unique)
- `password` - User password (plain text - consider hashing for production)
- `role` - User role: 'ADMIN' or 'CASHIER'
- `approved` - Approval status: 0 (pending) or 1 (approved)

**Default Data:**
- Admin user: username='admin', password='admin123', role='ADMIN', approved=1

### 2. Products Table
Stores product inventory

```sql
CREATE TABLE products (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    price REAL NOT NULL,
    stock INTEGER DEFAULT 0
);
```

**Fields:**
- `id` - Unique product identifier (auto-increment)
- `name` - Product name
- `price` - Product price (decimal)
- `stock` - Available quantity in inventory

### 3. Sales Table
Stores sales transactions

```sql
CREATE TABLE sales (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    cashier_id INTEGER NOT NULL,
    total REAL NOT NULL,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cashier_id) REFERENCES users(id)
);
```

**Fields:**
- `id` - Unique sale identifier (auto-increment)
- `cashier_id` - ID of the user who made the sale
- `total` - Total sale amount
- `sale_date` - Timestamp of the sale (auto-set)

### 4. Sale Items Table
Stores individual items in each sale

```sql
CREATE TABLE sale_items (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    sale_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    qty INTEGER NOT NULL,
    price REAL NOT NULL,
    FOREIGN KEY (sale_id) REFERENCES sales(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```

**Fields:**
- `id` - Unique sale item identifier (auto-increment)
- `sale_id` - Reference to the sale
- `product_id` - Reference to the product
- `qty` - Quantity sold
- `price` - Price at time of sale (may differ from current product price)

## Database Initialization

The database is automatically initialized when you run the application:

1. **First Run**: 
   - `DBConnection.initialize()` is called from `Main.java`
   - Database file `pos.db` is created
   - All tables are created
   - Default admin user is inserted

2. **Subsequent Runs**:
   - Existing database is used
   - Tables are checked (IF NOT EXISTS)
   - No data is lost

## Database Operations

### Connecting to Database

```java
Connection conn = DBConnection.getConnection();
```

The connection is managed automatically with connection pooling.

### Closing Connection

```java
DBConnection.closeConnection();
```

Called automatically when application exits.

## Data Access Layer (DAO)

All database operations go through DAO classes:

### ProductDao.java
- `addProduct(name, price, stock)` - Add new product
- `updateProduct(id, name, price, stock)` - Update product
- `deleteProduct(id)` - Delete product
- `getAllProducts()` - Get all products
- `getProductById(id)` - Get specific product
- `updateStock(productId, newStock)` - Update stock quantity

### UserDao.java
- `register(username, password)` - Register new cashier
- `login(username, password)` - Authenticate user
- `approve(id)` - Approve pending user
- `getPendingUsers()` - Get unapproved users
- `getAllUsers()` - Get all users
- `deleteUser(id)` - Delete user

### SaleDao.java
- `createSale(cashierId, total)` - Create new sale
- `addSaleItem(saleId, productId, qty, price)` - Add item to sale
- `getAllSales()` - Get all sales
- `getSalesByCashier(cashierId)` - Get sales by specific cashier
- `getSaleItems(saleId)` - Get items in a sale

## Database Backup

### Manual Backup
1. Close the application
2. Copy `pos.db` to a safe location
3. Name it with date: `pos_backup_2026-03-01.db`

### Restore from Backup
1. Close the application
2. Replace `pos.db` with your backup file
3. Restart the application

### Automated Backup (Recommended)
Consider implementing scheduled backups:
- Daily backups to a backup folder
- Keep last 7 days of backups
- Store backups on external drive or cloud

## Database Maintenance

### Reset Database
To start with a fresh database:
1. Close the application
2. Delete `pos.db`
3. Run the application
4. New database will be created with default admin

### View Database Contents
Use SQLite browser tools:
- **DB Browser for SQLite** (Free, cross-platform)
- **SQLiteStudio** (Free, feature-rich)
- **DBeaver** (Universal database tool)

### Database Queries
You can run SQL queries directly:

```sql
-- View all users
SELECT * FROM users;

-- View all products
SELECT * FROM products;

-- View sales with cashier names
SELECT s.id, u.username, s.total, s.sale_date 
FROM sales s 
JOIN users u ON s.cashier_id = u.id;

-- View low stock products
SELECT * FROM products WHERE stock < 10;

-- View total sales by cashier
SELECT u.username, COUNT(s.id) as sales_count, SUM(s.total) as total_revenue
FROM sales s
JOIN users u ON s.cashier_id = u.id
GROUP BY u.username;
```

## Performance Tips

1. **Indexes**: SQLite automatically indexes PRIMARY KEY and UNIQUE columns
2. **Transactions**: Use transactions for bulk operations
3. **Connection Pooling**: Reuse connections (already implemented)
4. **Regular Backups**: Prevent data loss
5. **Vacuum**: Periodically run `VACUUM` to optimize database file

## Troubleshooting

### Database Locked Error
**Cause**: Another process is accessing the database
**Solution**: 
- Close all applications using pos.db
- Restart the application
- Check for zombie processes

### Database Corrupted
**Cause**: Unexpected shutdown, disk errors
**Solution**:
- Restore from backup
- Use SQLite recovery tools
- Run `PRAGMA integrity_check;`

### Cannot Create Database
**Cause**: Permission issues
**Solution**:
- Check write permissions in project directory
- Run application with appropriate permissions
- Check disk space

### Slow Performance
**Cause**: Large database, no indexes
**Solution**:
- Run `VACUUM` to optimize
- Add indexes on frequently queried columns
- Archive old data

## Migration from MySQL

If you had MySQL before, here are the key differences:

| Feature | MySQL | SQLite |
|---------|-------|--------|
| Server | Required | Not required |
| Setup | Manual | Automatic |
| Configuration | Complex | Zero config |
| Portability | Low | High |
| File-based | No | Yes |
| Auto-increment | AUTO_INCREMENT | AUTOINCREMENT |
| Boolean | BOOLEAN | INTEGER (0/1) |
| Timestamp | TIMESTAMP | TIMESTAMP |

## Security Considerations

⚠️ **Important Security Notes:**

1. **Password Storage**: Currently plain text - implement hashing for production
2. **Database Encryption**: Consider SQLCipher for encrypted database
3. **File Permissions**: Restrict access to pos.db file
4. **Backup Security**: Encrypt backup files
5. **SQL Injection**: Already protected (using PreparedStatements)

## Advanced Features

### Enable Foreign Keys
SQLite requires explicit foreign key enforcement:

```java
Statement stmt = conn.createStatement();
stmt.execute("PRAGMA foreign_keys = ON;");
```

### Database Statistics
```sql
-- Database size
SELECT page_count * page_size as size FROM pragma_page_count(), pragma_page_size();

-- Table row counts
SELECT 'users' as table_name, COUNT(*) as rows FROM users
UNION ALL
SELECT 'products', COUNT(*) FROM products
UNION ALL
SELECT 'sales', COUNT(*) FROM sales
UNION ALL
SELECT 'sale_items', COUNT(*) FROM sale_items;
```

## Support

For database-related issues:
1. Check this guide first
2. Review `QUICK_START.md` for setup instructions
3. Check `FIXES_APPLIED.md` for recent changes
4. Use SQLite documentation: https://www.sqlite.org/docs.html

---

**Database Type**: SQLite 3
**Auto-configured**: Yes
**Backup Recommended**: Daily
**Last Updated**: 2026-03-01
