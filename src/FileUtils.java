import java.io.*;
import java.util.Scanner;

public class FileUtils {
    public static String readText(String fileLocation) throws FileNotFoundException {
        Reader read = new FileReader(fileLocation);
        StringBuilder fileContent = new StringBuilder();
        Scanner scanner = new Scanner(read);
        while (scanner.hasNextLine()){
            fileContent.append(scanner.nextLine());
        }
        return fileContent.toString();
    }
    public static void writeText(String fileLocation,String fileContent ) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileLocation));
        bw.write(fileContent);
        bw.close();
    }
}
