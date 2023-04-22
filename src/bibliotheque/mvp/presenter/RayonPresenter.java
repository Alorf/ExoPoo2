package bibliotheque.mvp.presenter;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Rayon;
import bibliotheque.mvp.model.DAO;
import bibliotheque.mvp.model.rayon.SpecialRayon;
import bibliotheque.mvp.view.rayon.RayonViewInterface;

import java.util.List;

public class RayonPresenter {
    private DAO<Rayon> model;
    private RayonViewInterface view;

    public RayonPresenter(DAO<Rayon> model, RayonViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
       view.setListDatas(getAll());
    }

    public List<Rayon> getAll(){
        return model.getAll();
    }

    public void addRayon(Rayon ray) {
        Rayon rayon = model.add(ray);
        if(rayon!=null) view.affMsg("création de :"+rayon);
        else view.affMsg("erreur de création");
       // List<Rayon> rayons = model.getRayons();
       // view.setListDatas(rayons); //désactivé pour éviter appels imbriqués
    }


    public void removeRayon(Rayon rayon) {
        boolean ok = model.remove(rayon);
        if(ok) view.affMsg("rayon effacé");
        else view.affMsg("rayon non effacé");
        //List<Rayon> rayons = model.getRayons();
        //view.setListDatas(rayons); //désactivé pour éviter appels imbriqués
    }
    public void update(Rayon rayon) {
        Rayon l  =model.update(rayon);
        if(l==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+l);
        //view.setListDatas(model.getClients());//désactivé pour éviter appels imbriqués
    }

    public void search(Rayon rayon) {
        Rayon l = model.read(rayon);
        if(l==null) view.affMsg("recherche infructueuse");
        else view.affMsg(l.toString());
    }

    public void listerExemplaires(Rayon r) {
        List<Exemplaire> lr =   ((SpecialRayon)model).listerExemplaires(r);
        if(lr==null || lr.isEmpty()) view.affMsg("aucun exemplaire trouvé");
        else view.affList(lr);
    }
}
