import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main  {

    public static void main(String[] args) throws IOException {
        // Creem el taulell, el seu controller i el Menú (Interfície Gràfica)
        /*Menu menu;
        Competicio competicio;
        ControllerCompeticio controllerCompeticio;


        menu = new Menu();
        competicio = new Competicio();
        controllerCompeticio = new ControllerCompeticio(competicio, menu);
        // Executem el bucle infinit del programa, el qual serà gestionat pel controller
        //taulellController.comencaCompeticio();//cosa de Malé!!!!!!
        controllerCompeticio.executaMenu();*/

        Json json;

        json = new Json("src/competicio.json", "src/batalles.json");

        //System.out.print(json.fileToString("src/competicio.json"));
    }

}
