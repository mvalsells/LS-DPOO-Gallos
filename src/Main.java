import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        ControllerCompeticio controllerCompeticio = new ControllerCompeticio();
        controllerCompeticio.executaMenu();
        Rapero a = new Rapero();
        Rapero b = new Rapero();
        a.setLevel(1);
        b.setLevel(1);
        Batalla aca = new Acapella(a,b);
        aca.simularBatalla();
        System.out.println("hola");





    }

}
