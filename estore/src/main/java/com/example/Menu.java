package com.example;

import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;

public class Menu {
    // static variables declaration & init

    private static final Dotenv dotenv = Dotenv
            .configure()
            .directory("estore\\.env")
            .load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USERNAME");
    private static String password = dotenv.get("DB_PASSWORD");
    private static Connection dbConnection = null;

    // returns the object connected to the DB
    public static Connection getDbConnection() {
        return dbConnection;
    }

    // init method
    public static void init() {
        // inits scanner inputs
        GlobalScanner.InitScannerInputs();

        // tries to establish a db connection
        try {
            dbConnection = DriverManager.getConnection(URL, USER, password);
        }

        // if connection failed
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // run method
    public static void run() {
        // variables declaration & init
        int menuOption = 0;
        EstoreDb usrTable = new EstoreDb("ordini");
        int ordineId = 0;
        int prodottoId = 0;
        int quantità = 0;
        String data = "";

        do {
            // menu description
            System.out.println("\nScegliere una delle operazioni:");
            System.out.println("1: inserimento ordine");
            System.out.println("2: visualizzazione ordine");
            System.out.println("3: modifica nome ordine");
            System.out.println("4: Cancellazione ordine");
            System.out.println("0: esci");

            // menu input
            menuOption = GlobalScanner.readIntInput();
            System.out.print("\n");

            switch (menuOption) {
                // option 1
                case 1:
                    // inizializzazione variabili
                    ordineId = 0;
                    quantità = 0;
                    prodottoId = 0;
                    data = "";

                    // Lettura ordineId
                    ordineId = GlobalScanner.readIntInput("Inserire l'id dell'ordine:", true);

                    // Lettura ordineId
                    quantità = GlobalScanner.readIntInput("Inserire la quantità:", true);

                    // Lettura ordineId
                    prodottoId = GlobalScanner.readIntInput("Inserire l'id del prodotto:", true);

                    // Lettura data
                    data = GlobalScanner.readStringInput("Inserire la data:", true);

                    // insert new user query
                    usrTable.insertOrdine(ordineId, quantità, data, prodottoId);

                    break;

                // option 2
                case 2:
                    usrTable.readOrdini();
                    break;

                // user update
                case 3:
                    // inizializzazione variabili
                    ordineId = 0;
                    quantità = 0;
                    prodottoId = 0;
                    data = "";

                    // Lettura ordineId
                    ordineId = GlobalScanner.readIntInput("Inserire il numero di Id:", true);

                    // Lettura ordineId
                    quantità = GlobalScanner.readIntInput("Inserire il numero di Id:", true);

                    // Lettura ordineId
                    prodottoId = GlobalScanner.readIntInput("Inserire il numero di Id:", true);

                    // Lettura data
                    data = GlobalScanner.readStringInput("Inserire la data:", true);

                    // update user username query
                    usrTable.updateOrdine(ordineId, quantità, data, prodottoId);

                    break;

                // specified user id delete
                case 4:
                    // inizializzazione variabili
                    ordineId = 0;
                    quantità = 0;
                    prodottoId = 0;
                    data = "";

                    // Lettura ordineId
                    ordineId = GlobalScanner.readIntInput("Inserire il numero di Id:", true);

                    // delete user query
                    usrTable.deleteOrdine(ordineId);
                    break;

                // exit
                case 0:
                    break;

                // menu input not available
                default:
                    System.out.println("Inserire un'opzione valida");
                    break;
            }
        } while (menuOption != 0);

        // closes scanner inputs
        GlobalScanner.CloseScannerInputs();

        // tries to close db connection
        try {
            dbConnection.close();
        }

        // if db connection close failed
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
