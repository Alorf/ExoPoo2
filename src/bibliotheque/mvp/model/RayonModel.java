package bibliotheque.mvp.model;

import bibliotheque.metier.Rayon;

import java.util.ArrayList;
import java.util.List;

public class RayonModel implements DAORayon {
    private List<Rayon> rayons = new ArrayList<>();

    private int numRey =0;


    @Override
    public Rayon addRayon(Rayon ray) {
        boolean present= rayons.contains(ray);
        if(!present){
            numRey++;
            ray.setCodeRayon("R" + numRey);
            rayons.add(ray);
            return ray;
        }
        else return null;
    }

    @Override
    public boolean updateRayon(Rayon newRayon) {
        int index = rayons.indexOf(newRayon);
        if (index != -1) {
            rayons.set(index, newRayon);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeRayon(Rayon ray) {
        return rayons.remove(ray);
    }

    @Override
    public List<Rayon> getRayons() {
        return new ArrayList<>(rayons);
    }
}

