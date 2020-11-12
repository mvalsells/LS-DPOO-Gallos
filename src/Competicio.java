import java.util.ArrayList;
import java.util.Date;

public class Competicio {

    //Atributs
    private String name = new String();
    private Date startDate = new Date();
    private Date endDate = new Date();
    private ArrayList<String> countries = new ArrayList<>();
    private ArrayList<Fase> phases = new ArrayList<>();
    private ArrayList<Rapero> rappers = new ArrayList<>();

    public Competicio(String name, Date startDate, Date endDate, ArrayList countries, ArrayList phases){

    }

    public boolean registraUsuari(){
        return false;
    }

    public int numFase(){
        return 1;
    }

    public int numParticipants(){
        return 2;
    }

    public boolean haAcabat(){
        return true;
    }

    public int faseActual(){
        return 2;
    }

    public boolean haComençat(){
        return false;
    }
}
