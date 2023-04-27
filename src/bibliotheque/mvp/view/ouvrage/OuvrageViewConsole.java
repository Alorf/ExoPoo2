package bibliotheque.mvp.view.ouvrage;

import bibliotheque.metier.*;
import bibliotheque.mvp.presenter.OuvragePresenter;
import bibliotheque.utilitaires.*;

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
        int choix = choixListe(louvrage);
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
                    System.out.printf("Entrez le nouvel age minimum");
                    int ageMin = lireInt();
                    o.setAgeMin(ageMin);

                    break;
                case 3:
                    o.setDateParution(lecDate());
                    break;
                case 4:
                    System.out.printf("Entrez le nouveau prix de location");
                    double prixLoc = lireDouble();
                    o.setPrixLocation(prixLoc);
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
                        int nbPage = lireInt();
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
            second:
            do {
                choix = choixListe(new ArrayList(Arrays.asList(menu2)));

                switch (choix) {
                    case 1:
                        System.out.println("Entrez le nouveau code");
                        long code = lireLong();
                        ((CD) o).setCode(code);
                        break;
                    case 2:
                        System.out.printf("Entrez le nouveau nombre de plages");
                        byte nbPlage = lireByte();
                        ((CD) o).setNbrePlages(nbPlage);
                        break;
                    case 3:
                        ((CD) o).setDureeTotale(lecTime());
                        break;
                    case 4:
                        break second;
                }
            } while (true);
        } else if (o instanceof DVD) {
            menu2 = new String[]{"code", "nombre bonus", "duree totale", "quitter"};
            second:
            do {
                choix = choixListe(new ArrayList(Arrays.asList(menu2)));

                switch (choix) {
                    case 1:
                        System.out.println("Entrez le nouveau code");
                        int code = lireInt();
                        ((DVD) o).setCode(code);
                        break;
                    case 2:
                        System.out.printf("Entrez le nouveau nombre de plages");
                        byte nbreBonus = lireByte();

                        ((DVD) o).setNbreBonus(nbreBonus);
                        break;
                    case 3:
                        ((DVD) o).setDureeTotale(lecTime());
                        break;
                    case 4:
                        break second;
                }
            } while (true);
        }

        presenter.update(o);
        louvrage = presenter.getAll();//rafraichissement
        Utilitaire.affListe(louvrage);
    }

    private void retirer() {
        int choix = choixListe(louvrage);
        Ouvrage ouvrage = louvrage.get(choix - 1);
        presenter.removeOuvrage(ouvrage);
        louvrage = presenter.getAll();//rafraichissement
        Utilitaire.affListe(louvrage);
    }


    protected void ajouter() {
        TypeOuvrage[] tto = TypeOuvrage.values();
        List<TypeOuvrage> lto = new ArrayList<>(Arrays.asList(tto));
        int choix = Utilitaire.choixListe(lto);
        Ouvrage o = null;
        List<OuvrageFactory> lof = new ArrayList<>(Arrays.asList(new LivreFactory(), new CDFactory(), new DVDFactory()));
        o = lof.get(choix - 1).create();
        presenter.addOuvrage(o);
        //TODO attribuer auteurs, les auteur sont triés par odre de nom et prénom, empêcher doublons
    }
}


