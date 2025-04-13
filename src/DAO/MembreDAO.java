
package DAO;

import MODEL.Membre;
import Utilitaire.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDAO {

    // Ajouter un membre
    public boolean ajouterMembre(Membre membre) {
        String sql = "INSERT INTO Membre (nom, prenom, email, date_inscription) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, membre.getNom());
            stmt.setString(2, membre.getPrenom());
            stmt.setString(3, membre.getEmail());
            stmt.setDate(4, Date.valueOf(membre.getDateInscription()));

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Modifier un membre
    public boolean modifierMembre(Membre membre) {
        String sql = "UPDATE Membre SET nom = ?, prenom = ?, email = ?, date_inscription = ? WHERE idMembre = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, membre.getNom());
            stmt.setString(2, membre.getPrenom());
            stmt.setString(3, membre.getEmail());
            stmt.setDate(4, Date.valueOf(membre.getDateInscription()));
            stmt.setInt(5, membre.getIdMembre());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Supprimer un membre
    public boolean supprimerMembre(int idMembre) {
        String sql = "DELETE FROM Membre WHERE idMembre = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMembre);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Récupérer un membre par ID
    public Membre getMembreParId(int idMembre) {
        String sql = "SELECT * FROM Membre WHERE idMembre = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMembre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Membre(
                        rs.getInt("idMembre"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getDate("date_inscription").toLocalDate()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer tous les membres
    public List<Membre> getTousLesMembres() {
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT * FROM Membre";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Membre membre = new Membre(
                        rs.getInt("idMembre"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getDate("date_inscription").toLocalDate()
                );
                membres.add(membre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return membres;
    }
}
