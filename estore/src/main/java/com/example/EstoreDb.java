package com.example;

import java.sql.*;

public class EstoreDb {
    private String tableName;

    public EstoreDb(String tableName) {
        this.tableName = tableName;
    }

    // Metodo statico che esegue l'inserimento (CREATE) di un ordine nel database
    public void insertOrdine(int id, int quantità, String data_ordine, int prodotto_id) {

        // Query SQL con parametri da sostituire con PreparedStatement
        String sql = "INSERT INTO ordine (id, quantità, data_ordine, prodotto_id) VALUES (?, ?, ?, ?)";

        // Apertura del blocco try-with-resources che chiude automaticamente connessione
        // e statement
        try (

                // Apertura della connessione al database
                Connection conn = Menu.getDbConnection();

                // Preparazione della query SQL da eseguire con parametri
                PreparedStatement stmt = conn.prepareStatement(sql)) {
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

            System.out.println("Ordine inserito correttamente nel DB");

            // Gestione degli eventuali errori SQL
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readOrdini() {
        String sql = "SELECT * FROM ordine";

        try (Connection conn = Menu.getDbConnection()) {

            // Verifica se la connessione è ancora attiva
            if (conn == null || conn.isClosed()) {
                System.err.println("Errore: Connessione al database non disponibile");
                return;
            }

            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(sql);

                System.out.println("=== ELENCO ORDINI ===");
                System.out.printf("%-5s %-10s %-15s %-12s%n", "ID", "Quantità", "Data Ordine", "Prodotto ID");
                System.out.println("------------------------------------------------");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    int quantita = rs.getInt("quantità");
                    String dataOrdine = rs.getString("data_ordine");
                    int prodottoId = rs.getInt("prodotto_id");

                    System.out.printf("%-5d %-10d %-15s %-12d%n",
                            id, quantita, dataOrdine, prodottoId);
                }
            }

        } catch (Exception e) {
            System.err.println("Errore durante la lettura degli ordini:");
            e.printStackTrace();
        }
    }

    // Aggiorna nome utente
    public void updateOrdine(int id, int quantità, String data_ordine, int prodotto_id) {
        String sql = "UPDATE ? SET nome = ? WHERE id = ?";
        try (
                Connection conn = Menu.getDbConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tableName);
            stmt.setInt(2, quantità);
            stmt.setString(3, data_ordine);
            stmt.setInt(4, prodotto_id);
            stmt.executeUpdate();
            System.out.println("Ordine aggiornato.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteOrdine(int id) {
        // safe delete query
        String sql = "DELETE FROM ? WHERE id = ?";

        // tries to delete the user record specified
        try {
            // gets db connection
            Connection conn = Menu.getDbConnection();

            // creates a prepare statement for safe queries
            PreparedStatement stmt = conn.prepareStatement(sql);

            // sets first '?' parameter
            stmt.setString(1, tableName);
            stmt.setInt(2, id);

            // executes query
            stmt.executeUpdate();

            System.out.println("Ordine eliminato.");
        }

        // if delete query failed
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
