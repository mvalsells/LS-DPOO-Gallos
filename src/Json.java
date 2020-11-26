import com.google.gson.*;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Json {

    //Atributs
    String fitxerCompeticio;
    String fitxerBatalla;

    //Constructor
    public Json(String fitxerCompeticio, String fitxerBatalla) {
        this.fitxerCompeticio = fitxerCompeticio;
        this.fitxerBatalla = fitxerBatalla;
    }

    //Mètodes

    private JsonObject competicioJsonObject(JsonObject data) {
        JsonObject jsonCompeticio = data.get("competition").getAsJsonObject();
        return jsonCompeticio;
    }

    private JsonObject llegirData() throws FileNotFoundException {
        Reader read = new FileReader(fitxerCompeticio);
        JsonObject data = JsonParser.parseReader(read).getAsJsonObject();
        return data;
    }

    public Competicio llegirCompeticio() throws IOException {
        //Atributs competició
        Competicio competicio;
        JsonObject competition;
        String name;
        LocalDate startDate;
        LocalDate endDate;
        ArrayList<String> countries = new ArrayList<>();
        ArrayList<Fase> phases = new ArrayList<>();

        //Atributs llegir JSON
        //Reader read = new FileReader(fitxerCompeticio);
        JsonObject data;
        JsonObject jsonCompeticio;
        String strDate;
        JsonArray array;

        //Execució
        //data = JsonParser.parseReader(read).getAsJsonObject();
        data = llegirData();
        //jsonCompeticio = data.get("competition").getAsJsonObject();
        jsonCompeticio = competicioJsonObject(data);
        name = jsonCompeticio.get("name").getAsString();

        //Llegir dades competició
        strDate = jsonCompeticio.get("startDate").getAsString();
        startDate = LocalDate.parse(strDate);
        strDate = jsonCompeticio.get("endDate").getAsString();
        endDate = LocalDate.parse(strDate);
        array = jsonCompeticio.get("phases").getAsJsonArray();

        //Llegir fases
        for (JsonElement jsonElement : array) {
            float budget;
            String strPais;
            JsonObject jsonPhase;
            StringBuilder sb;
            sb = new StringBuilder();
            jsonPhase = jsonElement.getAsJsonObject();

            budget = jsonPhase.get("budget").getAsFloat();
            strPais = jsonPhase.get("country").getAsString();
            Pais pais = new Pais(strPais);
            Fase fase = new Fase(budget, pais);
            phases.add(fase);
        }

        //Array countries
        array = data.get("countries").getAsJsonArray();
        for (JsonElement jsonElement : array) {
            String pais = jsonElement.getAsString();
            countries.add(pais);
        }

        //Registrar raperos
        array = data.get("rappers").getAsJsonArray();
        ArrayList<Rapero> raperos = new ArrayList<>();

        //escriureRapero(realName, );
        for (JsonElement jsonElement : array) {
            //Atributs
            String realName;
            String stageName;
            String strBirth;
            String birth;
            String nationality;
            int level;
            String photo;
            float puntuacio = 0;

            //Executar
            JsonObject jsonRappers = jsonElement.getAsJsonObject();
            realName = jsonRappers.get("realName").getAsString();
            stageName = jsonRappers.get("stageName").getAsString();
            birth = jsonRappers.get("birth").getAsString();
            nationality = jsonRappers.get("nationality").getAsString();
            level = jsonRappers.get("level").getAsInt();
            photo = jsonRappers.get("photo").getAsString();


            //guardar arraylist raperos
            Rapero rapero = new Rapero(realName, stageName, birth, nationality, level, photo, puntuacio);
            raperos.add(rapero);

            //Registrar rapero, si el nom artístic ja existeix és mostra un error
            /*if (!competicio.registraUsuari(realName, stageName, birth, nationality, level, photo)){
                //Mirar com tractar correctament aquest error, aixo va a rapero no aqui
                System.out.println("ERROR: Ja existeix usuari amb aquest nom artístic");
            }*/
        }

        for (Fase fase : phases) {
            fase.setRapperos(raperos);
        }

        //Creació d'una competició amb les dades llegides
        competicio = new Competicio(name, startDate, endDate, countries, phases, raperos, data);

        return competicio;

    }

    //Llegir temes
    public ArrayList<Tema> llegirTema() throws FileNotFoundException {
        //Atributs llegir JSON
        Reader read = new FileReader(fitxerBatalla);
        JsonObject data;
        JsonArray array;
        ArrayList<Tema> temes = new ArrayList<>();

        //Obtenir dades
        data = JsonParser.parseReader(read).getAsJsonObject();
        array = data.get("themes").getAsJsonArray();

        for (JsonElement jsonElement : array) {
            JsonObject jsonTema = jsonElement.getAsJsonObject();
            Tema tema;
            JsonArray arrayRhymes;
            String nom;
            ArrayList<String> estrofesN1 = new ArrayList<>();
            ArrayList<String> estrofesN2 = new ArrayList<>();

            nom = jsonTema.get("name").getAsString();
            arrayRhymes = jsonTema.get("rhymes").getAsJsonArray();
            for (JsonElement jsonRhymesE : arrayRhymes) {
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
            tema = new Tema(nom, estrofesN1, estrofesN2);
            temes.add(tema);

        }

        //Collections.shuffle(temes);


        return temes;

    }

    public void escriureRapero(ArrayList<String> countries, ArrayList<Rapero> raperos) throws IOException {

        JsonObject data = llegirData();
        JsonObject jsonCompeticio = competicioJsonObject(data);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();
/*
        JsonWriter jsonWriter = gson.newJsonWriter(fitxerCompeticio);


        jsonWriter.name("competition");
        jsonWriter.beginObject();
        jsonWriter.name("name");
        jsonWriter.value();*/

        JsonObject tot = new JsonObject();
        tot.add("competition", jsonCompeticio);
        tot.add("countries", gson.toJsonTree(countries));
        tot.add("rappers", gson.toJsonTree(raperos));

        String json = gson.toJson(tot);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/competicio.json"))) {
            bw.write(json);
        } catch (IOException ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
