import com.google.gson.*;

import java.io.*;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Esta clase nos permite tanto leer del json como escribir en él.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 10/12/2020.
 */

public class Json {
    /**
     * Constructor de Json
     */

    public Json() {
    }//Cierre del constructor

    //Mètodes

    /**
     * Método por el cual se lee el json de competición, una vez leido se separará en competition, countries o rappers.
     *
     * @return El conntenido que tiene cada competición.
     */

    public Competicio llegirCompeticio() {
        final String fitxerCompeticio  = "src/competicio.json";
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
            JsonObject data = JsonParser.parseString(FileUtils.readText(fitxerCompeticio)).getAsJsonObject();
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
                JsonObject jsonPhase;
                jsonPhase = jsonElement.getAsJsonObject();

                float budget = jsonPhase.get("budget").getAsFloat();
                String country = jsonPhase.get("country").getAsString();
                Fase fase = new Fase(country, budget);
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
                String birth;
                String nationality;
                int level;
                String photo;

                //Executar
                JsonObject jsonRappers = jsonElement.getAsJsonObject();
                realName = jsonRappers.get("realName").getAsString();
                stageName = jsonRappers.get("stageName").getAsString();
                birth = jsonRappers.get("birth").getAsString();
                nationality = jsonRappers.get("nationality").getAsString();
                level = jsonRappers.get("level").getAsInt();
                photo = jsonRappers.get("photo").getAsString();


                //guardar arraylist raperos
                Rapero rapero = new Rapero(realName, stageName, birth, nationality, level, photo);
                raperos.add(rapero);

                //Registrar rapero, si el nom artístic ja existeix és mostra un error
            }

            Fase.setRapperos(raperos);

            //Creació d'una competició amb les dades llegides
            competicio = new Competicio(name, startDate, endDate, countries, phases);

            return competicio;
        } catch (FileNotFoundException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("File ");
            sb.append(fitxerCompeticio);
            sb.append(" not found, can not load competition\nExiting program");
            System.out.println(sb.toString());
            System.exit(1);
            return null;
        }

    }//Cierre del método

    //Llegir temes

    /**
     * Método que se utiliza al momento de leer los temas y estrofas que hay en el fichero de batalla.
     *
     * @return El array de temas donde contiene todos los temas y estrofas.
     */

    public ArrayList<Tema> llegirTema() {
        final String fitxerBatalla = "src/batalles.json";
        try {
            ArrayList<Tema> temes = new ArrayList<>();

            //Obtenir dades
            JsonObject data = JsonParser.parseString(FileUtils.readText(fitxerBatalla)).getAsJsonObject();
            JsonArray array = data.get("themes").getAsJsonArray();

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

        } catch (FileNotFoundException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("File ");
            sb.append(fitxerBatalla);
            sb.append(" not found, can not load battles\nExiting program");
            System.out.println(sb.toString());
            System.exit(1);
            return null;
        }
    }//Cierre del método

    /**
     * Método que se usa al momento de escribir un rapero al json, cuando este se inscirbe en la competición.
     *
     * @param realName    El parámetro realName nos indica el nombre real del rapero
     * @param stageName   El parámetro stageName nos indica el nombre artístico del rapero.
     * @param birth       El parámetro birth nos indica la fecha de nacimiento del rapero.
     * @param nationality El parámetro nationality nos indica la nacionalidad del rapero.
     * @param level       El parámetro level indica cual es el level del usuario.
     * @param photo       El parámetro photo nos indica cual es la foto del rapero.
     */

    public void escriureRapero(String realName, String stageName, String birth, String nationality, int level, String photo) {
        final String fitxerCompeticio  = "src/competicio.json";
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();

        try {
            JsonObject data = JsonParser.parseString(FileUtils.readText(fitxerCompeticio)).getAsJsonObject();
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

            String jsonTot = gson.toJson(tot);
            FileUtils.writeText(fitxerCompeticio, jsonTot);
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error while processing ");
            sb.append(fitxerCompeticio);
            sb.append("\nExiting program");
            System.out.println(sb.toString());
            System.exit(1);
        }

    }//Cierre del método

    public Pais llegirPais(String nomAngles)/*thorw Malform... IOexception PropiaException*/{

        //Llegir API
        String jsonWeb = "";
        try {
            Api.getCountry(nomAngles);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.err.println("The provided URL is not correct");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not open connection to the provided URL");
        }
        //TODO segurmaent es podrà borrar aquest if quan haguem fet la exception donat que farà el throws abans i mai hi arribarem
        if (!jsonWeb.isEmpty()) {
            //Agafo el primer (i unic) element del array que hem retornen com a data
            JsonObject data = JsonParser.parseString(jsonWeb).getAsJsonArray().get(0).getAsJsonObject();
            int habitants = data.get("population").getAsInt();
            String region = data.get("region").getAsString();
            String bandera = data.get("flag").getAsString();

            ArrayList<String[]> languages = new ArrayList<>();
            JsonArray languagesJson = data.get("languages").getAsJsonArray();
            for (JsonElement languageElement : languagesJson) {
                JsonObject languageObject = languageElement.getAsJsonObject();
                String[] language = new String[2];
                language[0] = languageObject.get("name").getAsString();
                language[1] = languageObject.get("nativeName").getAsString();
                languages.add(language);
            }

            return new Pais(nomAngles, region, habitants, bandera, languages);
        } else {
            return null;
        }
    }
}//Cierre de la clase Json
