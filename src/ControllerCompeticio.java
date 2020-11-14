import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ControllerCompeticio {
    //Atributs
    private Competicio competicio;        // Model de dades
    private  Menu menu;              // Menu, interfície gràfica

    //Constructor
    public ControllerCompeticio () throws IOException {
        Json json = new Json("src/competicio.json", "src/batalles.json");
        competicio = json.llegirCompeticio();
        menu = new Menu();
    }

    public void executaMenu() throws IOException {
        menu.welcome(competicio);
        if (competicio.haAcabat()){
            System.exit(0);
        }
        int opcio = 0;
        while (opcio != 1) {
            //mostra menu i demana opcio
            opcio = menu.demanaOpcio();
            switch (opcio) {
                case 1:
                    if (!competicio.haComencat()) {
                        int estatRegistre;
                        do {
                            //Demano dades registre
                            estatRegistre = registrarUsuari();
                            //Mostro el resultat del registre
                            menu.resultatRegistre(estatRegistre);
                        } while (estatRegistre != 0);
                    } else {
                        //login
                    }
                    menu.welcome(competicio);
                    opcio = 0;
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    menu.display("Please enter a right option! (1 or 2)");
                    break;
            }
        }
    }


    public int registrarUsuari() throws  IOException{
        int estat=-1;
        boolean fechaok = true;
            ArrayList<String> dadesUsuari = menu.demanaInfoUser();
            String realName = dadesUsuari.get(0);
            String stageName = dadesUsuari.get(1);
            String bir = dadesUsuari.get(2);
            String nationality = dadesUsuari.get(3);
            String nivell = dadesUsuari.get(4);
            int level = Integer.parseInt(nivell);
            String photo = dadesUsuari.get(5);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");



            fechaok = validarFecha(bir);
            if(fechaok){
                LocalDate birth = LocalDate.parse(bir, formatter);
                estat = competicio.registreUsuari(realName, stageName, birth, nationality, level, photo);
            }else{
                estat=2;
            }

            return estat;




        // Array list -> tipus correcte

        /*if ( data no format correcte) {
            estat = 2;
        } else {
            estat = competicio.registreUsuari(Falta posra dades);
        }*/

        /* Llegenda return
        0 -> Dades correctes i guardat al JSON
        1 -> Nom artístic ja existeix
        2 -> Data neixament no valida
        3 -> País no existeix
        4 -> URL foto no correcte???
        */

    }
    public static boolean validarFecha(String bir){
        try{
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
            //SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY/MM/dd");
            formatoFecha.setLenient(false);
            formatoFecha.parse(bir);
        }
        catch (ParseException e){
            return false;
        }
        return true;
    }

    public int registreUsuariCompeticio(String realName, String stageName, LocalDate birth, String nationality, int level, String photo) {
        return competicio.registreUsuari(realName, stageName, birth, nationality, level, photo);
    }

    public int numFase() {
        return competicio.numFases();
    }

    public int numParticipants() {
        return competicio.numParticipants();
    }

    public boolean haAcabat() {
        return competicio.haAcabat();
    }

    public int faseActual() {
        return competicio.faseActual();
    }

    public boolean haComencat() {
        return competicio.haComencat();
    }
}
