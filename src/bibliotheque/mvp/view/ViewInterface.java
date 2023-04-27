package bibliotheque.mvp.view;

import bibliotheque.mvp.presenter.Presenter;
import bibliotheque.mvp.presenter.RayonPresenter;

import java.util.List;

public interface ViewInterface<T> {
    void setPresenter(RayonPresenter presenter);

    void setListDatas(List<T> objects);

    void affMsg(String msg);

    void affList(List lObjects);

    void setPresenter(Presenter<T> tPresenter);
}
