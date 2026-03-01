-- SQLite Database Schema for POS System
-- This file is for reference only - the database is auto-created by DBConnection.java

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    role TEXT CHECK(role IN ('ADMIN', 'CASHIER')) DEFAULT 'CASHIER',
    approved INTEGER DEFAULT 0
);

-- Products Table
CREATE TABLE IF NOT EXISTS products (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    price REAL NOT NULL,
    stock INTEGER DEFAULT 0
);

-- Sales Table
CREATE TABLE IF NOT EXISTS sales (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    cashier_id INTEGER NOT NULL,
    total REAL NOT NULL,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cashier_id) REFERENCES users(id)
);

-- Sale Items Table
CREATE TABLE IF NOT EXISTS sale_items (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    sale_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    qty INTEGER NOT NULL,
    price REAL NOT NULL,
    FOREIGN KEY (sale_id) REFERENCES sales(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Default Admin User
INSERT INTO users (username, password, role, approved) 
VALUES ('admin', 'admin123', 'ADMIN', 1);

-- Sample Products (Optional)
INSERT INTO products (name, price, stock) VALUES 
('Laptop', 999.99, 10),
('Mouse', 29.99, 50),
('Keyboard', 79.99, 30),
('Monitor', 299.99, 15),
('USB Cable', 9.99, 100),
('Headphones', 149.99, 25),
('Webcam', 89.99, 20),
('Desk Lamp', 39.99, 40),
('Office Chair', 249.99, 12),
('Notebook', 4.99, 200);
