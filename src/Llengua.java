/**
 * Esta clase nos permite controlar el idioma con el que se hará la batalla al igual que ppoder obtener los idiomas de los diferentes paises.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 10/12/2020.
 */

public class Llengua {

    //Campos de la clase
    private String nomIdiomaNatiu;
    private String nomIdiomaAngles;

    /**
     * Método que dirá con que lengua se ejecuta la batalla
     */

    public Llengua() {
    }//Cierre del método

    public Llengua(String nomIdiomaAngles, String nomIdiomaNatiu) {
        this.nomIdiomaAngles =nomIdiomaAngles;
        this.nomIdiomaNatiu=nomIdiomaNatiu;
    }

    @Override
    public String toString() {
        return "Llengua{" +
                "nomIdiomaNatiu='" + nomIdiomaNatiu + '\'' +
                ", nomIdiomaAngles='" + nomIdiomaAngles + '\'' +
                '}';
    }
    public String getNomIdiomaAngles(){
        return nomIdiomaAngles;
    }
}//Cierre de la clase LLengua
