import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Competicio {

    //Atributs
    //private JsonObject competition;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<String> countries;
    private ArrayList<Fase> phases;
    private ArrayList<Rapero> raperos;
    private ArrayList<Tema> temes;
    private JsonObject data;
    private Json json = new Json("src/competicio.json", "src/batalles.json");
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
    public int registreUsuari(String realName, String stageName, String birth, String nationality, int level, String photo, float puntuacio) throws IOException {
        int estat;

        //Comprovo si ja hi ha el rappero
        boolean existeixR = false;
        for (Rapero rapero : raperos) {
            if (stageName.equals(rapero.getStageName())) {
                existeixR = true;
                break;
            }
        }

        if (existeixR) {
            estat = 1;
        } else {
            //Comprobo que la data de neixament no sigui més tard que avui
            LocalDate avui = LocalDate.now();
            if (avui.isBefore(LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
                estat = 2;
            } else {
                //Comprobo si existeix el pais
                boolean existeixP = false;
                for (String pais : countries) {
                    if (nationality.equals(pais)) {
                        existeixP = true;
                        break;
                    }
                }
                if (!existeixP) {
                    estat = 3;
                } else {
                    estat = 0;
                }
            }
        }

        //Si dades rapero OK
        if (estat == 0) {
            //Creo rapero i el poso al arrayList
            Rapero rapero = new Rapero(realName, stageName, birth, nationality, level, photo, puntuacio);
            raperos.add(rapero);

            //Afegir el rapero al JSON
            json.escriureRapero(countries, raperos);
        }

        return estat;
        /* Llegenda return
        0 -> Dades correctes i guardat al JSON
        1 -> Nom artístic ja existeix
        2 -> Data neixament no valida
        3 -> País no existeix
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
        if (avui.isAfter(endDate)) {
            return true;
        } else {
            return false;
        }
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
        if (avui.isAfter(startDate)) {
            return true;
        } else {
            return false;
        }
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
        String rival = new String();
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
}