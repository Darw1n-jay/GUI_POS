-- SQLite Database Schema for POS System (Normalized to 3NF)
-- This file is for reference only - the database is auto-created by DBConnection.java

-- Lookup: product categories
CREATE TABLE IF NOT EXISTS categories (
    id   INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT UNIQUE NOT NULL
);

-- Lookup: payment methods
CREATE TABLE IF NOT EXISTS payment_methods (
    id   INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT UNIQUE NOT NULL
);

-- Users
CREATE TABLE IF NOT EXISTS users (
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE NOT NULL,
LL,
    role     TEXT CHECK(role IN ('ADMIN', 'CASHIER')) DEFAULT 'CASHIER',
    approved INTEGER DEFAULT 0
);

-- Products — category_id references categories
CREATE TABLE IF NOT EXISTS products (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    name        TEXT NOT NULL,
    category_id INTEGER,
    price       REAL NOT NULL,
    stock       INTEGER DEFAULT 0,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Sales — payment_method_id references payment_methods
CREATE TABLE IF NOT EXIST (
    id                INTEGER PRIMARY KEY AUTOINCREMENT,
    cashier_id        INTEGER NOT NULL,
    total             REAL NOT NULL,
    sale_date         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method_id INTEGER,
    FOREIGN KEY (cashier_id)        REFERENCES users(id),
    FOREIGN KEY (payment_method_id) REFERENCES payment_methods(id)
);

-- Sale Items (snapshot pricing — price stored at time of sale is intentional)
CREATE TABLE IF NOT EXISTS sale_items (
    id         INTEGER EMENT,
    sale_id    INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    qty        INTEGER NOT NULL,
    price      REAL NOT NULL,
    FOREIGN KEY (sale_id)    REFERENCES sales(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Seed lookup tables
INSERT OR IGNORE INTO payment_methods(name) VALUES ('Cash');
INSERT OR IGNORE INTO payment_methods(name) VALUES ('GCash');

-- Default admin
INSERT OR IGNORE INTO users (username, password, role, approved)
VALUES ('admin', 'admin123', 'ADMIN', 1);
