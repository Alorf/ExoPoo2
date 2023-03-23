package bibliotheque.mvp.model.auteur;

import bibliotheque.metier.Auteur;
import bibliotheque.metier.Ouvrage;

import java.util.List;

public interface DAOAuteur {
    Auteur addAuteur(Auteur auteur);

    boolean removeAuteur(Auteur auteur);
    Auteur updateAuteur(Auteur auteur);

    Auteur readAuteur(Auteur auteur);

    List<Auteur> getAuteurs();

}
