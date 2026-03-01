@echo off
echo ========================================
echo POS System File Verification
echo ========================================
echo.

echo Checking Models...
if exist "src\model\Product.java" (echo [OK] Product.java) else (echo [MISSING] Product.java)
if exist "src\model\Sale.java" (echo [OK] Sale.java) else (echo [MISSING] Sale.java)
if exist "src\model\SaleItem.java" (echo [OK] SaleItem.java) else (echo [MISSING] SaleItem.java)
if exist "src\model\User.java" (echo [OK] User.java) else (echo [MISSING] User.java)
echo.

echo Checking DAOs...
if exist "src\dao\ProductDao.java" (echo [OK] ProductDao.java) else (echo [MISSING] ProductDao.java)
if exist "src\dao\SaleDao.java" (echo [OK] SaleDao.java) else (echo [MISSING] SaleDao.java)
if exist "src\dao\UserDao.java" (echo [OK] UserDao.java) else (echo [MISSING] UserDao.java)
echo.

echo Checking Utilities...
if exist "src\util\ReceiptPrinter.java" (echo [OK] ReceiptPrinter.java) else (echo [MISSING] ReceiptPrinter.java)
echo.

echo Checking Admin GUIs...
if exist "src\test_package\ADMIN\DASHBOARD_SUMMARY.java" (echo [OK] DASHBOARD_SUMMARY.java) else (echo [MISSING] DASHBOARD_SUMMARY.java)
if exist "src\test_package\ADMIN\SALES_REPORT_DASH.java" (echo [OK] SALES_REPORT_DASH.java) else (echo [MISSING] SALES_REPORT_DASH.java)
if exist "src\test_package\ADMIN\LOW_STOCK_DASH.java" (echo [OK] LOW_STOCK_DASH.java) else (echo [MISSING] LOW_STOCK_DASH.java)
if exist "src\test_package\ADMIN\SEARCH_PRODUCT_DASH.java" (echo [OK] SEARCH_PRODUCT_DASH.java) else (echo [MISSING] SEARCH_PRODUCT_DASH.java)
if exist "src\test_package\ADMIN\MANAGE_PRODUCT_DASH.java" (echo [OK] MANAGE_PRODUCT_DASH.java) else (echo [MISSING] MANAGE_PRODUCT_DASH.java)
if exist "src\test_package\ADMIN\APPROVE_DASH.java" (echo [OK] APPROVE_DASH.java) else (echo [MISSING] APPROVE_DASH.java)
if exist "src\test_package\ADMIN\VIEW_INVE_DASH.java" (echo [OK] VIEW_INVE_DASH.java) else (echo [MISSING] VIEW_INVE_DASH.java)
if exist "src\test_package\ADMIN\MANAGE_SALES_DASH.java" (echo [OK] MANAGE_SALES_DASH.java) else (echo [MISSING] MANAGE_SALES_DASH.java)
if exist "src\test_package\ADMIN\MANAGE_USER_DASH.java" (echo [OK] MANAGE_USER_DASH.java) else (echo [MISSING] MANAGE_USER_DASH.java)
echo.

echo Checking Cashier GUIs...
if exist "src\test_package\CASHIER\CREATE_SALE_DASH.java" (echo [OK] CREATE_SALE_DASH.java) else (echo [MISSING] CREATE_SALE_DASH.java)
if exist "src\test_package\CASHIER\MY_SALES_DASH.java" (echo [OK] MY_SALES_DASH.java) else (echo [MISSING] MY_SALES_DASH.java)
if exist "src\test_package\CASHIER\MY_PRODUCTS_DASH.java" (echo [OK] MY_PRODUCTS_DASH.java) else (echo [MISSING] MY_PRODUCTS_DASH.java)
echo.

echo Checking Form Files...
if exist "src\test_package\ADMIN\DASHBOARD_SUMMARY.form" (echo [OK] DASHBOARD_SUMMARY.form) else (echo [MISSING] DASHBOARD_SUMMARY.form)
if exist "src\test_package\ADMIN\SALES_REPORT_DASH.form" (echo [OK] SALES_REPORT_DASH.form) else (echo [MISSING] SALES_REPORT_DASH.form)
if exist "src\test_package\ADMIN\LOW_STOCK_DASH.form" (echo [OK] LOW_STOCK_DASH.form) else (echo [MISSING] LOW_STOCK_DASH.form)
if exist "src\test_package\ADMIN\SEARCH_PRODUCT_DASH.form" (echo [OK] SEARCH_PRODUCT_DASH.form) else (echo [MISSING] SEARCH_PRODUCT_DASH.form)
if exist "src\test_package\CASHIER\CREATE_SALE_DASH.form" (echo [OK] CREATE_SALE_DASH.form) else (echo [MISSING] CREATE_SALE_DASH.form)
if exist "src\test_package\CASHIER\MY_SALES_DASH.form" (echo [OK] MY_SALES_DASH.form) else (echo [MISSING] MY_SALES_DASH.form)
if exist "src\test_package\CASHIER\MY_PRODUCTS_DASH.form" (echo [OK] MY_PRODUCTS_DASH.form) else (echo [MISSING] MY_PRODUCTS_DASH.form)
echo.

echo Checking Database Files...
if exist "pos_db_fixed.sql" (echo [OK] pos_db_fixed.sql) else (echo [MISSING] pos_db_fixed.sql)
if exist "fix_database.sql" (echo [OK] fix_database.sql) else (echo [MISSING] fix_database.sql)
echo.

echo Checking Documentation...
if exist "README.md" (echo [OK] README.md) else (echo [MISSING] README.md)
if exist "FEATURES.md" (echo [OK] FEATURES.md) else (echo [MISSING] FEATURES.md)
if exist "SETUP_GUIDE.md" (echo [OK] SETUP_GUIDE.md) else (echo [MISSING] SETUP_GUIDE.md)
if exist "CHECKLIST.md" (echo [OK] CHECKLIST.md) else (echo [MISSING] CHECKLIST.md)
if exist "NETBEANS_REFRESH.md" (echo [OK] NETBEANS_REFRESH.md) else (echo [MISSING] NETBEANS_REFRESH.md)
echo.

echo ========================================
echo Verification Complete!
echo ========================================
echo.
echo Next Steps:
echo 1. Open NetBeans
echo 2. Right-click project - Clean and Build
echo 3. Run the project
echo.
pause
