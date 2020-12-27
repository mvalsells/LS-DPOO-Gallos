package exceptions;

/**
 * Esta clase nos permite controlar que no salte el programa si hay un fallo en la API.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 27/12/2020.
 */

public class ApiReadException extends Exception {
    int httpCode;

    /**
     * Constructor de la clase
     *
     * @param httpCode parámetro que indica que código ha retornado el htttp
     */

    public ApiReadException(int httpCode) {
        super();
        this.httpCode = httpCode;
    }//Cierre del constructor

    /**
     * Método que devuelve el código que ha retornado el http.
     *
     * @return El código que ha retornado el http.
     */

    public int getHttpCode() {
        return httpCode;
    }//Cierre del método
}//Cierre de la clase ApiReadException
