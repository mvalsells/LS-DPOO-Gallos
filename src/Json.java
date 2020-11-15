import com.google.gson.*;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        JsonObject competition;
        String name;
        LocalDate startDate;
        LocalDate endDate;
        ArrayList<String> countries = new ArrayList<>();
        ArrayList<Fase> phases = new ArrayList<>();

        //Atributs llegir JSON
        Reader read = new FileReader(fitxerCompeticio);
        JsonObject data;
        JsonObject jsonCompeticio;
        String strDate;
        JsonArray array;

        //Execució
        data = JsonParser.parseReader(read).getAsJsonObject();
        competition = data.get("competition").getAsJsonObject();
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
        for(JsonElement jsonElement: array){
            String pais = jsonElement.getAsString();
            countries.add(pais);
        }

        //Registrar raperos
        array = data.get("rappers").getAsJsonArray();
        ArrayList<Rapero> raperos = new ArrayList<>();

        //escriureRapero(realName, );
        for(JsonElement jsonElement : array){
            //Atributs
            String realName;
            String stageName;
            String strBirth;
            LocalDate birth;
            String nationality;
            int level;
            String photo;

            //Executar
            JsonObject jsonRappers = jsonElement.getAsJsonObject();
            realName = jsonRappers.get("realName").getAsString();
            stageName = jsonRappers.get("stageName").getAsString();
            strBirth = jsonRappers.get("birth").getAsString();
            birth = LocalDate.parse(strBirth);
            nationality = jsonRappers.get("nationality").getAsString();
            level = jsonRappers.get("level").getAsInt();
            photo = jsonRappers.get("photo").getAsString();


            //guardar arraylist raperos
            Rapero rapero = new Rapero(realName, stageName, birth,nationality, level, photo);
            raperos.add(rapero);

            //Registrar rapero, si el nom artístic ja existeix és mostra un error
            /*if (!competicio.registraUsuari(realName, stageName, birth, nationality, level, photo)){
                //Mirar com tractar correctament aquest error, aixo va a rapero no aqui
                System.out.println("ERROR: Ja existeix usuari amb aquest nom artístic");
            }*/
        }


        //Creació d'una competició amb les dades llegides
        competicio = new Competicio(competition, name, startDate, endDate, countries, phases, raperos,data);

        return  competicio;

    }

    //Llegir temes
    public ArrayList llegirTemes() throws FileNotFoundException {
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
            tema = new Tema(nom, estrofesN1, estrofesN2);
            temes.add(tema);

        }

        return temes;

    }

    /*public void escriureRapero(ArrayList<Rapero> raperos, ArrayList<String> countries, String name, LocalDate startDate, LocalDate endDate, ArrayList<Fase> phases)  {

        Competicio competicio = null;

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();
        try {


            competicio = new Competicio(name, startDate, endDate, countries, phases, raperos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String json = gson.toJson(competicio);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/competicio1.json"))) {
            bw.write(json);
            System.out.println("Fichero creado");
        } catch (IOException ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
        }




    }*/

    public void escriureRapero(Rapero rapero, JsonObject data) {

       /* Rapero rapero = null;
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();

        rapero = new Rapero ( realName, stageName, birth, nationality, level, photo);
        String json = gson.toJson(rapero);*/

        JsonObject jObject  = new JsonObject();
        jObject.getAsJsonObject("rappers").addProperty(String.valueOf(rapero),1);
        System.out.println(jObject);

    }

    public void escriureRapero(JsonObject competition, ArrayList<String> countries, ArrayList<Rapero> raperos) throws FileNotFoundException {

        CopiaCompeticio copia = null;
        String rappero = new String();
        String rap = new String();
        //birthday=raperos.toString();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();

        /*for(int i=0; i<raperos.size(); i++ ){
            //birthday=raperos.get(i).getBirth().toString();
            rap = raperos.get(i).toString();
            rappero = rappero+rap;
        }*/



        copia = new CopiaCompeticio(competition, countries, rappero);

        String json = gson.toJson(copia);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/competicio1.json"))) {
            bw.write(json);
            System.out.println("Fichero creado");
        } catch (IOException ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
