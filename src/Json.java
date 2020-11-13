import java.io.*;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    public Competicio llegirCompeticio() throws IOException, ParseException {

        //



        //Vars competició
        Competicio competicio;
        String name = new String();
        LocalDate startDate;
        LocalDate endDate;
        ArrayList<String> countries = new ArrayList<>();
        ArrayList<String> phases = new ArrayList<>();

        //Llegir JSON
        Reader read = new FileReader(fitxerCompeticio);
        JsonObject data = new JsonObject();
        JsonObject jsonCompeticio = new JsonObject();
        String strDate = new String();
        JsonArray array = new JsonArray();

        //Altres



        data = JsonParser.parseReader(read).getAsJsonObject();

        // JSON  -> Variables

        jsonCompeticio = data.get("competition").getAsJsonObject();
        name = jsonCompeticio.get("name").getAsString();

        //Llegir dades competició
        strDate = jsonCompeticio.get("startDate").getAsString();
        startDate = LocalDate.parse(strDate);
        strDate = jsonCompeticio.get("endDate").getAsString();
        endDate = LocalDate.parse(strDate);
        array = jsonCompeticio.get("phases").getAsJsonArray();

        //Llegir fases
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
        array = data.get("countries").getAsJsonArray();
        for(JsonElement jsonElement: array){
            String pais = jsonElement.getAsString();
            countries.add(pais);
        }

        array = data.get("rappers").getAsJsonArray();
        for (JsonElement jsonElement : array) {

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
