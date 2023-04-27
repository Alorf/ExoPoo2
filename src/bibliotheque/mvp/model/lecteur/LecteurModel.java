package bibliotheque.mvp.model.lecteur;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;
import bibliotheque.mvp.model.DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LecteurModel implements DAO<Lecteur>,SpecialLecteur {
    private int numcli = 0;
    private List<Lecteur> lecteurs = new ArrayList<>();

    public LecteurModel(){
        populate();
    }
    @Override
    public Lecteur add(Lecteur lec) {
        boolean present = lecteurs.contains(lec);
        if (!present) {
            numcli++;
            lec.setNumlecteur(numcli);
            lecteurs.add(lec);
            return lec;
        } else return null;
    }

    @Override
    public boolean remove(Lecteur lec) {
        return lecteurs.remove(lec);
    }

    @Override
    public Lecteur update(Lecteur lecteur) {
        //int idLecteur = lecteur.getNumlecteur();
        int p = lecteurs.indexOf(lecteur);
        if (p < 0) return null;
        lecteurs.set(p, lecteur);//remplacement du lecteur à la même position
        return lecteur;
    }

    @Override
    public Lecteur read(Lecteur idLecteur) {
        for (Lecteur l : lecteurs) {
            if (l.equals(idLecteur)) return l;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LecteurModel that = (LecteurModel) o;
        return numcli == that.numcli;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numcli);
    }

    @Override
    public List<Lecteur> getAll() {
        return lecteurs;
    }

    private void populate(){
        try {
            Lecteur lec = new Lecteur(0,"Dupont","Jean", LocalDate.of(2000,1,4),"Mons","jean.dupont@mail.com","0458774411");
            add(lec);
            lec = new Lecteur(0,"Durant","Aline",LocalDate.of(1980,10,10),"Binche","aline.durant@mail.com","045874444");
            add(lec);
        }catch (Exception e){
            System.err.println("Erreur " + e.getMessage());
        }

    }

    @Override
    public List<Exemplaire> exemplairesEnLocation(Lecteur l) {
        return l.listerExemplairesEnLocation();
    }

    @Override
    public List<Exemplaire> exemplairesLoues(Lecteur l) {
        return new ArrayList<>(l.listerExemplairesLoues());
    }
}