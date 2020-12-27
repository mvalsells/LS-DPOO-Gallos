import exceptions.ApiReadException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Api {
    public static String getCountry(String countryName) throws IOException, ApiReadException {
        StringBuilder sb = new StringBuilder();
        sb.append("https://restcountries.eu/rest/v2/name/");
        sb.append(countryName.toLowerCase().replaceAll(" ","%20"));
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
    }
}
