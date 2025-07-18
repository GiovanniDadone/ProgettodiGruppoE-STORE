package com.example;
import java.sql.*;

public class Menu
{
    //static variables declaration & init
    private static final String URL = "jdbc:mysql://localhost:3306/users_crud";
    private static final String USER = "root";
    private static String password = "tiPiacerebbeSaperla";
    private static Connection dbConnection = null;

    //returns the object connected to the DB
    public static Connection getDbConnection()
    {
        return dbConnection;
    }

    //init method
    public static void init()
    {
        //inits scanner inputs
        GlobalScanner.InitScannerInputs();

        //password input
        password = PasswordField.readPassword("Insert password: ");

        //tries to establish a db connection
        try
        {
            dbConnection = DriverManager.getConnection(URL, USER, password);
        }

        //if connection failed
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //run method
    public static void run()
    {
        //variables declaration & init
        int menuOption = 0;
        EstoreDb usrTable = new EstoreDb("users");
        int userId = 0;
        String username = "";
        String email = "";

        do
        {
            //menu description
            System.out.println("\nScegliere una delle operazioni:");
            System.out.println("1: inserimento utente");
            System.out.println("2: visualizzazione utenti");
            System.out.println("3: modifica nome utente");
            System.out.println("4: Cancellazione utente");
            System.out.println("0: esci");

            //menu input
            menuOption = GlobalScanner.readIntInput();
            System.out.print("\n");

            switch(menuOption)
            {
                //option 1
                case 1:
                    //inizializzazione variabili
                    username = "";
                    email = "";

                    // Lettura nome
                    username = GlobalScanner.readStringInput("Inserire il nome utente:", true);

                    // Lettura email
                    email = GlobalScanner.readStringInput("Inserire l'email:", true);

                    //insert new user query
                    usrTable.insertUtente(username, email);

                    break;

                //option 2
                case 2:
                    System.out.println("Option not available");
                    break;

                //user update
                case 3:
                    //inizializzazione variabili
                    userId = 0;
                    username = "";

                    // Lettura ID
                    userId = GlobalScanner.readIntInput("Seleziona id dell'utente da rinominare:", true);
                    
                    // Lettura nuovo nome
                    username = GlobalScanner.readStringInput("Nuovo nome utente:", true);

                    // update user username query
                    usrTable.updateUtente(userId, username);

                    break;

                //specified user id delete
                case 4:
                    //inizializzazione variabili
                    userId = 0;

                    //user ID input
                    userId = GlobalScanner.readIntInput("Insert the user ID to delete", true);

                    //delete user query
                    usrTable.deleteUser(userId);
                    break;

                //exit
                case 0:
                    break;

                //menu input not available
                default:
                    System.out.println("Inserire un'opzione valida");
                    break;
            }
        }
        while(menuOption != 0);

        //closes scanner inputs
        GlobalScanner.CloseScannerInputs();

        //tries to close db connection
        try
        {
            dbConnection.close();
        }
        
        //if db connection close failed
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
