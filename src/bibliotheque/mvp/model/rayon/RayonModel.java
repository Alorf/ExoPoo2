package bibliotheque.mvp.model.rayon;

import bibliotheque.metier.Rayon;
import bibliotheque.metier.Exemplaire;

import java.util.ArrayList;
import java.util.List;

public class RayonModel implements DAORayon, SpecialRayon {
    private int codeRayon = 0;
    private List<Rayon> rayons = new ArrayList<>();

    public RayonModel(){
        populate();
    }
    @Override
    public Rayon addRayon(Rayon rayon) {
        boolean present = rayons.contains(rayon);
        if (!present) {
            codeRayon++;
            rayon.setCodeRayon("R"+codeRayon);
            rayons.add(rayon);
            return rayon;
        } else return null;
    }

    @Override
    public boolean removeRayon(Rayon rayon) {
        return rayons.remove(rayon);
    }

    @Override
    public Rayon updateRayon(Rayon rayon) {
        //int idRayon = rayon.getNumrayon();
        int p = rayons.indexOf(rayon);
        if (p < 0) return null;
        rayons.set(p, rayon);//remplacement du rayon à la même position
        return rayon;
    }

    @Override
    public Rayon readRayon(String codeRayon) {
        for (Rayon l : rayons) {
            if (l.getCodeRayon().equals(codeRayon)) return l;
        }
        return null;
    }

    @Override
    public List<Rayon> getRayons() {
        return rayons;
    }

    private void populate(){
        Rayon rayon = new Rayon("t1", "Science");
        addRayon(rayon);
        rayon = new Rayon("t2", "Programmation");
        addRayon(rayon);
    }

    @Override
    public List<Exemplaire> listerExemplaires(Rayon r) {
        return r.listerExemplaires();
    }
}