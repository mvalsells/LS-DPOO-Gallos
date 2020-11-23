import java.io.FileNotFoundException;
import java.util.*;

public abstract class Batalla {
    //Atributs
    Rapero[] raperos = new Rapero[2];
    ArrayList<Tema> tema;
    Json json = new Json("src/competicio.json", "src/batalles.json");


    //Constructor
    public Batalla(Rapero rapero1, Rapero rapero2) throws FileNotFoundException {
        this.raperos[0]=rapero1;
        this.raperos[1]=rapero2;
        tema = json.llegirTema();
    }

    public abstract double puntuacio(int rimes);

    public int simularBatalla() throws FileNotFoundException {
         return 1;
    }


    public String treuTema(Rapero rapero1){
        String temaBatalla = new String();
        Collections.shuffle(tema);

        temaBatalla = tema.get(0).getNom();
        eleccioEstrofaR1(rapero1, tema.get(0).getEstrofesN1());
        return temaBatalla;
    }
    public String eleccioEstrofaR1(Rapero rapero1, ArrayList<String> estrofa1){
        String verso = new String();
        if(rapero1.getLevel()==1){
            Collections.shuffle(tema);
            verso = estrofa1.get(0);
        }


        return verso;

    }
    public int ferBatalla(){
        return 1;
    }

    public int numRimes(String vers){
        int numRimes=0;
        ArrayList<String> finalsLinies = new ArrayList<>();
        String[] split = vers.split(",");
        //Eliminem el punt de l'últim vers
        split[split.length-1] = split[split.length-1].replace(".","");
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
