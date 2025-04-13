
package DAO;


import MODEL.Emprunt;
import MODEL.Livre;
import Utilitaire.DatabaseManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAO {

    // Enregistrer un nouvel emprunt
    public boolean enregistrerEmprunt(Emprunt emprunt) {
        LivreDao livreDAO = new LivreDao();
        Livre livre = livreDAO.getLivreParId(emprunt.getIdLivre());

        if (livre == null) {
            System.out.println("❌ Livre non trouvé dans la base de donnees.");
            return false;
        }

        if (livre.getStock() <= 0) {
            System.out.println("❌ Impossible d'emprunter ce livre : stock épuisé pour \"" + livre.getTitre() + "\"");
            return false;
        }



        String sql = "INSERT INTO Emprunt (idLivre, idMembre, dateEmprunt, dateRetourPrevue, dateRetourEffective) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprunt.getIdLivre());
            stmt.setInt(2, emprunt.getIdMembre());
            stmt.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            stmt.setDate(4, Date.valueOf(emprunt.getDateRetourPrevue()));

            if (emprunt.getDateRetourEffective() != null) {
                stmt.setDate(5, Date.valueOf(emprunt.getDateRetourEffective()));
            } else {
                stmt.setNull(5, Types.DATE);
            }

            int rowsInserted = stmt.executeUpdate();

            // Décrémenter le stock du livre si l'emprunt a été ajouté
            if (rowsInserted > 0) {
                return livreDAO.decrementerStock(emprunt.getIdLivre());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Enregistrer le retour d'un livre
    public boolean enregistrerRetour(int idEmprunt, LocalDate dateRetourEffective) {
        String sql = "UPDATE Emprunt SET dateRetourEffective = ? WHERE idEmprunt = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(dateRetourEffective));
            stmt.setInt(2, idEmprunt);

            int updated = stmt.executeUpdate();

            // Récupérer l'idLivre associé pour réapprovisionner le stock
            if (updated > 0) {
                int idLivre = getIdLivreParEmprunt(idEmprunt);
                if (idLivre > 0) {
                    LivreDao livreDAO = new LivreDao();
                    return livreDAO.incrementerStock(idLivre);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Méthode utilitaire : récupérer l'idLivre associé à un emprunt
    private int getIdLivreParEmprunt(int idEmprunt) {
        String sql = "SELECT idLivre FROM Emprunt WHERE idEmprunt = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEmprunt);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("idLivre");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Lister tous les emprunts actifs (non retournés)
    public List<Emprunt> getEmpruntsActifs() {
        List<Emprunt> emprunts = new ArrayList<>();
        String sql = "SELECT * FROM Emprunt WHERE dateRetourEffective IS NULL";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                emprunts.add(new Emprunt(
                        rs.getInt("idEmprunt"),
                        rs.getInt("idLivre"),
                        rs.getInt("idMembre"),
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetourPrevue").toLocalDate(),
                        null
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprunts;
    }

    // Lister les emprunts par membre
    public List<Emprunt> getEmpruntsParMembre(int idMembre) {
        List<Emprunt> emprunts = new ArrayList<>();
        String sql = "SELECT * FROM Emprunt WHERE idMembre = ? ORDER BY dateEmprunt DESC";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMembre);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                emprunts.add(new Emprunt(
                        rs.getInt("idEmprunt"),
                        rs.getInt("idLivre"),
                        rs.getInt("idMembre"),
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetourPrevue").toLocalDate(),
                        rs.getDate("dateRetourEffective") != null ? rs.getDate("dateRetourEffective").toLocalDate() : null
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprunts;
    }

    // Lister tous les emprunts
    public List<Emprunt> getTousLesEmprunts() {
        List<Emprunt> emprunts = new ArrayList<>();
        String sql = "SELECT * FROM Emprunt ORDER BY dateEmprunt DESC";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                emprunts.add(new Emprunt(
                        rs.getInt("idEmprunt"),
                        rs.getInt("idLivre"),
                        rs.getInt("idMembre"),
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetourPrevue").toLocalDate(),
                        rs.getDate("dateRetourEffective") != null ? rs.getDate("dateRetourEffective").toLocalDate() : null
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprunts;
    }
}
