package bibliotheque.mvp.view.rayon;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Rayon;
import bibliotheque.mvp.presenter.RayonPresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static bibliotheque.utilitaires.Utilitaire.*;

public class RayonViewConsole implements RayonViewInterface {
    private RayonPresenter presenter;
    private List<Rayon> lrayon;
    private Scanner sc = new Scanner(System.in);

    public RayonViewConsole() {

    }

    @Override
    public void setPresenter(RayonPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Rayon> rayons) {
        this.lrayon = rayons;
        affListe(lrayon);
        menu();
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("information:" + msg);
    }

    @Override
    public void affList(List<Exemplaire> lex) {
        affListe(lex);
    }

    public void menu() {
        List options = new ArrayList<>(Arrays.asList("ajouter", "retirer", "rechercher", "modifier", "special", "fin"));
        do {
            int ch = choixListe(options);

            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    special();
                    break;
                case 6:
                    return;
            }
        } while (true);
    }

    private void rechercher() {
        System.out.println("Code rayon : ");
        String codeRayon = sc.nextLine();
        try {
            Rayon rayon = new Rayon(codeRayon);
            presenter.search(rayon);
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    private void modifier() {
        int choix = choixListe(lrayon);
        Rayon l = lrayon.get(choix - 1);
        String genre = modifyIfNotBlank("genre", l.getGenre());

        try {
            Rayon rayon = new Rayon("temp", genre);
            presenter.update(rayon);
            lrayon = presenter.getAll();//rafraichissement
            Utilitaire.affListe(lrayon);
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    private void retirer() {
        int choix = choixListe(lrayon);
        Rayon rayon = lrayon.get(choix - 1);
        presenter.removeRayon(rayon);
        lrayon = presenter.getAll();//rafraichissement
        Utilitaire.affListe(lrayon);
    }


    private void ajouter() {
        System.out.print("Entrez le genre  : ");
        String genre = sc.nextLine();
        System.out.print("Entrez le code : ");
        String code = sc.nextLine();
        try {

            Rayon r = new Rayon(code, genre);
            presenter.addRayon(r);
            lrayon = presenter.getAll();//rafraichissement
            Utilitaire.affListe(lrayon);

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    private void special() {
        int choix = choixListe(lrayon);

        Rayon rayon = lrayon.get(choix - 1);
        do {
            System.out.println("1.Exemplaire en location\n3.menu principal");
            System.out.println("choix : ");
            int ch = lireInt();
            switch (ch) {
                case 1:
                    presenter.listerExemplaires(rayon);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);
    }
}


