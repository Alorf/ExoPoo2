package bibliotheque.mvp.model;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;
import bibliotheque.metier.Mail;

import java.time.LocalDate;
import java.util.List;

public class ExemplaireModel extends AbstractModel<Exemplaire> implements SpecialExemplaire {
    @Override
    public void modifierEtat(Exemplaire ex, String etat) {
         ex.setDescriptionEtat(etat);
    }

    @Override
    public Lecteur lecteurActuel(Exemplaire ex) {
        return ex.lecteurActuel();
    }

    @Override
    public List<Lecteur> lecteurs(Exemplaire ex) {
        return ex.lecteurs();
    }

    @Override
    public void envoiMailLecteurActuel(Exemplaire ex) {
        Lecteur lecActuel = lecteurActuel(ex);
        List<Exemplaire> lecEx = lecActuel.listerExemplairesEnLocation();

        String message = "";

        for (Exemplaire e : lecEx) {
            message += e.getOuvrage() + "\n";
        }

        Mail mail = new Mail("Vous avez loué les exemplaires suivants : ", message, LocalDate.now());
        ex.envoiMailLecteurActuel(mail);
    }

    @Override
    public void envoiMailLecteurs(Exemplaire ex) {
        List<Lecteur> tmp = lecteurs(ex);

        for (Lecteur l : tmp) {
            List<Exemplaire> lecEx = l.listerExemplairesEnLocation();
            String message = "";

            for (Exemplaire e : lecEx) {
                message += e.getOuvrage() + "\n";
            }

            Mail mail = new Mail("Vous avez loué les exemplaires suivants : ", message, LocalDate.now());
            ex.envoiMailLecteurActuel(mail);
        }
    }

    @Override
    public boolean enRetard(Exemplaire ex) {
        return ex.enRetard();
    }

    @Override
    public int joursRetard(Exemplaire ex) {
        return ex.joursRetard();
    }

    @Override
    public boolean enLocation(Exemplaire ex) {
        return ex.enLocation();
    }
}
