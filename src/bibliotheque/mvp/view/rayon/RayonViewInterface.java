package bibliotheque.mvp.view.rayon;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Rayon;
import bibliotheque.mvp.presenter.RayonPresenter;

import java.util.List;

public interface RayonViewInterface {
    public void setPresenter(RayonPresenter presenter);

    public void setListDatas(List<Rayon> rayons);

    public void affMsg(String msg);


    void affList(List<Exemplaire> lray);
}
