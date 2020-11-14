import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Competicio {

    //Atributs
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<String> countries;
    private ArrayList<Fase> phases;
    private int numFases;
    private ArrayList<Rapero> raperos;
    private ArrayList<Tema> temes;
    private Json json = new Json("src/competicio.json", "src/batalles.json");

    //Constructor
    public Competicio(String name, LocalDate startDate, LocalDate endDate, ArrayList countries, ArrayList phases,ArrayList raperos) throws FileNotFoundException {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.countries = countries;
        this.phases = phases;
        this.raperos = raperos;
        temes = json.llegirTemes();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<String> countries) {
        this.countries = countries;
    }

    public ArrayList<Fase> getPhases() {
        for(int i=0; i<phases.size(); i++){
            numFases++;
        }
        return phases;
    }

    public void setPhases(ArrayList<Fase> phases) {
        this.phases = phases;
    }

    public ArrayList<Rapero> getRappers() {
        return raperos;
    }

    public void setRappers(ArrayList<Rapero> rappers) {
        this.raperos = rappers;
    }

    //Metodes
    public boolean registraUsuari(String realName, String stageName, LocalDate birth, String nationality, int level, String photo){


        return true;
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
        java.util.Date fecha = new Date();
        if(startDate.equals(fecha)){
            return true;
        }else{
            return false;
        }

    }


    public void mostrarInfo() {
        System.out.println("Nom: " + name);
        System.out.println("Start date: " + startDate);
        System.out.println("End date: " + endDate);
        System.out.println("Array countries: " + countries);
        System.out.println("Fases: " + phases);
    }

}
