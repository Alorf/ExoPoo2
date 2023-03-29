package bibliotheque.mvp.view.ouvrage;

import bibliotheque.metier.Ouvrage;
import bibliotheque.mvp.presenter.OuvragePresenter;

import java.util.List;

public interface OuvrageViewInterface {
    public void setPresenter(OuvragePresenter presenter);

    public void setListDatas(List<Ouvrage> ouvrages);

    public void affMsg(String msg);


    void affList(List<Ouvrage> louv);
}
