-- Crear tabla de clientes
CREATE TABLE IF NOT EXISTS clients (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    phone TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla de ventas
CREATE TABLE IF NOT EXISTS sales (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    client_id INTEGER NOT NULL,
    product TEXT NOT NULL,
    quantity INTEGER NOT NULL CHECK(quantity > 0),
    price REAL NOT NULL CHECK(price >= 0),
    sale_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);

-- Insertar algunos datos iniciales de ejemplo
INSERT INTO clients (name, email, phone) VALUES
('Juan Pérez', 'juan.perez@example.com', '555-1234'),
('María Gómez', 'maria.gomez@example.com', '555-5678');

INSERT INTO sales (client_id, product, quantity, price) VALUES
(1, 'Laptop', 1, 850.00),
(2, 'Mouse', 2, 15.50);
