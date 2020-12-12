import java.io.IOException;

/**
 * Esta clase nos permite controlar el proyecto en su totalidad, tanto en el momento de ejecutar el menú, como en el de crear el controller.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 10/12/2020.
 */

public class Main {

    /**
     * Método que controla el proyecto, permite crear el controller y ejecutar el menú
     *
     * @param args El parámetro se encarga de ejecutar los argumentos del programa.
     * @throws IOException          El parámetro se encargra de si el programa falla por algo no se salga de la ejecución.
     * @throws InterruptedException El parámetro se encarga de si hay un fallo en el tiempo.
     */

    public static void main(String[] args) throws IOException, InterruptedException {
        ControllerCompeticio controllerCompeticio = new ControllerCompeticio();

        controllerCompeticio.executaMenu();
    }//Cierre del método
    //Fase2OK

}//Cierre de la clase Main
