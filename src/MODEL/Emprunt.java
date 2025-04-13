package MODEL;

import java.time.LocalDate;

public class Emprunt {
    private int idEmprunt;
    private int idLivre;
    private int idMembre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    // Constructeur
    public Emprunt(int idEmprunt, int idLivre, int idMembre, LocalDate dateEmprunt,
                   LocalDate dateRetourPrevue, LocalDate dateRetourEffective){
        this.idEmprunt = idEmprunt;
        this.idLivre = idLivre;
        this.idMembre = idMembre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
    }

    // Méthodes Getters et Setters


    public int getIdEmprunt() {
        return idEmprunt;
    }
    public void setIdEmprunt(int idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public int getIdLivre() {
        return idLivre;
    }
    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public int getIdMembre() {
        return idMembre;
    }
    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }
    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }
    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public LocalDate getDateRetourEffective() {
        return dateRetourEffective;
    }
    public void setDateRetourEffective(LocalDate dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }


    @Override
    public String toString(){
        return "Id_Emprunt : " + idEmprunt + " Id_Livre : " + idLivre + " ID_Membre : " + idMembre +
                " Date_Emprunt : " + dateEmprunt + " Date_Retour_Prevue : " + dateRetourPrevue +
                " Date_Retour_Effective : " + dateRetourEffective;
    }

}
