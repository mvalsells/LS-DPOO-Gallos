/**
 * Esta clase nos indica que la batalla que se ejecuta es Acapella por lo tanto nos dirá cuales son sus normas. Esta clase es una subclasse de Batalla.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 10/12/2020.
 */

public class Acapella extends Batalla {

    /**
     * Método que nos permite coger los raperos que vienen de la superclasse Batalla
     *
     * @param rapero1 información sobre el rapero 1.
     * @param rapero2 información sobre el rapero 2.
     */

    public Acapella(Rapero rapero1, Rapero rapero2) {
        super(rapero1, rapero2);
    }//Cierre del método

    /**
     * Método que nos calcula para cada uno de los raperos que entran a esta clse, cual es su puntuación en función de sus rimas.
     *
     * @param rimes El parámetro rimas indica cuantas rimas ha hecho el rapero.
     * @return Su puntuación en función de sus rimas.
     */

    public double puntuacio(int rimes) {
        return (6 * Math.sqrt(rimes) + 3) / 2;
    }//Cierre del método

}
