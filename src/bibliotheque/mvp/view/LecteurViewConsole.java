package bibliotheque.mvp.view;

import bibliotheque.metier.Lecteur;
import bibliotheque.mvp.presenter.LecteurPresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LecteurViewConsole implements LecteurViewInterface {
    private LecteurPresenter presenter;
    private List<Lecteur> llec;
    private Scanner sc = new Scanner(System.in);

    public LecteurViewConsole() {

    }

    @Override
    public void setPresenter(LecteurPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Lecteur> lecteurs) {
        this.llec = lecteurs;
        Utilitaire.affListe(llec);
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
        //Choisir un lecteur
        Lecteur lecteur = llec.get(Utilitaire.choixListe(llec)-1);

        String nom = lecteur.getNom();
        String prenom = lecteur.getPrenom();
        LocalDate dn = lecteur.getDn();
        String adresse = lecteur.getAdresse();
        String mail = lecteur.getMail();
        String tel = lecteur.getTel();

        String menu[] = {
                "Nom",
                "Prénom",
                "Date de naissance",
                "Adresse",
                "Mail",
                "Téléphone",
                "Sortir",
        };

        int choix = 0;

        //Choisir la modification
        do{
            choix = Utilitaire.choixListe(new ArrayList(Arrays.asList(menu)));

            switch (choix){
                case 1 -> {
                    nom = Utilitaire.regex("[a-zA-Z ]+", "Entrez le nouveau nom");
                }
                case 2 -> {
                    prenom = Utilitaire.regex("[a-zA-Z ]+", "Entrez le nouveau prénom");
                }
                case 3 -> {
                    dn = Utilitaire.lecDate();
                }
                case 4 -> {
                    adresse = Utilitaire.regex("[a-zA-Z 0-9]+", "Entrez la nouvelle adresse");
                }
                case 5 -> {
                    mail = Utilitaire.regex("[a-zA-Z@.]+", "Entrez le nouveau mail");
                }
                case 6 -> {
                    tel = Utilitaire.regex("[0-9]+", "Entrez le nouveau tel");
                }
            }
        }while(choix != 7);
        System.out.println("Va etre modifier" + lecteur);

        lecteur.setNom(nom);
        lecteur.setPrenom(prenom);
        lecteur.setDn(dn);
        lecteur.setAdresse(adresse);
        lecteur.setMail(mail);
        lecteur.setTel(tel);

        presenter.updateLecteur(lecteur);
    }

    private void retirer() {
        int choix = Utilitaire.choixListe(llec);
        Lecteur lecteur = llec.get(choix-1);
        presenter.removeLecteur(lecteur);
    }


    private void ajouter() {
        System.out.println("nom ");
        String nom = sc.nextLine();
        System.out.println("prénom ");
        String prenom = sc.nextLine();
        System.out.println("date de naissance");
        String[] jma = sc.nextLine().split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);
        LocalDate dn = LocalDate.of(a, m, j);
        System.out.println("adresse");
        String adr = sc.nextLine();
        System.out.println("mail");
        String mail = sc.nextLine();
        System.out.println("tel ");
        String tel = sc.nextLine();
        Lecteur lec = new Lecteur(0, nom, prenom, dn, adr, mail, tel);
        presenter.addLecteur(lec);
    }
}

