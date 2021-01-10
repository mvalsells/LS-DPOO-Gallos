import edu.salleurl.profile.Profileable;

/**
 * Esta clase nos permite almacenar información sobre los raperos.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 27/12/2020.
 */

public class Rapero implements Profileable {
    //Campos de la clase
    private final String realName;
    private final String stageName;
    private final String birth;
    private final Pais nationality;
    private final int level;
    private final String photo;
    private double puntuacio;

    /**
     * Constructor de Rapero
     *
     * @param realName    El parámetro realName nos indica el nombre real del rapero
     * @param stageName   El parámetro stageName nos indica el nombre artístico del rapero.
     * @param birth       El parámetro birth nos indica la fecha de nacimiento del rapero.
     * @param nationality El parámetro nationality nos indica la nacionalidad del rapero.
     * @param level       El parámetro level indica cual es el level del usuario.
     * @param photo       El parámetro photo nos indica cual es la foto del rapero.
     */

    public Rapero(String realName, String stageName, String birth, String nationality, int level, String photo) {
        this.realName = realName;
        this.stageName = stageName;
        this.birth = birth;
        Json json = new Json();
        Pais nation = new Pais(nationality);
        this.nationality = nation;
        this.level = level;
        this.photo = photo;
        puntuacio = 0.0f;
    }//Cierre del constructor

    //Getters and Setters

    /**
     * Método que devuelve el nombre artístico del rapero.
     *
     * @return El nombre artístico del rapero.
     */
    public String getStageName() {
        return stageName;
    }//Cierre del método

    /**
     * Método que devuelve el nivel del rapero.
     *
     * @return El nivel del rapero.
     */

    public int getLevel() {
        return level;
    }//Cierre del método

    /**
     * Método que devuelve la puntuaciçón del rapero.
     *
     * @return La puntuación del rapero.
     */

    public double getPuntuacio() {
        return puntuacio;
    }//Cierre del método

    /**
     * Método que coje la puntuaciçón del rapero.
     * @param puntuacio puntiación a establecer al rapero
     */
    public void setPuntuacio(double puntuacio) {
        this.puntuacio = puntuacio;
    }//Cierre del método

    //Metodes

    /**
     * Método que compara la puntuación del rapero.
     *
     * @param rapero El parámetro rapero contiende la información de rapero.
     * @return La puntuación del rapero.
     */

    public double comparePuntuacio(Rapero rapero) {
        return this.getPuntuacio() - rapero.getPuntuacio();
    }//cierre del método

    /**
     * Método que devuelve el nombre real del rapero.
     *
     * @return El nombre real del rapero.
     */

    @Override
    public String getName() {
        return realName;
    }//Cierre del método

    /**
     * Método que devuelve el nombre artístico del rapero.
     *
     * @return El nombre artístico del rapero.
     */

    @Override
    public String getNickname() {
        return stageName;
    }//cierre del método

    /**
     * Método que devuelve el año de nacimiento del rapero.
     *
     * @return El año de nacimiento del rapero.
     */

    @Override
    public String getBirthdate() {
        return birth;
    }//Cierre del método

    /**
     * Método que devuelve la foto del rapero.
     *
     * @return La foto del rapero.
     */

    @Override
    public String getPictureUrl() {
        return photo;
    }//Cierre del método

    /**
     * Método que clona la información del rapero a un nuevo array.
     *
     * @return la información del rapero clonada.
     */

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new Rapero(realName, stageName, birth, nationality.getNomAngles(), level, photo);
        }
    }//Cierre del método

    /**
     * Método que devuelve la bandera del pais.
     *
     * @return Devuelve la bandera del pais.
     */

    public String getBandera() {
        return nationality.getBandera();
    }//cierre del método

    /**
     * Método que devuelve el nombre del pais en inglés.
     *
     * @return El nombre del pais en inglés.
     */

    public String getCountryName() {
        return nationality.getNomAngles();
    }//Cierre del método
}//Cierre de la clase Rapero