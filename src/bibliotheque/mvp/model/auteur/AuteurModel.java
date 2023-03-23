package bibliotheque.mvp.model.auteur;

import bibliotheque.metier.*;

import java.util.ArrayList;
import java.util.List;

public class AuteurModel implements DAOAuteur,SpecialAuteur {
    private int numcli = 0;
    private List<Auteur> auteurs = new ArrayList<>();

    public AuteurModel(){
        populate();
    }
    @Override
    public Auteur addAuteur(Auteur auteur) {
        boolean present = auteurs.contains(auteur);
        if (!present) {
            auteurs.add(auteur);
            return auteur;
        } else return null;
    }

    @Override
    public boolean removeAuteur(Auteur auteur) {
        return auteurs.remove(auteur);
    }

    @Override
    public Auteur updateAuteur(Auteur auteur) {
        //int idAuteur = auteur.getNumauteur();
        int p = auteurs.indexOf(auteur);
        if (p < 0) return null;
        auteurs.set(p, auteur);//remplacement du auteur à la même position
        return auteur;
    }

    @Override
    public Auteur readAuteur(Auteur auteur) {
        for (Auteur l : auteurs) {
            if (l.equals(auteur)) return l;
        }
        return null;
    }

    @Override
    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    private void populate(){
        Auteur auteur = new Auteur("Vert", "Claire", "Français");
        addAuteur(auteur);
        auteur  = new Auteur("Roques","Pascal","Belge");
        addAuteur(auteur);
    }

    @Override
    public List<Ouvrage> listerOuvrages(Auteur aut) {
        return aut.listerOuvrages();
    }

    @Override
    public List<Ouvrage> listerOuvrages(Auteur aut, TypeOuvrage to, TypeLivre tl) {
        if (to == null){
            return aut.listerLivres(tl);
        }else if (tl == null){
            return aut.listerOuvrages(to);
        }

        return null;
    }

    @Override
    public List<Ouvrage> listerOuvrages(Auteur aut, String genre) {
        return aut.listerOuvrages(genre);
    }
}