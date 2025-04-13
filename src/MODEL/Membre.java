package MODEL;

import java.time.LocalDate;

public class Membre {
    private int idMembre;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateInscription;

    // Constructeur de la classe Membre
    public Membre(int idMembre, String nom, String prenom, String email, LocalDate dateInscription){
        this.idMembre = idMembre;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateInscription = dateInscription;
    }

    // Setters et Getters
    public int getIdMembre(){
        return idMembre;
    }
    public void setIdMembre(int idMembre){
        this.idMembre = idMembre;
    }

    public String getNom(){
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    @Override
    public String toString(){
        return "Id_Membre : " + idMembre + " Nom : " + nom + " Prenom : " + prenom +
                " Email : " + email + " Date_Inscription : " + dateInscription;
    }

}
