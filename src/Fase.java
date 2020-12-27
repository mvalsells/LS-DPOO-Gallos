import edu.salleurl.profile.Profileable;
import exceptions.RapperNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Esta clase nos permite controlar lo que sucede en cada fase, ya sea los raperos de ella, los temas, las batallas e incluso oendar la clasificacion.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 10/12/2020.
 */

public class Fase {

    //Campos de la clase
    private static ArrayList<Rapero> raperos;
    private ArrayList<Batalla> batalles;
    private int batallaActual;
    private String country;
    private float budget;

    /**
     * Constructor de Fase
     *
     * @param country   El parámetro country nos indica el pais donde se ejecuta la fase.
     * @param budget El parámetro budget nos indica cual es el presupuesto para esa fase.
     */
    public Fase(String country, float budget) {
        raperos = new ArrayList<>();
        batalles = new ArrayList<>();
        batallaActual = 0;
        this.country = country;
        this.budget = budget;
    }//Cierre del constructor

    //Getter and setters

    /**
     * Método que devuelve el nombre del rapero.
     *
     * @param i El parámetro i nos indica en que posición del array de raperos estamos situados.
     * @return El nombre del rapero.
     */

    public static String getNameRapper(int i) {
        return raperos.get(i).getStageName();

    }//Cierre del métodos

    /**
     * Método que devuelve la puntuacion de cada uno de los raperos.
     *
     * @param i El parámetro i indica en que posición del array raperos se situa, por tanto que rapero estamos viendo.
     * @return La puntuacion de cada uno de los raperos.
     */
    public static double getScoreRappers(int i) {
        return raperos.get(i).getPuntuacio();

    }//Cierre del método

    /**
     * Método que devuelve el número de participantes que tiene la competición.
     *
     * @return El número de participantes que tiene la competición.
     */

    public static int getNumParticipants() {
        return raperos.size();
    }//Cierre del método

    /**
     * Método que devuelve el nombre artístico del rapero.
     *
     * @param pos El parámetro pos nos indica en que posición del array de raperos estamos situados.
     * @return El nombre artístico del rapero.
     */

    public static String getStageNameRapero(int pos) {
        return raperos.get(pos).getStageName();
    }//Cierre del método

    /**
     * Método que devuelve el número de raperos que tiene la fase.
     */

    public static void setRapperos(ArrayList<Rapero> raperos) {
        Fase.raperos = raperos;
    }//Cierre del método

    /**
     * Ordena los raperos en función de su puntuación.
     */
    public static void ordenarRaperos() {
        Comparator<Rapero> compararPuntuacio = (Rapero r1, Rapero r2) -> (int) (r1.comparePuntuacio(r2) * 1000000);
        raperos.sort(compararPuntuacio.reversed());
    }//Cierre del método.

    /**
     * Método que añade el rapero que se ha registrado al array de raperos
     *
     * @param realName    El parámetro realName nos indica el nombre real del rapero
     * @param stageName   El parámetro stageName nos indica el nombre artístico del rapero.
     * @param birth       El parámetro birth nos indica la fecha de nacimiento del rapero.
     * @param nationality El parámetro nationality nos indica la nacionalidad del rapero.
     * @param level       El parámetro level indica cual es el level del usuario.
     * @param photo       El parámetro photo nos indica cual es la foto del rapero.
     */

    public static void afegirRapero(String realName, String stageName, String birth, String nationality, int level, String photo) {
        Rapero rapero = new Rapero(realName, stageName, birth, nationality, level, photo);
        raperos.add(rapero);
    }//Cierre del método

    /**
     * Método que comprueba que en el momento de hacer el login, el nombre puesto por pantalla exista.
     *
     * @param login El parámetro login indica el nombre del usuario.
     * @return El nombre puesto por pantalla existe o no.
     */

    public static boolean ferLogin(String login) {
        boolean existex = false;
        for (Rapero rapero : raperos) {
            if (login.equals(rapero.getStageName())) {
                existex = true;
                break;
            }
        }
        return existex;
    }//Cierre del método

    public static Profileable rapperProfile(String stageName) {
        boolean found = false;
        for (Rapero rapero : raperos){
            if (rapero.getStageName().equals(stageName)){
                return (Profileable) rapero.clone();
            }
        }
        //TODO throws rapper not found
        return null;
    }

    public static String[] infoProfile(String stageName) throws RapperNotFoundException {
        ordenarRaperos();
        for (int i=0; i<getNumParticipants(); i++) {
            if (raperos.get(i).getStageName().equals(stageName)){
                String[] infoProfile = new String[3];
                infoProfile[0] = Integer.toString((int)raperos.get(i).getPuntuacio()); //Puntuació
                infoProfile[1] = Integer.toString(i+1); //Posició
                //infoProfile.add(raperos.get(i).getBandera());
                infoProfile[2] = raperos.get(i).getCountryName(); //Nom Pais
                //infoProfile.addAll(raperos.get(i).getLanguages());
                return infoProfile;
            }
        }
        throw new RapperNotFoundException(stageName);
    }

    //Metodes

    /**
     * Método que devuelve la batalla actual en la que estamos, dependiendo de la fases actual en la que estemos.
     *
     * @return La batalla actual en la que estamos, dependiendo de la fase actual.
     */

    public int getBatallaActual() {
        return batallaActual;
    }//Cierre del método

    /**
     * Método que nos indica el numero de la batalla actual.
     *
     * @param batallaActual El parámetro batallaActual, indica que batalla es la actual.
     */

    public void setBatallaActual(int batallaActual) {
        this.batallaActual = batallaActual;
    }//Cierre del método

    //Fer login

    /**
     * Método que devuelve la puntuación del login.
     *
     * @param login El parámetro login indica el nombre del usuario
     * @return La puntuación del login
     */

    public double getPuntuacioRapero(String login) {
        int pos = -1;
        for (int i = 0; i < raperos.size(); i++) {
            if (raperos.get(i).getStageName().equals(login)) {
                pos = i;
                break;
            }
        }
        return raperos.get(pos).getPuntuacio();
    }//Cierre del método

    /**
     * Método que nos comprueba si el número de participantes es par o inpar, en el segundo caso, eliminará a uno de forma aleatoria( siempre que no sea el login ).
     *
     * @param login El parámetro login indica el nombre del usuario.
     */

    public void participantsParells(String login) {
        if (raperos.size() % 2 != 0) {
            Random rand = new Random();
            int posRandom = rand.nextInt(raperos.size() - 1);
            while (raperos.get(posRandom).getStageName().equals(login)) {
                posRandom = rand.nextInt(raperos.size() - 1);
            }
            raperos.remove(posRandom);
        }
    }//Cierre del método.

    /**
     * Método que nos envia los participantes que hay a la fase 1.
     *
     * @param login El parámetro login indica el nombre del usuario.
     */

    public void preFase1(String login) {
        participantsParells(login);
    }//Cierre del método

    /**
     * Método que nos ordena los participantes, elimina uno si es impar, elimina la mitad de los raperos que es lo que pasa antes de la fase 2
     * y lo envia a la fase 2.
     *
     * @param login El parámetro login indica el nombre del usuario.
     */

    public void preFase2(String login) {
        participantsParells(login);
        ordenarRaperos();
        //Eliminar la meitat dels participants
        int pos = raperos.size();
        int originalSize = raperos.size();
        while (pos >= (originalSize / 2)) {
            raperos.remove(pos - 1);
            pos--;
        }
        for (Rapero rapero : raperos) {
            rapero.setPuntuacio(0);
        }
    }//Cierre del método

    /**
     * Método que ordena los raperos y elimina a todos menos los dos con la mayor puntuación, luego lo envia a la fase 3.
     */

    public void preFase3() {
        ordenarRaperos();
        if (raperos.size() > 2) {
            raperos.subList(2, raperos.size()).clear();
        }
        for (Rapero rapero : raperos) {
            rapero.setPuntuacio(0);
        }
    }//Cierre del método

    /**
     * Método que ordena por puntación los raperos que llegan a la fase final, y envia al ganador, osea el rapero de la posición 0.
     *
     * @return El rapero de la posición 0.
     */

    public String winner() {
        ordenarRaperos();
        return raperos.get(0).getStageName();
    }//Cierre del método

    /**
     * Método que simula todas las batallas incluidas las del login determinando el tipo de batalla y creando las parejas.
     *
     * @param login El parámetro login indica el nombre artístico del rapero.
     * @return Todas las batallas incluidas las del login determinando el tipo de batalla y creando las parejas.
     */

    public String[] simularBatalles(String login) {
        Collections.shuffle(raperos);
        String[] info = new String[4];
        for (int i = 0; i < raperos.size(); i = i + 2) {
            Batalla batalla;
            Random rand = new Random();
            int posR1 = i;
            int posR2 = i + 1;

            //Si el que ha fet login està a la segona posició el canvio i el poso a la primera
            if (raperos.get(i + 1).getStageName().equals(login)) {
                posR1 = i + 1;
                posR2 = i;
            }

            //Crear batalla
            switch (rand.nextInt(3)) {
                case 0:
                    batalla = new Escrita(raperos.get(posR1), raperos.get(posR2));
                    info[1] = "Escrita";
                    break;
                case 1:
                    batalla = new Sangre(raperos.get(posR1), raperos.get(posR2));
                    info[1] = "Sangre";
                    break;
                default:
                    batalla = new Acapella(raperos.get(posR1), raperos.get(posR2));
                    info[1] = "Acapella";
                    break;
            }

            //Si hi ha el rapero login guardo info
            if (raperos.get(i).getStageName().equals(login)) {
                //Nom del contrincant
                info[0] = raperos.get(i + 1).getStageName();
                //Puntuacio del login
                info[2] = Double.toString(raperos.get(i).getPuntuacio());
                //Com que encara no he afegit la batalla al arraylist, la mida dle arraylist serà la posició de la nova batalla
                info[3] = Integer.toString(batalles.size());
            } else if (raperos.get(i + 1).getStageName().equals(login)) {
                //Nom del contrincant
                info[0] = raperos.get(i).getStageName();
                //Puntuacio del login
                info[2] = Double.toString(raperos.get(i + 1).getPuntuacio());
                //Com que encara no he afegit la batalla al arraylist, la mida dle arraylist serà la posició de la nova batalla
                info[3] = Integer.toString(batalles.size());
            } else {
                //Si no és el rapero de login, simula la batalla
                batalla.simularBatalla();
            }
            batalles.add(batalla);
        }
        return info;
        /*Array
        [0] -> Nom contrincant
        [1] -> Tipus de batalla
        [2] -> Puntuació usuari
        [3] -> Posició de la batalla usuari

         */
    }//Cierre del método

    /**
     * Método que nos devuelve la información del tema del cual el login y su rival están rapeando.
     *
     * @param battlePos El parámetro battlePos indica cual es la posición en el array de batallas en la que se situa esta.
     * @param temaPos   El parámetro temaPos indica cual es la posición en el array de temas en la que se situa esta.
     * @return La información del tema del cual el login y su rival estan rapeando.
     */

    public ArrayList<String> infoTema(int battlePos, int temaPos) {
        return this.batalles.get(battlePos).infoTema(temaPos);
    }//Cierre del método

    /**
     * Método que nos indica la información que se debe tratar en la batalla del useer contra su rival
     *
     * @param battlePos          El parámetro battlePos indica cual es la posición en el array de batallas en la que se situa esta.
     * @param estrofaLogin       El parámetro estrofaLogin indica cual es la estrofa del login.
     * @param estrofaContrincant El parámetro estrofaContrincant indica cual es la estrofa del contrincante.
     */

    public void ferBatalla(int battlePos, String estrofaLogin, String estrofaContrincant) {
        batalles.get(battlePos).ferBatalla(estrofaLogin, estrofaContrincant);
    }//Cierre del método

}//Cierre de la clase Fase
