package bibliotheque.mvp.presenter;

import bibliotheque.metier.Auteur;
import bibliotheque.metier.Ouvrage;
import bibliotheque.metier.TypeLivre;
import bibliotheque.metier.TypeOuvrage;
import bibliotheque.mvp.model.DAO;
import bibliotheque.mvp.model.auteur.SpecialAuteur;
import bibliotheque.mvp.view.auteur.AuteurViewInterface;

import java.util.List;

public class AuteurPresenter {
    private DAO<Auteur> model;
    private AuteurViewInterface view;

    public AuteurPresenter(DAO<Auteur> model, AuteurViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
       view.setListDatas(getAll());
    }

    public List<Auteur> getAll(){
        return model.getAll();
    }

    public void addAuteur(Auteur auteur) {
        Auteur lec = model.add(auteur);
        if(lec!=null) view.affMsg("création de :"+lec);
        else view.affMsg("erreur de création");
       // List<Auteur> auteurs = model.getAuteurs();
       // view.setListDatas(auteurs); //désactivé pour éviter appels imbriqués
    }


    public void removeAuteur(Auteur auteur) {
        boolean ok = model.remove(auteur);
        if(ok) view.affMsg("auteur effacé");
        else view.affMsg("auteur non effacé");
        //List<Auteur> auteurs = model.getAuteurs();
        //view.setListDatas(auteurs); //désactivé pour éviter appels imbriqués
    }
    public void update(Auteur auteur) {
        Auteur l  =model.update(auteur);
        if(l==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+l);
        //view.setListDatas(model.getClients());//désactivé pour éviter appels imbriqués
    }

    public void search(Auteur auteur) {
        Auteur l = model.read(auteur);
        if(l==null) view.affMsg("recherche infructueuse");
        else view.affMsg(l.toString());
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
