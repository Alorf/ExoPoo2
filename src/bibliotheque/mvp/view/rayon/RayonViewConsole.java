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
        List options = new ArrayList<>(Arrays.asList("ajouter", "retirer", "rechercher","modifier","special","fin"));
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
        Rayon rayon = new Rayon(codeRayon, null);
        presenter.search(rayon);
    }

    private void modifier() {
        int choix = choixElt(lrayon);
        Rayon l = lrayon.get(choix-1);
        String genre = modifyIfNotBlank("prénom",l.getGenre());

        Rayon rayon = new Rayon("temp", genre);
        presenter.update(rayon);
        lrayon=presenter.getAll();//rafraichissement
        Utilitaire.affListe(lrayon);
    }

    private void retirer() {
        int choix = choixElt(lrayon);
        Rayon rayon = lrayon.get(choix-1);
        presenter.removeRayon(rayon);
        lrayon=presenter.getAll();//rafraichissement
        Utilitaire.affListe(lrayon);
    }


    private void ajouter() {
        System.out.println("Entrez le genre  : ");
        String genre = sc.nextLine();
        Rayon r = new Rayon("r", genre);
        presenter.addRayon(r);
        lrayon=presenter.getAll();//rafraichissement
        Utilitaire.affListe(lrayon);
    }
    private void special() {
        int choix =  choixElt(lrayon);
        Rayon rayon = lrayon.get(choix-1);
            do {
                System.out.println("1.Exemplaire en location\n3.menu principal");
                System.out.println("choix : ");
                int ch = sc.nextInt();
                sc.skip("\n");
                switch (ch) {
                    case 1:
                        presenter.listerExemplaires(rayon);
                        break;
                    case 2: return;
                    default:
                        System.out.println("choix invalide recommencez ");
                }
            } while (true);


        }
    }


