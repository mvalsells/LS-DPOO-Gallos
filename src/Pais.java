import java.util.ArrayList;

/**
 * Esta clase nos permite controlar toda la información que tiene el pais.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 27/12/2020.
 */
public class Pais {

    //Campos de la clase
    private final String nomAngles;
    private int habitants;
    private String regio;
    private String bandera;
    private ArrayList<Llengua> llengues;


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


    /**
     * Método que nos coge la bandera del pais.
     *
     * @return La bandera del pais.
     */

    public String getBandera() {
        return bandera;
    }//Cierre del método

    /**
     * Método que nos idica las lenguas que se hablan en el pais y las escirbe en inglés.
     *
     * @return Las lenguas que se halan en el pais escrutas en inlgés.
     */

    public ArrayList<String> getLanguages() {
        ArrayList<String> lang = new ArrayList<>();
        for (Llengua llengua : llengues) {
            lang.add(llengua.getNomIdiomaAngles());
        }
        return lang;
    }//Cierre del método

}//Cierre de la clase Pais
