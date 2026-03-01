# NetBeans Refresh Guide

## If Changes Don't Appear in NetBeans

### Method 1: Clean and Build (Recommended)
1. In NetBeans, right-click your project name
2. Select **"Clean and Build"**
3. Wait for the build to complete
4. Run the project

### Method 2: Refresh Project
1. Close NetBeans completely
2. Delete the following folders in your project:
   - `build/`
   - `dist/`
   - `nbproject/private/`
3. Reopen NetBeans
4. Open your project
5. Clean and Build

### Method 3: Force Reload
1. In NetBeans menu: **File → Close All Projects**
2. **File → Open Project** → Select your project
3. Right-click project → **Clean and Build**

### Method 4: Manual File Verification
Check if these files exist in your project:

**Models:**
- `src/model/Product.java`
- `src/model/Sale.java`
- `src/model/SaleItem.java`
- `src/model/User.java`

**DAOs:**
- `src/dao/ProductDao.java`
- `src/dao/SaleDao.java`
- `src/dao/UserDao.java`

**Utilities:**
- `src/util/ReceiptPrinter.java`

**Admin GUIs:**
- `src/test_package/ADMIN/DASHBOARD_SUMMARY.java` + `.form`
- `src/test_package/ADMIN/SALES_REPORT_DASH.java` + `.form`
- `src/test_package/ADMIN/LOW_STOCK_DASH.java` + `.form`
- `src/test_package/ADMIN/SEARCH_PRODUCT_DASH.java` + `.form`
- `src/test_package/ADMIN/MANAGE_PRODUCT_DASH.java` (updated)
- `src/test_package/ADMIN/APPROVE_DASH.java` (updated)
- `src/test_package/ADMIN/VIEW_INVE_DASH.java` (updated)
- `src/test_package/ADMIN/MANAGE_SALES_DASH.java`
- `src/test_package/ADMIN/MANAGE_USER_DASH.java`

**Cashier GUIs:**
- `src/test_package/CASHIER/CREATE_SALE_DASH.java` + `.form`
- `src/test_package/CASHIER/MY_SALES_DASH.java` + `.form`
- `src/test_package/CASHIER/MY_PRODUCTS_DASH.java` + `.form`

**Updated Dashboards:**
- `src/test_package/ADMIN_DASH.java` (with 11 buttons)
- `src/test_package/CASHIER_DASH.java` (with 4 buttons)

### Method 5: Check for Compilation Errors
1. In NetBeans, go to **Window → Output**
2. Look for any red error messages
3. Common issues:
   - Missing imports
   - Package name mismatches
   - Missing .form files

### Method 6: Verify Package Structure
Your `src` folder should look like this:
```
src/
├── dao/
│   ├── ProductDao.java
│   ├── SaleDao.java
│   └── UserDao.java
├── db/
│   └── DBConnection.java
├── logic/
│   ├── AuthValidator.java
│   ├── LoginResult.java
│   ├── RegisterValidator.java
│   └── User.java
├── model/
│   ├── Product.java
│   ├── Sale.java
│   ├── SaleItem.java
│   └── User.java
├── util/
│   └── ReceiptPrinter.java
├── test_package/
│   ├── ADMIN/
│   │   ├── APPROVE_DASH.java + .form
│   │   ├── DASHBOARD_SUMMARY.java + .form
│   │   ├── LOW_STOCK_DASH.java + .form
│   │   ├── MANAGE_PRODUCT_DASH.java + .form
│   │   ├── MANAGE_SALES_DASH.java + .form
│   │   ├── MANAGE_USER_DASH.java + .form
│   │   ├── SALES_REPORT_DASH.java + .form
│   │   ├── SEARCH_PRODUCT_DASH.java + .form
│   │   └── VIEW_INVE_DASH.java + .form
│   ├── CASHIER/
│   │   ├── CREATE_SALE_DASH.java + .form
│   │   ├── MY_PRODUCTS_DASH.java + .form
│   │   └── MY_SALES_DASH.java + .form
│   ├── ADMIN_DASH.java + .form
│   ├── CASHIER_DASH.java + .form
│   ├── LOG_IN.java + .form
│   └── REGISTER_DASH.java + .form
├── 3.png
└── 6.png
```

## Common NetBeans Issues

### Issue: "Cannot find symbol" errors
**Solution:**
1. Check package declarations match folder structure
2. Verify all imports are correct
3. Clean and Build

### Issue: GUI doesn't show new buttons
**Solution:**
1. Open the `.form` file in NetBeans Design view
2. The GUI should regenerate
3. If not, the `.java` file has the code - it will work at runtime

### Issue: "Class not found" at runtime
**Solution:**
1. Ensure all `.java` files are in correct packages
2. Clean and Build
3. Check Main Class is set to `test_package.LOG_IN`

### Issue: Images not loading
**Solution:**
1. Ensure `3.png` and `6.png` are in `src/` folder
2. Clean and Build (copies to build folder)
3. Check image paths use `/3.png` and `/6.png`

## Verification Steps

### 1. Check Compilation
```bash
# In project root
ant clean
ant compile
```

### 2. Check All Files Compile
In NetBeans Output window, you should see:
```
BUILD SUCCESSFUL
```

### 3. Test Run
1. Right-click `LOG_IN.java`
2. Select **Run File**
3. Login screen should appear

### 4. Test Admin Dashboard
1. Login as admin (admin/admin123)
2. You should see 11 buttons:
   - DASHBOARD
   - MANAGE PRODUCT
   - VIEW INVENTORY
   - MAKE SALE
   - MANAGE SALES
   - APPROVE REGISTER
   - MANAGE USERS
   - SALES REPORT
   - LOW STOCK ALERT
   - SEARCH PRODUCT (on right side)
   - LOG OUT

### 5. Test Cashier Dashboard
1. Register a cashier
2. Approve as admin
3. Login as cashier
4. You should see 4 buttons:
   - CREATE SALE
   - LIST OF MY SALE
   - LIST OF MY PRODUCT
   - LOG OUT

## If Still Having Issues

### Nuclear Option: Complete Reset
1. **Backup your database** (export pos_db)
2. Close NetBeans
3. Delete these folders:
   - `build/`
   - `dist/`
   - `nbproject/private/`
   - `nbproject/build-impl.xml`
4. Open NetBeans
5. **File → Open Project**
6. Select your project
7. Right-click project → **Clean and Build**
8. Run

### Check File Encoding
1. In NetBeans: **Tools → Options → Editor → General**
2. Ensure encoding is **UTF-8**

### Verify Java Version
1. In NetBeans: Right-click project → **Properties**
2. **Sources** → Ensure **Source/Binary Format** is Java 8 or higher
3. **Libraries** → Ensure JDK is properly configured

## Quick Checklist

- [ ] All `.java` files exist in correct folders
- [ ] All `.form` files exist for GUI classes
- [ ] Package declarations match folder structure
- [ ] No compilation errors in Output window
- [ ] Clean and Build completed successfully
- [ ] Images (3.png, 6.png) in src folder
- [ ] MySQL JDBC driver added to Libraries
- [ ] Database connection configured
- [ ] Main class set to `test_package.LOG_IN`

## Still Not Working?

If changes still don't appear:
1. Copy all the `.java` files manually
2. Paste them in the correct folders in NetBeans
3. NetBeans will ask to overwrite - click **Yes to All**
4. Clean and Build
5. Run

---

**After following these steps, your POS system should be fully functional with all 15 modules!**
