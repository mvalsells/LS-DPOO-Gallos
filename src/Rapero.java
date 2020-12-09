import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

public class Rapero {

    //Atributs
    private String realName;
    private String stageName;
    private String  birth;
    private String nationality;
    private int level;
    private String photo;
    private double puntuacio;

    //Constructor
    public Rapero(String realName, String stageName, String birth, String nationality, int level, String photo, float puntuacio) {
        this.realName = realName;
        this.stageName = stageName;
        this.birth = birth;
        this.nationality = nationality;
        this.level = level;
        this.photo = photo;
        this.puntuacio = puntuacio;
    }
    //Getters and Setters
    public String getStageName() {
        return stageName;
    }

    public int getLevel() {
        return level;
    }

    public double getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(double puntuacio) {
        this.puntuacio = puntuacio;
    }

    //Metodes
    public double comparePuntuacio(Rapero rapero) {
        return this.getPuntuacio()-rapero.getPuntuacio();
    }
}