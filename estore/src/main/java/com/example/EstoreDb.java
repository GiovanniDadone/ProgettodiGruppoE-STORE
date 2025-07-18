package com.example;

import java.sql.*;

public class EstoreDb
{
    private String tableName;

    public EstoreDb(String tableName)
    {
        this.tableName = tableName;
    }

    // Metodo statico che esegue l'inserimento (CREATE) di un utente nel database
    public void insertUtente(String nome, String email) {

        // Query SQL con parametri da sostituire con PreparedStatement 
        String sql = "INSERT INTO ? (nome, email) VALUES (?, ?)";

        // Apertura del blocco try-with-resources che chiude automaticamente connessione e statement
        try (

            // Apertura della connessione al database
            Connection conn = Menu.getDbConnection();

            // Preparazione della query SQL da eseguire con parametri
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // Sostituzione del primo parametro (?) con il nome della tabella
            stmt.setString(1, tableName);

            // Sostituzione del secondo parametro (?) con il nome ricevuto in input
            stmt.setString(2, nome);

            // Sostituzione del terzo parametro (?) con l'email ricevuta in input
            stmt.setString(3, email);

            // Esecuzione dell'istruzione SQL (INSERT)
            stmt.executeUpdate();

            System.out.println("L'utente " + nome + " con email " + email + " è stato inserito correttamente nel DB");

        // Gestione degli eventuali errori SQL 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo statico che esegue l'inserimento (CREATE) di un ordine nel database
    public void insertOrdine(int id, int quantità, String data_ordine, int prodotto_id) {

        // Query SQL con parametri da sostituire con PreparedStatement 
        String sql = "INSERT INTO ordine (id, quantità, data_ordine, prodotto_id) VALUES (?, ?, ?, ?)";

        // Apertura del blocco try-with-resources che chiude automaticamente connessione e statement
        try (

            // Apertura della connessione al database
            Connection conn = Menu.getDbConnection();

            // Preparazione della query SQL da eseguire con parametri
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // Sostituzione del primo parametro 
            stmt.setInt(1, id);

            // Sostituzione del secondo parametro 
            stmt.setInt(2, quantità);

            // Sostituzione del terzo parametro 
            stmt.setString(3, data_ordine);

            // Sostituzione del quarto
            stmt.setInt(4, prodotto_id);

            // Esecuzione dell'istruzione SQL (INSERT)
            stmt.executeUpdate();

            System.out.println("prodotto inserito correttamente nel DB");

        // Gestione degli eventuali errori SQL 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Aggiorna nome utente
    public void updateUtente(int id, String nuovoNome) {
        String sql = "UPDATE ? SET nome = ? WHERE id = ?";
        try (
                Connection conn = Menu.getDbConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tableName);
            stmt.setString(2, nuovoNome);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("Utente aggiornato.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteUser(int id)
    {
        //safe delete query
        String sql = "DELETE FROM ? WHERE id = ?";

        //tries to delete the user record specified
        try
        {
            //gets db connection
            Connection conn = Menu.getDbConnection();

            //creates a prepare statement for safe queries
            PreparedStatement stmt = conn.prepareStatement(sql);

            //sets first '?' parameter
            stmt.setString(1, tableName);
            stmt.setInt(2, id);

            //executes query
            stmt.executeUpdate();

            System.out.println("Utente eliminato.");
        }
        
        //if delete query failed
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
