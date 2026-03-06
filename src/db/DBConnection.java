package db;

import java.sql.*;

public class DBConnection {
    private static final String DB_URL = "jdbc:sqlite:pos.db";
    private static boolean initialized = false;
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(DB_URL);
            
            // Enable WAL mode for better concurrency
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("PRAGMA journal_mode=WAL");
                stmt.execute("PRAGMA synchronous=NORMAL");
                stmt.execute("PRAGMA cache_size=10000");
                stmt.execute("PRAGMA temp_store=memory");
                stmt.execute("PRAGMA busy_timeout=30000");
            }
            
            // Initialize database only once
            if (!initialized) {
                synchronized (DBConnection.class) {
                    if (!initialized) {
                        createTables(conn);
                        insertDefaultAdmin(conn);
                        initialized = true;
                    }
                }
            }
            
            return conn;
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found!", e);
        }
    }
    
    private static void createTables(Connection connection) {
        String[] tables = {
            "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT UNIQUE NOT NULL,"
                + "password TEXT NOT NULL,"
                + "role TEXT CHECK(role IN ('ADMIN', 'CASHIER')) DEFAULT 'CASHIER',"
                + "approved INTEGER DEFAULT 0"
                + ")",
                
            "CREATE TABLE IF NOT EXISTS products ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL,"
                + "category TEXT,"
                + "price REAL NOT NULL,"
                + "stock INTEGER DEFAULT 0"
                + ")",
                
            "CREATE TABLE IF NOT EXISTS sales ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "cashier_id INTEGER NOT NULL,"
                + "total REAL NOT NULL,"
                + "sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "payment_method TEXT DEFAULT 'Cash',"
                + "FOREIGN KEY (cashier_id) REFERENCES users(id)"
                + ")",
                
            "CREATE TABLE IF NOT EXISTS sale_items ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "sale_id INTEGER NOT NULL,"
                + "product_id INTEGER NOT NULL,"
                + "qty INTEGER NOT NULL,"
                + "price REAL NOT NULL,"
                + "FOREIGN KEY (sale_id) REFERENCES sales(id),"
                + "FOREIGN KEY (product_id) REFERENCES products(id)"
                + ")"
        };
        
        try (Statement stmt = connection.createStatement()) {
            for (String table : tables) {
                stmt.execute(table);
            }
            
            // Add category column if it doesn't exist (for existing databases)
            try {
                stmt.execute("ALTER TABLE products ADD COLUMN category TEXT");
                System.out.println("✓ Added category column to products table");
            } catch (SQLException e) {
                // Column already exists, ignore
            }
            
            // Add payment_method column if it doesn't exist (for existing databases)
            try {
                stmt.execute("ALTER TABLE sales ADD COLUMN payment_method TEXT DEFAULT 'Cash'");
                System.out.println("✓ Added payment_method column to sales table");
            } catch (SQLException e) {
                // Column already exists, ignore
            }
            
            System.out.println("✓ Database tables created successfully");
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }
    
    private static void insertDefaultAdmin(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            
            // Check if admin exists
            String checkAdmin = "SELECT COUNT(*) FROM users WHERE username='admin'";
            ResultSet rs = stmt.executeQuery(checkAdmin);
            rs.next();
            int adminCount = rs.getInt(1);
            
            if (adminCount == 0) {
                // Insert default admin user
                String admin = "INSERT INTO users (username, password, role, approved) "
                             + "VALUES ('admin', 'admin123', 'ADMIN', 1)";
                stmt.execute(admin);
                System.out.println("✓ Default admin user created (username: admin, password: admin123)");
            }
            
            // Check if products exist
            String checkProducts = "SELECT COUNT(*) FROM products";
            rs = stmt.executeQuery(checkProducts);
            rs.next();
            int productCount = rs.getInt(1);
            
            if (productCount == 0) {
                insertSampleProducts(connection);
            }
            
        } catch (SQLException e) {
            System.err.println("Error inserting default data: " + e.getMessage());
        }
    }
    
    private static void insertSampleProducts(Connection connection) throws SQLException {
        System.out.println("✓ Inserting coffee shop products...");
        
        try (Statement stmt = connection.createStatement()) {
        
        String[] products = {
            // Hot Coffee
            "INSERT INTO products (name, category, price, stock) VALUES ('Espresso Single', 'Hot Coffee', 2.50, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Espresso Double', 'Hot Coffee', 3.50, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Americano', 'Hot Coffee', 3.00, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Cappuccino', 'Hot Coffee', 4.00, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Latte', 'Hot Coffee', 4.50, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Flat White', 'Hot Coffee', 4.25, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Mocha', 'Hot Coffee', 4.75, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Macchiato', 'Hot Coffee', 3.75, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Caramel Latte', 'Hot Coffee', 5.00, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Vanilla Latte', 'Hot Coffee', 5.00, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Hazelnut Latte', 'Hot Coffee', 5.00, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Turkish Coffee', 'Hot Coffee', 3.50, 100)",
            
            // Iced Coffee
            "INSERT INTO products (name, category, price, stock) VALUES ('Iced Americano', 'Iced Coffee', 3.50, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Iced Latte', 'Iced Coffee', 4.75, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Iced Mocha', 'Iced Coffee', 5.00, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Iced Caramel Macchiato', 'Iced Coffee', 5.25, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Cold Brew', 'Iced Coffee', 4.50, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Iced Vanilla Latte', 'Iced Coffee', 5.00, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Iced Hazelnut Coffee', 'Iced Coffee', 5.00, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Frappe', 'Iced Coffee', 5.50, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Nitro Cold Brew', 'Iced Coffee', 5.75, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Iced Cappuccino', 'Iced Coffee', 4.50, 100)",
            
            // Tea
            "INSERT INTO products (name, category, price, stock) VALUES ('English Breakfast Tea', 'Tea', 2.50, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Earl Grey Tea', 'Tea', 2.50, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Green Tea', 'Tea', 2.75, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Chamomile Tea', 'Tea', 2.75, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Peppermint Tea', 'Tea', 2.75, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Chai Latte', 'Tea', 4.25, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Matcha Latte', 'Tea', 4.75, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Iced Green Tea', 'Tea', 3.00, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Iced Peach Tea', 'Tea', 3.50, 100)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Jasmine Tea', 'Tea', 2.75, 100)",
            
            // Pastries
            "INSERT INTO products (name, category, price, stock) VALUES ('Croissant Plain', 'Pastries', 3.00, 50)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Chocolate Croissant', 'Pastries', 3.50, 50)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Almond Croissant', 'Pastries', 3.75, 50)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Blueberry Muffin', 'Pastries', 3.25, 50)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Chocolate Chip Muffin', 'Pastries', 3.25, 50)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Banana Bread', 'Pastries', 3.50, 50)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Cinnamon Roll', 'Pastries', 4.00, 50)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Danish Pastry', 'Pastries', 3.50, 50)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Scone', 'Pastries', 3.00, 50)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Donut Glazed', 'Pastries', 2.50, 50)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Donut Chocolate', 'Pastries', 2.75, 50)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Bagel Plain', 'Pastries', 2.50, 50)",
            
            // Cakes & Desserts
            "INSERT INTO products (name, category, price, stock) VALUES ('Cheesecake Slice', 'Cakes & Desserts', 5.50, 30)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Chocolate Cake Slice', 'Cakes & Desserts', 5.00, 30)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Carrot Cake Slice', 'Cakes & Desserts', 5.00, 30)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Red Velvet Cake Slice', 'Cakes & Desserts', 5.50, 30)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Tiramisu', 'Cakes & Desserts', 6.00, 30)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Brownie', 'Cakes & Desserts', 3.50, 40)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Cookie Chocolate Chip', 'Cakes & Desserts', 2.50, 60)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Cookie Oatmeal Raisin', 'Cakes & Desserts', 2.50, 60)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Macaron Box (6pcs)', 'Cakes & Desserts', 8.00, 25)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Cupcake Vanilla', 'Cakes & Desserts', 3.50, 40)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Cupcake Chocolate', 'Cakes & Desserts', 3.50, 40)",
            
            // Sandwiches & Snacks
            "INSERT INTO products (name, category, price, stock) VALUES ('Ham & Cheese Sandwich', 'Sandwiches & Snacks', 6.50, 40)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Turkey Club Sandwich', 'Sandwiches & Snacks', 7.50, 40)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Chicken Pesto Sandwich', 'Sandwiches & Snacks', 7.00, 40)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Veggie Sandwich', 'Sandwiches & Snacks', 6.00, 40)",
            "INSERT INTO products (name, category, price, stock) VALUES ('BLT Sandwich', 'Sandwiches & Snacks', 6.50, 40)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Tuna Sandwich', 'Sandwiches & Snacks', 6.50, 40)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Bagel with Cream Cheese', 'Sandwiches & Snacks', 4.00, 50)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Bagel with Lox', 'Sandwiches & Snacks', 8.50, 30)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Granola Bar', 'Sandwiches & Snacks', 2.50, 80)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Protein Bar', 'Sandwiches & Snacks', 3.50, 80)",
            
            // Smoothies & Juices
            "INSERT INTO products (name, category, price, stock) VALUES ('Strawberry Banana Smoothie', 'Smoothies & Juices', 5.50, 60)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Mango Smoothie', 'Smoothies & Juices', 5.50, 60)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Berry Blast Smoothie', 'Smoothies & Juices', 5.75, 60)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Green Detox Smoothie', 'Smoothies & Juices', 6.00, 60)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Protein Smoothie', 'Smoothies & Juices', 6.50, 60)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Orange Juice Fresh', 'Smoothies & Juices', 4.50, 60)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Apple Juice', 'Smoothies & Juices', 3.50, 60)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Carrot Juice', 'Smoothies & Juices', 4.00, 60)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Lemonade Fresh', 'Smoothies & Juices', 3.50, 60)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Iced Tea Lemon', 'Smoothies & Juices', 3.00, 60)",
            
            // Specialty Drinks
            "INSERT INTO products (name, category, price, stock) VALUES ('Hot Chocolate', 'Specialty Drinks', 4.00, 80)",
            "INSERT INTO products (name, category, price, stock) VALUES ('White Hot Chocolate', 'Specialty Drinks', 4.25, 80)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Iced Chocolate', 'Specialty Drinks', 4.50, 80)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Affogato', 'Specialty Drinks', 5.50, 50)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Irish Coffee', 'Specialty Drinks', 7.00, 40)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Caramel Frappuccino', 'Specialty Drinks', 5.75, 70)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Mocha Frappuccino', 'Specialty Drinks', 5.75, 70)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Vanilla Frappuccino', 'Specialty Drinks', 5.75, 70)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Pumpkin Spice Latte', 'Specialty Drinks', 5.50, 60)",
            "INSERT INTO products (name, category, price, stock) VALUES ('Gingerbread Latte', 'Specialty Drinks', 5.50, 60)"
        };
        
        for (String product : products) {
            stmt.execute(product);
        }
        
        System.out.println("✓ Inserted " + products.length + " coffee shop products across 8 categories");
        }
    }
    
    public static void initialize() {
        try (Connection conn = getConnection()) {
            System.out.println("✓ Coffee Shop POS database initialized");
        } catch (SQLException e) {
            System.err.println("✗ Database initialization failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void closeConnection() {
        // No longer needed since we're not using singleton connections
        System.out.println("✓ Using connection-per-operation pattern - no global connection to close");
    }
    
    // Method for handling transactions
    public static void executeTransaction(TransactionCallback callback) throws SQLException {
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            try {
                callback.execute(conn);
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw new SQLException("Transaction failed: " + e.getMessage(), e);
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }
    
    @FunctionalInterface
    public interface TransactionCallback {
        void execute(Connection conn) throws Exception;
    }
}