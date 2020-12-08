import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        ControllerCompeticio controllerCompeticio = new ControllerCompeticio();

        controllerCompeticio.demanarOpcion(2);

        controllerCompeticio.executaMenu();
        /*Rapero a = new Rapero();
        Rapero b = new Rapero();
        a.setLevel(1);
        b.setLevel(1);
        Batalla aca = new Acapella(a,b);
        aca.simularBatalla();
        System.out.println("hola");

        Json json = new Json("src/competicio.json", "src/batalles.json");
        ArrayList<Tema> temes = json.llegirTema();
        Tema tema = temes.get(0);
        System.out.println(tema.toString());*/
    }

}
