import java.util.ArrayList;

public class Fase {
    //Atributs
    Pais pais;
    float budget;
    ArrayList<Batalla> batalles = new ArrayList<>();

    public Fase(float budget, Pais pais) {
        this.budget = budget;
        this.pais = pais;
    }
}
