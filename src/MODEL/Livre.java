package MODEL;

public class Livre {
    private int idLivre;
    private String titre;
    private String auteur;
    private int anneePublication;
    private int stock;


    public Livre(int idLivre, String titre, String auteur, int anneePublication, int stock){
        this.idLivre = idLivre;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.stock = stock;
    }

    // Méthodes Getters et Setters
    public int getIdLivre(){
        return idLivre;
    }
    public void setIdLivre(int idLivre){
        this.idLivre = idLivre;
    }

    public String getTitre(){
        return titre;
    }
    public void setTitre(String titre){
        this.titre = titre;
    }

    public String getAuteur(){
        return auteur;
    }
    public void setAuteur(String auteur){
        this.auteur = auteur;
    }

    public int getAnneePublication(){
        return anneePublication;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stock) {
        if (stock >= 0){
            this.stock = stock;
        }
        else{
            throw new IllegalArgumentException("Le stock ne peut pas être négatif !!! ");
        }
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Id_Livre : " + idLivre + " Titre : " + titre + " Auteur : " + auteur +
                " Année Publication : " + anneePublication + " Stock : " + stock;
    }

}
