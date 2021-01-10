import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Esta clase nos permite leer la información de la API
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 27/12/2020.
 */

public class Api {

    /**
     * Método el cual se encarga de conectar con la API al igual que comprobar si el pais existe
     *
     * @param countryName El parámetro countryName nos indica el nombre del pais
     * @return retorna a información leida de la API guardada en un string
     * @throws IOException      El parametro IOException controla los errores de la url
     * @throws ApiReadException El parámetro ApiReadException controla si hay un fallo en la lectura de la API
     */

    public static String getCountry(String countryName) throws IOException, ApiReadException {
        StringBuilder sb = new StringBuilder();
        sb.append("https://restcountries.eu/rest/v2/name/");
        sb.append(countryName.toLowerCase().replaceAll(" ", "%20"));
        StringBuilder jsonWeb = new StringBuilder();
        URL urlAPI = new URL(sb.toString());
        HttpURLConnection conn = (HttpURLConnection) urlAPI.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        if (conn.getResponseCode() == 200) {
            Scanner scanner = new Scanner(urlAPI.openStream());
            while (scanner.hasNextLine()) {
                jsonWeb.append(scanner.nextLine());
            }
        } else {
            conn.disconnect();
            throw new ApiReadException(conn.getResponseCode());
        }
        conn.disconnect();
        return jsonWeb.toString();
    }//Cierre del método
}//Cierre de la clase API
