package exceptions;

public class ApiReadException extends Exception{
    int httpCode;

    public ApiReadException(int httpCode){
        super();
        this.httpCode=httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
