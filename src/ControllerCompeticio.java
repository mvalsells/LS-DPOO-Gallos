import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ControllerCompeticio {
    //Atributs
    private final Competicio competicio;        // Model de dades
    private final Menu menu;              // Menu, interfície gràfica

    //Constructor
    public ControllerCompeticio() throws IOException {
        Json json = new Json("src/competicio.json", "src/batalles.json");
        competicio = json.llegirCompeticio();
        menu = new Menu();
    }

    public static boolean validarFecha(String bir) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            //SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY/MM/dd");
            //SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY/MM/dd");
            formatoFecha.setLenient(false);
            formatoFecha.parse(bir);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public void executaMenu() throws IOException {
        menu.welcome(competicio);
        if (competicio.haAcabat()) {
            System.exit(0);
        }
        int opcio = 0;
        while (opcio != 1) {
            //mostra menu i demana opcio
            opcio = menu.demanaOpcio();
            switch (opcio) {
                case 1:
                    if (!competicio.haComencat()) {
                        //Demano dades registre
                        int estatRegistre = registrarUsuari();
                        //Mostro el resultat del registre
                        menu.resultatRegistre(estatRegistre);
                    } else {
                        //Login
                        String login = menu.obtenirLogin();
                        if(competicio.ferLogin(login)) {
                            //Anar Lobby
                            System.out.println("He anat i tornat del loby tant ràpid que ni m'has vist");
                        } else {
                            menu.noRegistrat(login);
                        }
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

    public int registrarUsuari() throws IOException {
        int estat = -1;
        ArrayList<String> dadesUsuari = menu.demanaInfoUser();
        String realName = dadesUsuari.get(0);
        String stageName = dadesUsuari.get(1);
        String birthInput = dadesUsuari.get(2);
        String nationality = dadesUsuari.get(3);
        String nivell = dadesUsuari.get(4);
        int level = Integer.parseInt(nivell);
        String photo = dadesUsuari.get(5);



        if (validarFecha(birthInput)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthLocalDate = LocalDate.parse(birthInput, formatter);
            String birth = birthLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            estat = competicio.registreUsuari(realName, stageName, birth, nationality, level, photo);
        } else {
            estat = 2;
        }

        return estat;
            /* Llegenda return
            0 -> Dades correctes i guardat al JSON
            1 -> Nom artístic ja existeix
            2 -> Data neixament no valida
            3 -> País no existeix
            */
    }

    /*public int registreUsuariCompeticio(String realName, String stageName, String birth, String nationality, int level, String photo) throws IOException {
        return competicio.registreUsuari(realName, stageName, birth, nationality, level, photo);
    }*/

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
