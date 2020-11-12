import java.io.FileReader;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import java.util.Map.Entry;

public class Json {
    public static void main(String args[]) throws java.io.IOException {
        JsonParser parser = new JsonParser();
        FileReader fr = new FileReader("competició.json");
        JsonElement datos = parser.parse(fr);
        dumpJSONElement(datos);
    }

    public static void dumpJSONElement(JsonElement element){
        //El método «dumpJSONElement» debe determinar el tipo de elemento que
        // recibe como argumento (elemento simple, hashtable o array), y procesarlo en consecuencia.
        //
        //Si el elemento recibido es un elemento compuesto de otros
        // elementos (array o hashtable), «dumpJSONElement» se llama a sí mismo recursivamente:


    }


}
