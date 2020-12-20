import edu.salleurl.profile.Profile;
import edu.salleurl.profile.ProfileFactory;
import edu.salleurl.profile.Profileable;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Esta clase sirve para controlar lo que sucede en competicion al igual que será un intermediario entre las clases competición y menú.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 10/12/2020.
 */

public class ControllerCompeticio {

    //Campos de la clase
    private final Competicio competicio;        // Model de dades
    private final Menu menu;              // Menu, interfície gràfica

    /**
     * Contructor de ControllerCompeticio
     */
    public ControllerCompeticio() {
        Json json = new Json();
        menu = new Menu();
        menu.display("Reading data from JSON file and API...");
        competicio = json.llegirCompeticio();

    }//Cierre del constructor

    /**
     * Método que nos permite ejecutar el menú de bienvenida para saber si ha empezado o no, al igual que nos dara la diferenciacion entre los proximos métodos
     * dependiendo de si ha empezado la competicion, o no.
     *
     * @throws InterruptedException
     */
    public void executaMenu() throws InterruptedException {
        int opcio;
        do {
            //mostra menu i demana opcio


            if (competicio.haAcabat()) {
                simularCompeticioRestant();
                menu.welcome(competicio.getName(), competicio.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), competicio.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), competicio.getNumFases(), competicio.getNumParticipants(), competicio.nomGuanyador(), competicio.estat());
                System.exit(0);
            } else {
                menu.welcome(competicio.getName(), competicio.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), competicio.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), competicio.getNumFases(), competicio.getNumParticipants(), "pp", competicio.estat());
            }

            opcio = menu.demanaOpcio();
            switch (opcio) {
                case 1:
                    if (!competicio.haComencat()) {
                        registrarUsuari();
                    } else {
                        if (competicio.getNumParticipants() > 0) {
                            login();
                            System.exit(0);
                        } else {
                            menu.display("There aren't any users registered so it is not possbile to make login");
                        }
                    }
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    menu.display("Please enter a right option! (1 or 2)");
                    break;
            }
        } while (true);
    }//Cierre del método

    /**
     * Metodo en el cual controlaremos si el usuario se ha registrado o no. En el primer caso, pediremos directamente las dadas al menu,
     * en el segundo simplemente pediremos las que estan mal. Para controlar eso, guardamos la información y la que esté erronea la pondremos a null
     */

    private void registrarUsuari() {
        boolean primercop = true;
        Boolean[] estat;
        ArrayList<String> dadesUsuari = new ArrayList<>();
        do {
            if (primercop) {
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
            //int level = Integer.parseInt(nivell);
            String photo = dadesUsuari.get(5);
            float puntuacio = 0;

            //Creo el usuari
            estat = competicio.registreUsuari(realName, stageName, birth, nationality, nivell, photo);
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

            if(estat[3]){
                Json json = new Json();
                int level = Integer.parseInt(nivell);
                json.escriureRapero(realName, stageName, birth, nationality, level, photo);
            }

            if(!estat[4]){
                dadesUsuari.set(4,null);
            }

            // Anar demanant mentres el nom del rapero no sigui vàlid i la data no sigui valida i el pais sigui correcte
        } while ((!estat[0] || !estat[1] || !estat[4]) && estat[2]);

    }//Cierre del método

    /**
     * Método que usaremos una vez la competicion haya empezado, en el controlaremos desde si el usuario existe o no, hasta los diferntes menus que se irán
     * mostrando durante el transcurso de la batalla, ya sean los de la batalla misma, los del ranking, los de las fases o si el usuario pierde.
     * Tambien controlaremos si la moneda se ha lanzado, si se han ya completado las dos batallas.
     *
     * @throws InterruptedException El parámetro InterrupExceotion controla si hay un fallo en el tiempo.
     */
    private void login() throws InterruptedException {

        int puntuacioLouser = 0;
        String login = menu.obtenirLogin();
        String[] info;
        if (competicio.ferLogin(login)) {
            //Anem fent el menu fins que no tinguem opció 4
            int opcio;
            boolean showrankingnext = false;
            boolean finalCompeticio = false;
            String guanyador = "";
            competicio.preFase(login); //PreFase1
            info = competicio.simularBatalles(login);//Simular fase 1 batalla 1
            do {

                try {
                    if (showrankingnext && (competicio.getBatallaActual() + 1) < 3) {
                        info = competicio.simularBatalles(login);
                        showrankingnext = false;
                    }
                } catch (IndexOutOfBoundsException e) {

                    finalCompeticio = true;
                    guanyador = competicio.nomGuanyador();
                }


                //Info menu
                int numFases = competicio.numFases();
                int faseActual = competicio.getFaseActual();

                String battleType = info[1];
                String contrincant = info[0];

                int posicio = 0;

                boolean perdedor = true;
                try {
                    competicio.nomGuanyador().equals(login);
                } catch (IndexOutOfBoundsException e) {
                    perdedor = false;
                }

                if (!finalCompeticio) {
                    try {
                        posicio = Integer.valueOf(info[3]);
                        do {
                            //Mostrar info de la batalla
                            menu.registrat(numFases, faseActual, (int) competicio.getPuntuacioRapero(login), competicio.getBatallaActual() + 1, battleType, contrincant, perdedor);

                            opcio = menu.demanaOpcio();

                            if (opcio != 1 && opcio != 2 && opcio != 3 && opcio != 4) {
                                menu.display("Number introduced not corresponding to the menu");
                            }
                        } while (opcio != 1 && opcio != 2 && opcio != 3 && opcio != 4);

                    } catch (NumberFormatException e) {

                        faseActual = faseActual - 1;

                        competicio.setBatallaActual(4);

                        do {
                            //Mostrar info de la batalla
                            menu.registrat(numFases, faseActual, puntuacioLouser, competicio.getBatallaActual() + 1, battleType, contrincant, perdedor);
                            opcio = menu.demanaOpcio();
                            if (opcio != 1 && opcio != 2 && opcio != 3 && opcio != 4) {
                                menu.display("Number introduced not corresponding to the menu");
                            } else if (opcio == 1) {
                                menu.display("Number introduced is desactivated");
                            }

                        } while (opcio != 2 && opcio != 3 && opcio != 4);

                    }
                } else {
                    faseActual = faseActual - 1;
                    do {
                        //Mostrar info de la batalla
                        menu.registrat(numFases, faseActual, puntuacioLouser, 6, battleType, guanyador, perdedor);
                        opcio = menu.demanaOpcio();

                        if (opcio != 1 && opcio != 2 && opcio != 3 && opcio != 4) {
                            menu.display("Number introduced not corresponding to the menu");
                        } else if (opcio == 1) {
                            menu.display("Number introduced is desactivated");
                        }

                    } while (opcio != 2 && opcio != 3 && opcio != 4);
                }

                //Executem opció
                switch (opcio) {
                    case 1:
                        //Start the battle
                        if ((competicio.getBatallaActual() + 1) == 3) {
                            // Si ja he fet les 2 batalles canvi de fase
                            puntuacioLouser = (int) competicio.getPuntuacioRapero(login);

                            competicio.nextPhase();
                            if (competicio.getFaseActual() <= competicio.getNumFases()) {
                                competicio.preFase(login);
                            }
                            try {
                                info = competicio.simularBatalles(login);
                            } catch (IndexOutOfBoundsException e) {
                                finalCompeticio = true;
                            }

                        } else {
                            //Si encara he de fer batalles fer-les
                            Random rand = new Random();
                            int coin = rand.nextInt(2);
                            makeBattle(posicio, contrincant, 0, coin);
                            menu.display("... ... ...");
                            Thread.sleep(2000);
                            makeBattle(posicio, contrincant, 1, coin);
                            competicio.setBatallaActual(competicio.getBatallaActual() + 1);
                            //info = competicio.simularBatalles(login);
                            showrankingnext = true;
                        }
                        break;
                    case 2:
                        //Show ranking
                        competicio.ordenarRaperos();
                        ArrayList<String> nom = new ArrayList<>();
                        ArrayList<Integer> puntuacions = new ArrayList<>();

                        for (int i = 0; i < competicio.getNumParticipants(); i++) {
                            nom.add(competicio.nameRapper(i));
                            puntuacions.add((int) competicio.getScoreRappers(i));
                        }

                        menu.showRanking(nom, puntuacions, login, competicio.getNumFases() - competicio.getFaseActual());
                        break;
                    case 3:
                        //Create profile
                        //menu.createProfile();
                        createProfile(finalCompeticio);
                        break;
                    case 4:
                        //Leave competition
                        try {
                            simularCompeticioRestant();
                        } catch (IndexOutOfBoundsException e) {
                            finalCompeticio = true;
                            menu.leaveCompetition(competicio.nomGuanyador());
                            break;
                        }

                        finalCompeticio = true;
                        menu.leaveCompetition(competicio.nomGuanyador());
                        break;
                }


            } while (opcio != 4);
        } else {
            menu.noRegistrat(login);
        }
    }//Cierre del método

    private void createProfile(boolean finalCompeticio) {
        String rapperName = menu.askForRapper();
        //TODO fer en el catch de l'exception

        String stageName = competicio.findRapper(rapperName);
        while (stageName.equals("")){
            stageName = competicio.findRapper(rapperName);
            menu.display("Rapper not found");
            rapperName = menu.askForRapper();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("RappersHTMLProfiles/");
        sb.append(stageName);
        sb.append(".html");

        Profileable rapperProfile = competicio.rapperProfile(stageName);
        Profile profile = ProfileFactory.createProfile(sb.toString(),rapperProfile);

        ArrayList<String> infoProfile = competicio.infoProfile(stageName);

        sb = new StringBuilder();
        sb.append("Getting information about their country of origin (");
        sb.append(infoProfile.get(3));
        sb.append(")...");
        menu.display(sb.toString());
        profile.addExtra("Points", infoProfile.get(0));
        if (finalCompeticio && infoProfile.get(1).equals("1")){
            profile.addExtra("Position", "Winner");
        } else if (finalCompeticio && infoProfile.get(1).equals("2")) {
            profile.addExtra("Position", "Loser");
        } else {
            profile.addExtra("Position", infoProfile.get(1));
        }
        profile.setFlagUrl(infoProfile.get(2));
        profile.setCountry(infoProfile.get(3));
        for (int i=4; i<infoProfile.size(); i++ ){
            profile.addLanguage(infoProfile.get(i));
        }

        menu.display("Generating HTML file...");

        try {
            profile.writeAndOpen();
            menu.display("Done! The profile will open in your default browser.");
        } catch (IOException e){
            System.err.println("The HTML file can't be created / written to / opened for any reason");
        }
    }

    /**
     * Método que nos controlara lo que sucede en cada batalla, ya sea en que posición de la batalla se encuentra el usuario, si se ha lanzado la moneda.
     * Posteriormente nos enviara a competicion la estrofa del login, el tema del que se ha hablado y la posicion de la batalla.
     *
     * @param battlePos   El parámetro battlePos nos idica en que posición del array batalla se encuentra la batalla del login.
     * @param contrincant El parámetro contrincant nos indica cual es el nombre de nuestro contrincant.
     * @param temaPos     El parámetro temaPos nos indica que en que posición del array temas está el tema que le ha tocado al login.
     * @param coin        El parámetro coin determinará de forma aleatoria quien empieza primero la batalla.
     * @throws InterruptedException El parámetro InterrupExceotion controla si hay un fallo en el tiempo.
     */

    private void makeBattle(int battlePos, String contrincant, int temaPos, int coin) throws InterruptedException {
        //Obtinc nom del tema i estrofes del contrincant
        ArrayList<String> infoTema = competicio.infoTema(battlePos, temaPos);

        //Miro si ja he llençat la moneda o no
        boolean monedaLlancada = false;
        if (temaPos == 1) {
            monedaLlancada = true;
        }
        String estrofaLogin = menu.doBattle(coin, infoTema.get(0), contrincant, infoTema.get(1), monedaLlancada);
        competicio.ferBatalla(battlePos, estrofaLogin, infoTema.get(1));
    }//Cierre del método

    /**
     * Este método se ejecuta en el momento que el usaurio abandona la competición, si eso sucede, este método simulará las batlallas restantes a cuando el usuario
     * ha abandonado la competición. Cabe decir que si en el momento de inicializar el programa la competición ya ha finalizado, tambien se ejecutará este método para
     * poder visualizar el ganador.
     *
     * @throws IndexOutOfBoundsException El parámetro IndexOutOfBoundsexception nos sirve para identificar si ya se ha llegado a las fase 3 y al incrementar la fase,
     *                                   nos permite mostrar el ganador.
     */

    private void simularCompeticioRestant() throws IndexOutOfBoundsException {
        do {
            for (int i = competicio.getBatallaActual(); i < 2; i++) {
                competicio.simularBatalles("");
                competicio.setBatallaActual(competicio.getBatallaActual() + 1);
            }
            competicio.nextPhase();
            if (competicio.getFaseActual() <= competicio.getNumFases()) {
                competicio.preFase("");
            }
        } while (competicio.getFaseActual() <= competicio.getNumFases());
    }//Cierre del método
}//Cierre de la clase ControllerCompetició
