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
    FOREIGN KEY (prodotto_id) REFERENCES prodotto(id)
);