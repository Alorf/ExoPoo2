package bibliotheque.mvp.model;

import bibliotheque.metier.Ouvrage;

import java.util.ArrayList;
import java.util.List;

public class OuvrageModel implements DAOOuvrage {
    private List<Ouvrage> ouvrages = new ArrayList<>();

    @Override
    public Ouvrage addOuvrage(Ouvrage ouv) {
        boolean present= ouvrages.contains(ouv);
        if(!present){
            ouvrages.add(ouv);
            return ouv;
        }
        else return null;
    }

    @Override
    public boolean updateOuvrage(Ouvrage newOuvrage) {
        int index = ouvrages.indexOf(newOuvrage);
        if (index != -1) {
            ouvrages.set(index, newOuvrage);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeOuvrage(Ouvrage ouv) {
        return ouvrages.remove(ouv);
    }

    @Override
    public List<Ouvrage> getOuvrages() {
        return new ArrayList<>(ouvrages);
    }
}

