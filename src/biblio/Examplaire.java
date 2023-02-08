package biblio;

import java.util.ArrayList;
import java.util.List;

public class Examplaire {
    private Long matricule;
    // unique
    private String descriptionEtat;

    private Ouvrage ouvrage;
    private Rayon rayon;
    private List<Location> locations = new ArrayList<>();

    public Examplaire(Long matricule, String descriptionEtat, Ouvrage ouvrage, Rayon rayon, List<Location> locations) {
        this.matricule = matricule;
        this.descriptionEtat = descriptionEtat;
        this.ouvrage = ouvrage;
        this.rayon = rayon;
        this.locations = locations;
    }

    public Long getMatricule() {
        return matricule;
    }

    public void setMatricule(Long matricule) {
        this.matricule = matricule;
    }

    public String getDescriptionEtat() {
        return descriptionEtat;
    }

    public void setDescriptionEtat(String descriptionEtat) {
        this.descriptionEtat = descriptionEtat;
    }

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    public Rayon getRayon() {
        return rayon;
    }

    public void setRayon(Rayon rayon) {
        this.rayon = rayon;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void modifierEtat(){
        return;
    }

    public void lecteurActuel(){
        return;
    }

    public void lecteurs(){
        return;
    }

    public void envoiMailLecteurActuel(Mail mail){
        return;
    }

    public void envoiMailLecteurs(Mail mail){
        return;
    }

    public int joursRetard(){
        return 0;
    }

    public boolean enLocation(){
        return true;
    }

    public void rendre(){
        return;
    }
}
