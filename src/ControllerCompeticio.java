import java.io.IOException;
import java.time.LocalDate;

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
                        //register
                        System.out.println("Please, enter your personal information:");
                        //competicio.registraUsuari()
                    } else {
                        //login
                    }
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    menu.display("Please enter a rigth option! (1 or 2)");
                    break;
            }
        }
    }

    public boolean registraUsuari(String realName, String stageName, LocalDate birth, String nationality, int level, String photo) {
        return competicio.registraUsuari(realName, stageName, birth, nationality, level, photo);
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
