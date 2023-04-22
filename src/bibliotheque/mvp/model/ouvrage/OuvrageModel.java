package bibliotheque.mvp.model.ouvrage;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Ouvrage;
import bibliotheque.mvp.model.DAO;

import java.util.ArrayList;
import java.util.List;

public class OuvrageModel implements DAO<Ouvrage> {
    private int codeOuvrage = 0;
    private List<Ouvrage> ouvrages = new ArrayList<>();

    public OuvrageModel(){
    }
    @Override
    public Ouvrage add(Ouvrage ouvrage) {
        boolean present = ouvrages.contains(ouvrage);
        if (!present) {
            ouvrages.add(ouvrage);
            return ouvrage;
        } else return null;
    }

    @Override
    public boolean remove(Ouvrage ouvrage) {
        return ouvrages.remove(ouvrage);
    }

    @Override
    public Ouvrage update(Ouvrage ouvrage) {
        int p = ouvrages.indexOf(ouvrage);
        if (p < 0) return null;
        ouvrages.set(p, ouvrage);
        return ouvrage;
    }

    @Override
    public Ouvrage read(Ouvrage ouv) {
        int p = ouvrages.indexOf(ouv);
        if(p<0) return null;
        return ouvrages.get(p);
    }

    @Override
    public List<Ouvrage> getAll() {
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