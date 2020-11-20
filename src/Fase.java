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

    public Fase(float budget, Pais pais) {
        this.budget = budget;
        this.pais = pais;
    }

    //Metodes
    //Execute fase tipus1
    //Executa fase tipus 2
    //executa fase tipus 3

    public Integer[] aparellament(int numParticipants){
        participants = new Integer[numParticipants];
        for (int i=0; i < numParticipants; i++){
            participants[i]=i+1;
        }
        List<Integer> intList = Arrays.asList(participants);
        Collections.shuffle(intList);
        intList.toArray(participants);
        return participants;
    }
}
