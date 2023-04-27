package bibliotheque.mvp.model.lecteur;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;
import bibliotheque.mvp.model.DAO;

import java.util.List;

public interface SpecialLecteur extends DAO <Lecteur>{
    public List<Exemplaire> exemplairesEnLocation(Lecteur l);
    public List<Exemplaire> exemplairesLoues(Lecteur l);

}
