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
        String codeOuvrage = Utilitaire.regex("R[0-9]+", "Code ouvrage : ");
        presenter.search(codeOuvrage);
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
                    o.setTitre(regex("[a-zA-Z]+", "Entrez le nouveau titre"));
                    break;
                case 2:
                    o.setAgeMin(Integer.parseInt(regex("[0-9]+", "Entrez le nouvel age minimum")));
                    break;
                case 3:
                    o.setDateParution(lecDate());
                    break;
                case 4:
                    o.setPrixLocation(Double.parseDouble(regex("[0-9]+", "Entrez le nouveau prix de location")));
                    break;
                case 5:
                    o.setLangue(regex("[a-zA-Z]+", "Entrez la nouvelle langue"));
                    break;
                case 6:
                    o.setGenre(regex("[a-zA-Z]", "Entrez le nouveau genre"));
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
                        ((Livre) o).setIsbn(regex(".*", "Entrez le nouvel ISBN"));
                        break;
                    case 2:
                        ((Livre) o).setNbrePages(Integer.parseInt(regex("[0-9]+", "Entrez le nouveau nombre de pages")));
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
                        ((Livre) o).setResume(regex(".*", "Entrez le nouveau résumé"));
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
                        ((CD) o).setCode(Long.parseLong(regex("[0-9]+", "Entrez le nouveau code")));
                        break;
                    case 2:
                        ((CD) o).setNbrePlages(Byte.parseByte(regex("[0-9]+", "Entrez le nouveau nombre de plages")));
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
                        ((DVD) o).setCode(Long.parseLong(regex("[0-9]+", "Entrez le nouveau code")));
                        break;
                    case 2:
                        ((DVD) o).setNbreBonus(Byte.parseByte(regex("[0-9]+", "Entrez le nouveau nombre de plages")));
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


        String titre = Utilitaire.regex(".*", "Entrez le titre de l'ouvrage");

        int ageMin = Integer.parseInt(Utilitaire.regex("[0-9]+", "Entrez l'age minimum pour lire l'ouvrage"));

        System.out.println("Date de parution");
        LocalDate dateParution = Utilitaire.lecDate();

        double prixLoc = Double.parseDouble(Utilitaire.regex("[0-9]+", "Entrez le prix de location de l'ouvrage"));
        //todo : Trouver un meilleur moyen pour lire un double en regex

        String langue = Utilitaire.regex("[a-zA-Z ]", "Entrez la langue de l'ouvrage");

        String genre = Utilitaire.regex("[a-zA-Z]+", "Entrez le genre de l'ouvrage");

        if (choix == 1) {
            to = TypeOuvrage.LIVRE;
            String isbn = Utilitaire.regex(".*", "Entrez l'isbn");
            int nbPage = Integer.parseInt(Utilitaire.regex("[0-9]+", "Nombre de page"));

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

            String resume = Utilitaire.regex(".*", "Entrez un résumé pour le livre");

            ouv = new Livre(titre, ageMin, dateParution, prixLoc, langue, genre, isbn, nbPage, tl, resume);

        } else if (choix == 2) {
            to = TypeOuvrage.CD;

            long code = Long.parseLong(Utilitaire.regex("[0-9]+", "Entrez un code"));

            byte nbPlages = Byte.parseByte(Utilitaire.regex("[0-9]+", "Entrez le nombre de plages"));

            System.out.println("Entrez la durée totale en minute");
            LocalTime dureeTotale = lecTime();

            ouv = new CD(titre, ageMin, dateParution, prixLoc, langue, genre, code, nbPlages, dureeTotale);

        } else if (choix == 3) {
            to = TypeOuvrage.DVD;

            long code = Long.parseLong(Utilitaire.regex("[0-9]+", "Entrez un code"));

            byte nbreBonus = Byte.parseByte(Utilitaire.regex("[0-9]+", "Entrez le nombre de bonus"));

            System.out.println("Entrez la durée totale en minute");
            LocalTime dureeTotale = lecTime();

            String resp, tplangue;
            Set<String> autresLangues = new HashSet<>();


            while (true) {
                resp = regex("[oONn]", "Voulez-vous ajouter une langue ?");
                if (resp.equalsIgnoreCase("o")) {
                    tplangue = Utilitaire.regex("[a-zA-Z ]+", "Entrez la langue : ");
                    autresLangues.add(tplangue.toLowerCase());
                } else {
                    break;
                }
            }

            Set<String> sousTitres = new HashSet<>();


            while (true) {
                resp = regex("[oONn]", "Voulez-vous ajouter des langues de sous-titres ?");
                if (resp.equalsIgnoreCase("o")) {
                    tplangue = Utilitaire.regex("[a-zA-Z ]+", "Entrez la langue du sous-titre : ");
                    sousTitres.add(tplangue.toLowerCase());
                } else {
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


