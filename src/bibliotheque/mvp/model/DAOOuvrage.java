package bibliotheque.mvp.model;

import bibliotheque.metier.Ouvrage;

import java.util.List;

public interface DAOOuvrage {
    Ouvrage addOuvrage(Ouvrage ouvrage);

    boolean updateOuvrage(Ouvrage ouvrage);

    boolean removeOuvrage(Ouvrage ouvrage);

    List<Ouvrage> getOuvrages();
}
