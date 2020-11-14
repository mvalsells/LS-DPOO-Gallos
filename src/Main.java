import java.io.IOException;
import java.text.ParseException;

public class Main  {

    public static void main(String[] args) throws IOException {
        // Creem el taulell, el seu controller i el Menú (Interfície Gràfica)

        Json json;

        json = new Json("src/competicio.json", "src/batalles.json");

        Competicio competicio = json.llegirCompeticio();


        Menu menu;
        //Competicio competicio;
        ControllerCompeticio controllerCompeticio;


        menu = new Menu();
        //competicio = new Competicio();
        controllerCompeticio = new ControllerCompeticio(competicio, menu);
        // Executem el bucle infinit del programa, el qual serà gestionat pel controller
        //taulellController.comencaCompeticio();//cosa de Malé!!!!!!
        controllerCompeticio.executaMenu();


        //competicio.mostrarInfo();
        //json.llegirTemes();



    }

}
