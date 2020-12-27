import java.io.*;
import java.util.Scanner;

/**
 * Esta clase nos permite tanto leer en los ficheros como escribir en ellos.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 27/12/2020.
 */

public class FileUtils {
    /**
     * Método que se encarga de leer del archivo, y comprobar que no hay ningún problema.
     * @param fileLocation El parámetro fileLocation se encarga de leer que fichero estamos leyendo.
     * @return El fichero que estamos leyendo.
     * @throws FileNotFoundException El parámetro FileNotFoundException indica que ha habido un error en la lectura de ficheros.
     */

    public static String readText(String fileLocation) throws FileNotFoundException {
        Reader read = new FileReader(fileLocation);
        StringBuilder fileContent = new StringBuilder();
        Scanner scanner = new Scanner(read);
        while (scanner.hasNextLine()){
            fileContent.append(scanner.nextLine());
        }
        return fileContent.toString();
    }//Cierre del método

    /**
     * Método que se encarga de escribir del archivo, y comprobar que no hay ningún problema.
     * @param fileLocation El parámetro fileLocation se encarga de leer que fichero vamos a leer
     * @param fileContent El parámetro fileContent se encarga de indicar que contenido hemos de escribir
     * @throws IOException El parámetro IOException indica que ha habido un error en la escriptura de ficheros.
     */

    public static void writeText(String fileLocation,String fileContent ) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileLocation));
        bw.write(fileContent);
        bw.close();
    }//Cierre del método
}//Cierre de la clase FileUtils
