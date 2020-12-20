import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Api {
    public static String getCountry(String countryName) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("https://restcountries.eu/rest/v2/name/");
        sb.append(countryName.toLowerCase());
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
            //TODO aquest else if i else segurament és pot fer amb una exception nostra que faci un throws amb el responseCode i ja ho solucionarem que fem si tenim 404 (pais no trobat) o un altre ("eeror servidor" on cridem el metode
        } else if (conn.getResponseCode() == 404) {
            // pais no trobat
            sb = new StringBuilder();
            sb.append("Country ");
            sb.append(countryName);
            sb.append(" not found");
            System.err.println(sb.toString());
        } else {
            //fer la nostra exception
            //throws new Respostade la web no esperado o algo similar exception
        }
        conn.disconnect();
        return jsonWeb.toString();
    }
}
