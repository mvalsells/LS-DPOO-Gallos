import java.net.URL;

public class Pais {
    //Atributs
    private String nomAngles;
    private int habitants;
    private String regio;
    private URL bandera;


    //Constructor
    public Pais(String nomAngles){
        this.nomAngles=nomAngles;
    }

    //Getters and Setters
    public String getNomAngles() {
        return nomAngles;
    }
}
