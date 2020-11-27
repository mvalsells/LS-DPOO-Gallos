import java.io.FileNotFoundException;
import java.util.*;

public abstract class Batalla {
    //Atributs
    Rapero[] raperos = new Rapero[2];
    Tema tema;
    Json json = new Json("src/competicio.json", "src/batalles.json");


    //Constructor
    public Batalla(Rapero rapero1, Rapero rapero2) throws FileNotFoundException {
        this.raperos[0]=rapero1;
        this.raperos[1]=rapero2;
        ArrayList<Tema> temes = json.llegirTema();
        Collections.shuffle(temes);
        tema = temes.get(0);
    }

    public abstract double puntuacio(int numRimes);

    public int simularBatalla() throws FileNotFoundException {
        String estrofaR1 = eleccioEstrofa(raperos[0],tema);
        int rimesR1 = numRimes(estrofaR1);
        String estrofaR2 = eleccioEstrofa(raperos[1],tema);
        int rimesR2 = numRimes(estrofaR2);
        double puntuacioR1 = puntuacio(rimesR1);
        double puntuacioR2 = puntuacio(rimesR2);



        return 1;
    }


    /*public String treuTema(Rapero rapero1, Rapero rapero2){
        String temaBatalla = new String();

        temaBatalla = tema.getNom();
        eleccioEstrofaR1(rapero1, tema.getEstrofesN1(), tema.getEstrofesN2());
        //eleccioEstrofaR2(rapero2, tema.get(0).getEstrofesN1(), tema.get(0).getEstrofesN2());
        return temaBatalla;
    }*/
    public String eleccioEstrofa(Rapero rapero, Tema tema){
        String verso = new String();
        if(rapero.getLevel()==1){
            Collections.shuffle(tema.getEstrofesN1());
            verso = tema.getEstrofesN1().get(0);
        }else{
            Collections.shuffle(tema.getEstrofesN2());
            verso = tema.getEstrofesN2().get(0);
        }
       return verso;
    }

    /* !!!!!!!!!!!!!!!!!!!!Codi eleccioestrofar2, estic pensant en una especie de bucle perque primer faci la estrofa del rapero 1, calculi la puntuacio, i despres
    faci el mateix amb el de rapero 2

     */
   /* public void eleccioEstrofaR2(Rapero rapero2, ArrayList<String> estrofa1, ArrayList<String> estrofa2){
        String verso = new String();
        if(rapero2.getLevel()==1){
            Collections.shuffle(estrofa1);
            verso = estrofa1.get(0);
        }else{
            Collections.shuffle(estrofa2);
            verso = estrofa2.get(0);
        }
        numRimes(verso);

    }*/



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
