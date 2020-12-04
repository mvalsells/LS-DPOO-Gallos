import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

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
            if (bir.length() != 10) {
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

        //Login
        //Obtenir nom artistic
        int puntuacioLouser = 0;
        String login = menu.obtenirLogin();

        if (competicio.ferLogin(login)) {
            //Anem fent el menu fins que no tinguem opció 4
            int opcio;
            do {
                //Crear parelles i simular les batalles
                String[] info = competicio.preFase(login);
                /*Array
                [0] -> Nom contrincant
                [1] -> Tipus de batalls
                [2] -> Puntuació usuari
                [3] -> Posició de la batalla usuari
                 */

                //Info menu
                int totalfase = competicio.numFases();
                int fase = competicio.getFaseActual();
                //String puntuacio = info[2];
                String battleType = info[1];
                String contrincant = info[0];
                int posicio=0;

                try{
                    posicio = Integer.valueOf(info[3]);
                    do {
                        //Mostrar info de la batalla
                        menu.Registrat(totalfase, fase, (int) competicio.getPuntuacioRapero(login), competicio.getBatallaActual()+1, battleType, contrincant);
                        opcio = menu.demanaOpcio();
                        if (opcio != 1 && opcio != 2 && opcio != 3 && opcio != 4) {
                            menu.display("Number introduced not corresponding to the menu");
                        }
                    } while (opcio != 1 && opcio != 2 && opcio != 3 && opcio != 4);
                }catch (NumberFormatException e){

                    fase = fase-1;
                    competicio.setBatallaActual(4);
                    do {
                        //Mostrar info de la batalla
                        menu.Registrat(totalfase, fase, puntuacioLouser, competicio.getBatallaActual()+1, battleType, contrincant);
                        opcio = menu.demanaOpcio();
                        if (opcio != 1 && opcio != 2 && opcio != 3 && opcio != 4) {
                            menu.display("Number introduced not corresponding to the menu");
                        }
                        else if (opcio ==1){
                            menu.display("Number introduced is desactivated");
                        }

                    } while (opcio != 2 && opcio != 3 && opcio != 4);

                }

                int puntuacio = 0;

                //Demanem opció fins que sigui correcte


                //Executem opció
                switch (opcio) {
                    case 1:
                        //Start the battle
                        if ((competicio.getBatallaActual()+1)==3){
                            // Si ja he fet les 2 batalles canvi de fase
                             puntuacioLouser = (int) competicio.getPuntuacioRapero(login);

                            competicio.nextPhase();
                        }else{
                            //Si encara he de fer batalles fer-les
                            Random rand = new Random();
                            int coin = rand.nextInt(2);
                            makeBattle(posicio, contrincant, 0, coin);
                            Thread.sleep(2000);
                            makeBattle(posicio, contrincant, 1, coin);
                            competicio.setBatallaActual(competicio.getBatallaActual() + 1);
                        }



                        break;
                    case 2:
                        //Show ranking
                        competicio.ordenarRaperos();
                        ArrayList<String>nom = new ArrayList<>();
                        ArrayList<Integer>puntuacions = new ArrayList<>();



                        for (int i=0; i<competicio.getNumParticipants(); i++){
                            nom.add(competicio.nameRapper(i));
                            puntuacions.add((int) competicio.getScoreRappers(i));
                        }
                        /*
                        Array -> noms
                        getPuntuacios rapero
                        for int i, length raperos/num part{
                        noms[i] = raper(i)..getnoms

                        noms[]
                        Arrays -> puntuacio
                         */
                        menu.showRanking(nom, puntuacions);
                        break;
                    case 3:
                        //Create profile
                        menu.createProfile();
                        break;
                    case 4:
                        //Leave competition
                        String guanyador = "";
                        guanyador = competicio.nomGuanyador();
                        menu.leaveCompetition(guanyador);
                        //ok=1;
                        break;
                }


            } while (opcio != 4);
        } else {
            menu.noRegistrat(login);
        }
    }

    private void makeBattle(int battlePos, String contrincant, int temaPos, int coin) throws InterruptedException {
        //Obtinc nom del tema i estrofes del contrincant
        ArrayList<String> infoTema = competicio.infoTema(battlePos, temaPos);

        //Miro si ja he llençat la moneda o no
        boolean monedaLlancada = false;
        if (temaPos==1){
            monedaLlancada = true;
        }
        String estrofaLogin = menu.doBattle(coin, infoTema.get(0), contrincant, infoTema.get(1), monedaLlancada);
        competicio.ferBatalla(battlePos, estrofaLogin, infoTema.get(1));
    }
}
