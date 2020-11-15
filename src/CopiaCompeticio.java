import com.google.gson.JsonObject;

import java.util.ArrayList;

public class CopiaCompeticio {

    private JsonObject competition;
    private ArrayList<String> countries;
    private String rappers;


    public CopiaCompeticio(JsonObject competition, ArrayList<String> countries, String rappers ){
        this.competition=competition;
        this.countries=countries;
        this.rappers=rappers;
    }

}
