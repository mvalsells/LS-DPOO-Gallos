import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Batalla {
    //Atributs
    Rapero[] raperos = new Rapero[2];
    Tema tema;
    Json json = new Json("src/competicio.json", "src/batalles.json");


    //Constructor
    public Batalla(Rapero rapero1, Rapero rapero2) throws FileNotFoundException {
        this.raperos[0]=rapero1;
        this.raperos[1]=rapero2;
        tema = json.llegirTema();
    }

    public Float[] puntuacio(int Rimes){
        Float[] puntuacio = new Float[2];
        return puntuacio;
    };

    public int simularBatalla() throws FileNotFoundException {
        Random rand = new Random();
        int tipus = rand.nextInt(3);
        switch (tipus){
            case 0:
                //Acapella
                Acapella acapella = new Acapella(this.raperos[0],this.raperos[1]);

                break;
            case 1:
                //Sangre
                break;
            case 2:
                //Escrita
                break;
        }
        //int pR1 = raperos[1].getPuntuacio() + 4040;
        //raperos[1].setPuntuacio(pR1);
        return 1;
    }
    public int ferBatalla(){
        return 1;
    }

    public int numRimes(String vers){
        int numRimes=0;
        ArrayList<String> finalsLinies = new ArrayList<>();
        String[] split = vers.split(",");
        //Obtenir tots els finals de linies
        for (String linia : split){
            String ultimesLletres = linia.substring(linia.length()-2,linia.length());
            finalsLinies.add(ultimesLletres);
        }
        //Agafo els finals unics
        Set<String> set = new HashSet<>(finalsLinies);
        ArrayList<String> finalsLiniesUnics = new ArrayList<>(set);
        //Per cada final unics
        for (String finalLiniaUnic : finalsLiniesUnics){
            int tmp=0;
            //Miro quants cops existeix en total
            for (String finalLinia : finalsLinies){
                if (finalLinia.equals(finalLiniaUnic)){
                    tmp++;
                }
            }
            //si és més de 1 vol dir que hi ha rima
            if (tmp>1){
                numRimes+=tmp;
            }
        }
        return numRimes;
    }
}
