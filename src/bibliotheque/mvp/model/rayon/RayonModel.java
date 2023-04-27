package bibliotheque.mvp.model.rayon;

import bibliotheque.metier.Rayon;
import bibliotheque.metier.Exemplaire;
import bibliotheque.mvp.model.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RayonModel implements DAO<Rayon>, SpecialRayon {
    private int codeRayon = 0;
    private List<Rayon> rayons = new ArrayList<>();

    public RayonModel() {
        populate();
    }

    @Override
    public Rayon add(Rayon rayon) {
        boolean present = rayons.contains(rayon);
        if (!present) {
            codeRayon++;
            rayon.setCodeRayon("R" + codeRayon);
            rayons.add(rayon);
            return rayon;
        } else return null;
    }

    @Override
    public boolean remove(Rayon rayon) {
        return rayons.remove(rayon);
    }

    @Override
    public Rayon update(Rayon rayon) {
        //int idRayon = rayon.getNumrayon();
        int p = rayons.indexOf(rayon);
        if (p < 0) return null;
        rayons.set(p, rayon);//remplacement du rayon à la même position
        return rayon;
    }

    @Override
    public Rayon read(Rayon rayon) {
        for (Rayon l : rayons) {
            if (l.equals(rayon)) return l;
        }
        return null;
    }



    @Override
    public List<Rayon> getAll() {
        return rayons;
    }

    private void populate() {
        try{
            Rayon rayon = new Rayon("t1", "Science");
            add(rayon);
            rayon = new Rayon("t2", "Programmation");
            add(rayon);
        }catch (Exception e){
            System.err.println("Erreur " + e.getMessage() );
        }
    }

    @Override
    public List<Exemplaire> listerExemplaires(Rayon r) {
        return r.listerExemplaires();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RayonModel that = (RayonModel) o;
        return codeRayon == that.codeRayon;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeRayon);
    }
}