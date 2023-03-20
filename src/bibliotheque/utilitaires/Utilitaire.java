package bibliotheque.utilitaires;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Utilitaire {
    private static Scanner reader = new Scanner(System.in);
    public static int choixListe(List l){
        affListe(l);
        return choixElt(l);
    }

    public static String regex(String regex, String msg) {
        String chaine;
        msg = msg.endsWith(" ") ? msg : msg + " ";
        do {
            System.out.printf(msg);
            chaine = reader.nextLine();
            if (!chaine.matches(regex)) {
                System.out.println("Recommencez !");
            }
        } while (!chaine.matches(regex));

        return chaine;
    }

    public static void affListe(List l){
        int i =1;
        for(Object o :l) {
            System.out.println((i++)+"."+o);
        }
    }

    public static int choixElt(List l){
        int choix;
        do {
            choix = Integer.parseInt(regex("[0-9]+", "Choix : "));
            if (choix <1 || choix > l.size()){
                System.out.println("Mauvaise entrée");
            }
        } while(choix <1 || choix > l.size());
        return choix;
    }

    public static LocalDate lecDate(){
        String splitBy = "";
        String date = regex("^((0[1-9]|1[0-2])[\\/ ]){2}[0-9]{2,4}$", "Entrez la date : ");
        String[] jma = null;

        if (date.contains("/")){
            splitBy = "/";
        }

        jma = date.split(splitBy);

        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);

        return LocalDate.of(a,m,j);
    }

    public static LocalTime lecTime(){
        String splitBy = " ";
        String heure = regex("^[0-9]{1,2}[:][0-9]{2}[:][0-9]{2}$", "Entrez l'heure : ");
        String[] hms = null;

        if (heure.contains(":")){
            splitBy = ":";
        }

        hms = heure.split(splitBy);

        int h = Integer.parseInt(hms[0]);
        int m = Integer.parseInt(hms[1]);
        int s = Integer.parseInt(hms[2]);

        return LocalTime.of(h,m,s);
    }
}
