import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    //Execute fase tipus1
    //Executa fase tipus 2
    //executa fase tipus 3

    public Integer[] aparellament(int numParticipants){
        participants = new Integer[numParticipants];
        for (int i=0; i < numParticipants; i++){
            participants[i]=i;
        }
        List<Integer> intList = Arrays.asList(participants);
        Collections.shuffle(intList);
        intList.toArray(participants);
        return participants;
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

    public Integer preFase1(String login) throws FileNotFoundException {
        Integer contrincant;
        participantsParells(login);
        contrincant = simularBatalles(login)[1];
        return contrincant;
    }
    public void preFase2(String login){

    }
    public void preFase3(String login){

    }

    private Integer[] simularBatalles(String login) throws FileNotFoundException {
        Collections.shuffle(raperos);
        Integer [] usuariIparella = new Integer[2]; //Posició 0 -> Usuari; Posicio 1 -> Parella
        usuariIparella[0]=-1;
        usuariIparella[1] = -1;
        for (int i=0; i<raperos.size();i=i+2){
            if (raperos.get(i).getStageName().equals(login)){
                usuariIparella[1] = i+1;
                usuariIparella[0] = i;
            } else if (raperos.get(i + 1).getStageName().equals(login)){
                usuariIparella[1] = i;
                usuariIparella[0] = i+1;
            } else
                {

                Batalla batalla = new Batalla(raperos.get(i), raperos.get(i+1));
                batalla.simularBatalla(); //Falta mirar el tema gunyador/puntuacio
                batalles.add(batalla);
            }
        }
       return  usuariIparella;
    }
}
