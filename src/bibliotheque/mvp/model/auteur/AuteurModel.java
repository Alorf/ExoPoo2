package bibliotheque.mvp.model.auteur;

import bibliotheque.metier.*;
import bibliotheque.mvp.model.DAO;

import java.util.ArrayList;
import java.util.List;

public class AuteurModel implements DAO<Auteur>,SpecialAuteur {
    private int numcli = 0;
    private List<Auteur> auteurs = new ArrayList<>();

    public AuteurModel(){
        populate();
    }
    @Override
    public Auteur add(Auteur auteur) {
        boolean present = auteurs.contains(auteur);
        if (!present) {
            auteurs.add(auteur);
            return auteur;
        } else return null;
    }

    @Override
    public boolean remove(Auteur auteur) {
        return auteurs.remove(auteur);
    }

    @Override
    public Auteur update(Auteur auteur) {
        //int idAuteur = auteur.getNumauteur();
        int p = auteurs.indexOf(auteur);
        if (p < 0) return null;
        auteurs.set(p, auteur);//remplacement du auteur à la même position
        return auteur;
    }

    @Override
    public Auteur read(Auteur auteur) {
        for (Auteur l : auteurs) {
            if (l.equals(auteur)) return l;
        }
        return null;
    }

    @Override
    public List<Auteur> getAll() {
        return auteurs;
    }

    private void populate(){
        Auteur auteur = new Auteur("Vert", "Claire", "Français");
        add(auteur);
        auteur  = new Auteur("Roques","Pascal","Belge");
        add(auteur);
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