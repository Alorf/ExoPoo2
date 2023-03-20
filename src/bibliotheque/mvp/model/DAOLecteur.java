package bibliotheque.mvp.model;

import bibliotheque.metier.Lecteur;

import java.util.List;

public interface DAOLecteur {
    Lecteur addLecteur(Lecteur lec);

    boolean updateLecteur(Lecteur lecteur);

    boolean removeLecteur(Lecteur lec);

    List<Lecteur> getLecteurs();
}
