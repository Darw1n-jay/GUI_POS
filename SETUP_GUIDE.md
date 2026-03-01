# POS System Setup Guide

## Prerequisites
- Java JDK 8 or higher
- MySQL Server 5.7 or higher
- NetBeans IDE (or any Java IDE)
- MySQL JDBC Driver (mysql-connector-java)

## Step-by-Step Setup

### 1. Database Setup

#### Option A: Use the Fixed Schema (Recommended)
```bash
# Login to MySQL
mysql -u root -p

# Run the fixed schema
source pos_db_fixed.sql
```

#### Option B: Fix Existing Database
If you already imported the original schema with the typo, run this fix:

```sql
-- Login to MySQL
mysql -u root -p

-- Use the database
USE pos_db;

-- Fix the column name typo
ALTER TABLE users CHANGE COLUMN passwrod password VARCHAR(50) NOT NULL;

-- Add default admin user if not exists
INSERT IGNORE INTO users (username, password, role, approved) 
VALUES ('admin', 'admin123', 'ADMIN', 1);
```

### 2. Configure Database Connection

Open `src/db/DBConnection.java` and update these lines:

```java
private static final String URL = "jdbc:mysql://localhost:3306/pos_db?useSSL=false";
private static final String USER = "root"; // Your MySQL username
private static final String PASSWORD = ""; // Your MySQL password
```

**Common configurations:**
- XAMPP/WAMP: `USER = "root"`, `PASSWORD = ""`
- Custom MySQL: Use your actual username and password

### 3. Add MySQL JDBC Driver

#### For NetBeans:
1. Right-click on "Libraries" in your project
2. Select "Add JAR/Folder"
3. Navigate to MySQL Connector JAR file
4. Click "Open"

**Download MySQL Connector:**
- https://dev.mysql.com/downloads/connector/j/
- Or use Maven: `mysql:mysql-connector-java:8.0.33`

#### For Maven Projects:
Add to `pom.xml`:
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

### 4. Build and Run

1. **Clean and Build:**
   - In NetBeans: Right-click project → Clean and Build
   - Or use: `ant clean jar`

2. **Run the Application:**
   - Main class: `test_package.LOG_IN`
   - In NetBeans: Right-click `LOG_IN.java` → Run File
   - Or: F6 to run project

### 5. First Login

**Default Admin Credentials:**
- Username: `admin`
- Password: `admin123`

**Important:** Change the admin password after first login!

## Troubleshooting

### Error: "Communications link failure"
**Solution:**
1. Check if MySQL is running
2. Verify database name is `pos_db`
3. Check username and password in `DBConnection.java`
4. Ensure MySQL is listening on port 3306

### Error: "Unknown column 'password'"
**Solution:**
- Your database has the typo `passwrod`
- Run the ALTER TABLE command from Option B above
- Or drop and recreate using `pos_db_fixed.sql`

### Error: "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
**Solution:**
- MySQL JDBC driver not added to project
- Follow Step 3 to add the driver

### Error: "Access denied for user"
**Solution:**
- Wrong MySQL username or password
- Update `DBConnection.java` with correct credentials

### Error: "Unknown database 'pos_db'"
**Solution:**
- Database not created
- Run the SQL schema file first

### Images Not Loading (3.png, 6.png)
**Solution:**
1. Ensure images are in `src/` folder
2. Rebuild project
3. Check image paths in GUI files
4. Images should be copied to build folder automatically

## Testing the System

### Test Admin Functions:
1. Login as admin
2. Click "Dashboard" to see summary
3. Add a test product:
   - Name: "Test Product"
   - Price: 10.00
   - Stock: 100
4. Create a test sale
5. View reports

### Test Cashier Functions:
1. Register a new cashier account
2. Login as admin
3. Approve the registration
4. Logout and login as cashier
5. Create a sale
6. View sales history

## Database Verification

Run these queries to verify setup:

```sql
-- Check if database exists
SHOW DATABASES LIKE 'pos_db';

-- Check tables
USE pos_db;
SHOW TABLES;

-- Verify admin user
SELECT * FROM users WHERE role = 'ADMIN';

-- Check table structure
DESCRIBE users;
DESCRIBE products;
DESCRIBE sales;
DESCRIBE sale_items;
```

Expected output for users table:
```
+----------+-------------+------+-----+---------+----------------+
| Field    | Type        | Null | Key | Default | Extra          |
+----------+-------------+------+-----+---------+----------------+
| id       | int(11)     | NO   | PRI | NULL    | auto_increment |
| username | varchar(50) | NO   | UNI | NULL    |                |
| password | varchar(50) | NO   |     | NULL    |                |
| role     | enum(...)   | NO   |     | NULL    |                |
| approved | tinyint(1)  | NO   |     | 0       |                |
+----------+-------------+------+-----+---------+----------------+
```

## Quick Start Commands

```bash
# 1. Start MySQL
# Windows (XAMPP): Start from XAMPP Control Panel
# Linux: sudo systemctl start mysql
# Mac: mysql.server start

# 2. Import Database
mysql -u root -p < pos_db_fixed.sql

# 3. Verify Import
mysql -u root -p -e "USE pos_db; SELECT * FROM users;"

# 4. Run Application
# From NetBeans or:
java -cp "dist/YourProject.jar:lib/mysql-connector.jar" test_package.LOG_IN
```

## Project Structure Verification

Ensure your project has this structure:
```
project/
├── src/
│   ├── dao/
│   ├── db/
│   ├── logic/
│   ├── model/
│   ├── util/
│   ├── test_package/
│   │   ├── ADMIN/
│   │   └── CASHIER/
│   ├── 3.png
│   └── 6.png
├── build.xml
├── pos_db_fixed.sql
└── README.md
```

## Security Notes

⚠️ **Important for Production:**
1. Change default admin password
2. Implement password hashing (currently plain text)
3. Use environment variables for DB credentials
4. Enable SSL for MySQL connection
5. Add input sanitization
6. Implement session timeout
7. Add audit logging

## Support

If you encounter issues:
1. Check MySQL error log
2. Check NetBeans output window
3. Verify all files are in correct locations
4. Ensure MySQL JDBC driver is added
5. Confirm database schema is correct

## Next Steps

After successful setup:
1. ✅ Login as admin
2. ✅ Explore dashboard summary
3. ✅ Add products
4. ✅ Create test sales
5. ✅ Register and approve a cashier
6. ✅ Test cashier functions
7. ✅ View reports
8. ✅ Test all features

---

**System is ready when:**
- ✅ Database created with correct schema
- ✅ Admin user exists and can login
- ✅ All GUI windows open without errors
- ✅ Can add products
- ✅ Can create sales
- ✅ Receipts generate correctly

Enjoy your complete POS system! 🎉
