package bibliotheque.mvp.presenter;

import bibliotheque.metier.Auteur;
import bibliotheque.mvp.model.DAOAuteur;
import bibliotheque.mvp.view.AuteurViewInterface;

import java.util.List;

public class AuteurPresenter {
    private DAOAuteur model;
    private AuteurViewInterface view;

    public AuteurPresenter(DAOAuteur model, AuteurViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        List<Auteur> auts = model.getAuteurs();
        view.setListDatas(auts);
    }

    public void addAuteur(Auteur aut) {
        Auteur auteur = model.addAuteur(aut);
        if(auteur!=null) view.affMsg("création de :"+auteur);
        else view.affMsg("erreur de création");
        List<Auteur> Auteurs = model.getAuteurs();
        view.setListDatas(Auteurs);
    }

    public void updateAuteur(Auteur Auteur){
        boolean ok = model.updateAuteur(Auteur);

        if (ok){
            view.affMsg("Modification effectuée");
            List<Auteur> Auteurs = model.getAuteurs();
            view.setListDatas(Auteurs);

        }else{
            view.affMsg("Pas de modification à effectuer");
        }

    }


    public void removeAuteur(Auteur Auteur) {
        boolean ok = model.removeAuteur(Auteur);
        if(ok) view.affMsg("Auteur effacé");
        else view.affMsg("Auteur non effacé");
        List<Auteur> Auteurs = model.getAuteurs();
        view.setListDatas(Auteurs);
    }
}
