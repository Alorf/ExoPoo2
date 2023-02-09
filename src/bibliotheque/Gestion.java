package bibliotheque;

import java.time.LocalDate;

public class Gestion {

    /*
	=> Créer un auteur
	=> Créer un livre de cet auteur
	=> Créer un rayon
	=> Créer un exemplaire de ce livre dans le rayon
	=> Créer un lecteur
	=> Louer cet exemplaire à ce lecteur
	=> Afficher les infos de tous les objets crées
     */

    /*
        LocalDatge dt = LocalDate.of(2023,2,15);
     */
    public static void main(String[] args) {

        Auteur auteur = new Auteur("de Maupassant", "Guy", "Français");
        Livre belami = new Livre("Bel-Ami", 17, LocalDate.of(2023,4,6), 7.79, "Français", "Fiction", "9782253009009", 441, TypeLivre.ROMAN, "Bel-Ami retrace l'itinéraire de Georges Duroy, un jeune soldat revenu de la guerre de la conquête de l'Algérie qui se retrouve sans le sou à son retour à Paris. Aidé par son ami Forestier, il rencontre un patron de presse qui l'emploie après lui avoir commandé un premier article sur l'Algérie.");

        auteur.getLouvrage().add(belami);
        //a.addOuvrage(belami);

        Rayon r = new Rayon("1234", "Fiction");
        Exemplaire exemplaire = new Exemplaire("MAT1234", "Comme neuf", belami);

        exemplaire.setRayon(r);
        r.getLex().add(exemplaire);

        Lecteur lecteur = new Lecteur(1, "Fourchette", "Patrique", LocalDate.of(1975, 4, 15), "36 Rue du Mouton, 7000", "patrique.fourchette@gmail.com", "0472123456");
        Location location = new Location(lecteur, exemplaire);

        lecteur.getLloc().add(location);
        exemplaire.getLloc().add(location);

        System.out.println(auteur);
        System.out.println(belami);
        System.out.println(r);
        System.out.println(exemplaire);
        System.out.println(lecteur);
        System.out.println(location);

    }
}
