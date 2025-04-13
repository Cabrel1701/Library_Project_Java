import DAO.LivreDao;
import DAO.MembreDAO;
import DAO.EmpruntDAO;
import MODEL.Livre;
import MODEL.Membre;
import MODEL.Emprunt;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Initialisation des DAO
        LivreDao livreDAO = new LivreDao();
        MembreDAO membreDAO = new MembreDAO();
        EmpruntDAO empruntDAO = new EmpruntDAO();




            // Afficher tous les livres
        System.out.println("Voici la liste de tous les livres : ");
        List<Livre> livres = livreDAO.getTousLesLivres();
        for (Livre l : livres) {
            System.out.println(l);
        }


            // Afficher tous les livres disponibles
            System.out.println("📚Liste des Livres disponibles :");
            for (Livre l : livreDAO.getLivresDisponibles()) {
                System.out.println(l);
            }

            // Afficher les emprunts actifs
            System.out.println("\\n📄Liste des Emprunts en cours :");
            for (Emprunt e : empruntDAO.getEmpruntsActifs()) {
                System.out.println(e);
            }



            //   Afficher la liste de tous les membres
        System.out.println("👥 Liste de Tous les membres :");
        List<Membre> membres = membreDAO.getTousLesMembres();
        for (Membre m : membres) {
            System.out.println(m);
        }


        // Afficher la liste des emprunts actifs
        System.out.println("\\n📄 Liste des Emprunts actifs (non retournés) :");
        List<Emprunt> emprunts = empruntDAO.getEmpruntsActifs();
        for (Emprunt e : emprunts) {
            System.out.println(e);
        }

















    }
}
