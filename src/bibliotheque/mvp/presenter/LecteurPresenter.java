package bibliotheque.mvp.presenter;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;
import bibliotheque.mvp.model.DAO;
import bibliotheque.mvp.model.lecteur.SpecialLecteur;
import bibliotheque.mvp.view.lecteur.LecteurViewInterface;

import java.util.List;

public class LecteurPresenter {
    private SpecialLecteur model;
    private LecteurViewInterface view;

    public LecteurPresenter(DAO<Lecteur> model, LecteurViewInterface view) {
        this.model = (SpecialLecteur) model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
       view.setListDatas(getAll());
    }

    public List<Lecteur> getAll(){
        return model.getAll();
    }

    public void addLecteur(Lecteur lecteur) {
        Lecteur lec = model.add(lecteur);
        if(lec!=null) view.affMsg("création de :"+lec);
        else view.affMsg("erreur de création");
       // List<Lecteur> lecteurs = model.getLecteurs();
       // view.setListDatas(lecteurs); //désactivé pour éviter appels imbriqués
    }


    public void removeLecteur(Lecteur lecteur) {
        boolean ok = model.remove(lecteur);
        if(ok) view.affMsg("lecteur effacé");
        else view.affMsg("lecteur non effacé");
        //List<Lecteur> lecteurs = model.getLecteurs();
        //view.setListDatas(lecteurs); //désactivé pour éviter appels imbriqués
    }
    public void update(Lecteur lecteur) {
        Lecteur l  =model.update(lecteur);
        if(l==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+l);
        //view.setListDatas(model.getClients());//désactivé pour éviter appels imbriqués
    }

    public void search(int idLecteur) {
        Lecteur l = model.read(idLecteur);
        if(l==null) view.affMsg("recherche infructueuse");
        else view.affMsg(l.toString());
    }

    public void exemplairesEnLocation(Lecteur l) {
        List<Exemplaire> lex =   ((SpecialLecteur)model).exemplairesEnLocation(l);
        if(lex==null || lex.isEmpty()) view.affMsg("aucun exemplaire trouvé");
        else view.affList(lex);
    }
    public void exemplairesLoues(Lecteur l) {
        List<Exemplaire> lex =   ((SpecialLecteur)model).exemplairesLoues(l);
        if(lex==null || lex.isEmpty()) view.affMsg("aucun exemplaire trouvé");
        else view.affList(lex);
    }
}
