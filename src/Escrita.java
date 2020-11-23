import java.io.FileNotFoundException;

public class Escrita extends Batalla{
    public Escrita(Rapero rapero1, Rapero rapero2) throws FileNotFoundException {
        super(rapero1, rapero2);
    }

    @Override
    public double puntuacio(int rimes) {
        return 1+3*rimes;
    }
}
