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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<String> getEstrofesN1() {
        return estrofesN1;
    }

    public void setEstrofesN1(ArrayList<String> estrofesN1) {
        this.estrofesN1 = estrofesN1;
    }

    public ArrayList<String> getEstrofesN2() {
        return estrofesN2;
    }

    public void setEstrofesN2(ArrayList<String> estrofesN2) {
        this.estrofesN2 = estrofesN2;
    }
}
