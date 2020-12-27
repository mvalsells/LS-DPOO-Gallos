import edu.salleurl.profile.Profileable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Esta clase nos permite almacenar información sobre los raperos.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 10/12/2020.
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
    }

    @Override
    public String getName() {
        return realName;
    }

    @Override
    public String getNickname() {
        return stageName;
    }

    @Override
    public String getBirthdate() {
        return birth;
    }

    @Override
    public String getPictureUrl() {
        return photo;
    }


    public Object clone(){
        try {
            return (Rapero) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Rapero(realName,stageName,birth,nationality.getNomAngles(),level, photo);
        }
    }

    public String getBandera() {
        return nationality.getBandera();
    }

    public ArrayList<String> getLanguages() {
        return nationality.getLanguages();
    }

    public String getCountryName() {
        return nationality.getNomAngles();
    }
}