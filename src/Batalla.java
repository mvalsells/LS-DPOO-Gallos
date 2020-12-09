import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Batalla {
    //Atributs
    private final Rapero[] raperos = new Rapero[2];
    private final Tema[] temas = new Tema[2];
    private final Json json = new Json("src/competicio.json", "src/batalles.json");


    //Constructor
    public Batalla(Rapero rapero1, Rapero rapero2) {
        this.raperos[0] = rapero1;
        this.raperos[1] = rapero2;
        ArrayList<Tema> temes = json.llegirTema();
        Collections.shuffle(temes);
        temas[0] = temes.get(0);
        temas[1] = temes.get(1);
    }

    public abstract double puntuacio(int numRimes);

    public void simularBatalla() {
        //Per cada tema
        for (Tema tema : temas) {

            int posicioNivell1 = 0;
            int posicioNivell2 = 0;

            //Per cada rapero
            for (Rapero rapero : raperos) {

                //Obtenim el nivell del rapero
                int nivellRapero = rapero.getLevel();
                double puntuacio;

                //Obtenim la estrofa
                String estrofa = eleccioEstrofa(nivellRapero, tema, posicioNivell1, posicioNivell2);
                if (estrofa.length() == 0) {
                    //Si la estrofa és buida, el rapero fa el ridicul i s'acaba la batalla
                    break;
                } else {
                    //Canviem la posició de l'array d'estrofes
                    if (nivellRapero == 1) {
                        posicioNivell1++;
                    } else {
                        posicioNivell2++;
                    }

                    //Calculem les rimes i la puntuació
                    int rimes = numRimes(estrofa);
                    puntuacio = puntuacio(rimes);


                }

                //Actualitzem la puntuació
                rapero.setPuntuacio(rapero.getPuntuacio() + puntuacio);
            }
        }
    }

    public String eleccioEstrofa(int nivell, Tema tema, int posicioNivell1, int posicioNivell2) {
        String verso;
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


    public void ferBatalla(String estrofaLogin, String estrofaContrincant) {
        double puntuacio = 0;
        //Si el Login no fa el ridicul calculem les rimes i la puntuació
        if (!(estrofaLogin.length() == 0)) {

                int rimes = numRimes(estrofaLogin);
                puntuacio = puntuacio(rimes);

        }
        //Actualitzem la puntuació
        raperos[0].setPuntuacio(raperos[0].getPuntuacio() + puntuacio);

        puntuacio = 0;
        //Si el contrincant no fa el ridicul calculem les rimes i la puntuació
        if (!(estrofaContrincant.length() == 0)) {
                int rimes = numRimes(estrofaLogin);
                puntuacio = puntuacio(rimes);


        }
        //Actualitzem la puntuació
        raperos[1].setPuntuacio(raperos[1].getPuntuacio() + puntuacio);
    }

    public int numRimes(String vers){
        int numRimes = 0;
        ArrayList<String> finalsLinies = new ArrayList<>();
        String[] split = vers.split(",");
        //Eliminem el punt de l'últim vers
        split[split.length - 1] = split[split.length - 1].replace(".", "");
        //Obtenir tots els finals de linies
        for (String linia : split) {
            try{
                String ultimesLletres = linia.substring(linia.length() - 2);
                finalsLinies.add(ultimesLletres);
            }catch (StringIndexOutOfBoundsException e){
                return 0;
            }

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

    public ArrayList<String> infoTema(int temaPos) {
        ArrayList<String> info = new ArrayList<>();
        ArrayList<String> estrofes;
        //Primera posició el tema
        info.add(temas[temaPos].getNom());

        //Agafos les estrofes del segon rapero i el seu nivell
        if (raperos[1].getLevel() == 1) {
            estrofes = temas[temaPos].getEstrofesN1();
        } else {
            estrofes = temas[temaPos].getEstrofesN2();
        }

        if (estrofes.size() == 0) {
            info.add("");
        } else {
            Collections.shuffle(estrofes);
            info.add(estrofes.get(0));
        }

        return info;
    }

}
