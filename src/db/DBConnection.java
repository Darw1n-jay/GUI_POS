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
            // Lookup: categories
            "CREATE TABLE IF NOT EXISTS categories ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT UNIQUE NOT NULL"
                + ")",

            // Lookup: payment_methods
            "CREATE TABLE IF NOT EXISTS payment_methods ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT UNIQUE NOT NULL"
                + ")",

            // Users
            "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT UNIQUE NOT NULL,"
                + "password TEXT NOT NULL,"
                + "role TEXT CHECK(role IN ('ADMIN', 'CASHIER')) DEFAULT 'CASHIER',"
                + "approved INTEGER DEFAULT 0"
                + ")",

            // Products — category_id FK to categories
            "CREATE TABLE IF NOT EXISTS products ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL,"
                + "category_id INTEGER,"
                + "price REAL NOT NULL,"
                + "stock INTEGER DEFAULT 0,"
                + "FOREIGN KEY (category_id) REFERENCES categories(id)"
                + ")",

            // Sales — payment_method_id FK to payment_methods
            "CREATE TABLE IF NOT EXISTS sales ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "cashier_id INTEGER NOT NULL,"
                + "total REAL NOT NULL,"
                + "sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "payment_method_id INTEGER,"
                + "FOREIGN KEY (cashier_id) REFERENCES users(id),"
                + "FOREIGN KEY (payment_method_id) REFERENCES payment_methods(id)"
                + ")",

            // Sale Items
            "CREATE TABLE IF NOT EXISTS sale_items ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "sale_id INTEGER NOT NULL,"
                + "product_id INTEGER NOT NULL,"
                + "qty INTEGER NOT NULL,"
                + "price REAL NOT NULL,"
                + "FOREIGN KEY (sale_id) REFERENCES sales(id),"
                + "FOREIGN KEY (product_id) REFERENCES products(id)"
                + ")",

            // Archived Products
            "CREATE TABLE IF NOT EXISTS archived_products ("
                + "id INTEGER PRIMARY KEY,"
                + "name TEXT NOT NULL,"
                + "category_id INTEGER,"
                + "price REAL NOT NULL,"
                + "stock INTEGER DEFAULT 0,"
                + "archived_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "FOREIGN KEY (category_id) REFERENCES categories(id)"
                + ")",

            // Archived Users
            "CREATE TABLE IF NOT EXISTS archived_users ("
                + "id INTEGER PRIMARY KEY,"
                + "username TEXT NOT NULL,"
                + "password TEXT NOT NULL,"
                + "role TEXT NOT NULL,"
                + "approved INTEGER DEFAULT 0,"
                + "archived_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ")"
        };

        try (Statement stmt = connection.createStatement()) {
            for (String table : tables) {
                stmt.execute(table);
            }

            // Seed payment_methods lookup
            stmt.execute("INSERT OR IGNORE INTO payment_methods(name) VALUES ('Cash')");
            stmt.execute("INSERT OR IGNORE INTO payment_methods(name) VALUES ('GCash')");

            // Migrate old products.category text column → category_id if needed
            try {
                stmt.execute("ALTER TABLE products ADD COLUMN category_id INTEGER REFERENCES categories(id)");
                // Migrate existing text values
                stmt.execute(
                    "INSERT OR IGNORE INTO categories(name) SELECT DISTINCT category FROM products WHERE category IS NOT NULL"
                );
                stmt.execute(
                    "UPDATE products SET category_id = (SELECT id FROM categories WHERE categories.name = products.category)"
                );
                stmt.execute("ALTER TABLE products DROP COLUMN category");
            } catch (SQLException e) {
                // Already migrated or column doesn't exist
            }

            // Migrate old sales.payment_method text column → payment_method_id if needed
            try {
                stmt.execute("ALTER TABLE sales ADD COLUMN payment_method_id INTEGER REFERENCES payment_methods(id)");
                stmt.execute(
                    "INSERT OR IGNORE INTO payment_methods(name) SELECT DISTINCT payment_method FROM sales WHERE payment_method IS NOT NULL"
                );
                stmt.execute(
                    "UPDATE sales SET payment_method_id = (SELECT id FROM payment_methods WHERE payment_methods.name = sales.payment_method)"
                );
                stmt.execute("ALTER TABLE sales DROP COLUMN payment_method");
            } catch (SQLException e) {
                // Already migrated or column doesn't exist
            }

            System.out.println("✓ Database tables created/normalized successfully");

            // Add is_archived column to products if not exists
            try {
                stmt.execute("ALTER TABLE products ADD COLUMN is_archived INTEGER DEFAULT 0");
            } catch (SQLException e) { /* already exists */ }

            // Add is_available column to products if not exists
            try {
                stmt.execute("ALTER TABLE products ADD COLUMN is_available INTEGER DEFAULT 1");
            } catch (SQLException e) { /* already exists */ }

            // Add is_archived column to users if not exists
            try {
                stmt.execute("ALTER TABLE users ADD COLUMN is_archived INTEGER DEFAULT 0");
            } catch (SQLException e) { /* already exists */ }

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

        // Ensure categories exist and get their IDs
        String[] categoryNames = {
            "Hot Coffee", "Iced Coffee", "Tea", "Pastries",
            "Cakes & Desserts", "Sandwiches & Snacks", "Smoothies & Juices", "Specialty Drinks"
        };
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT OR IGNORE INTO categories(name) VALUES (?)")) {
            for (String cat : categoryNames) {
                ps.setString(1, cat);
                ps.executeUpdate();
            }
        }

        String[][] products = {
            // {name, category, price, stock}
            {"Espresso Single","Hot Coffee","2.50","100"},
            {"Espresso Double","Hot Coffee","3.50","100"},
            {"Americano","Hot Coffee","3.00","100"},
            {"Cappuccino","Hot Coffee","4.00","100"},
            {"Latte","Hot Coffee","4.50","100"},
            {"Flat White","Hot Coffee","4.25","100"},
            {"Mocha","Hot Coffee","4.75","100"},
            {"Macchiato","Hot Coffee","3.75","100"},
            {"Caramel Latte","Hot Coffee","5.00","100"},
            {"Vanilla Latte","Hot Coffee","5.00","100"},
            {"Hazelnut Latte","Hot Coffee","5.00","100"},
            {"Turkish Coffee","Hot Coffee","3.50","100"},
            {"Iced Americano","Iced Coffee","3.50","100"},
            {"Iced Latte","Iced Coffee","4.75","100"},
            {"Iced Mocha","Iced Coffee","5.00","100"},
            {"Iced Caramel Macchiato","Iced Coffee","5.25","100"},
            {"Cold Brew","Iced Coffee","4.50","100"},
            {"Iced Vanilla Latte","Iced Coffee","5.00","100"},
            {"Iced Hazelnut Coffee","Iced Coffee","5.00","100"},
            {"Frappe","Iced Coffee","5.50","100"},
            {"Nitro Cold Brew","Iced Coffee","5.75","100"},
            {"Iced Cappuccino","Iced Coffee","4.50","100"},
            {"English Breakfast Tea","Tea","2.50","100"},
            {"Earl Grey Tea","Tea","2.50","100"},
            {"Green Tea","Tea","2.75","100"},
            {"Chamomile Tea","Tea","2.75","100"},
            {"Peppermint Tea","Tea","2.75","100"},
            {"Chai Latte","Tea","4.25","100"},
            {"Matcha Latte","Tea","4.75","100"},
            {"Iced Green Tea","Tea","3.00","100"},
            {"Iced Peach Tea","Tea","3.50","100"},
            {"Jasmine Tea","Tea","2.75","100"},
            {"Croissant Plain","Pastries","3.00","50"},
            {"Chocolate Croissant","Pastries","3.50","50"},
            {"Almond Croissant","Pastries","3.75","50"},
            {"Blueberry Muffin","Pastries","3.25","50"},
            {"Chocolate Chip Muffin","Pastries","3.25","50"},
            {"Banana Bread","Pastries","3.50","50"},
            {"Cinnamon Roll","Pastries","4.00","50"},
            {"Danish Pastry","Pastries","3.50","50"},
            {"Scone","Pastries","3.00","50"},
            {"Donut Glazed","Pastries","2.50","50"},
            {"Donut Chocolate","Pastries","2.75","50"},
            {"Bagel Plain","Pastries","2.50","50"},
            {"Cheesecake Slice","Cakes & Desserts","5.50","30"},
            {"Chocolate Cake Slice","Cakes & Desserts","5.00","30"},
            {"Carrot Cake Slice","Cakes & Desserts","5.00","30"},
            {"Red Velvet Cake Slice","Cakes & Desserts","5.50","30"},
            {"Tiramisu","Cakes & Desserts","6.00","30"},
            {"Brownie","Cakes & Desserts","3.50","40"},
            {"Cookie Chocolate Chip","Cakes & Desserts","2.50","60"},
            {"Cookie Oatmeal Raisin","Cakes & Desserts","2.50","60"},
            {"Macaron Box (6pcs)","Cakes & Desserts","8.00","25"},
            {"Cupcake Vanilla","Cakes & Desserts","3.50","40"},
            {"Cupcake Chocolate","Cakes & Desserts","3.50","40"},
            {"Ham & Cheese Sandwich","Sandwiches & Snacks","6.50","40"},
            {"Turkey Club Sandwich","Sandwiches & Snacks","7.50","40"},
            {"Chicken Pesto Sandwich","Sandwiches & Snacks","7.00","40"},
            {"Veggie Sandwich","Sandwiches & Snacks","6.00","40"},
            {"BLT Sandwich","Sandwiches & Snacks","6.50","40"},
            {"Tuna Sandwich","Sandwiches & Snacks","6.50","40"},
            {"Bagel with Cream Cheese","Sandwiches & Snacks","4.00","50"},
            {"Bagel with Lox","Sandwiches & Snacks","8.50","30"},
            {"Granola Bar","Sandwiches & Snacks","2.50","80"},
            {"Protein Bar","Sandwiches & Snacks","3.50","80"},
            {"Strawberry Banana Smoothie","Smoothies & Juices","5.50","60"},
            {"Mango Smoothie","Smoothies & Juices","5.50","60"},
            {"Berry Blast Smoothie","Smoothies & Juices","5.75","60"},
            {"Green Detox Smoothie","Smoothies & Juices","6.00","60"},
            {"Protein Smoothie","Smoothies & Juices","6.50","60"},
            {"Orange Juice Fresh","Smoothies & Juices","4.50","60"},
            {"Apple Juice","Smoothies & Juices","3.50","60"},
            {"Carrot Juice","Smoothies & Juices","4.00","60"},
            {"Lemonade Fresh","Smoothies & Juices","3.50","60"},
            {"Iced Tea Lemon","Smoothies & Juices","3.00","60"},
            {"Hot Chocolate","Specialty Drinks","4.00","80"},
            {"White Hot Chocolate","Specialty Drinks","4.25","80"},
            {"Iced Chocolate","Specialty Drinks","4.50","80"},
            {"Affogato","Specialty Drinks","5.50","50"},
            {"Irish Coffee","Specialty Drinks","7.00","40"},
            {"Caramel Frappuccino","Specialty Drinks","5.75","70"},
            {"Mocha Frappuccino","Specialty Drinks","5.75","70"},
            {"Vanilla Frappuccino","Specialty Drinks","5.75","70"},
            {"Pumpkin Spice Latte","Specialty Drinks","5.50","60"},
            {"Gingerbread Latte","Specialty Drinks","5.50","60"}
        };

        String sql = "INSERT INTO products(name, category_id, price, stock) "
                   + "VALUES(?, (SELECT id FROM categories WHERE name=?), ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (String[] p : products) {
                ps.setString(1, p[0]);
                ps.setString(2, p[1]);
                ps.setDouble(3, Double.parseDouble(p[2]));
                ps.setInt(4, Integer.parseInt(p[3]));
                ps.executeUpdate();
            }
        }
        System.out.println("✓ Inserted " + products.length + " products across 8 categories");
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