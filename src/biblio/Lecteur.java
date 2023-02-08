package biblio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lecteur {
    private long numLecteur;
    //unique
    private String nom;
    private String prenom;
    private LocalDate dateNaiss;
    private String mail;
    private String adresse;
    private String tel;

    public Lecteur(long numLecteur, String nom, String prenom, LocalDate dateNaiss, String mail, String adresse, String tel, List<Location> locations) {
        this.numLecteur = numLecteur;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.mail = mail;
        this.adresse = adresse;
        this.tel = tel;
        this.locations = locations;
    }

    public long getNumLecteur() {
        return numLecteur;
    }

    public void setNumLecteur(long numLecteur) {
        this.numLecteur = numLecteur;
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

    public LocalDate getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(LocalDate dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    private List<Location> locations = new ArrayList<>();

    public void listerExemplairesEnLocation(){
        return;
    }

    public void listerExemplairesLoues(){
        return;
    }
}
