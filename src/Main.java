import java.awt.Menu;

public class Main  {

    public static void main(String[] args) {
        // Creem el taulell, el seu controller i el Menú (Interfície Gràfica)
        Menu menu;
        Competicio competició;
        ControllerCompeticio controllerCompetició;


        menu = new Menu();
        competició = new Competicio();
        controllerCompetició = new ControllerCompeticio(competició, menu);
        // Executem el bucle infinit del programa, el qual serà gestionat pel controller
        //taulellController.comencaCompeticio();//cosa de Malé!!!!!!
        //menu.mostraMenu();

    }

}
