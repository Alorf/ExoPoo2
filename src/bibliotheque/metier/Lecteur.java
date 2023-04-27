package bibliotheque.metier;

import java.time.LocalDate;
import java.util.*;

public class Lecteur {
    private int numlecteur;
    private  String nom,prenom;
    private LocalDate dn;
    private String adresse;
    private String mail;
    private String tel;

    private List<Location> lloc=new ArrayList<>();

    public Lecteur(int numlecteur, String nom, String prenom, LocalDate dn, String adresse, String mail, String tel) throws Exception{
        if (nom.isEmpty()){
            throw new Exception("Le champ nom ne peut pas être vide");
        }

        if (prenom.isEmpty()){
            throw new Exception("Le champ prenom ne peut pas être vide");
        }

        if (adresse.isEmpty()){
            throw new Exception("Le champ adresse ne peut pas être vide");
        }

        if (mail.isEmpty()){
            throw new Exception("Le champ mail ne peut pas être vide");
        }

        if (tel.isEmpty()){
            throw new Exception("Le champ tel ne peut pas être vide");
        }

        this.numlecteur = numlecteur;
        this.nom = nom;
        this.prenom = prenom;
        this.dn = dn;
        this.adresse = adresse;
        this.mail = mail;
        this.tel = tel;
    }

    public Lecteur(int numlecteur){
        this.numlecteur = numlecteur;
    }

    public int getNumlecteur() {
        return numlecteur;
    }

    public void setNumlecteur(int numlecteur) {
        this.numlecteur = numlecteur;
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

    public LocalDate getDn() {
        return dn;
    }

    public void setDn(LocalDate dn) {
        this.dn = dn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<Location> getLloc() {
        return lloc;
    }

    public void setLloc(List<Location> lloc) {
        this.lloc = lloc;
    }

    @Override
    public String toString() {
        return "Lecteur{" +
                "numlecteur=" + numlecteur +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dn=" + dn +
                ", adresse='" + adresse + '\'' +
                ", mail='" + mail + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecteur lecteur = (Lecteur) o;
        return numlecteur == lecteur.numlecteur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numlecteur);
    }

    public List<Exemplaire> listerExemplairesEnLocation(){
        List<Exemplaire> lex = new ArrayList<>();
        for(Location loc : lloc){
            if(loc.getDateRestitution()!=null)lex.add(loc.getExemplaire());
        }
        return lex;
    }

    public Set<Exemplaire> listerExemplairesLoues(){
        Set<Exemplaire> stex = new HashSet<>();
        for(Location loc : lloc){
            stex.add(loc.getExemplaire());
        }
       return stex;
    }
}
