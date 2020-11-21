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

    public void preFase1(String login){
        participantsParells(login);
        simularBatalles(login);
    }
    public void preFase2(String login){

    }
    public void preFase3(String login){

    }

    private void simularBatalles(String login){
        Collections.shuffle(raperos);
        int parellaUsuari=-1;
        int usuari = -1;
        for (int i=0; i<raperos.size();i=i+2){
            if (raperos.get(i).getStageName() == login){
                parellaUsuari = i+1;
                usuari = i;
            } else if (raperos.get(i+1).getStageName() == login){
                parellaUsuari = i;
                usuari = i+1;
            } else
                {
                Batalla batalla = new Batalla(raperos.get(i), raperos.get(i+1));
                batalla.simularBatalla(); //Falta mirar el tema gunyador/puntuacio
                batalles.add(batalla);
            }
        }
        Batalla batalla = new Batalla(raperos.get(usuari),raperos.get(parellaUsuari));
        batalla.ferBatalla(); //Falta mirar el tema gunyador/puntuacio
    }
}
