import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

public class Rapero {

    //Atributs
    private String realName = new String();
    private String stageName;
    //private String strBirth;
    private String  birth;
    private String nationality;
    private int level;
    //canvi de URL-->string
    private String photo;
    private double puntuacio;

    //Constructor

    public Rapero(){};

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

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getBirthLocalDate(){
        return LocalDate.parse(birth);
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