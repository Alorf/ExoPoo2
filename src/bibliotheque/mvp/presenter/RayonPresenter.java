package bibliotheque.mvp.presenter;

import bibliotheque.metier.Rayon;
import bibliotheque.mvp.model.DAORayon;
import bibliotheque.mvp.view.RayonViewInterface;

import java.util.List;

public class RayonPresenter {
    private DAORayon model;
    private RayonViewInterface view;

    public RayonPresenter(DAORayon model, RayonViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        List<Rayon> rays = model.getRayons();
        view.setListDatas(rays);
    }

    public void addRayon(Rayon ray) {
        Rayon rayon = model.addRayon(ray);
        if(rayon!=null) view.affMsg("création de :"+rayon);
        else view.affMsg("erreur de création");
        List<Rayon> Rayons = model.getRayons();
        view.setListDatas(Rayons);
    }

    public void updateRayon(Rayon Rayon){
        boolean ok = model.updateRayon(Rayon);

        if (ok){
            view.affMsg("Modification effectuée");
            List<Rayon> Rayons = model.getRayons();
            view.setListDatas(Rayons);

        }else{
            view.affMsg("Pas de modification à effectuer");
        }

    }


    public void removeRayon(Rayon Rayon) {
        boolean ok = model.removeRayon(Rayon);
        if(ok) view.affMsg("Rayon effacé");
        else view.affMsg("Rayon non effacé");
        List<Rayon> Rayons = model.getRayons();
        view.setListDatas(Rayons);
    }
}
