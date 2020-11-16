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

    //Constructor

    public Rapero(String realName, String stageName, String birth, String nationality, int level, String photo) {
        this.realName = realName;
        this.stageName = stageName;
        this.birth = birth;
        this.nationality = nationality;
        this.level = level;
        this.photo = photo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

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

    @Override
    public String toString() {
        return "Rapero{" +
                "realName='" + realName + '\'' +
                ", stageName='" + stageName + '\'' +
                ", birth=" + birth +
                ", nationality='" + nationality + '\'' +
                ", level=" + level +
                ", photo=" + photo +
                '}';
    }
}
