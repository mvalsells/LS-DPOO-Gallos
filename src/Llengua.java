/**
 * Esta clase nos permite controlar el idioma con el que se hará la batalla al igual que ppoder obtener los idiomas de los diferentes paises.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 27/12/2020.
 */

public class Llengua {

    //Campos de la clase
    private String nomIdiomaNatiu;
    private String nomIdiomaAngles;


    /**
     * Constructor de Llengua
     *
     * @param nomIdiomaAngles El parámetro nomIdiomaAngles, sirve para canviar el nombre del pais al nombre del pais en inglés.
     * @param nomIdiomaNatiu  El parámetro nomIdiomaNatiu, sirve para identificar el idioma que rige en ese pais
     */

    public Llengua(String nomIdiomaAngles, String nomIdiomaNatiu) {
        this.nomIdiomaAngles = nomIdiomaAngles;
        this.nomIdiomaNatiu = nomIdiomaNatiu;
    }//Cierre del constructor

    /**
     * Método que dirá con que lengua se ejecuta la batalla
     */

    public Llengua() {
    }//Cierre del método

    /**
     * Método que sirve para canviar el nombre del pais, al nombre del pais escrito en inglés.
     *
     * @return Nombre del pais escrito en inglés.
     */

    public String getNomIdiomaAngles() {
        return nomIdiomaAngles;
    }//Cierre del método
}//Cierre de la clase LLengua
