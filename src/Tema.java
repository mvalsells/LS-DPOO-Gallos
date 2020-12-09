import java.util.ArrayList;

public class Tema {
    private String nom;
    private ArrayList<String> estrofesN1;
    private ArrayList<String> estrofesN2;


    public Tema(String nom, ArrayList<String> estrofesN1, ArrayList<String> estrofesN2) {
        this.nom = nom;
        this.estrofesN1 = estrofesN1;
        this.estrofesN2 = estrofesN2;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<String> getEstrofesN1() {
        return estrofesN1;
    }

    public ArrayList<String> getEstrofesN2() {
        return estrofesN2;
    }

}
