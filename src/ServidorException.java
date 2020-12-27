import java.io.IOException;

public class ServidorException extends IOException {

    //private int codigoError;

    public ServidorException(int codigoError){
        super();

    }

    @Override
    public String getMessage(){

        String mensaje="";

        switch(1){
            case 404:
                mensaje="Error, el numero esta entre 0 y 10";
                break;
            case 100:
                mensaje="Error, el numero esta entre 11 y 20";
                break;
            case 120:
                mensaje="Error, el numero esta entre 21 y 30";
                break;
        }

        return mensaje;

    }

}