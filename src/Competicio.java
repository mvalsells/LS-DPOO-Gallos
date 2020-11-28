import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Competicio {

    //Atributs
    //private JsonObject competition;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final ArrayList<String> countries;
    private final ArrayList<Fase> phases;
    private final ArrayList<Rapero> raperos;
    private ArrayList<Tema> temes;
    private final JsonObject data;
    private final Json json = new Json("src/competicio.json", "src/batalles.json");
    private String rappers;
    private int faseActual;

    //Constructor
    public Competicio(String name, LocalDate startDate, LocalDate endDate, ArrayList<String> countries, ArrayList<Fase> phases, ArrayList<Rapero> raperos, JsonObject data) throws FileNotFoundException {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.countries = countries;
        this.phases = phases;
        this.raperos = raperos;
        this.data = data;
        faseActual = 1;

    }


    //Getters & Setters
    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getNumFases() {
        return phases.size();
    }

    public int getNumParticipants() {
        return raperos.size();
    }

    public int getFaseActual() {
        return faseActual;
    }

    //Metodes
    public Boolean[] registreUsuari(String realName, String stageName, String birth, String nationality, int level, String photo, float puntuacio) throws IOException {
        Boolean[] estat = new Boolean[4];
        for (int i = 0; i<estat.length; i++) {
            estat[i] = true;
        }

        //Comprovo si ja hi ha el rappero
        for (Rapero rapero : raperos) {
            if (stageName.equals(rapero.getStageName())) {
                estat[0] = false;
                break;
            }
        }

        //Comprobo que la data de neixament no sigui valida
        //Miro que s'hagi introduit en el format correcte
        if (validarFecha(birth)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthLocalDate = LocalDate.parse(birth, formatter);
            String birthOK = birthLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate avui = LocalDate.now();
            //Miro si la data de neixament és posterior a avui
            if (avui.isBefore(LocalDate.parse(birthOK, DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
                estat[1] = false;
            }
        } else {
            estat[1] = false;
        }


        //Comprobo si existeix el pais
        estat[2] = false;
        for (String pais : countries) {
            if (nationality.equals(pais)) {
                estat[2] = true;
                break;
            }
        }

        for (boolean b : estat) {
            if (!b) {
                estat[3] = false;
                break;
            }
        }

        if (estat[3]) {
            //Creo rapero i el poso al arrayList
            Rapero rapero = new Rapero(realName, stageName, birth, nationality, level, photo, puntuacio);
            raperos.add(rapero);

            //Afegir el rapero al JSON
            json.escriureRapero(countries, raperos);
        }

        return estat;
        /* Llegenda return
        estat[0] -> Nom artístic OK/NO OK
        estat[1] -> Data neixament OK/NO OK
        estat[2] -> País acceptat OK/No OK
        estat[3] -> Totes les dades OK/NO OK
        */
    }

    public boolean ferLogin(String login) {
        boolean existex = false;
        for (Rapero rapero : raperos) {
            if (login.equals(rapero.getStageName())) {
                existex = true;
                break;
            }
        }
        return existex;
    }

    public String nomGuanyador() {
        return "Pepito Grillo";
    }


    public int numFases() {
        return phases.size();
    }

    public int numParticipants() {
        return raperos.size();
    }


    public boolean haAcabat() {
        LocalDate avui = LocalDate.now();
        return avui.isAfter(endDate);
    }

    /* //Ho HE PASSAT A FASES -> a l'espra del tema raperos i fases
    public void participantsParells(String login) {
             if (numParticipants() % 2 != 0) {
                int random = (int) (Math.random() * raperos.size());
                while (raperos.get(random).getStageName().equals(login)) {
                    random = (int) (Math.random() * raperos.size());
                }
                raperos.remove(random);
            }
    }*/

    @Override
    public String toString() {
        return "Competicio{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", countries=" + countries +
                ", phases=" + phases +
                ", raperos=" + raperos +
                ", temes=" + temes +
                ", json=" + json +
                '}';
    }

    public boolean haComencat() {
        LocalDate avui = LocalDate.now();
        return avui.isAfter(startDate);
    }

    public int estat() {
        if (!haComencat()) {
            return 0;
        } else if (!haAcabat()) {
            return 1;
        } else {
            return 2;
        }
    }

    public void seguentFase() {
        faseActual++;
    }


    public String preFase(String login) throws FileNotFoundException {
        String rival = "";
        int contrincant;
        if (numFases() == 3) {
            // 3 Fases
            switch (faseActual) {
                case 1:
                    contrincant = phases.get(0).preFase1(login);
                    rival = raperos.get(contrincant).getStageName();
                    break;
                case 2:
                    phases.get(1).preFase2(login);
                    break;
                case 3:
                    phases.get(2).preFase3(login);
                    break;
                default:
                    System.out.println("Numero de fase no és 1, 2 o 3");
                    break;
            }
        } else {
            // 2 Fases
            switch (faseActual) {
                case 1:
                    phases.get(0).preFase1(login);
                    break;
                case 2:
                    phases.get(1).preFase3(login);
                    break;
                default:
                    System.out.println("Numero de fase no és 1 o 2");
                    break;
            }
        }
        return rival;
    }

    public boolean validarFecha(String bir) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            //Date dataok;
            formatoFecha.setLenient(false);
            formatoFecha.parse(bir);
            if(bir.length() != 10){
                return false;
            }

        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}