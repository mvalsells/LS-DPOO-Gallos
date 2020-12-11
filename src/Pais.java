import java.net.URL;

/**
 * Esta clase nos permite controlar toda la información que tiene el pais.
 *
 * @ author: Marc Valsells y Albert Clarimón.
 * @ version: 10/12/2020.
 */
public class Pais {

    //Campos de la clase
    private final String nomAngles;
    private int habitants;
    private String regio;
    private URL bandera;
    private Llengua llengua;


    //Constructor

    /**
     * Constructor de Pais
     *
     * @param nomAngles El parámetro de nomAngles nos traduce el nombre del pais al inglés.
     */
    public Pais(String nomAngles) {
        this.nomAngles = nomAngles;
    }//Cierre del constructor

    //Getters and Setters

    /**
     * Método que nos coge el nombre en inglés del pais.
     *
     * @return El nombre en inglés del pais.
     */

    public String getNomAngles() {
        return nomAngles;
    }//Cierre del método
}//Cierre de la clase Pais
