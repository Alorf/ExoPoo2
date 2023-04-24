package bibliotheque.metier;

import java.time.LocalDate;

public class Mail {
    private String objet;
    private String message;
    private LocalDate dateEnvoi;

    public Mail(String objet, String message, LocalDate dateEnvoi) throws Exception{
        if (objet.isEmpty()){
            throw new Exception("le champ objet ne peut pas être vide");
        }

        if (message.isEmpty()){
            throw new Exception("Le champ message ne peut pas être vide");
        }

        this.objet = objet;
        this.message = message;
        this.dateEnvoi = dateEnvoi;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDate dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "objet='" + objet + '\'' +
                ", message='" + message + '\'' +
                ", dateEnvoi='" + dateEnvoi + '\'' +
                '}';
    }
}
