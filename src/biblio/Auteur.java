package biblio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Auteur {
    private String nom;
    private String prenom;
    private String nationalite;

    private List<Ouvrage> ouvrages = new ArrayList<>();

    public Auteur(String nom, String prenom, String nationalite, List<Ouvrage> ouvrages) {
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
        this.ouvrages = ouvrages;
    }

    public String getNom() {
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

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public List<Ouvrage> getOuvrages() {
        return ouvrages;
    }

    public void setOuvrages(List<Ouvrage> ouvrages) {
        this.ouvrages = ouvrages;
    }

    public void listerOuvrages(){
        return;
    }

    public void listerOuvrages(TypeOuvrage typeOuvrage,TypeLivre typeLivre){
        return;
    }

    public void listerOuvrage(String genre){
        return;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auteur auteur = (Auteur) o;
        return Objects.equals(nom, auteur.nom) && Objects.equals(prenom, auteur.prenom) && Objects.equals(nationalite, auteur.nationalite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, nationalite);
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nationalite='" + nationalite + '\'' +
                ", ouvrages=" + ouvrages +
                '}';
    }
}
