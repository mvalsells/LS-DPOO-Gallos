import java.net.URL;

public class Pais {
    //Atributs
    String nomAngles;
    int habitants;
    String regio;
    URL bandera;


    //Constructor
    public Pais(String nomAngles){
        this.nomAngles=nomAngles;
    }

    //Getters and Setters
    public String getNomAngles() {
        return nomAngles;
    }
}
