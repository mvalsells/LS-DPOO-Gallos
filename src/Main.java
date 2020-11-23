import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
       // ControllerCompeticio controllerCompeticio = new ControllerCompeticio();
       // controllerCompeticio.executaMenu();
        Rapero a = new Rapero();
        Batalla aca = new Acapella(a,a);
        int numRimes = aca.numRimes("Tiene los faros cuadrados,\nde intermitentes dos flores,\nlos asientos son de rayas,\nde muchísimos colores.");
        System.out.println("Rimes: " + numRimes + " Puntuacio: " + aca.puntuacio(numRimes));
    }

}
