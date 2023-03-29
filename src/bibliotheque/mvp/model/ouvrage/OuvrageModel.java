package bibliotheque.mvp.model.ouvrage;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Ouvrage;

import java.util.ArrayList;
import java.util.List;

public class OuvrageModel implements DAOOuvrage {
    private int codeOuvrage = 0;
    private List<Ouvrage> ouvrages = new ArrayList<>();

    public OuvrageModel(){
    }
    @Override
    public Ouvrage addOuvrage(Ouvrage ouvrage) {
        boolean present = ouvrages.contains(ouvrage);
        if (!present) {
            ouvrages.add(ouvrage);
            return ouvrage;
        } else return null;
    }

    @Override
    public boolean removeOuvrage(Ouvrage ouvrage) {
        return ouvrages.remove(ouvrage);
    }

    @Override
    public Ouvrage updateOuvrage(Ouvrage ouvrage) {
        int p = ouvrages.indexOf(ouvrage);
        if (p < 0) return null;
        ouvrages.set(p, ouvrage);
        return ouvrage;
    }

    @Override
    public Ouvrage readOuvrage(Ouvrage ouv) {
        for (Ouvrage o : ouvrages) {
            if (o.equals(ouv)) return o;
        }
        return null;
    }

    @Override
    public List<Ouvrage> getOuvrages() {
        return ouvrages;
    }

    /*
    private void populate(){
        Ouvrage ouvrage = new Ouvrage("t1", "Science");
        addOuvrage(ouvrage);
        ouvrage = new Ouvrage("t2", "Programmation");
        addOuvrage(ouvrage);
    }

     */
}