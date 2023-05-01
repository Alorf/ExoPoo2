package bibliotheque.mvp.model;


import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LecteurModelV2 implements DAO<Lecteur>,SpecialLecteur {
    private int numcli = 0;
    private List<Lecteur> ldatas = new ArrayList<>();
    private HashMap<String, Lecteur> HMdatas = new HashMap<>();
    private int numLecteur = 0;


    @Override
    public boolean remove(Lecteur elt) {
        Lecteur lec = HMdatas.remove(elt.getMail());
        return lec != null;
    }

    @Override
    public Lecteur update(Lecteur elt) {
        Lecteur lec = HMdatas.get(elt.getMail());
        if (lec == null) return null;
        HMdatas.remove(elt.getMail());
        HMdatas.put(elt.getMail(), elt);
        return elt;
    }

    @Override
    public Lecteur read(Lecteur rech) {
        return HMdatas.get(rech.getMail());
    }

    @Override
    public List<Lecteur> getAll() {
        return new ArrayList<>(HMdatas.values());
    }
    public Lecteur add(Lecteur nl){
        if(HMdatas.get(nl.getMail()) != null) return null;
        HMdatas.put(nl.getMail(), nl);
        nl.setNumlecteur(++numLecteur);
        return  nl;
    }

    @Override
    public List<Exemplaire> exemplairesEnLocation(Lecteur l) {
        return l.listerExemplairesEnLocation();
    }

    @Override
    public List<Exemplaire> exemplairesLoues(Lecteur l) {
        return new ArrayList<>(l.listerExemplairesLoues());
    }
}