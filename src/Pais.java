import java.util.ArrayList;

/**
 * Esta clase nos permite controlar toda la información que tiene el pais.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 10/12/2020.
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
    //TODO mirar on és cridar el constructor vuit per borrar-ho/canviar-ho el correcte
    public Pais(String nomAngles) {
        this.nomAngles = nomAngles;
    }//Cierre del constructor

    public Pais(String nomAngles, String regio, int habitants, String bandera, ArrayList<String[]> languages) {
        this.nomAngles=nomAngles;
        this.regio=regio;
        this.habitants=habitants;
        this.bandera=bandera;
        this.llengues = new ArrayList<>();
        for (String[] lang : languages){
            Llengua language = new Llengua(lang[0], lang[1]);
            llengues.add(language);
        }
    }

    //Getters and Setters

    /**
     * Método que nos coge el nombre en inglés del pais.
     *
     * @return El nombre en inglés del pais.
     */

    public String getNomAngles() {
        return nomAngles;
    }//Cierre del método

    @Override
    public String toString() {
        return "Pais{" +
                "nomAngles='" + nomAngles + '\'' +
                ", habitants=" + habitants +
                ", regio='" + regio + '\'' +
                ", bandera='" + bandera + '\'' +
                ", llengues=" + llengues.toString() +
                '}';
    }
}//Cierre de la clase Pais
