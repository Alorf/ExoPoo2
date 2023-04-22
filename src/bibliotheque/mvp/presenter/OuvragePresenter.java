package bibliotheque.mvp.presenter;

import bibliotheque.metier.Ouvrage;
import bibliotheque.mvp.model.DAO;
import bibliotheque.mvp.view.ouvrage.OuvrageViewInterface;

import java.util.List;

public class OuvragePresenter {
    private DAO<Ouvrage> model;
    private OuvrageViewInterface view;

    public OuvragePresenter(DAO<Ouvrage> model, OuvrageViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
       view.setListDatas(getAll());
    }

    public List<Ouvrage> getAll(){
        return model.getAll();
    }

    public void addOuvrage(Ouvrage ray) {
        Ouvrage ouvrage = model.add(ray);
        if(ouvrage!=null) view.affMsg("création de :"+ouvrage);
        else view.affMsg("erreur de création");
       // List<Ouvrage> ouvrages = model.getOuvrages();
       // view.setListDatas(ouvrages); //désactivé pour éviter appels imbriqués
    }


    public void removeOuvrage(Ouvrage ouvrage) {
        boolean ok = model.remove(ouvrage);
        if(ok) view.affMsg("ouvrage effacé");
        else view.affMsg("ouvrage non effacé");
        //List<Ouvrage> ouvrages = model.getOuvrages();
        //view.setListDatas(ouvrages); //désactivé pour éviter appels imbriqués
    }
    public void update(Ouvrage ouvrage) {
        Ouvrage l  =model.update(ouvrage);
        if(l==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+l);
        //view.setListDatas(model.getClients());//désactivé pour éviter appels imbriqués
    }

    public void search(Ouvrage ouv) {
        Ouvrage l = model.read(ouv);
        if(l==null) view.affMsg("recherche infructueuse");
        else view.affMsg(l.toString());
    }
}
