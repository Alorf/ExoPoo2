package bibliotheque.mvp.presenter;

import bibliotheque.metier.Auteur;
import bibliotheque.mvp.model.DAO;
import bibliotheque.mvp.view.ViewInterface;

import java.util.List;

public class Presenter<T> {

    protected DAO<T> model;
    protected ViewInterface<T> view;

    public Presenter(DAO<T> model, ViewInterface<T> ViewInterface){
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        view.setListDatas(getAll());
    }

    public void add(T objet) {
        T obj = model.add(objet);
        if (obj != null) view.affMsg("création de :" + obj);
        else view.affMsg("erreur de création");
    }

    public void update(T objet) {
        boolean ok = model.remove(objet);
        if (ok) view.affMsg("auteur effacé");
        else view.affMsg("auteur non effacé");
    }

    public void remove(T objet) {
        T obj = model.update(objet);
        if (obj == null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : " + obj);
    }

    public void search(T objet) {
        T obj = model.read(objet);
        if (obj == null) view.affMsg("recherche infructueuse");
        else view.affMsg(obj.toString());
    }

    public List<T> getAll() {
        return model.getAll();
    }

}
