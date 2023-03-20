package bibliotheque.mvp.model;

import bibliotheque.metier.Auteur;

import java.util.List;

public interface DAOAuteur {
    Auteur addAuteur(Auteur auteur);

    boolean updateAuteur(Auteur auteur);

    boolean removeAuteur(Auteur auteur);

    List<Auteur> getAuteurs();
}
