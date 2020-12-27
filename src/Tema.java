import java.util.ArrayList;

/**
 * Esta clase contiene toda la información del tema y de las estrófas. Esta clse solo guarda la información de un tema.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 27/12/2020.
 */

public class Tema {

    //Campos de Tema
    private final String nom;
    private final ArrayList<String> estrofesN1;
    private final ArrayList<String> estrofesN2;

    /**
     * Constructor de Tema.
     *
     * @param nom        El parámetro nom contiene el nombre del tema.
     * @param estrofesN1 El parámetro estrofaN1 contiene las estrofas de nivel 1.
     * @param estrofesN2 El parámetro estrofaN2 contiene las estrofas de nivel 2.
     */

    public Tema(String nom, ArrayList<String> estrofesN1, ArrayList<String> estrofesN2) {
        this.nom = nom;
        this.estrofesN1 = estrofesN1;
        this.estrofesN2 = estrofesN2;
    }//Cierre del constructor

    /**
     * Método que coge el nombre del tema.
     *
     * @return El nombre del tema.
     */
    public String getNom() {
        return nom;
    }//Cierre del método

    /**
     * Método que coge las estrofas de nivel 1
     *
     * @return Las estrofas de nivel 1
     */

    public ArrayList<String> getEstrofesN1() {
        return estrofesN1;
    }//Cierre del método

    /**
     * Método que coge las estrofas de nivel 2
     *
     * @return Las estrofas de nivel 2
     */

    public ArrayList<String> getEstrofesN2() {
        return estrofesN2;
    }//Cierre del método

}//Cierre de la clase Tema
