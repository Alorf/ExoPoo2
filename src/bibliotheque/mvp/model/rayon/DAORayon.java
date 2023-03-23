package bibliotheque.mvp.model.rayon;

import bibliotheque.metier.Rayon;

import java.util.List;

public interface DAORayon {
    Rayon addRayon(Rayon rayon);

    boolean removeRayon(Rayon rayon);
    Rayon updateRayon(Rayon rayon);

    Rayon readRayon(String CodeRayon);

    List<Rayon> getRayons();
}
