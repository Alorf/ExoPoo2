package bibliotheque.mvp.presenter;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Ouvrage;
import bibliotheque.mvp.model.ouvrage.DAOOuvrage;
import bibliotheque.mvp.view.ouvrage.OuvrageViewInterface;

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
       view.setListDatas(getAll());
    }

    public List<Ouvrage> getAll(){
        return model.getOuvrages();
    }

    public void addOuvrage(Ouvrage ray) {
        Ouvrage ouvrage = model.addOuvrage(ray);
        if(ouvrage!=null) view.affMsg("création de :"+ouvrage);
        else view.affMsg("erreur de création");
       // List<Ouvrage> ouvrages = model.getOuvrages();
       // view.setListDatas(ouvrages); //désactivé pour éviter appels imbriqués
    }


    public void removeOuvrage(Ouvrage ouvrage) {
        boolean ok = model.removeOuvrage(ouvrage);
        if(ok) view.affMsg("ouvrage effacé");
        else view.affMsg("ouvrage non effacé");
        //List<Ouvrage> ouvrages = model.getOuvrages();
        //view.setListDatas(ouvrages); //désactivé pour éviter appels imbriqués
    }
    public void update(Ouvrage ouvrage) {
        Ouvrage l  =model.updateOuvrage(ouvrage);
        if(l==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+l);
        //view.setListDatas(model.getClients());//désactivé pour éviter appels imbriqués
    }

    public void search(String codeOuvrage) {
        Ouvrage l = model.readOuvrage(codeOuvrage);
        if(l==null) view.affMsg("recherche infructueuse");
        else view.affMsg(l.toString());
    }
}
