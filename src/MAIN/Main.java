package MAIN;

import DAO.LivreDao;
import DAO.MembreDAO;
import DAO.EmpruntDAO;
import MODEL.Emprunt;
import MODEL.Livre;
import MODEL.Membre;



import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // DAO instanciés une seule fois
        LivreDao livreDAO = new LivreDao();
        MembreDAO membreDAO = new MembreDAO();
        EmpruntDAO empruntDAO = new EmpruntDAO();

        int choix;

        do {
            // Menu principal
            System.out.println("\n===== MENU BIBLIOTHÈQUE =====\n");
            System.out.println("1. Lister tous les livres");
            System.out.println("2. Lister tous les membres");
            System.out.println("3. Ajouter un livre");
            System.out.println("4. Modifier un livre");
            System.out.println("5. Supprimer un livre");
            System.out.println("6. Ajouter un membre");
            System.out.println("7. Modifier un membre");
            System.out.println("8. Supprimer un membre");
            System.out.println("9. Enregistrer un emprunt");
            System.out.println("10. Retourner un livre");
            System.out.println("11. Lister les emprunts actifs (Livres non retournés)");
            System.out.println("12. Lister les emprunts terminés (Livres retournés)");
            System.out.println("13. Lister tous les emprunts");
            System.out.println("14. Lister les emprunts d’un membre");
            System.out.println("0. Quitter");
            System.out.println("\n");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n");




            switch (choix) {
                case 1:
                    // Afficher tous les livres disponibles
                    System.out.println("================================ LISTE DES LIVRES DISPONIBLES ================================\n\n");
                    List<Livre> livres = livreDAO.getTousLesLivres();
                    System.out.printf("%-4s | %-30s | %-30s | %-20s | %-10s\n", "ID", "TITRE", "AUTEUR", "ANNÉE DE PUBLICATION", "STOCK");
                    System.out.println("====+==============================+================================+==========================+==========");
                    for (Livre l : livres) {
                        System.out.printf("%-4d | %-30s | %-30s | %-20d | %-10d\n", l.getIdLivre(), l.getTitre(), l.getAuteur(), l.getAnneePublication(), l.getStock());
                        System.out.println("----+------------------------------+-----------------------------------+----------------------+----------");
                    }
                    break;

                case 2:
                    // Afficher tous les membres
                    System.out.println("============ LISTE DE TOUS LES MEMBRES DE LA BIBLIOTHEQUE ============\n\n");
                    List<Membre> membres = membreDAO.getTousLesMembres();
                    System.out.printf("%-4s | %-15s | %-15s | %-30s\n", "ID", "Nom", "Prénom", "Email");
                    System.out.println("=====+=================+=================+================================");
                    for (Membre m : membres) {
                        System.out.printf("%-4d | %-15s | %-15s | %-30s\n", m.getIdMembre(), m.getNom(), m.getPrenom(), m.getEmail());
                        System.out.println("-----+-----------------+-----------------+--------------------------------");
                    }
                    break;



                case 3:
                    // Ajouter un livre
                    System.out.println("============ AJOUT D'UN LIVRE ============\n");
                    System.out.print("Titre : ");
                    String titre = scanner.nextLine();
                    System.out.print("Auteur : ");
                    String auteur = scanner.nextLine();
                    System.out.print("Année de publication : ");
                    int annee = scanner.nextInt();
                    System.out.print("Stock : ");
                    int stock = scanner.nextInt(); scanner.nextLine();
                    Livre newLivre = new Livre(0, titre, auteur, annee, stock);
                    System.out.println(livreDAO.ajouterLivre(newLivre) ? "✅ Livre ajouté." : "❌ Échec.");
                    break;

                case 4:
                    // Modifier un livre
                    System.out.println("============ MODIFICATION D'UN LIVRE ============\n");
                    System.out.print("ID du livre à modifier : ");
                    int idModif = scanner.nextInt(); scanner.nextLine();
                    Livre lm = livreDAO.getLivreParId(idModif);
                    if (lm == null) { System.out.println("❌ Livre introuvable (L'ID que vous avez fourni n'existe pas !!!)"); break; }
                    System.out.print("Nouveau titre (" + lm.getTitre() + ") : ");
                    String t = scanner.nextLine(); if (!t.isBlank()) lm.setTitre(t);
                    System.out.print("Nouvel auteur (" + lm.getAuteur() + ") : ");
                    String a = scanner.nextLine(); if (!a.isBlank()) lm.setAuteur(a);
                    System.out.print("Nouvelle année de publication (" + lm.getAnneePublication() + ") : ");
                    String an = scanner.nextLine(); if (!an.isBlank()) lm.setAnneePublication(Integer.parseInt(an));
                    System.out.print("Nouveau Stock (" + lm.getStock() + ") : ");
                    String st = scanner.nextLine(); if (!st.isBlank()) lm.setStock(Integer.parseInt(st));
                    System.out.println(livreDAO.modifierLivre(lm) ? "✅ Le livre a été Modifié." : "❌ Échec de la modification du livre");
                    break;




                case 5:
                    // Supprimer un livre
                    System.out.println("============ SUPPRESSION D'UN LIVRE ============\n");
                    System.out.print("ID livre à supprimer : ");
                    int idSup = scanner.nextInt(); scanner.nextLine();
                    Livre lSup = livreDAO.getLivreParId(idSup);
                    if (lSup == null) { System.out.println("❌ Livre Introuvable (L'ID que vous avez fourni n'existe pas !!!)"); break; }
                    System.out.print("Confirmer suppression du livre ? (o/n) : ");
                    String confirm = scanner.nextLine();
                    System.out.println(confirm.equalsIgnoreCase("o") && livreDAO.supprimerLivre(idSup) ? "✅ Le livre a été supprimé." : "❌ Échec de la suppresson du livre.");
                    break;

                case 6:
                    // Ajouter un membre
                    System.out.println("============ AJOUT D'UN MEMBRE ============\n");
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Email : ");
                    String email = scanner.nextLine();
                    Membre membre = new Membre(0, nom, prenom, email, LocalDate.now());
                    System.out.println(membreDAO.ajouterMembre(membre) ? "✅ Membre ajouté avec ssucès." : "❌ Échec de l'ajout du membre.");
                    break;

                case 7:
                    // Modifier un membre
                    System.out.println("============ MODIFICATION D'UN MEMBRE ============\n");
                    System.out.print("ID membre : ");
                    int idm = scanner.nextInt(); scanner.nextLine();
                    Membre mm = membreDAO.getMembreParId(idm);
                    if (mm == null) { System.out.println("❌ Membre Introuvable (L'ID que vous avez fourni n'existe pas dans la base de données"); break; }
                    System.out.print("Nouveau prénom (" + mm.getPrenom() + ") : ");
                    String pr = scanner.nextLine(); if (!pr.isBlank()) mm.setPrenom(pr);
                    System.out.print("Nouveau nom (" + mm.getNom() + ") : ");
                    String no = scanner.nextLine(); if (!no.isBlank()) mm.setNom(no);
                    System.out.print("Nouvel email (" + mm.getEmail() + ") : ");
                    String em = scanner.nextLine(); if (!em.isBlank()) mm.setEmail(em);
                    System.out.println(membreDAO.modifierMembre(mm) ? "✅ Membre modifié avec succès." : "❌ Échec de la modification du membre.");
                    break;




                case 8:
                    // Supprimer un membre
                    System.out.println("============ SUPPRESSION D'UN MEMBRE ============\n");
                    System.out.print("ID membre : ");
                    int iddel = scanner.nextInt(); scanner.nextLine();
                    Membre mdel = membreDAO.getMembreParId(iddel);
                    if (mdel == null) { System.out.println("❌ Introuvable (L'ID que vous avez fourni n'existe pas dans la base de données\")"); break; }
                    System.out.print("Confirmer suppression ? (o/n) : ");
                    String cdel = scanner.nextLine();
                    System.out.println(cdel.equalsIgnoreCase("o") && membreDAO.supprimerMembre(iddel) ? "✅ Supprimé." : "❌ Échec (Ce membre a des livres empruntés qui n'ont pas été retournés).");
                    break;

                case 9:
                    // Enregistrer un emprunt
                    System.out.println("============ ENREGISTREMENT D'UN EMPRUNT ============\n");
                    System.out.print("ID livre : ");
                    int lid = scanner.nextInt();
                    System.out.print("ID membre : ");
                    int mid = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Date emprunt (YYYY-MM-DD) : ");
                    LocalDate d1 = LocalDate.parse(scanner.nextLine());
                    System.out.print("Retour prévu (YYYY-MM-DD) : ");
                    LocalDate d2 = LocalDate.parse(scanner.nextLine());
                    Emprunt emp = new Emprunt(0, lid, mid, d1, d2, null);
                    System.out.println(empruntDAO.enregistrerEmprunt(emp) ? "✅ Emprunt enregistré." : "❌ Échec de l'emprunt du livre.");
                    break;

                case 10:
                    // Retourner un livre
                    System.out.println("============ RETOUR D'UN LIVRE EMPRUNTÉ ============\n");
                    System.out.print("ID emprunt : ");
                    int idE = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Date retour (YYYY-MM-DD) : ");
                    LocalDate dR = LocalDate.parse(scanner.nextLine());
                    System.out.println(empruntDAO.enregistrerRetour(idE, dR) ? "✅ Retour enregistré." : "❌ Échec de l'enregistrement du retour.");
                    break;





                case 11:
                    // Liste des emprunts actifs
                    System.out.println("============ LISTE DES EMPRUNTS ACTIFS (LIVRES NON RETOURNÉS) ============\n");
                    List<Emprunt> actifs = empruntDAO.getEmpruntsActifs();
                    System.out.printf("%-4s | %-8s | %-10s | %-12s | %-13s | %-16s\n", "ID", "Livre ID", "Membre ID", "Emprunté le", "Retour prévu", "Retour effectif");
                    System.out.println("=====+==========+============+==============+===============+==================");
                    for (Emprunt e : actifs) {
                        System.out.printf("%-4d | %-8d | %-10d | %-12s | %-13s | %-16s\n", e.getIdEmprunt(), e.getIdLivre(), e.getIdMembre(), e.getDateEmprunt(), e.getDateRetourPrevue(), e.getDateRetourEffective() != null ? e.getDateRetourEffective() : "Non retourné");
                        System.out.println("-----+----------+------------+--------------+---------------+------------------");
                    }
                    break;

                case 12:
                    // Liste des emprunts retournés
                    System.out.println("============ LISTE DES EMPRUNTS TERMINÉS (LIVRES RETOURNÉS) ============\n");
                    List<Emprunt> termines = empruntDAO.getTousLesEmprunts().stream().filter(e -> e.getDateRetourEffective() != null).toList();
                    System.out.printf("%-12s | %-10s | %-10s | %-15s | %-20s\n", "ID Emprunt", "ID Livre", "ID Membre", "Date Emprunt", "Date Retour Effective");
                    System.out.println("=============+============+============+=================+======================");
                    for (Emprunt e : termines) {
                        System.out.printf("%-12d | %-10d | %-10d | %-15s | %-20s\n", e.getIdEmprunt(), e.getIdLivre(), e.getIdMembre(), e.getDateEmprunt(), e.getDateRetourEffective());
                        System.out.println("-------------+------------+------------+-----------------+----------------------");
                    }
                    break;

                case 13:
                    // Lister tous les emprunts
                    System.out.println("============ LISTE DES TOUS LES EMPRUNTS (LIVRES RETOURNÉS ET NON RETOURNÉS) ============\n");
                    List<Emprunt> tous = empruntDAO.getTousLesEmprunts();
                    System.out.printf("%-12s | %-10s | %-10s | %-15s | %-15s | %-20s\n", "ID Emprunt", "ID Livre", "ID Membre", "Date Emprunt", "Date Retour Prévue", "Date Retour Effective");
                    System.out.println("=============+============+============+=================+====================+======================");
                    for (Emprunt e : tous) {
                        System.out.printf("%-12d | %-10d | %-10d | %-15s | %-18s | %-20s\n", e.getIdEmprunt(), e.getIdLivre(), e.getIdMembre(), e.getDateEmprunt(), e.getDateRetourPrevue(), e.getDateRetourEffective() != null ? e.getDateRetourEffective() : "Non retourné");
                        System.out.println("-------------+------------+------------+-----------------+--------------------+----------------------");
                    }
                    break;

                case 14:
                    // Emprunts par membre
                    System.out.print("ID membre : ");
                    int idmbr = scanner.nextInt(); scanner.nextLine();
                    Membre memb = membreDAO.getMembreParId(idmbr);
                    System.out.println("👤  Prenom : " + memb.getPrenom() + "  | Nom : " + memb.getNom() + "  | Email : " + memb.getEmail());
                    System.out.println("\n");
                    List<Emprunt> ems = empruntDAO.getEmpruntsParMembre(idmbr);
                    System.out.printf("%-12s | %-10s | %-15s | %-20s | %-20s\n", "ID Emprunt", "ID Livre", "Date Emprunt", "Date Retour Prévue", "Date Retour Effective");
                    System.out.println("=============+============+=================+====================+==========================");
                    for (Emprunt e : ems) {
                        System.out.printf("%-12d | %-10d | %-15s | %-20s | %-20s\n", e.getIdEmprunt(), e.getIdLivre(), e.getDateEmprunt(), e.getDateRetourPrevue(), e.getDateRetourEffective() != null ? e.getDateRetourEffective() : "Non retourné");
                        System.out.println("-------------+------------+-----------------+----------------------+-----------------------");
                    }
                    break;



                case 0:
                    // Quitter le programme
                    System.out.println("👋 Merci et à bientôt !");
                    break;

                default:
                    System.out.println("❌ Choix invalide, Veuillez entrer un choix valide !!! ");
            }

        } while (choix != 0);
        scanner.close();
    }
}







