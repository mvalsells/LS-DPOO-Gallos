import java.io.*;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;

public class Json {

    //Atributs
    String fitxerCompeticio;
    String fitxerBatalla;

    //Constructor
    public Json (String fitxerCompeticio, String fitxerBatalla){
        this.fitxerCompeticio = fitxerCompeticio;
        this.fitxerBatalla = fitxerBatalla;
    }

    //Mètodes
    /*private String fileToString (String fileName) throws IOException {

        FileReader fr;
        BufferedReader br;
        StringBuilder sb;
        String line;
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);
        sb = new StringBuilder();
        line = br.readLine();

        while (line != null ){
            sb.append(line);
            line = br.readLine();
        }

        return sb.toString();
    }*/

    public Competicio llegirCompeticio(String fileName) throws IOException, ParseException {
        String json;
        Competicio competicio;

        String name = new String();
        Date startDate = new Date();
        Date endDate = new Date();
        ArrayList<String> countries = new ArrayList<>();
        ArrayList<String> phases = new ArrayList<>();
        Reader read = new FileReader(fileName);
        JsonObject data = new JsonObject();
        JsonObject jsonCompeteicio = new JsonObject();
        SimpleDateFormat strToDate = new SimpleDateFormat("YYYY-MM-dd");
        String strDate = new String();
        JsonArray array = new JsonArray();



        data = JsonParser.parseReader(read).getAsJsonObject();



        

        // JSON  -> Variables

        jsonCompeteicio = data.get("competition").getAsJsonObject();
        name = jsonCompeteicio.get("name").getAsString();

        strDate = jsonCompeteicio.get("startDate").getAsString();
        startDate = strToDate.parse(strDate);
        strDate = jsonCompeteicio.get("endDate").getAsString();
        endDate = strToDate.parse(strDate);
        array = jsonCompeteicio.get("phases").getAsJsonArray();

        for (JsonElement jsonElement: array) {
            String budget = new String();
            String country = new String();
            JsonObject jsonPhase = new JsonObject();
            StringBuilder sb;
            sb = new StringBuilder();
            jsonPhase = jsonElement.getAsJsonObject();

            budget = jsonPhase.get("budget").getAsString();
            country = jsonPhase.get("country").getAsString();
            sb.append(budget);
            sb.append(";");
            sb.append(country);
            //sb.toString();
            phases.add(sb.toString());
        }

        //array countries

        array = jsonCompeteicio.get("countries").getAsJsonArray();
        for(JsonElement jsonElement: array){
            String pais = new String();
            JsonObject jsonCompeticio = new JsonObject();
            StringBuilder sb;
            sb = new StringBuilder();
            jsonCompeticio = jsonElement.getAsJsonObject();

            pais = jsonCompeticio.getAsString();

            sb.append(pais);
            sb.append(",");
            countries.add(sb.toString());

        }




        competicio = new Competicio(name, startDate, endDate, countries, phases);

        return  competicio;
    }



    /*public static void main(String args[]) throws java.io.IOException {
        JsonParser parser = new JsonParser();
        FileReader fr = new FileReader("competicio.json");
        JsonElement datos = parser.parse(fr);
        dumpJSONElement(datos);
    }*/

    public static void dumpJSONElement(JsonElement element){
        //El método «dumpJSONElement» debe determinar el tipo de elemento que
        // recibe como argumento (elemento simple, hashtable o array), y procesarlo en consecuencia.
        //
        //Si el elemento recibido es un elemento compuesto de otros
        // elementos (array o hashtable), «dumpJSONElement» se llama a sí mismo recursivamente:


    }


}
