package exceptions;

public class RapperNotFoundException extends Exception{
    String stageName;
    public RapperNotFoundException(String stageName){
        super();
        this.stageName=stageName;
    }

    public String getStageName() {
        return stageName;
    }
}
