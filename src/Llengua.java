/**
 * Esta clase nos permite controlar el idioma con el que se hará la batalla al igual que ppoder obtener los idiomas de los diferentes paises.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 10/12/2020.
 */

public class Llengua {

    //Campos de la clase
    private String nomNatiu;
    private String nomAngles;

    /**
     * Método que dirá con que lengua se ejecuta la batalla
     */
    //TODO mirar on és cridar el constructor vuit per borrar-ho/canviar-ho el correcte
    public Llengua() {
    }//Cierre del método

    public Llengua(String nomAngles, String nomNatiu) {
        this.nomAngles=nomAngles;
        this.nomNatiu=nomNatiu;
    }

    @Override
    public String toString() {
        return "Llengua{" +
                "nomNatiu='" + nomNatiu + '\'' +
                ", nomAngles='" + nomAngles + '\'' +
                '}';
    }
}//Cierre de la clase LLengua
