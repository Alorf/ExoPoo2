package biblio;

import java.util.ArrayList;
import java.util.List;

public class Rayon {
    private String codeRayon;
    // unique
    private String genre;

    private List<Examplaire> examplaires = new ArrayList<>();

    public Rayon(String codeRayon, String genre, List<Examplaire> examplaires) {
        this.codeRayon = codeRayon;
        this.genre = genre;
        this.examplaires = examplaires;
    }

    public String getCodeRayon() {
        return codeRayon;
    }

    public void setCodeRayon(String codeRayon) {
        this.codeRayon = codeRayon;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Examplaire> getExamplaires() {
        return examplaires;
    }

    public void setExamplaires(List<Examplaire> examplaires) {
        this.examplaires = examplaires;
    }

    public void listerExamplaires(){
        return;
    }
}
