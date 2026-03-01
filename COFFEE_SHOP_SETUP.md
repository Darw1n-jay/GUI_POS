# Coffee Shop POS - Quick Setup Guide

## System Updated! ☕

Your POS system has been transformed into a Coffee Shop POS with 90 authentic coffee shop products.

## Quick Start

### Step 1: Reset the Database
**IMPORTANT:** You need to delete the old database to see the new coffee shop products.

**Easy Method:**
1. Close the POS application (if running)
2. Close NetBeans IDE
3. Double-click `reset_database.bat`
4. Follow the prompts

**Manual Method:**
1. Close the POS application
2. Navigate to the project folder
3. Delete the file `pos.db`
4. Done!

### Step 2: Run the Application
1. Open the project in NetBeans
2. Run `Main.java`
3. The database will be created automatically with coffee shop products

### Step 3: Login
- Username: `admin`
- Password: `admin123`

### Step 4: Explore Your Coffee Shop
- View 90 coffee shop products in 8 categories
- Create sales for coffee, pastries, sandwiches, and more
- Manage inventory with appropriate stock levels
- Track daily sales and revenue

## What's New?

### Product Categories
1. **Hot Coffee** - Espresso, Latte, Cappuccino, Mocha, etc.
2. **Iced Coffee** - Cold Brew, Iced Latte, Frappe, etc.
3. **Tea** - Green Tea, Chai Latte, Matcha Latte, etc.
4. **Pastries** - Croissants, Muffins, Donuts, Bagels, etc.
5. **Cakes & Desserts** - Cheesecake, Tiramisu, Cookies, etc.
6. **Sandwiches & Snacks** - Various sandwiches and snacks
7. **Smoothies & Juices** - Fresh smoothies and juices
8. **Specialty Drinks** - Hot Chocolate, Frappuccinos, etc.

### Pricing
- Coffee drinks: $2.50 - $5.75
- Tea: $2.50 - $4.75
- Pastries: $2.50 - $4.00
- Desserts: $2.50 - $8.00
- Sandwiches: $2.50 - $8.50
- Smoothies: $3.00 - $6.50
- Specialty: $4.00 - $7.00

## Features Available

### For Admin:
- Dashboard Summary (view sales statistics)
- Manage Products (add/edit/delete coffee shop items)
- View Inventory (check stock levels)
- Create Sales (process orders)
- Manage Sales (view all transactions)
- Approve Registrations (approve new cashiers)
- Manage Users (user management)
- Sales Reports (detailed reports)
- Low Stock Alerts (monitor inventory)
- Search Products (find items quickly)

### For Cashier:
- Create Sales (process customer orders)
- View My Sales (personal sales history)
- View Products (browse available items)

## Troubleshooting

### Database Won't Delete?
If `reset_database.bat` can't delete the database:
1. Make sure the POS application is closed
2. Close NetBeans IDE completely
3. Close any SQLite database viewers
4. Try running the script again
5. If still locked, restart your computer

### No Products Showing?
- Make sure you deleted the old `pos.db` file
- Restart the application
- Check the console for "✓ Inserted 90 coffee shop products" message

### Can't Login?
- Default credentials: admin / admin123
- Make sure the database was created (check for pos.db file)
- Check console for any error messages

## Next Steps

1. ✅ Database reset complete
2. ✅ Coffee shop products loaded
3. 🎯 Start processing orders!
4. 📊 Monitor your daily sales
5. 📦 Manage inventory levels

Enjoy your Coffee Shop POS System! ☕🥐🍰
