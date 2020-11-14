import java.io.IOException;
import java.time.LocalDate;
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


    public int registrarUsuari() {
        ArrayList<String> dadesUsuari = menu.demanaInfoUser();
        String realName = dadesUsuari.get(0);
        int estat=-1;

        // Array list -> tipus correcte

        if (/* data no format correcte*/) {
            estat = 2;
        } else {
            estat = competicio.registreUsuari(/*Falta posra dades*/);
        }

        /* Llegenda return
        0 -> Dades correctes i guardat al JSON
        1 -> Nom artístic ja existeix
        2 -> Data neixament no valida
        3 -> País no existeix
        4 -> URL foto no correcte???
        */
        return estat;
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
