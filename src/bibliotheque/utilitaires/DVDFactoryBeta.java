package bibliotheque.utilitaires;

import bibliotheque.metier.DVD;
import bibliotheque.metier.Ouvrage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static bibliotheque.utilitaires.Utilitaire.*;

public class DVDFactoryBeta {
    protected Scanner sc= new Scanner(System.in);
    public Ouvrage create() {

        System.out.println("titre");
        String titre= sc.nextLine();
        System.out.println("age minimum");
        int ageMin= lireInt();
        System.out.println("date de parution");
        LocalDate dp= lecDate();
        System.out.println("prix de location");
        double ploc = lireDouble();
        System.out.println("langue");
        String langue=sc.nextLine();
        System.out.println("genre");
        String genre=sc.nextLine();

        //détails propres à la classe DVD
        System.out.println("code : ");
        long code= lireLong();
        LocalTime dureeTotale=Utilitaire.lecTime();
        byte nbreBonus= lireByte();
        DVD dvd = null;

        try  {
            dvd =new DVD(titre,ageMin,dp,ploc,langue,genre,code,dureeTotale,nbreBonus);
        }catch (Exception e){
            System.err.println("Erreur factory : " + e);
            return null;
        }

        System.out.println("autres langues");
        List<String> langues = new ArrayList<>(Arrays.asList("anglais","français","italien","allemand","fin"));
        int choix;
        do{
            choix=choixListe(langues);
            if(choix==langues.size())break;
            dvd.getAutresLangues().add(langues.get(choix-1));//TODO vérifier unicité ou utiliser set et pas de doublon avec langue d'origine
        }while(true);
        System.out.println("sous-titres");
        do{
            choix=Utilitaire.choixListe(langues);
            if(choix==langues.size())break;
            dvd.getSousTitres().add(langues.get(choix-1));//TODO vérifier unicité ou utiliser set
        }while(true);

        return dvd;
    }
}
