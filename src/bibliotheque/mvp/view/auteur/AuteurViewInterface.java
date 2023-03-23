package bibliotheque.mvp.view.auteur;

import bibliotheque.metier.Auteur;
import bibliotheque.metier.Ouvrage;
import bibliotheque.mvp.presenter.AuteurPresenter;

import java.util.List;

public interface AuteurViewInterface {
    public void setPresenter(AuteurPresenter presenter);

    public void setListDatas(List<Auteur> auteurs);

    public void affMsg(String msg);


    void affList(List<Ouvrage> laut);
}
