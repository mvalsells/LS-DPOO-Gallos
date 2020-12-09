import com.google.gson.*;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class Json {

    //Atributs
    private String fitxerCompeticio;
    private String fitxerBatalla;

    //Constructor
    public Json(String fitxerCompeticio, String fitxerBatalla) {
        this.fitxerCompeticio = fitxerCompeticio;
        this.fitxerBatalla = fitxerBatalla;
    }

    //Mètodes


    public Competicio llegirCompeticio() {
        try {
            //Atributs competició
            Competicio competicio;
            String name;
            LocalDate startDate;
            LocalDate endDate;
            ArrayList<String> countries = new ArrayList<>();
            ArrayList<Fase> phases = new ArrayList<>();

            //Atributs llegir JSON
            String strDate;
            JsonArray array;

            //Execució
            Reader read = new FileReader(fitxerCompeticio);
            JsonObject data = JsonParser.parseReader(read).getAsJsonObject();
            JsonObject jsonCompeticio = data.get("competition").getAsJsonObject();
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

            //TODO posar static a fases
            for (Fase fase : phases) {
                fase.setRapperos(raperos);
            }

            //Creació d'una competició amb les dades llegides
            competicio = new Competicio(name, startDate, endDate, countries, phases, raperos, data);

            return competicio;
        }catch (FileNotFoundException e){
            StringBuilder sb = new StringBuilder();
            sb.append("File ");
            sb.append(fitxerCompeticio);
            sb.append(" not found, can not load competition\nExiting program");
            System.out.println(sb.toString());
            System.exit(1);
            return null;
        }

    }

    //Llegir temes
    public ArrayList<Tema> llegirTema() {
        try {
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
            return temes;

        } catch (FileNotFoundException e){
            StringBuilder sb = new StringBuilder();
            sb.append("File ");
            sb.append(fitxerBatalla);
            sb.append(" not found, can not load battles\nExiting program");
            System.out.println(sb.toString());
            System.exit(1);
            return null;
        }
    }

    public void escriureRapero(String realName, String stageName, String birth, String nationality, int level, String photo) throws IOException {



        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();

       // try {
            Reader read = new FileReader(fitxerCompeticio);
            JsonObject data = JsonParser.parseReader(read).getAsJsonObject();
            JsonObject jsonCompeticio = data.get("competition").getAsJsonObject();
            JsonArray jsonCountries = data.get("countries").getAsJsonArray();
            JsonArray jsonRappers = data.get("rappers").getAsJsonArray();

            JsonObject aRapper = new JsonObject();
            aRapper.addProperty("realName", realName);
            aRapper.addProperty("stageName", stageName);
            aRapper.addProperty("birth", birth);
            aRapper.addProperty("nationality", nationality);
            aRapper.addProperty("level", level);
            aRapper.addProperty("photo", photo);

            jsonRappers.add(aRapper);

            JsonObject tot = new JsonObject();
            tot.add("competition", jsonCompeticio);
            tot.add("countries", jsonCountries);
            tot.add("rappers", jsonRappers);

            //String strJson = gson.toJson(tot);
            //BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerCompeticio));
            //bw.write(strJson);

       // JsonWriter writer = new JsonWriter(new FileWriter(fitxerCompeticio));
        FileWriter fw = new FileWriter(fitxerCompeticio);
        fw.write(tot.toString());

        /*} catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error while processing ");
            sb.append(fitxerCompeticio);
            sb.append("\nExiting program");
            System.out.println(sb.toString());
            System.out.println(e.getCause());
            System.exit(1);
        }*/

    }
}
