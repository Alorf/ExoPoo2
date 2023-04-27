package bibliotheque.mvp.presenter;

import bibliotheque.metier.Auteur;
import bibliotheque.metier.Ouvrage;
import bibliotheque.metier.TypeLivre;
import bibliotheque.metier.TypeOuvrage;
import bibliotheque.mvp.model.DAO;
import bibliotheque.mvp.model.auteur.SpecialAuteur;
import bibliotheque.mvp.view.ViewInterface;
import bibliotheque.mvp.view.auteur.AuteurViewInterface;

import java.util.List;

public class AuteurPresenter extends Presenter<Auteur> {

    public AuteurPresenter(DAO<Auteur> model, ViewInterface<Auteur> view) {
        super(model, view);
    }

    public void listerOuvrages(Auteur auteur) {
        List<Ouvrage> louv =   ((SpecialAuteur)model).listerOuvrages(auteur);
        if(louv==null || louv.isEmpty()) view.affMsg("aucun ouvrage trouvé");
        else view.affList(louv);
    }

    public void listerOuvrage(Auteur auteur, TypeOuvrage to, TypeLivre tl) {
        List<Ouvrage> louv =   ((SpecialAuteur)model).listerOuvrages(auteur, to, tl);
        if(louv==null || louv.isEmpty()) view.affMsg("aucun ouvrage trouvé");
        else view.affList(louv);
    }

    public void listerOuvrage(Auteur auteur, String genre) {
        List<Ouvrage> louv =   ((SpecialAuteur)model).listerOuvrages(auteur, genre);
        if(louv==null || louv.isEmpty()) view.affMsg("aucun ouvrage trouvé");
        else view.affList(louv);
    }
}
