import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Batalla {
    //Atributs
    Rapero[] raperos = new Rapero[2];
    Tema[] tema = new Tema[2];
    Json json = new Json("src/competicio.json", "src/batalles.json");


    //Constructor
    public Batalla(Rapero rapero1, Rapero rapero2) throws FileNotFoundException {
        this.raperos[0] = rapero1;
        this.raperos[1] = rapero2;
        ArrayList<Tema> temes = json.llegirTema();
        Collections.shuffle(temes);
        tema[0] = temes.get(0);
        tema[1] = temes.get(1);
    }

    public abstract double puntuacio(int numRimes);

    public void simularBatalla() throws FileNotFoundException {


        for (int i = 0; i < tema.length; i++) {

            int posicioNivell1 = 0;
            int posicioNivell2 = 0;

            for (int j = 0; j < raperos.length; j++) {

                int nivellRapero = raperos[j].getLevel();
                double puntuacio = 0;

                String estrofa = eleccioEstrofa(nivellRapero, tema[i], posicioNivell1, posicioNivell1);
                if (estrofa.length() == 0) {
                    puntuacio = 0;
                    break;
                } else {
                    if (nivellRapero == 1) {
                        posicioNivell1++;
                    } else {
                        posicioNivell2++;
                    }
                    int rimes = numRimes(estrofa);
                    puntuacio = puntuacio(rimes);
                }


                raperos[j].setPuntuacio(raperos[j].getPuntuacio() + puntuacio);

            }

        }

        System.out.println("hola");
    }

    public String eleccioEstrofa(int nivell, Tema tema, int posicioNivell1, int posicioNivell2) {
        String verso = "";

        try {
            if (nivell == 1) {

                //Collections.shuffle(tema.getEstrofesN1());
                verso = tema.getEstrofesN1().get(posicioNivell1);
            } else {
                //Collections.shuffle(tema.getEstrofesN2());
                verso = tema.getEstrofesN2().get(posicioNivell2);
            }
            return verso;
        } catch (IndexOutOfBoundsException exception) {
            return "";
        }

    }


    public int ferBatalla() {

        // igual que simular batalka pero en el for en comptes de agafar estrofes aleatories
        //he de agafar per pantalla
        return 1;
    }

    public int numRimes(String vers) {
        int numRimes = 0;
        ArrayList<String> finalsLinies = new ArrayList<>();
        String[] split = vers.split(",");
        //Eliminem el punt de l'últim vers
        split[split.length - 1] = split[split.length - 1].replace(".", "");
        //Obtenir tots els finals de linies
        for (String linia : split) {
            String ultimesLletres = linia.substring(linia.length() - 2);
            finalsLinies.add(ultimesLletres);
        }
        //Agafo els finals unics
        Set<String> set = new HashSet<>(finalsLinies);
        ArrayList<String> finalsLiniesUnics = new ArrayList<>(set);
        //Per cada final unics
        for (String finalLiniaUnic : finalsLiniesUnics) {
            int tmp = 0;
            //Miro quants cops existeix en total
            for (String finalLinia : finalsLinies) {
                if (finalLinia.equals(finalLiniaUnic)) {
                    tmp++;
                }
            }
            //si és més de 1 vol dir que hi ha rima
            if (tmp > 1) {
                numRimes += tmp;
            }
        }
        return numRimes;
    }
}
