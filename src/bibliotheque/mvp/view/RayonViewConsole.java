package bibliotheque.mvp.view;

import bibliotheque.metier.Rayon;
import bibliotheque.mvp.presenter.RayonPresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RayonViewConsole implements RayonViewInterface {
    private RayonPresenter presenter;
    private List<Rayon> lray;
    private Scanner sc = new Scanner(System.in);

    public RayonViewConsole() {

    }

    @Override
    public void setPresenter(RayonPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Rayon> rayons) {
        this.lray = rayons;
        Utilitaire.affListe(lray);
        menu();
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("information:" + msg);
    }

    public void menu() {



        List options = new ArrayList<>(Arrays.asList("ajouter", "retirer", "modifier", "fin"));
        do {
            int ch = Utilitaire.choixListe(options);

            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    modifier();
                    break;
                case 4:
                    System.exit(0);
            }
        } while (true);
    }

    private void modifier() {
        Rayon rayon = lray.get(Utilitaire.choixListe(lray)-1);

        String genre = rayon.getGenre();


        String menu[] = {
                "Genre",
                "Sortir",
        };

        int choix = 0;

        //Choisir la modification
        do{
            choix = Utilitaire.choixListe(new ArrayList(Arrays.asList(menu)));

            switch (choix){
                case 1 -> {
                    genre = Utilitaire.regex("[a-zA-Z ]+", "Entrez le nouveau nom du rayon");
                }
            }
        }while(choix != menu.length);
        System.out.println("Va etre modifier" + rayon);

        rayon.setGenre(genre);
        presenter.updateRayon(rayon);
    }

    private void retirer() {
        int choix = Utilitaire.choixListe(lray);
        Rayon rayon = lray.get(choix-1);
        presenter.removeRayon(rayon);
    }


    private void ajouter() {
        String genre = Utilitaire.regex("[a-zA-Z ]+", "Entrez le genre  : ");
        Rayon r = new Rayon("r", genre);
        presenter.addRayon(r);
    }
}

