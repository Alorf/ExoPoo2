package bibliotheque.utilitaires;

import bibliotheque.metier.CD;
import bibliotheque.metier.Ouvrage;

import java.time.LocalDate;
import java.time.LocalTime;

public class CDFactory extends OuvrageFactory{
    public Ouvrage addDetail(String titre, int ageMin, LocalDate dateParution, double prixLocation, String langue, String genre){
        System.out.println("code : ");
        long code= sc.nextLong();
        System.out.println("nombre de plages :");
        byte nbrePlages= sc.nextByte();sc.skip("\n");
        System.out.println("durée en H M S : ");
        LocalTime dureeTotale = Utilitaire.lecTime();

        try{
            CD cd =new CD(titre,ageMin,dateParution,prixLocation,langue,genre,code,nbrePlages,dureeTotale);
            return cd;
        }catch (Exception e){
            System.err.println("Erreur factory : " + e);
        }

        return null;
    }
}
