public class Sangre extends Batalla {
    public Sangre(Rapero rapero1, Rapero rapero2) {
        super(rapero1, rapero2);
    }

    @Override
    public double puntuacio(int rimes) {
        return (Math.PI * Math.pow(rimes,2))/4;
    }
}
