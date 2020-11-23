import java.io.FileNotFoundException;

public class Acapella extends Batalla{

    public Acapella(Rapero rapero1, Rapero rapero2) throws FileNotFoundException {
        super(rapero1, rapero2);
    }
    public double puntuacio(int rimes){
        return (6*Math.sqrt(rimes)+3)/2;
    }
    public String treuTema(){
        String temaBatalla = new String();
        temaBatalla = tema.getNom();
        return temaBatalla;
    }
}
