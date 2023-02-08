package biblio;

import java.time.LocalDate;

public class Location {
    private LocalDate dateLoc;
    private LocalDate dateRestitution;
    private double ammende;

    private Examplaire examplaire;
    private Lecteur loueur;

    public Location(LocalDate dateLoc, LocalDate dateRestitution, double ammende, Examplaire examplaire, Lecteur loueur) {
        this.dateLoc = dateLoc;
        this.dateRestitution = dateRestitution;
        this.ammende = ammende;
        this.examplaire = examplaire;
        this.loueur = loueur;
    }

    public LocalDate getDateLoc() {
        return dateLoc;
    }

    public void setDateLoc(LocalDate dateLoc) {
        this.dateLoc = dateLoc;
    }

    public LocalDate getDateRestitution() {
        return dateRestitution;
    }

    public void setDateRestitution(LocalDate dateRestitution) {
        this.dateRestitution = dateRestitution;
    }

    public double getAmmende() {
        return ammende;
    }

    public void setAmmende(double ammende) {
        this.ammende = ammende;
    }

    public Examplaire getExamplaire() {
        return examplaire;
    }

    public void setExamplaire(Examplaire examplaire) {
        this.examplaire = examplaire;
    }

    public Lecteur getLoueur() {
        return loueur;
    }

    public void setLoueur(Lecteur loueur) {
        this.loueur = loueur;
    }

    public void calculerAmende(){
        return;
    }

    public void enregistrerRetour(){
        return;
    }
}
