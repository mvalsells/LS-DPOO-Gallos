import java.io.FileNotFoundException;
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

    public boolean validarFecha(String bir) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            //Date dataok;
            formatoFecha.setLenient(false);
            formatoFecha.parse(bir);
            if(bir.length() != 10){
                return false;
            }

        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public void executaMenu() throws IOException, InterruptedException {
        int opcio;
        do {
            //mostra menu i demana opcio
            menu.welcome(competicio.getName(), competicio.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), competicio.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), competicio.getNumFases(), competicio.getNumParticipants(), competicio.nomGuanyador(), competicio.estat());

            if (competicio.haAcabat()) {
                //TODO Mostrar guanyador
                System.exit(0);
            }
            opcio = menu.demanaOpcio();
            switch (opcio) {
                case 1:
                    if (!competicio.haComencat()) {
                        registrarUsuari();
                    } else {
                        login();
                    }
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    menu.display("Please enter a right option! (1 or 2)");
                    break;
            }
        } while (opcio != 2 && opcio != 4);
    }

    public void registrarUsuari() throws IOException {
        boolean primercop = true;
        Boolean[] estat;
        ArrayList<String> dadesUsuari = new ArrayList<>();
        do {
            if (primercop){
                //Primer cop -> Demano totes les dades
                dadesUsuari = menu.demanaInfoUser();
                primercop = false;
            } else {
                //No primer cop -> Demano les dades incorrectes
                dadesUsuari = menu.demanaInfoUser(dadesUsuari);
            }

            //Extrect les dades
            String realName = dadesUsuari.get(0);
            String stageName = dadesUsuari.get(1);
            String birth = dadesUsuari.get(2);
            String nationality = dadesUsuari.get(3);
            String nivell = dadesUsuari.get(4);
            int level = Integer.parseInt(nivell);
            String photo = dadesUsuari.get(5);
            float puntuacio = 0;

            //Creo el usuari
            estat = competicio.registreUsuari(realName, stageName, birth, nationality, level, photo, puntuacio);
            //Mostro si s'ha creat o hi han errors
            menu.resultatRegistre(estat);

            //Si el nom no és correcte el borrem
            if (!estat[0]) {
                dadesUsuari.set(1, null);
            }

            //Si la data no és correcte la borrem
            if (!estat[1]) {
                dadesUsuari.set(2, null);
            }

        // Anar demanant mentres el nom del rapero no sigui vàlid i la data no sigui valida i el pais sigui correcte
        } while ((!estat[0] || !estat[1]) && estat[2]);

    }

    private void login() throws InterruptedException, FileNotFoundException {
        int opcio;
        //Login
        //Obtenir nom artistic
        String login = menu.obtenirLogin();
        String contrincant = "";
        //preFase -> Crear parelles + simular batalla


        //mostrarInfoMenu();


        if (competicio.ferLogin(login)) {
            competicio.preFase(login);

            int fase = competicio.getFaseActual();
            //competicio.comencarfase1();
            int totalfase = competicio.numFases();
            String guanyador = new String();
            guanyador = competicio.nomGuanyador();
                            /*competicio.faseActual();


                            if(faseActual(fase)==1){

                                competicio.numParticipants(login,fase, random, contrincant);
                                menu.Registrat();
                            }*/

            String enemy = competicio.preFase(login);
            String parrafada = new String();
            menu.Registrat(totalfase, fase, enemy);
            do{
                do {
                    opcio = menu.demanaOpcio();
                    if (opcio != 1 && opcio != 2 && opcio != 3 && opcio != 4) {
                        menu.display("Number introduced not corresponding to the menu");
                        menu.Registrat(totalfase, fase, enemy);
                    }
                } while (opcio != 1 && opcio != 2 && opcio != 3 && opcio != 4);

                switch (opcio) {
                    case 1:
                        menu.doBattle(0, parrafada);
                        break;
                    case 2:
                        menu.showRanking();
                        break;
                    case 3:
                        menu.createProfile();
                        break;
                    case 4:
                        menu.leaveCompetition(guanyador);
                        //ok=1;
                        break;
                }
                menu.Registrat(totalfase, fase, enemy);
                //opcio = menu.demanaOpcio();
            } while (opcio != 4 );


            //Anar Lobby

            //System.out.println("He anat i tornat del loby tant ràpid que ni m'has vist");
        } else {
            menu.noRegistrat(login);
        }
    }
}
