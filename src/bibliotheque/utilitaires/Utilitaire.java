package bibliotheque.utilitaires;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Utilitaire {
    private static Scanner sc = new Scanner(System.in);
    public static int choixListe(List l){
       affListe(l);
       return choixElt(l);
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
            while (true) {
                try {
                    affListe(l);
                    System.out.println("choix : ");
                    while (true) {
                        try {
                            choix = sc.nextInt();
                            sc.skip("\n");
                            break;
                        } catch (Exception e) {
                            System.err.println("Erreur " + e.getMessage());
                        }
                    }
                    sc.skip("\n");
                    break;
                } catch (Exception e) {
                    System.err.println("Erreur" + e.getMessage());
                    System.out.println("Recommencez votre saisie");
                }

            }
        } while(choix <1 || choix > l.size());
        return choix;
    }

    public static LocalDate lecDate() {
        int a;
        int m;
        int j;
        while (true) {
            try {
                String[] jma = sc.nextLine().split(" ");
                j = Integer.parseInt(jma[0]);
                m = Integer.parseInt(jma[1]);
                a = Integer.parseInt(jma[2]);
                break;
            } catch (Exception e) {
                System.err.println("Erreur " + e.getMessage());
            }
        }
        return LocalDate.of(a, m, j);
    }

    public static LocalTime lecTime(){
        int h = 0;
        int m = 0;
        int s = 0;
        while (true){
            try{

                String[] hms = sc.nextLine().split(" ");
                h = Integer.parseInt(hms[0]);
                m = Integer.parseInt(hms[1]);
                s = Integer.parseInt(hms[2]);
                break;
            }catch (Exception e){
                System.err.println("Erreur " + e.getMessage());
            }
        }
        return LocalTime.of(h,m,s);
    }

    public static String getDateFrench(LocalDate d){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MM yyyy");
        return dtf.format(d);
    }

    public static String modifyIfNotBlank(String label,String oldValue){
        System.out.println(label+" : "+oldValue);
        System.out.print("nouvelle valeur (enter si pas de changement) : ");
        String newValue= sc.nextLine();
        if(newValue.isBlank()) return oldValue;
        return newValue;
    }

}
