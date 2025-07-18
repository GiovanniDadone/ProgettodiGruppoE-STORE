CREATE DATABASE estore;
USE estore;

CREATE TABLE prodotto (
    id INT PRIMARY KEY,
    nome VARCHAR(100),
    prezzo DECIMAL(6,2)
);

CREATE TABLE ordine (
    id INT PRIMARY KEY,
    quantità INT,
    data_ordine VARCHAR(100),
    prodotto_id INT,
    FOREIGN KEY (prodotto_id) REFERENCES prodotto(id) ON DELETE CASCADE ON UPDATE CASCADE
);

REPLACE INTO prodotto (id, nome, prezzo) VALUES
(1, 'Smartphone Samsung Galaxy', 699.99),
(2, 'Laptop Dell XPS 13', 1299.50),
(3, 'Cuffie Bluetooth Sony', 199.99),
(4, 'Tablet iPad Air', 599.00),
(5, 'Smartwatch Apple Watch', 399.99),
(6, 'Tastiera Meccanica Logitech', 89.99),
(7, 'Mouse Gaming Razer', 79.50),
(8, 'Monitor 4K LG 27"', 449.99),
(9, 'Webcam HD Logitech', 69.99),
(10, 'Altoparlante Bluetooth JBL', 129.99),
(11, 'Caricatore Wireless Anker', 29.99),
(12, 'Powerbank 20000mAh', 39.99),
(13, 'Custodia iPhone 14', 24.99),
(14, 'Cavo USB-C 2m', 14.99),
(15, 'Adattatore HDMI', 19.99),
(16, 'Microfono Blue Yeti', 159.99),
(17, 'Disco SSD Samsung 1TB', 119.99),
(18, 'Router Wi-Fi 6 ASUS', 189.99),
(19, 'Stampante HP LaserJet', 299.99),
(20, 'Webcam 4K Logitech Brio', 199.99);