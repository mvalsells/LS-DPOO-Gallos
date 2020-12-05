import java.io.FileNotFoundException;
import java.util.*;

public class Fase {
    //Atributs
    private Pais pais;
    private float budget;
    private ArrayList<Batalla> batalles;
    private static ArrayList<Rapero> raperos;
    private int batallaActual;


    public Fase(float budget, Pais pais) {
        this.budget = budget;
        this.pais = pais;
        raperos = new ArrayList<>();
        batalles = new ArrayList<>();
        batallaActual=0;
    }

    public void setRapperos(ArrayList<Rapero> raperos) {
        this.raperos = raperos;
    }

    public ArrayList<Batalla> getBatalles() {
        return batalles;
    }

    public void setBatalles(ArrayList<Batalla> batalles) {
        this.batalles = batalles;
    }




    //Metodes

    public static void ordenarRaperos(){
        Comparator<Rapero> compararPuntuacio = (Rapero r1, Rapero r2) -> (int) r1.comparePuntuacio(r2)*1000;
        Collections.sort(raperos, compararPuntuacio.reversed());
    }


    public void participantsParells(String login) {
        if (raperos.size() % 2 != 0) {
            int random = (int) (Math.random() * raperos.size());
            while (raperos.get(random).getStageName().equals(login)) {
                random = (int) (Math.random() * raperos.size());
            }
            raperos.remove(random);
        }
    }

    public static String getNameRapper(int i){
        return raperos.get(i).getStageName();

    }
    public static double getScoreRappers(int i){
        return raperos.get(i).getPuntuacio();

    }

    
    
    //Getters and setters


    public int getBatallaActual() {
        return batallaActual;
    }

    public void setBatallaActual(int batallaActual) {
        this.batallaActual = batallaActual;
    }

    public ArrayList<Rapero> getRaperos() {
        return raperos;
    }
    public void setRaperos(ArrayList<Rapero> raperos){
        this.raperos=raperos;
    }

    public static int getNumParticipants(){
        return raperos.size();
    }

    //Getters and setters de raperos
    public double getPuntuacioRapero(String login){
        int pos=-1;
        for (int i=0; i<raperos.size(); i++){
           if (raperos.get(i).getStageName().equals(login)){
               pos = i;
               break;
           }
        }
        return raperos.get(pos).getPuntuacio();
    }

    public static String getStageNameRapero(int pos){
        return raperos.get(pos).getStageName();
    }

    //Metodes

    public static void afegirRapero(String realName, String stageName, String birth, String nationality, int level, String photo, float puntuacio){
        Rapero rapero = new Rapero(realName, stageName, birth, nationality, level, photo, puntuacio);
        raperos.add(rapero);
    }

    //Fer login
    public static boolean ferLogin(String login) {
        boolean existex = false;
        for (Rapero rapero : raperos) {
            if (login.equals(rapero.getStageName())) {
                existex = true;
                break;
            }
        }
        return existex;
    }

    public String[] preFase1(String login) throws FileNotFoundException {
        participantsParells(login);
        String[] info = simularBatalles(login);
        return info;
        /*Array
        [0] -> Nom contrincant
        [1] -> Tipus de batalls
        [2] -> Puntuació usuari
        [3] -> Posició de la batalla usuari
         */
    }

    public String[] preFase2(String login) throws FileNotFoundException {
        participantsParells(login);
        ordenarRaperos();
        //Eliminar la meitat dels participants
        int pos=raperos.size();
        int originalSize= raperos.size();
        while (pos>=(originalSize/2)){
            raperos.remove(pos-1);
            pos--;
        }
        String[] info = simularBatalles(login);
        return info;
    }

    public void preFase3(String login) {
        participantsParells(login);
        ordenarRaperos();
        for (int i = raperos.size()-1; i > 1 ; i--) {
            raperos.remove(i);
        }

    }

    private String[] simularBatalles(String login) throws FileNotFoundException {
        Collections.shuffle(raperos);
        String[] info = new String[4];
        for (int i = 0; i < raperos.size(); i = i + 2) {
            Batalla batalla;
            Random rand = new Random();
            int posR1=i;
            int posR2=i+1;

            //Si el que ha fet login està a la segona posició el canvio i el poso a la primera
            if(raperos.get(i + 1).getStageName().equals(login)) {
                posR1=i+1;
                posR2=i;
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
                info[0] = raperos.get(i+1).getStageName();
                //Puntuacio del login
                info[2] = Double.toString(raperos.get(i).getPuntuacio());
                //Com que encara no he afegit la batalla al arraylist, la mida dle arraylist serà la posició de la nova batalla
                info[3] = Integer.toString(batalles.size());
            } else if (raperos.get(i + 1).getStageName().equals(login)) {
                //Nom del contrincant
                info[0] = raperos.get(i).getStageName();
                //Puntuacio del login
                info[2] = Double.toString(raperos.get(i+1).getPuntuacio());
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
    }

    public ArrayList<String> infoTema(int battlePos, int temaPos) {
        return this.batalles.get(battlePos).infoTema(temaPos);
    }

    public void ferBatalla(int battlePos, String estrofaLogin, String estrofaContrincant) {
        batalles.get(battlePos).ferBatalla(estrofaLogin, estrofaContrincant);
    }
}
