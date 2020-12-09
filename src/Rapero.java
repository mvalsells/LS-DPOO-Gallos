public class Rapero {

    //Atributs
    private String realName;
    private String stageName;
    private String  birth;
    private Pais nationality;
    private int level;
    private String photo;
    private double puntuacio;

    //Constructor
    public Rapero(String realName, String stageName, String birth, String nationality, int level, String photo) {
        this.realName = realName;
        this.stageName = stageName;
        this.birth = birth;
        this.nationality = new Pais(nationality);
        this.level = level;
        this.photo = photo;
        puntuacio = 0.0f;
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