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
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final ArrayList<String> countries;
    private final ArrayList<Fase> phases;
    private final JsonObject data;
    private final Json json;
    private int faseActual;

    //Constructor
    public Competicio(String name, LocalDate startDate, LocalDate endDate, ArrayList<String> countries, ArrayList<Fase> phases, ArrayList<Rapero> raperos, JsonObject data) throws FileNotFoundException {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.countries = countries;
        this.phases = phases;
        this.data = data;
        faseActual = 1;
        json = new Json("src/competicio.json", "src/batalles.json");

    }


    //Getters & Setters competicio
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
        return Fase.getNumParticipants();
    }

    public int getFaseActual() {
        return faseActual;
    }

    public int numFases() {
        return phases.size();
    }

    //Getters and Setter de Fase
    public int getBatallaActual() {
        return phases.get(faseActual - 1).getBatallaActual();
    }

    public void setBatallaActual(int num) {
        phases.get(faseActual - 1).setBatallaActual(num);
    }

    public double getPuntuacioRapero(String login) {
        return phases.get(faseActual - 1).getPuntuacioRapero(login);
    }

    public String nameRapper(int i) {
        return Fase.getNameRapper(i);

    }

    public double getScoreRappers(int i) {
        return Fase.getScoreRappers(i);

    }

    //Metodes
    public Boolean[] registreUsuari(String realName, String stageName, String birth, String nationality, int level, String photo, float puntuacio) throws IOException {
        Boolean[] estat = new Boolean[4];
        for (int i = 0; i < estat.length; i++) {
            estat[i] = true;
        }

        //Comprovo si ja hi ha el rappero
        for (int i = 0; i < Fase.getNumParticipants(); i++) {
            if (stageName.equals(Fase.getStageNameRapero(i))) {
                estat[0] = false;
                break;
            }
        }

        //Comprobo que la data de neixament no sigui valida
        //Miro que s'hagi introduit en el format correcte
        if (validarFecha(birth)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthLocalDate = LocalDate.parse(birth, formatter);
            birth = birthLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate avui = LocalDate.now();
            //Miro si la data de neixament és posterior a avui
            if (avui.isBefore(LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
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
            // TODO mirar puntuacio
            Fase.afegirRapero(realName, stageName, birth, nationality, level, photo, puntuacio);

            //Afegir el rapero al JSON
            //TODO Reescriure el JSON

            json.escriureRapero(realName, stageName, birth, nationality, level, photo);
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
        return Fase.ferLogin(login);
    }

    public String nomGuanyador() {

        return phases.get(numFases()-1).winner();
    }

    public boolean haAcabat() {
        LocalDate avui = LocalDate.now();
        return avui.isAfter(endDate);
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

    public void preFase(String login) throws IndexOutOfBoundsException {
        if (numFases() == 3) {
            // 3 Fases
            switch (faseActual - 1) {
                case 0:
                    phases.get(0).preFase1(login);
                    break;
                case 1:
                    phases.get(1).preFase2(login);
                    break;
                case 2:
                    phases.get(2).preFase3();
                    break;
                default:
                    System.err.println("Numero de fase no és 1, 2 o 3");
                    break;
            }
        } else {
            // 2 Fases
            switch (faseActual - 1) {
                case 0:
                    phases.get(0).preFase1(login);
                    break;
                case 1:
                    phases.get(1).preFase3();
                    break;
                default:
                    System.err.println("Numero de fase no és 1 o 2");
                    break;
            }
        }

    }

    public boolean validarFecha(String bir) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            //Date dataok;
            formatoFecha.setLenient(false);
            formatoFecha.parse(bir);
            if (bir.length() != 10) {
                return false;
            }

        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public ArrayList<String> infoTema(int battlePos, int temaPos) {
        return phases.get(faseActual - 1).infoTema(battlePos, temaPos);
    }


    public void ferBatalla(int battlePos, String estrofaLogin, String estrofaContrincant) {
        phases.get(faseActual - 1).ferBatalla(battlePos, estrofaLogin, estrofaContrincant);
    }

    public void nextPhase() {
        faseActual++;
    }

    public void ordenarRaperos() {
        Fase.ordenarRaperos();
    }

    public String[] simularBatalles(String login) {
        return phases.get(faseActual - 1).simularBatalles(login);
    }
}