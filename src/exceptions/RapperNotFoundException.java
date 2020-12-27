package exceptions;

/**
 * Esta clase nos permite controlar que no salte el programa si no existe el rapero solicitado.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 27/12/2020.
 */

public class RapperNotFoundException extends Exception {
    String stageName;

    /**
     * Constructor de la clase
     *
     * @param stageName El parámetro stageName india el nombre artítico del rapreo.
     */

    public RapperNotFoundException(String stageName) {
        super();
        this.stageName = stageName;
    }//Cierre del constructor

    /**
     * Método que devuelve el nombre del rapero solicitado
     *
     * @return
     */
    public String getStageName() {
        return stageName;
    }//Cierre del método
}//Cierre de la clase
