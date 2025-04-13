package DAO;

import MODEL.Livre;
import Utilitaire.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;  // pour plus tard si on veut retourner plusieurs livres
import java.util.List;


public class LivreDao {
    // Ajouter un livre à la base de données
    public boolean ajouterLivre(Livre livre) {       // Creation d'une methode qui prend un objet livre et renvoie un boolean (succes ou echec)
        String sql = "INSERT INTO Livre (titre, auteur, anneePublication, stock) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();   // On ouvre une connection
             PreparedStatement stmt = conn.prepareStatement(sql)) {  // Prepare une requete sql avec des parametres sécurisés
                                                                    // Garantit que la connection sera fermée automatiquement, meme en cas d'erreur
            stmt.setString(1, livre.getTitre());  // Injecte la valeur de l'objet
            stmt.setString(2, livre.getAuteur());
            stmt.setInt(3, livre.getAnneePublication());  // On remplace chaque '?' par les vraies valeurs du livre
            stmt.setInt(4, livre.getStock());

            int rowsInserted = stmt.executeUpdate();    // execute une requete
            return rowsInserted > 0;  // retourne le nombre de lignes affectées

        } catch (SQLException e) {  // Si un erreur se produit, on affiche l'erreur et on retourne false
            e.printStackTrace();
            return false;
        }
    }


    // Modifier un livre à la base de données
    public boolean modifierLivre(Livre livre) {
        String sql = "UPDATE Livre SET titre = ?, auteur = ?, anneePublication = ?, stock = ? WHERE idLivre = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setInt(3, livre.getAnneePublication());
            stmt.setInt(4, livre.getStock());
            stmt.setInt(5, livre.getIdLivre());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Supprimer un livre à la base de données par son ID
    public boolean supprimerLivre(int idLivre) {
        String sql = "DELETE FROM Livre WHERE idLivre = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLivre);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




    // Méthode pour récupérer un livre par son ID
    public Livre getLivreParId(int idLivre) {
        String sql = "SELECT * FROM Livre WHERE idLivre = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLivre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Livre(
                        rs.getInt("idLivre"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getInt("anneePublication"),
                        rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    // Méthode pour récuperer tous les livres

    public List<Livre> getTousLesLivres() {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM Livre";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Livre livre = new Livre(
                        rs.getInt("IdLivre"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getInt("anneePublication"),
                        rs.getInt("stock")
                );
                livres.add(livre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livres;

    }



    // Méthode pour afficher la liste des livres disponibles (stock > 0)

    public List<Livre> getLivresDisponibles() {
        List<Livre> disponibles = new ArrayList<>();
        String sql = "SELECT * FROM Livre WHERE stock > 0";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Livre livre = new Livre(
                        rs.getInt("idLivre"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getInt("anneePublication"),
                        rs.getInt("stock")
                );
                disponibles.add(livre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disponibles;
    }



    // Méthode qui permet de décrementer le stock d'un livre lors d'un emprunt

    public boolean decrementerStock(int idLivre) {
        String sql = "UPDATE Livre SET stock = stock - 1 WHERE idLivre = ? AND stock > 0";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLivre);
            int updated = stmt.executeUpdate();
            return updated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Méthode pour incrementer le stock d'un livre lors d'un retour
    public boolean incrementerStock(int idLivre) {
        String sql = "UPDATE Livre SET stock = stock + 1 WHERE idLivre = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLivre);
            int updated = stmt.executeUpdate();
            return updated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
