import java.io.FileNotFoundException;
import java.util.Random;

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

    public int simularBatalla(){
        //int pR1 = raperos[1].getPuntuacio() + 4040;
        //raperos[1].setPuntuacio(pR1);
        return 1;
    }
    public int ferBatalla(){
        return 1;
    }



}
