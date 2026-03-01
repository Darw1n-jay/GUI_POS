@echo off
echo ========================================
echo Coffee Shop POS - Database Reset
echo ========================================
echo.
echo This will delete the existing database and create a new one
echo with coffee shop products when you run the application.
echo.
echo IMPORTANT: Close any running instances of the POS application
echo and any database tools before continuing!
echo.
pause

taskkill /F /IM java.exe /FI "WINDOWTITLE eq *POS*" 2>nul
timeout /t 2 /nobreak >nul

if exist pos.db (
    del /F pos.db 2>nul
    if exist pos.db (
        echo ERROR: Could not delete database file!
        echo Please close all applications using the database and try again.
        echo.
        echo Try these steps:
        echo 1. Close the POS application
        echo 2. Close NetBeans IDE
        echo 3. Close any SQLite database viewers
        echo 4. Run this script again
    ) else (
        echo ✓ Database deleted successfully!
        echo.
        echo Next steps:
        echo 1. Run the POS application
        echo 2. The database will be recreated automatically
        echo 3. Login with: admin / admin123
        echo 4. You will see 90 coffee shop products!
    )
) else (
    echo No database file found. 
    echo Run the application to create a new database with coffee shop products.
)
echo.
pause
