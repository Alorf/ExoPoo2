package bibliotheque.mvp.model;

import bibliotheque.metier.Rayon;

import java.util.List;

public interface DAORayon {
    Rayon addRayon(Rayon rayon);

    boolean updateRayon(Rayon rayon);

    boolean removeRayon(Rayon rayon);

    List<Rayon> getRayons();
}
