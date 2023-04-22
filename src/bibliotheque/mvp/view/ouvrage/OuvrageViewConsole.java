package bibliotheque.mvp.view.ouvrage;

import bibliotheque.metier.*;
import bibliotheque.mvp.presenter.OuvragePresenter;
import bibliotheque.utilitaires.CDFactory;
import bibliotheque.utilitaires.Utilitaire;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static bibliotheque.utilitaires.Utilitaire.*;

public class OuvrageViewConsole implements OuvrageViewInterface {
    private OuvragePresenter presenter;
    private List<Ouvrage> louvrage;
    private Scanner sc = new Scanner(System.in);

    public OuvrageViewConsole() {

    }

    @Override
    public void setPresenter(OuvragePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Ouvrage> ouvrages) {
        this.louvrage = ouvrages;
        affListe(louvrage);
        menu();
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("information:" + msg);
    }

    @Override
    public void affList(List<Ouvrage> louv) {
        affListe(louv);
    }

    public void menu() {
        List options = new ArrayList<>(Arrays.asList("ajouter", "retirer", "rechercher", "modifier", "fin"));
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
                    return;
            }
        } while (true);
    }

    private void rechercher() {
        System.out.printf("Code ouvrage : ");
        String codeOuvrage = sc.nextLine();
        //Ouvrage ouv = new Ouvrage();
        //presenter.search(codeOuvrage);
    }

    private void modifier() {
        int choix = choixElt(louvrage);
        Ouvrage o = louvrage.get(choix - 1);

        String[] menu = {"titre", "age min", "dateParution", "PrixLocation", "Langue", "Genre", "Etape suivante"};

        first:
        do {
            choix = choixListe(new ArrayList(Arrays.asList(menu)));
            switch (choix) {
                case 1:
                    System.out.printf("Entrez le nouveau titre");
                    String titre = sc.nextLine();
                    o.setTitre(titre);
                    break;
                case 2:
                    check:while (true) {
                        try {
                            System.out.printf("Entrez le nouvel age minimum");
                            int ageMin = -1;
                            while (true) {
                                try {
                                    ageMin = sc.nextInt();
                                    sc.skip("\n");
                                    break;
                                } catch (Exception e) {
                                    System.err.println("Erreur " + e.getMessage());
                                }
                            }
                            o.setAgeMin(ageMin);
                            break check;
                        } catch (Exception e) {
                            System.err.println("Erreur" + e.getMessage());
                            System.out.println("Recommencez votre saisie");
                        }

                    }
                    break;
                case 3:
                    o.setDateParution(lecDate());
                    break;
                case 4:
                    check:while (true) {
                        try {
                            System.out.printf("Entrez le nouveau prix de location");
                            Double prixLoc= (double) -1;
                            while (true) {
                                try {
                                    prixLoc = sc.nextDouble();
                                    sc.skip("\n");
                                    break;
                                } catch (Exception e) {
                                    System.err.println("Erreur " + e.getMessage());
                                }
                            }
                            o.setPrixLocation(prixLoc);
                            break check;
                        } catch (Exception e) {
                            System.err.println("Erreur" + e.getMessage());
                            System.out.println("Recommencez votre saisie");
                        }
                    }
                    break;
                case 5:
                    System.out.printf("Entrez la nouvelle langue");
                    String langue = sc.nextLine();
                    o.setLangue(langue);
                    break;
                case 6:
                    System.out.printf("Entrez le nouveau genre");
                    String genre = sc.nextLine();
                    o.setGenre(genre);
                    break;
                case 7:
                    break first;
            }
        } while (true);

        String[] menu2 = null;

        if (o instanceof Livre) {
            menu2 = new String[]{"isbn", "nbrePages", "tl", "resume", "quitter"};
            second:
            do {
                choix = choixListe(new ArrayList(Arrays.asList(menu2)));

                switch (choix) {
                    case 1:
                        System.out.printf("Entrez le nouvel ISBN :");
                        String isbn = sc.nextLine();
                        ((Livre) o).setIsbn(isbn);
                        break;
                    case 2:
                        System.out.printf("Entrez le nouveau nombre de pages");
                        int nbPage = -1;
                        while (true) {
                            try {
                                nbPage = sc.nextInt();
                                sc.skip("\n");
                                break;
                            } catch (Exception e) {
                                System.err.println("Erreur " + e.getMessage());
                            }
                        }
                        ((Livre) o).setNbrePages(nbPage);
                        break;
                    case 3:
                        String[] typesLivre = {"Roman", "Nouvelle", "Essai", "Documentaire", "Biographie"};
                        TypeLivre tl;
                        choix = Utilitaire.choixListe(new ArrayList(Arrays.asList(typesLivre)));

                        switch (choix) {
                            case 1:
                                tl = TypeLivre.ROMAN;
                                break;
                            case 2:
                                tl = TypeLivre.NOUVELLE;
                                break;
                            case 3:
                                tl = TypeLivre.ESSAI;
                                break;
                            case 4:
                                tl = TypeLivre.DOCUMENTAIRE;
                                break;
                            case 5:
                                tl = TypeLivre.BIOGRAPHIE;
                                break;
                            default:
                                tl = TypeLivre.ROMAN;
                                break;
                        }
                        break;
                    case 4:
                        System.out.printf("Entrez le nouveau résumé :  ");
                        ((Livre) o).setResume(sc.nextLine());
                        break;
                    case 5:
                        break second;
                }
            } while (true);
        } else if (o instanceof CD) {
            menu2 = new String[]{"code", "nombre plages", "duree totale", "quitter"};
            second:do {
                choix = choixListe(new ArrayList(Arrays.asList(menu2)));

                switch (choix){
                    case 1:
                        System.out.println("Entrez le nouveau code");
                        long code = -1;
                        while (true) {
                            try {
                                code = sc.nextLong();
                                sc.skip("\n");
                                break;
                            } catch (Exception e) {
                                System.err.println("Erreur " + e.getMessage());
                            }
                        }
                        ((CD) o).setCode(code);
                        break;
                    case 2:
                        System.out.printf("Entrez le nouveau nombre de plages");
                        byte nbPlage=-1;
                        while (true) {
                            try {
                                choix = sc.nextByte();
                                sc.skip("\n");
                                break;
                            } catch (Exception e) {
                                System.err.println("Erreur " + e.getMessage());
                            }
                        }
                        ((CD) o).setNbrePlages(nbPlage);
                        break;
                    case 3:
                        ((CD) o).setDureeTotale(lecTime());
                        break;
                    case 4:
                        break second;
                }
            }while (true);
        } else if (o instanceof DVD) {
            menu2 = new String[]{"code", "nombre bonus", "duree totale", "quitter"};
            second:do {
                choix = choixListe(new ArrayList(Arrays.asList(menu2)));

                switch (choix){
                    case 1:
                        System.out.println("Entrez le nouveau code");
                        int code=-1;
                        while (true) {
                            try {
                                code = sc.nextInt();
                                sc.skip("\n");
                                break;
                            } catch (Exception e) {
                                System.err.println("Erreur " + e.getMessage());
                            }
                        }
                        ((DVD) o).setCode(code);
                        break;
                    case 2:
                        System.out.printf("Entrez le nouveau nombre de plages");
                        Byte nbreBonus=-1;
                        while (true) {
                            try {
                                nbreBonus = sc.nextByte();
                                sc.skip("\n");
                                break;
                            } catch (Exception e) {
                                System.err.println("Erreur " + e.getMessage());
                            }
                        }
                        ((DVD) o).setNbreBonus(nbreBonus);
                        break;
                    case 3:
                        ((DVD) o).setDureeTotale(lecTime());
                        break;
                    case 4:
                        break second;
                }
            }while (true);
        }

        presenter.update(o);
        louvrage = presenter.getAll();//rafraichissement
        Utilitaire.affListe(louvrage);
    }

    private void retirer() {
        int choix = choixElt(louvrage);
        Ouvrage ouvrage = louvrage.get(choix - 1);
        presenter.removeOuvrage(ouvrage);
        louvrage = presenter.getAll();//rafraichissement
        Utilitaire.affListe(louvrage);
    }


    private void ajouter() {
        Ouvrage ouv = null;
        TypeOuvrage to;

        String[] listeTo = {"Livre", "Cd", "DVD"};
        int choix = Utilitaire.choixElt(new ArrayList(Arrays.asList(listeTo)));

        System.out.println("Entrez le titre de l'ouvrage");
        String titre = sc.nextLine();

        System.out.println("Entrez l'age minimum pour lire l'ouvrage");
        int ageMin;
        while (true) {
            try {
                ageMin = sc.nextInt();
                sc.skip("\n");
                break;
            } catch (Exception e) {
                System.err.println("Erreur " + e.getMessage());
            }
        }

        System.out.println("Date de parution");
        LocalDate dateParution = Utilitaire.lecDate();

        System.out.println("Entrez le prix de location de l'ouvrage");
        double  prixLoc=-1;
        while (true) {
            try {
                prixLoc = sc.nextDouble();
                sc.skip("\n");
                break;
            } catch (Exception e) {
                System.err.println("Erreur " + e.getMessage());
            }
        }

        System.out.println("Entrez la langue de l'ouvrage");
        String langue = sc.nextLine();

        System.out.println("Entrez le genre de l'ouvrage");
        String genre = sc.nextLine();

        if (choix == 1) {
            to = TypeOuvrage.LIVRE;
            System.out.println("Entrez l'isbn");
            String isbn = sc.nextLine();

            System.out.println("Nombre de page");
            int nbPage;
            while (true) {
                try {
                    nbPage = sc.nextInt();
                    sc.skip("\n");
                    break;
                } catch (Exception e) {
                    System.err.println("Erreur " + e.getMessage());
                }
            }

            String[] typesLivre = {"Roman", "Nouvelle", "Essai", "Documentaire", "Biographie"};
            TypeLivre tl;
            choix = Utilitaire.choixListe(new ArrayList(Arrays.asList(typesLivre)));

            switch (choix) {
                case 1:
                    tl = TypeLivre.ROMAN;
                    break;
                case 2:
                    tl = TypeLivre.NOUVELLE;
                    break;
                case 3:
                    tl = TypeLivre.ESSAI;
                    break;
                case 4:
                    tl = TypeLivre.DOCUMENTAIRE;
                    break;
                case 5:
                    tl = TypeLivre.BIOGRAPHIE;
                    break;
                default:
                    tl = TypeLivre.ROMAN;
                    break;
            }

            System.out.println("Entrez un résumé pour le livre");
            String resume = sc.nextLine();

            ouv = new Livre(titre, ageMin, dateParution, prixLoc, langue, genre, isbn, nbPage, tl, resume);

        } else if (choix == 2) {
            to = TypeOuvrage.CD;

            System.out.println("Entrez un code");
            long code;
            while (true) {
                try {
                    code = sc.nextLong();
                    sc.skip("\n");
                    break;
                } catch (Exception e) {
                    System.err.println("Erreur " + e.getMessage());
                }
            }

            System.out.println("Entrez le nombre de plages");
            byte nbPlages;
            while (true) {
                try {
                    nbPlages = sc.nextByte();
                    sc.skip("\n");
                    break;
                } catch (Exception e) {
                    System.err.println("Erreur " + e.getMessage());
                }
            }

            System.out.println("Entrez la durée totale en minute");
            LocalTime dureeTotale = lecTime();

            ouv = new CD(titre, ageMin, dateParution, prixLoc, langue, genre, code, nbPlages, dureeTotale);

        } else if (choix == 3) {
            to = TypeOuvrage.DVD;

            System.out.println("Entrez un code");
            long code;
            while (true) {
                try {
                    code = sc.nextLong();
                    sc.skip("\n");
                    break;
                } catch (Exception e) {
                    System.err.println("Erreur " + e.getMessage());
                }
            }

            System.out.println("Entrez le nombre de bonus");
            byte nbreBonus=-1;
            while (true) {
                try {
                    nbreBonus = sc.nextByte();
                    sc.skip("\n");
                    break;
                } catch (Exception e) {
                    System.err.println("Erreur " + e.getMessage());
                }
            }

            System.out.println("Entrez la durée totale en minute");
            LocalTime dureeTotale = lecTime();

            String resp, tplangue;
            Set<String> autresLangues = new HashSet<>();


            while (true) {

                System.out.println("Voulez-vous ajouter une langue ? O/N");
                resp = sc.nextLine();
                if (resp.equalsIgnoreCase("o")) {
                    System.out.println("Entrez la langue : ");
                    tplangue = sc.nextLine();
                    autresLangues.add(tplangue.toLowerCase());
                } else if (resp.equalsIgnoreCase("n")){
                    break;
                }
            }

            Set<String> sousTitres = new HashSet<>();


            while (true) {
                System.out.println("Voulez-vous ajouter une langue de sous-titres ? O/N");
                resp = sc.nextLine();
                if (resp.equalsIgnoreCase("o")) {
                    System.out.println("Entrez la langue du sous-titre : ");
                    tplangue = sc.nextLine();
                    sousTitres.add(tplangue.toLowerCase());
                } else if (resp.equalsIgnoreCase("n")){
                    break;
                }
            }


            ouv = new DVD(titre, ageMin, dateParution, prixLoc, langue, genre, code, dureeTotale, nbreBonus);
            ((DVD) ouv).setSousTitres(sousTitres);
            ((DVD) ouv).setAutresLangues(autresLangues);
        }

        presenter.addOuvrage(ouv);
        louvrage = presenter.getAll();//rafraichissement
        Utilitaire.affListe(louvrage);
    }
}


