package bibliotheque.mvp.view.auteur;

import bibliotheque.metier.Auteur;
import bibliotheque.metier.Ouvrage;
import bibliotheque.metier.TypeOuvrage;
import bibliotheque.metier.TypeLivre;
import bibliotheque.mvp.presenter.AuteurPresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static bibliotheque.utilitaires.Utilitaire.*;

public class AuteurViewConsole implements AuteurViewInterface {
    private AuteurPresenter presenter;
    private List<Auteur> lauteur;
    private Scanner sc = new Scanner(System.in);

    public AuteurViewConsole() {

    }

    @Override
    public void setPresenter(AuteurPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Auteur> auteurs) {
        this.lauteur = auteurs;
        affListe(lauteur);
        menu();
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("information:" + msg);
    }

    @Override
    public void affList(List<Ouvrage> lex) {
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
        System.out.printf("Entrez le nom : ");
        String prenom = sc.nextLine();

        System.out.printf("Entrez le prenom : ");
        String nom = sc.nextLine();

        System.out.printf("Entrez la nationalite : ");
        String nat = sc.nextLine();

        try {
            Auteur auteur = new Auteur(nom, prenom, nat);
            presenter.search(auteur);
        }catch (Exception e){
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    private void modifier() {

        int choix = choixListe(lauteur);
        Auteur a = lauteur.get(choix - 1);
        String nom = modifyIfNotBlank("nom", a.getNom());
        String prenom = modifyIfNotBlank("prénom", a.getPrenom());

        String nat = modifyIfNotBlank("Nationalite", a.getNationalite());

        try {
            Auteur auteur = new Auteur(nom, prenom, nat);
            presenter.update(auteur);
            lauteur = presenter.getAll();//rafraichissement
            Utilitaire.affListe(lauteur);
        }catch (Exception e){
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    private void retirer() {
        int choix = choixListe(lauteur);
        Auteur auteur = lauteur.get(choix - 1);
        presenter.removeAuteur(auteur);
        lauteur = presenter.getAll();//rafraichissement
        Utilitaire.affListe(lauteur);
    }

    private void ajouter() {
        System.out.printf("Entrez le nom : ");
        String prenom = sc.nextLine();

        System.out.printf("Entrez le prenom : ");
        String nom = sc.nextLine();

        System.out.printf("Entrez la nationalite : ");
        String nat = sc.nextLine();

        try {
            Auteur auteur = new Auteur(nom, prenom, nat);
            presenter.addAuteur(auteur);
            lauteur = presenter.getAll();//rafraichissement
            Utilitaire.affListe(lauteur);
        }catch (Exception e){
            System.err.println("Erreur  : " + e.getMessage());
        }
    }

    private void special() {
        int choix = choixListe(lauteur);
        Auteur auteur = lauteur.get(choix - 1);
        do {
            System.out.println("1.Lister les ouvrages\n2.Lister ouvrage typeOuvrage  ou typeLivre\n3.Lister ouvrage genre\n4.menu principal");
            System.out.print("choix : ");
            choix = lireInt();

            switch (choix) {
                case 1:
                    presenter.listerOuvrages(auteur);
                    break;
                case 2:
                    TypeOuvrage to = TypeOuvrage.LIVRE;
                    TypeLivre tl = TypeLivre.ROMAN;
                    presenter.listerOuvrage(auteur, to, tl);
                    break;
                case 3:
                    String genre = sc.nextLine();
                    presenter.listerOuvrage(auteur, genre);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);
    }
}


