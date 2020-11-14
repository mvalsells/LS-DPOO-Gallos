import java.util.ArrayList;

public class Tema {
    private String nom;
    private ArrayList<String> estrofesN1 = new ArrayList<>();
    private ArrayList<String> estrofesN2 = new ArrayList<>();


    public Tema(String nom, ArrayList<String> estrofesN1, ArrayList<String> estrofesN2) {
        this.nom = nom;
        this.estrofesN1 = estrofesN1;
        this.estrofesN2 = estrofesN2;
    }
}
