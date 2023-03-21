package bibliotheque.mvp.presenter;

import bibliotheque.metier.Ouvrage;
import bibliotheque.mvp.model.DAOOuvrage;
import bibliotheque.mvp.view.OuvrageViewInterface;

import java.util.List;

public class OuvragePresenter {
    private DAOOuvrage model;
    private OuvrageViewInterface view;

    public OuvragePresenter(DAOOuvrage model, OuvrageViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        List<Ouvrage> ouvs = model.getOuvrages();
        view.setListDatas(ouvs);
    }

    public void addOuvrage(Ouvrage ouv) {
        Ouvrage ouvrage = model.addOuvrage(ouv);
        if(ouvrage!=null) view.affMsg("création de :"+ouvrage);
        else view.affMsg("erreur de création");
        List<Ouvrage> Ouvrages = model.getOuvrages();
        view.setListDatas(Ouvrages);
    }

    public void updateOuvrage(Ouvrage Ouvrage){
        boolean ok = model.updateOuvrage(Ouvrage);

        if (ok){
            view.affMsg("Modification effectuée");
            List<Ouvrage> Ouvrages = model.getOuvrages();
            view.setListDatas(Ouvrages);

        }else{
            view.affMsg("Pas de modification à effectuer");
        }

    }


    public void removeOuvrage(Ouvrage Ouvrage) {
        boolean ok = model.removeOuvrage(Ouvrage);
        if(ok) view.affMsg("Ouvrage effacé");
        else view.affMsg("Ouvrage non effacé");
        List<Ouvrage> Ouvrages = model.getOuvrages();
        view.setListDatas(Ouvrages);
    }
}
