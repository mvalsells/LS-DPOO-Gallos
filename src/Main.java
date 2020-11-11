

public class Main  {

    public static void main(String[] args) {
        // Creem el taulell, el seu controller i el Menú (Interfície Gràfica)
        Menu menu;
        Competicio competicio;
        ControllerCompeticio controllerCompeticio;


        menu = new Menu();
        competicio = new Competicio();
        controllerCompeticio = new ControllerCompeticio(competicio, menu);
        // Executem el bucle infinit del programa, el qual serà gestionat pel controller
        //taulellController.comencaCompeticio();//cosa de Malé!!!!!!
        controllerCompeticio.executaMenu();

    }

}
