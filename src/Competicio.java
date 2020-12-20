import edu.salleurl.profile.Profileable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Esta clase nos permite registrar el usuario, controlar las prefases para saber en que fase estamos y a cual iremos, escribir en el json al igual que cojer sus datos,
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 10/12/2020.
 */
public class Competicio {

    //Campos de la clase
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<String> countries;
    private ArrayList<Fase> phases;
    private int faseActual;

    /**
     * Constructor de Competició
     *
     * @param name      El parámetro name nos indiaca el nombre de la competición
     * @param startDate El parámetro startDate nos indica la fecha de inicio de la competición.
     * @param endDate   El parámetro endDate nos indica la fecha de fin de la competición
     * @param countries El parámetro countries nos muestra el array de countries validas de la competición
     * @param phases    El parámetro phases nos muestra el array de phases que hay en la competición
     */

    public Competicio(String name, LocalDate startDate, LocalDate endDate, ArrayList<String> countries, ArrayList<Fase> phases) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.countries = countries;
        this.phases = phases;
        faseActual = 1;

    }//Cierre del método

    //Getters & Setters competicio

    /**
     * Método que devuelve el nombre de la competición.
     *
     * @return El nombre de la competición.
     */

    public String getName() {
        return name;
    }//Cierre del método

    /**
     * Método que devuelve el dia que empieza la competición.
     *
     * @return El dia que empieza la competición.
     */

    public LocalDate getStartDate() {
        return startDate;
    }//Cierre del método

    /**
     * Método que devuelve el dia que acaba la competición.
     *
     * @return El dia que acaba la competición.
     */

    public LocalDate getEndDate() {
        return endDate;
    }//Cierre del método

    /**
     * Método que devuelve el número de fases que tiene la competición.
     *
     * @return El número de fases que tiene la competición.
     */

    public int getNumFases() {
        return phases.size();
    }//Cierre del método

    /**
     * Método que devuelve el número de participantes que tiene la competición.
     *
     * @return El número de participantes que tiene la competición.
     */

    public int getNumParticipants() {
        return Fase.getNumParticipants();
    }//Cierre del método

    /**
     * Método que devuelve la fase actual en la que estamos.
     *
     * @return La fase actual en la que estamos.
     */

    public int getFaseActual() {
        return faseActual;
    }

    /**
     * Método que devuelve el número de fases, dependiendo del tamaño del array.
     *
     * @return El número de fases dependiendo del array.
     */

    public int numFases() {
        return phases.size();
    }//Cierre del método

    //Getters and Setter de Fase

    /**
     * Método que devuelve la batalla actual en la que estamos, dependiendo de la fases actual en la que estemos.
     *
     * @return La batalla actual en la que estamos, dependiendo de la fase actual.
     */

    public int getBatallaActual() {
        return phases.get(faseActual - 1).getBatallaActual();
    }//Cierre del método

    /**
     * Método que nos indica el numero de la batalla actual.
     *
     * @param num El parámetro num, indica que batalla es la actual.
     */

    public void setBatallaActual(int num) {
        phases.get(faseActual - 1).setBatallaActual(num);
    }//Cierre del método.

    /**
     * Método que devuelve la puntuación del login.
     *
     * @param login El parámetro login indica el nombre del usuario
     * @return La puntuación del login
     */
    public double getPuntuacioRapero(String login) {
        return phases.get(faseActual - 1).getPuntuacioRapero(login);
    }//Cierre del método

    /**
     * Método que devuelve el nombre del rapero.
     *
     * @param i El parámetro i indica en que posición del array raperos se situa, por tanto que rapero estamos viendo.
     * @return El nombre del rapero.
     */

    public String nameRapper(int i) {
        return Fase.getNameRapper(i);

    }//Cierro del método

    /**
     * Método que devuelve la puntuacion de cada uno de los raperos.
     *
     * @param i El parámetro i indica en que posición del array raperos se situa, por tanto que rapero estamos viendo.
     * @return La puntuacion de cada uno de los raperos.
     */

    public double getScoreRappers(int i) {
        return Fase.getScoreRappers(i);

    }//Cierre del método

    //Metodes

    /**
     * Método que nos permote registrar al usuario, comprobando si los datos son correctos o no
     *
     * @param realName    El parámetro realName nos indica el nombre real del rapero
     * @param stageName   El parámetro stageName nos indica el nombre artístico del rapero.
     * @param birth       El parámetro birth nos indica la fecha de nacimiento del rapero.
     * @param nationality El parámetro nationality nos indica la nacionalidad del rapero.
     * @param nivell      El parámetro nivell indica cual es el level del usuario.
     * @param photo       El parámetro photo nos indica cual es la foto del rapero.
     * @return Devuelve cual es el estado del registro.
     */
    public Boolean[] registreUsuari(String realName, String stageName, String birth, String nationality, String nivell, String photo) {
        Boolean[] estat = new Boolean[5];
        Arrays.fill(estat, true);
        int level=0;

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
        try {
             level = Integer.parseInt(nivell);
        }catch (NumberFormatException e){
            estat[4]=false;
        }

        for (boolean b : estat) {
            if (!b) {
                estat[3] = false;
                break;
            }
        }

        if (estat[3]) {
            //Creo rapero i el poso al arrayList
            Fase.afegirRapero(realName, stageName, birth, nationality, level, photo);
        }

        return estat;
        /* Llegenda return
        estat[0] -> Nom artístic OK/NO OK
        estat[1] -> Data neixament OK/NO OK
        estat[2] -> País acceptat OK/No OK
        estat[3] -> Totes les dades OK/NO OK
        */
    }//Cierre del método

    /**
     * Método que devuelve el login del usuario.
     *
     * @param login El parámetro login indica el nombre artístico del usuario.
     * @return El lógin del usuario.
     */

    public boolean ferLogin(String login) {
        return Fase.ferLogin(login);
    }//Cierre del método

    /**
     * Método que devuelve el nombre del ganador.
     *
     * @return El nombre del ganador.
     */

    public String nomGuanyador() {

        return phases.get(numFases() - 1).winner();
    }//Cierre del método

    /**
     * Método que devuelve si la competición ha acabdo o no.
     *
     * @return La competición ha acabdo o no.
     */

    public boolean haAcabat() {
        LocalDate avui = LocalDate.now();
        return avui.isAfter(endDate);
    }//Cierre de método

    /**
     * Método que devuelve si la competición ha empezado o no.
     *
     * @return La competición ha empezado o no.
     */

    public boolean haComencat() {
        LocalDate avui = LocalDate.now();
        return avui.isAfter(startDate);
    }//Cierre del método

    /**
     * Método que devuelve si la competición ha empezado o no.
     *
     * @return Estado de la competición.
     */

    public int estat() {
        if (!haComencat()) {
            return 0;
        } else if (!haAcabat()) {
            return 1;
        } else {
            return 2;
        }
    }//Cierre de método

    /**
     * Metodo que indica en que fase nos encontramos y su respectiva información.
     *
     * @param login El parámetro login indica el nombre artístico del rapero.
     * @throws IndexOutOfBoundsException El parámetro IndexOutOfBoundsException indica si hemos superado el número total de fases
     */

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

    }//Cierre del método

    /**
     * Método que nos indica si la fecha introducida es válida.
     *
     * @param bir El parámetro bir nos indica cual es el contenido de la fecha.
     * @return La fecha introducida es válida.
     */
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
    }//Cierre del método

    /**
     * Método que nos devuelve la informacion del tema del cual el login y su rival estan rapeando.
     *
     * @param battlePos El parámetro battlePos indica cual es la posición en el array de batallas en la que se situa esta.
     * @param temaPos   El parámetro temaPos indica cual es la posición en el array de temas en la que se situa esta.
     * @return La información del tema del cual el login y su rival estan rapeando.
     */

    public ArrayList<String> infoTema(int battlePos, int temaPos) {
        return phases.get(faseActual - 1).infoTema(battlePos, temaPos);
    }//Cierre del método

    /**
     * Método que nos indica la información que se debe tratar en la batalla del useer contra su rival
     *
     * @param battlePos          El parámetro battlePos indica cual es la posición en el array de batallas en la que se situa esta.
     * @param estrofaLogin       El parámetro estrofaLogin indica cual es la estrofa del login.
     * @param estrofaContrincant El parámetro estrofaContrincant indica cual es la estrofa del contrincante.
     */

    public void ferBatalla(int battlePos, String estrofaLogin, String estrofaContrincant) {
        phases.get(faseActual - 1).ferBatalla(battlePos, estrofaLogin, estrofaContrincant);
    }//Cierre del método

    /**
     * Incrementa la fase actual.
     */

    public void nextPhase() {
        faseActual++;
    }//Cierre del método

    /**
     * Ordena los raperos en función de su puntuación.
     */

    public void ordenarRaperos() {
        Fase.ordenarRaperos();
    }//Cierre del método

    /**
     * Método que simula todas las batallas incluidas las del login determinando el tipo de batalla y creando las parejas.
     *
     * @param login El parámetro login indica el nombre artístico del rapero.
     * @return Todas las batallas incluidas las del login determinando el tipo de batalla y creando las parejas.
     */

    public String[] simularBatalles(String login) {
        return phases.get(faseActual - 1).simularBatalles(login);
    }//Cierre del método

    public Profileable rapperProfile(String stageName) {
        return Fase.rapperProfile(stageName);
    }

    public String findRapper(String rapperName) {
        for (int i=0; i<Fase.getNumParticipants(); i++){
            if (Fase.getNameRapper(i).equals(rapperName)){
                return Fase.getStageNameRapero(i);
            }
            if (Fase.getStageNameRapero(i).equals(rapperName)) {
                return rapperName;
            }
        }
        //TODO fer amb una exception
        return "";
    }

    public ArrayList<String> infoProfile(String stageName) {
        return Fase.infoProfile(stageName);
    }
}//Cierre de la clase Competicio