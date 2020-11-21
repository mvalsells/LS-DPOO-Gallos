import java.io.FileNotFoundException;
import java.util.Random;

public class Batalla {
    //Atributs
    Rapero[] raperos = new Rapero[2];
    Tema tema;


    //Constructor
    public Batalla(Rapero rapero1, Rapero rapero2){
        this.raperos[0]=rapero1;
        this.raperos[1]=rapero2;

    }

    public int simularBatalla(){
        return 1;
    }
    public int ferBatalla(){
        return 1;
    }



}
