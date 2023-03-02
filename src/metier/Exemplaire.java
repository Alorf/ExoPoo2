package metier;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exemplaire {

    private String matricule;
    private String descriptionEtat;

    private Ouvrage ouvrage;
    private Rayon rayon;

    private List<Location> lloc = new ArrayList<>();


    public Exemplaire(String matricule, String descriptionEtat, Ouvrage ouvrage) {
        this.matricule = matricule;
        this.descriptionEtat = descriptionEtat;
        this.ouvrage = ouvrage;
        this.ouvrage.getLex().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exemplaire that = (Exemplaire) o;
        return Objects.equals(matricule, that.matricule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricule);
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
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
        if (this.ouvrage != null) this.ouvrage.getLex().remove(this);
        this.ouvrage = ouvrage;
        this.ouvrage.getLex().add(this);
    }

    public Rayon getRayon() {
        return rayon;
    }

    public void setRayon(Rayon rayon) {
        if (this.rayon != null) this.rayon.getLex().remove(this);
        this.rayon = rayon;
        this.rayon.getLex().add(this);
    }

    public List<Location> getLloc() {
        return lloc;
    }

    public void setLloc(List<Location> lloc) {
        this.lloc = lloc;
    }

    @Override
    public String toString() {
        return "Exemplaire{" +
                "matricule='" + matricule + '\'' +
                ", descriptionEtat='" + descriptionEtat + '\'' +
                ", ouvrage=" + ouvrage +
                ", rayon=" + rayon +
                '}';
    }

    public void modifierEtat(String etat) {
        this.descriptionEtat = etat;
    }

    public Lecteur lecteurActuel() {
        if (enLocation()){
            return lloc.get(lloc.size()-1).getLoueur();
        }
        return null;
    }

    public List<Lecteur> lecteurs() {
        List<Lecteur> temp = new ArrayList<>();
        for (Location l : lloc){
            temp.add(l.getLoueur());
        }
        return temp;
    }

    public void envoiMailLecteurActuel(Mail mail) {
        System.out.println(mail.getMessage());
        System.out.println(mail.getDateEnvoi());
    }

    public void envoiMailLecteurs(Mail mail) {
        System.out.println(mail.getMessage());
        System.out.println(mail.getDateEnvoi());
    }

    public boolean enRetard() {
        LocalDate dateLoc;
        dateLoc = lloc.get(lloc.size()-1).getDateLocation().plusDays(7);

        return (LocalDate.now().isAfter(dateLoc));
    }

    public int joursRetard() {
        LocalDate dateLoc;
        dateLoc = lloc.get(lloc.size()-1).getDateLocation();

        return (int) Duration.between(dateLoc, dateLoc.plusDays(7)).toDays();
    }


    public boolean enLocation() {
        if (lloc.get(lloc.size()-1).getDateRestitution() != null){
            return false;
        }
        return true;
    }


}
