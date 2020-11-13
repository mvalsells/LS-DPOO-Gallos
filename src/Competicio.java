import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Competicio {

    //Atributs
    private String name = new String();
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<String> countries = new ArrayList<>();
    private ArrayList<Fase> phases = new ArrayList<>();
    private ArrayList<Rapero> rappers = new ArrayList<>();

    //Constructor
    public Competicio(String name, LocalDate startDate, LocalDate endDate, ArrayList countries, ArrayList phases){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.countries = countries;
        this.phases = phases;
    }


    //Metodes
    public boolean registraUsuari(){
        return false;
    }

    public int numFase(){
        return 1;
    }

    public int numParticipants(){
        return 2;
    }

    public boolean haAcabat(){
        return true;
    }

    public int faseActual(){
        return 2;
    }

    public boolean haComençat(){
        return false;
    }


    public void mostrarInfo() {
        System.out.println("Nom: " + name);
        System.out.println("Start date: " + startDate);
        System.out.println("End date: " + endDate);
        System.out.println("Array countries: " + countries);
        System.out.println("Fases: " + phases);
    }

}
