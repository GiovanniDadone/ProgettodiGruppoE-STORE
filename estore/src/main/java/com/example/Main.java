package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv
        .configure()
        .directory("estore\\.env")
        .load();

        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connessione Riuscita");
            } else {
                System.out.println("Errore di connessione");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}