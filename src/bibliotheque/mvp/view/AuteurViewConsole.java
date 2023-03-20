package bibliotheque.mvp.view;

import bibliotheque.metier.Auteur;
import bibliotheque.mvp.presenter.AuteurPresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AuteurViewConsole implements AuteurViewInterface {
    private AuteurPresenter presenter;
    private List<Auteur> laut;
    private Scanner sc = new Scanner(System.in);

    public AuteurViewConsole() {

    }

    @Override
    public void setPresenter(AuteurPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Auteur> auteurs) {
        this.laut = auteurs;
        Utilitaire.affListe(laut);
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
        //fixme : revoir la modification car l'equal ce fait sur nom prenom
        Auteur auteur = laut.get(Utilitaire.choixListe(laut)-1);

        String nom = auteur.getNom();
        String prenom = auteur.getPrenom();
        String nat = auteur.getNationalite();


        String menu[] = {
                "Nationalité",
                "Sortir",
        };

        int choix = 0;

        //Choisir la modification
        do{
            choix = Utilitaire.choixListe(new ArrayList(Arrays.asList(menu)));

            switch (choix){
                case 1 -> {
                    nat = Utilitaire.regex("[a-zA-Z ]+", "Entrez la nouvelle nationalité");
                }
            }
        }while(choix != 3);
        System.out.println("Va etre modifier" + auteur);

        auteur.setNom(nom);
        auteur.setPrenom(prenom);
        auteur.setNationalite(nat);

        presenter.updateAuteur(auteur);
    }

    private void retirer() {
        int choix = Utilitaire.choixListe(laut);
        Auteur auteur = laut.get(choix-1);
        presenter.removeAuteur(auteur);
    }


    private void ajouter() {
        String nom = Utilitaire.regex("[a-zA-Z ]+", "Entrez le nom : ");
        String prenom = Utilitaire.regex("[a-zA-Z ]+", "Entrez le prenom : ");
        String nat = Utilitaire.regex("[a-zA-Z ]+", "Entrez la nationalite : ");
        Auteur lec = new Auteur(nom, prenom, nat);
        presenter.addAuteur(lec);
    }
}

