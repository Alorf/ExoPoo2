package bibliotheque.mvp.model.auteur;

import bibliotheque.metier.*;

import java.lang.reflect.Type;
import java.util.List;

public interface SpecialAuteur {

    public List<Ouvrage> listerOuvrages(Auteur aut);
    public List<Ouvrage> listerOuvrages(Auteur aut, TypeOuvrage to, TypeLivre tl);
    public List<Ouvrage> listerOuvrages(Auteur aut, String genre);


}
