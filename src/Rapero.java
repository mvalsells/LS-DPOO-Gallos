
/**
 * Esta clase nos permite controlar el poryecto en su totalidad, tanto en el momento de ejecutar el menú, como en el de crear el controller.
 *
 * @ author: Marc Valsells y Albert Clarimón.
 * @ version: 10/12/2020.
 */

public class Rapero {

    //Campos de la clase
    private String realName;
    private String stageName;
    private String  birth;
    private Pais nationality;
    private int level;
    private String photo;
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
        this.nationality = new Pais(nationality);
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
     */
    public void setPuntuacio(double puntuacio) {
        this.puntuacio = puntuacio;
    }//Cierre del método

    //Metodes

    /**
     * Método que compara la puntuación del rapero.
     * @param rapero El parámetro rapero contiende la información de rapero.
     * @return La puntuación del rapero.
     */
    public double comparePuntuacio(Rapero rapero) {
        return this.getPuntuacio()-rapero.getPuntuacio();
    }
}