import java.io.FileNotFoundException;
import java.util.*;

public class Fase {
    //Atributs
    Pais pais;
    float budget;
    ArrayList<Batalla> batalles = new ArrayList<>();
    Integer[] participants;
    ArrayList<Rapero> raperos;

    public Fase(float budget, Pais pais) {
        this.budget = budget;
        this.pais = pais;
        raperos = new ArrayList<>();
        batalles = new ArrayList<>();
    }

    public void setRapperos(ArrayList<Rapero> raperos) {
        this.raperos = raperos;
    }

    //Metodes

    public void participantsParells(String login) {
        if (raperos.size() % 2 != 0) {
            int random = (int) (Math.random() * raperos.size());
            while (raperos.get(random).getStageName().equals(login)) {
                random = (int) (Math.random() * raperos.size());
            }
            raperos.remove(random);
        }
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

    public void preFase2(String login) {

    }

    public void preFase3(String login) {

    }

    private String[] simularBatalles(String login) throws FileNotFoundException {
        Collections.shuffle(raperos);
        Integer[] usuariIparella = new Integer[2]; //Posició 0 -> Usuari; Posicio 1 -> Parella
        String[] info = new String[4];
        for (int i = 0; i < raperos.size(); i = i + 2) {
            Batalla batalla;
            Random rand = new Random();
            int tipus = rand.nextInt(3);

            //Miro si la posició actual i la següent hi ha el usuari que ha fet login
            if (raperos.get(i).getStageName().equals(login)) {
                switch (tipus) {
                    case 0:
                        batalla = new Escrita(raperos.get(i), raperos.get(i + 1));
                        info[1] = "Escrita";
                        break;
                    case 1:
                        batalla = new Sangre(raperos.get(i), raperos.get(i + 1));
                        info[1] = "Sangre";
                        break;
                    default:
                        batalla = new Acapella(raperos.get(i), raperos.get(i + 1));
                        info[1] = "Acapella";
                        break;
                }
                //Guardo nom del contrincant
                info[0] = raperos.get(i+1).getStageName();
                //Guardo puntuacio del login
                info[2] = Double.toString(raperos.get(i).getPuntuacio());
                //Guardo posició de la batalla al array
                info[3] = Integer.toString(i);
            } else if (raperos.get(i + 1).getStageName().equals(login)) {
                switch (tipus) {
                    case 0:
                        batalla = new Escrita(raperos.get(i+1), raperos.get(i));
                        info[1] = "Escrita";
                        break;
                    case 1:
                        batalla = new Sangre(raperos.get(i+1), raperos.get(i));
                        info[1] = "Sangre";
                        break;
                    default:
                        batalla = new Acapella(raperos.get(i+1), raperos.get(i));
                        info[1] = "Acapella";
                        break;
                }
                //Guardo nom del contrincant
                info[0] = raperos.get(i).getStageName();
                //Guardo puntuacio del login
                info[2] = Double.toString(raperos.get(i+1).getPuntuacio());
                //Guardo posició de la batalla al array
                info[3] = Integer.toString(i);
            } else {
                switch (tipus) {
                    case 0:
                        batalla = new Escrita(raperos.get(i), raperos.get(i + 1));
                        info[1] = "Escrita";
                        break;
                    case 1:
                        batalla = new Sangre(raperos.get(i), raperos.get(i + 1));
                        info[1] = "Sangre";
                        break;
                    default:
                        batalla = new Acapella(raperos.get(i), raperos.get(i + 1));
                        info[1] = "Acapella";
                        break;
                }
                batalla.simularBatalla();
            }
            batalles.add(batalla);
        }
        return info;
        /*Array
        [0] -> Nom contrincant
        [1] -> Tipus de batalls
        [2] -> Puntuació usuari
        [3] -> Posició de la batalla usuari
         */
    }

    public String infoTema(int battlePos, int temaPos) {

        return batalles.get(battlePos).infoTema(temaPos);
    }
}
