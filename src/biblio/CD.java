package biblio;

import java.time.LocalTime;
import java.util.Objects;

public class CD extends Ouvrage{
    private long code;
    private byte nbrePlages;
    private String dureeTotale;

    public CD(long code, byte nbrePlages, String dureeTotale) {
        this.code = code;
        this.nbrePlages = nbrePlages;
        this.dureeTotale = dureeTotale;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public byte getNbrePlages() {
        return nbrePlages;
    }

    public void setNbrePlages(byte nbrePlages) {
        this.nbrePlages = nbrePlages;
    }

    public String getDureeTotale() {
        return dureeTotale;
    }

    public void setDureeTotale(String dureeTotale) {
        this.dureeTotale = dureeTotale;
    }

    @Override
    public double amendeRetard(int njours){
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CD cd = (CD) o;
        return code == cd.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "CD{" +
                "code=" + code +
                ", nbrePlages=" + nbrePlages +
                ", dureeTotale='" + dureeTotale + '\'' +
                '}' + super.toString();
    }
}
