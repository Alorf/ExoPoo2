package bibliotheque.metier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Mail {
    private String objet;
    private String message;
    private LocalDate dateEnvoi;

    public Mail(String objet, String message, LocalDate dateEnvoi) {
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

    public void envoiMail(Lecteur destinataire){
        File monFichier = new File(destinataire.getMail()+".txt");

        try(FileWriter fw = new FileWriter(monFichier)){
            PrintWriter pw = new PrintWriter(fw);
            pw.println("Objet : "+objet);
            pw.println(message+"\n\n");
            pw.println("La bibliothèque, le " + dateEnvoi+"\n\n");

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
