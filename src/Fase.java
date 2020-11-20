import java.util.ArrayList;

public class Fase {
    //Atributs
    Pais pais;
    float budget;
    ArrayList<Batalla> batalles = new ArrayList<>();

    public Fase(float budget, Pais pais) {
        this.budget = budget;
        this.pais = pais;
    }

    public ArrayList<String> aparellament(Competicio competicio, String login){
        ArrayList<String> rival = new ArrayList<String>();
        int random1 = 0;
        int random2 = 0;
        for(int i=0; i< competicio.treureParticipants(login); i = i+2){

            random1 = (int) (Math.random() * competicio.treureParticipants(login));
            random2 = (int) (Math.random() * competicio.treureParticipants(login));


            //rival.get(i) = (random1, random2);
            //rival.add(competicio.getRappers().get(random1));||||||||||||algo falla i no se el que
        }


        return rival;
    }

}
