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

    //Getters & Setters
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
    public int registreUsuari(String realName, String stageName, LocalDate birth, String nationality, int level, String photo){

        /*
        1- Comprobar dades, rapera exiteix???, pais existeix?? data neixament és més gran q avui????
        2- Crear rapero amb les dades ok
        3- Afegir rapero al JSON
             Opció
              a- Afegir només el rapero al array
              b- Eliminar array del json i tronar-los afegir tots
         */




        /* Llegenda return
        0 -> Dades correctes i guardat al JSON
        1 -> Nom artístic ja existeix
        2 -> Data neixament no valida
        3 -> País no existeix
        4 -> URL foto no correcte???
        */
        return 0;
    }

    public String nomGuanyador() {
        return "Pepito Grillo";
    }


    public int numFases(){
        return phases.size();
    }

    public int numParticipants(){
        return raperos.size();
    }

    public int faseActual(){
        return 2;
    }

    public boolean haAcabat(){
        LocalDate avui = LocalDate.now();
        if(avui.isAfter(endDate)){
            return true;
        }else{
            return false;
        }
    }

    public boolean haComencat(){
        LocalDate avui = LocalDate.now();
        if(avui.isAfter(startDate)){
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
