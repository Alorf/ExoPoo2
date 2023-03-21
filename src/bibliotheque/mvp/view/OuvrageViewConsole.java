package bibliotheque.mvp.view;

import bibliotheque.metier.*;
import bibliotheque.mvp.presenter.OuvragePresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OuvrageViewConsole implements OuvrageViewInterface {
    private OuvragePresenter presenter;
    private List<Ouvrage> louv;
    private Scanner sc = new Scanner(System.in);

    public OuvrageViewConsole() {

    }

    @Override
    public void setPresenter(OuvragePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Ouvrage> ouvrages) {
        this.louv = ouvrages;
        Utilitaire.affListe(louv);
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
        String[] menu = null;
        Ouvrage ouvrage = louv.get(Utilitaire.choixListe(louv)-1);

        String titre = ouvrage.getTitre();
        int ageMin = ouvrage.getAgeMin();
        LocalDate dateParution = ouvrage.getDateParution();
        TypeOuvrage to = ouvrage.getTo();
        double prixLocation = ouvrage.getPrixLocation();
        String langue = ouvrage.getLangue();
        String genre = ouvrage.getGenre();

        int nbrePage;
        TypeLivre tl;
        String resum;
        LocalTime dureeTotale;
        int nbreBonus;
        int nbrPlage;



        if (ouvrage instanceof Livre){
            menu = new String[]{
                    "Titre",
                    "Age minimum",
                    "Date de parution",
                    "Type d'ouvrage",
                    "Prix de location",
                    "Langue",
                    "Genre",
                    "Nombre de page",
                    "Type de livre",
                    "Résumé",
                    "Sortir"
            };

            nbrePage = ((Livre) ouvrage).getNbrePages();
            tl = ((Livre) ouvrage).getTl();
            resum = ((Livre) ouvrage).getResume();

        } else if (ouvrage instanceof DVD) {
            menu = new String[]{
                    "Titre",
                    "Age minimum",
                    "Date de parution",
                    "Type d'ouvrage",
                    "Prix de location",
                    "Langue",
                    "Genre",
                    "Duree totale",
                    "Nombre bonus",
                    "Sortir"
            };

            dureeTotale = ((DVD) ouvrage).getDureeTotale();
            nbreBonus = ((DVD) ouvrage).getNbreBonus();


        } else if (ouvrage instanceof CD){
            menu = new String[]{
                    "Titre",
                    "Age minimum",
                    "Date de parution",
                    "Type d'ouvrage",
                    "Prix de location",
                    "Langue",
                    "Genre",
                    "Nombre de plages",
                    "Durée totale",
                    "Quitter"
            };

            nbrPlage = ((CD) ouvrage).getNbrePlages();
            dureeTotale = ((CD) ouvrage).getDureeTotale();
        }

        int choix = 0;

        System.out.println(menu);
        //Choisir la modification
        do{
            choix = Utilitaire.choixListe(new ArrayList(Arrays.asList(menu)));

            if (ouvrage instanceof Livre){

                switch (choix){
                    case 1 -> {
                        titre = Utilitaire.regex("[a-zA-Z ]+", "Entrez le nouveau nom :");
                    }
                    case 2 -> {
                        ageMin = Integer.parseInt(Utilitaire.regex("[0-9]+", "Entrez le nouvel age requis :"));
                    }
                    case 3 -> {
                        System.out.println("Entrez la date de parution : ");
                        dateParution = Utilitaire.lecDate();
                    }
                    case 4 -> {
                        prixLocation = Integer.parseInt(Utilitaire.regex("[0-9]+", "Entrez le prix de location  : "));
                    }
                    case 5 -> {
                        langue = Utilitaire.regex("[a-zA-Z ]+", "Entrez la nouvelle langue du média : ");
                    }
                    case 6 -> {
                        genre = Utilitaire.regex("[a-zA-Z ]+", "Entez le nouveau genre du média : ");
                    }
                    case 7 -> {
                        nbrePage = Integer.parseInt(Utilitaire.regex("[0-9]+", "Nombre de page : "));
                    }
                }

            } else if (ouvrage instanceof DVD) {

                switch (choix){
                    case 1 -> {
                        titre = Utilitaire.regex("[a-zA-Z ]+", "Entrez le nouveau nom :");
                    }
                    case 2 -> {
                        ageMin = Integer.parseInt(Utilitaire.regex("[0-9]+", "Entrez le nouvel age requis :"));
                    }
                    case 3 -> {
                        System.out.println("Entrez la date de parution : ");
                        dateParution = Utilitaire.lecDate();
                    }
                    case 4 -> {
                        prixLocation = Integer.parseInt(Utilitaire.regex("[0-9]+", "Entrez le prix de location  : "));
                    }
                    case 5 -> {
                        langue = Utilitaire.regex("[a-zA-Z ]+", "Entrez la nouvelle langue du média : ");
                    }
                    case 6 -> {
                        genre = Utilitaire.regex("[a-zA-Z ]+", "Entez le nouveau genre du média : ");
                    }
                }

            } else if (ouvrage instanceof CD) {

                switch (choix){
                    case 1 -> {
                        titre = Utilitaire.regex("[a-zA-Z ]+", "Entrez le nouveau nom :");
                    }
                    case 2 -> {
                        ageMin = Integer.parseInt(Utilitaire.regex("[0-9]+", "Entrez le nouvel age requis :"));
                    }
                    case 3 -> {
                        System.out.println("Entrez la date de parution : ");
                        dateParution = Utilitaire.lecDate();
                    }
                    case 4 -> {
                        prixLocation = Integer.parseInt(Utilitaire.regex("[0-9]+", "Entrez le prix de location  : "));
                    }
                    case 5 -> {
                        langue = Utilitaire.regex("[a-zA-Z ]+", "Entrez la nouvelle langue du média : ");
                    }
                    case 6 -> {
                        genre = Utilitaire.regex("[a-zA-Z ]+", "Entez le nouveau genre du média : ");
                    }
                }

            }


        }while(choix != menu.length);
        System.out.println("Va etre modifier" + ouvrage);

        ouvrage.setTitre(titre);
        ouvrage.setAgeMin(ageMin);
        ouvrage.setGenre(genre);

        presenter.updateOuvrage(ouvrage);
    }

    private void retirer() {
        int choix = Utilitaire.choixListe(louv);
        Ouvrage ouvrage = louv.get(choix-1);
        presenter.removeOuvrage(ouvrage);
    }


    private void ajouter() {
        String nom = Utilitaire.regex("[a-zA-Z ]+", "Entrez le nom : ");
        String prenom = Utilitaire.regex("[a-zA-Z ]+", "Entrez le prenom : ");
        String nat = Utilitaire.regex("[a-zA-Z ]+", "Entrez la nationalite : ");
        //Ouvrage ouv = new Ouvrage(titre, ageMin, dateParution);
        //presenter.addOuvrage(ouv);
    }
}

