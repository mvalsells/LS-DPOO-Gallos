import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Collections;

public class Acapella extends Batalla{

    public Acapella(Rapero rapero1, Rapero rapero2) throws FileNotFoundException {
        super(rapero1, rapero2);
    }
    public double puntuacio(int rimes){
        return (6*Math.sqrt(rimes)+3)/2;
    }
    public String treuTema(){
        String temaBatalla = new String();
        Collections.shuffle(tema);
        temaBatalla = tema.get(0).getNom();
        return temaBatalla;
    }
}
