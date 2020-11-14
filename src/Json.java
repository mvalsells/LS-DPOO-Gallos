import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;

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
    public Competicio llegirCompeticio() throws IOException {
        //Atributs competició
        Competicio competicio;
        String name;
        LocalDate startDate;
        LocalDate endDate;
        ArrayList<String> countries = new ArrayList<>();
        ArrayList<String> phases = new ArrayList<>();

        //Atributs llegir JSON
        Reader read = new FileReader(fitxerCompeticio);
        JsonObject data;
        JsonObject jsonCompeticio;
        String strDate;
        JsonArray array;

        //Execució
        data = JsonParser.parseReader(read).getAsJsonObject();
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
            String budget;
            String country;
            JsonObject jsonPhase;
            StringBuilder sb;
            sb = new StringBuilder();
            jsonPhase = jsonElement.getAsJsonObject();

            budget = jsonPhase.get("budget").getAsString();
            country = jsonPhase.get("country").getAsString();
            sb.append(budget);
            sb.append(";");
            sb.append(country);
            phases.add(sb.toString());
        }

        //Array countries
        array = data.get("countries").getAsJsonArray();
        for(JsonElement jsonElement: array){
            String pais = jsonElement.getAsString();
            countries.add(pais);
        }

        //Creació d'una competició amb les dades llegides
        competicio = new Competicio(name, startDate, endDate, countries, phases);

        //Registrar raperos
        array = data.get("rappers").getAsJsonArray();
        for(JsonElement jsonElement : array){
            //Atributs
            String realName;
            String stageName;
            String strBirth;
            LocalDate birth;
            String nationality;
            int level;
            String strPhoto;
            //URL photo;

            //Executar
            JsonObject jsonRappers = jsonElement.getAsJsonObject();
            realName = jsonRappers.get("realName").getAsString();
            stageName = jsonRappers.get("stageName").getAsString();
            strBirth = jsonRappers.get("birth").getAsString();
            birth = LocalDate.parse(strBirth);
            nationality = jsonRappers.get("nationality").getAsString();
            level = jsonRappers.get("level").getAsInt();
            strPhoto = jsonRappers.get("photo").getAsString();
            //photo = new URL(strPhoto);

            //Registrar rapero, si el nom artístic ja existeix és mostra un error
            if (!competicio.registraUsuari(realName, stageName, birth, nationality, level, strPhoto)){
                //Mirar com tractar correctament aquest error
                System.out.println("ERROR: Ja existeix usuari amb aquest nom artístic");
            }
        }

        /*for (JsonElement jsonElement :array) {
            //ArrayList<Rapero> raperos = new ArrayList<>();
            String realName = new String();
            String stageName = new String();
            String bir = new String();
            LocalDate birth;
            String nationality = new String();
            Integer level;
            String url = new String();
            URL photo;

            //realName = jsonElement.getAsString();

            String raperito = array.get(i).getAsString();
            String raperos.get(i) = data.get("realName").getAsString();
            stageName = raperos.get(i).getStageName();
            birth = raperos.get(i).getBirth();
            nationality = raperos.get(i).getNationality();
            level = raperos.get(i).getLevel();

            photo = raperos.get(i).getPhoto();
            realName = jsonElement.getAsString();
            stageName = jsonElement.getAsString();
            bir = jsonElement.getAsString();
            birth = LocalDate.parse(bir);
            nationality = jsonElement.getAsString();
            level = jsonElement.getAsInt();
            url = jsonElement.getAsString();
            photo = new URL("url");

            //competicio.registraUsuari(nom, nomrapper....);

        }*/

        return  competicio;
    }

    //Llegir temes
    public void llegirTemes() throws FileNotFoundException {
        //Atributs llegir JSON
        Reader read = new FileReader(fitxerBatalla);
        JsonObject data;
        JsonArray array;

        //Obtenir dades
        data = JsonParser.parseReader(read).getAsJsonObject();
        array = data.get("themes").getAsJsonArray();

        for (JsonElement jsonElement : array) {
            JsonObject jsonTema = jsonElement.getAsJsonObject();
            JsonArray arrayRhymes;
            String nom;
            ArrayList<String> estrofesN1 = new ArrayList<>();
            ArrayList<String> estrofesN2 = new ArrayList<>();

            nom = jsonTema.get("name").getAsString();
            arrayRhymes = jsonTema.get("rhymes").getAsJsonArray();
            for(JsonElement jsonRhymesE : arrayRhymes) {
                JsonObject jsonRhymes = jsonRhymesE.getAsJsonObject();
                JsonArray rhymesN1 = jsonRhymes.get("1").getAsJsonArray();
                for (JsonElement rhymesN1E : rhymesN1) {
                    estrofesN1.add(rhymesN1E.getAsString());
                }
                JsonArray rhymesN2 = jsonRhymes.get("2").getAsJsonArray();
                for (JsonElement rhymesN2E : rhymesN2) {
                    estrofesN2.add(rhymesN2E.getAsString());
                }
            }

            System.out.println("Nom: " + nom);
            System.out.println("Estrofes N1: " + estrofesN1);
            System.out.println("Estrofes N2: "+estrofesN2);
            System.out.println("------------------------------------------------------------------------------------");
        }

    }

}
