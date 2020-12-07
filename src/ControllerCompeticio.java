import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
            //TODO he hagut de treure el nom guanyador del welcome sino petava
            menu.welcome(competicio.getName(), competicio.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), competicio.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), competicio.getNumFases(), competicio.getNumParticipants(), "PP", competicio.estat());

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
                        //TODO controlar que hi hagin 0 participiants
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

    private void login() throws InterruptedException{

        //Login
        //Obtenir nom artistic
        int puntuacioLouser = 0;
        String login = menu.obtenirLogin();
        String[] info =  new String[4];
        if (competicio.ferLogin(login)) {
            //Anem fent el menu fins que no tinguem opció 4
            int opcio;
            boolean showrankingnext = true;
            boolean finalCompeticio = false;
            String guanyador = new String();
            competicio.preFase(login); //PreFase1
            do {
                //Crear parelles i simular les batalles

                try {
                    if(showrankingnext && (competicio.getBatallaActual()+1)<3){
                        info = competicio.simularBatalles(login);
                        showrankingnext = false;
                    }
                }catch (IndexOutOfBoundsException e){

                    finalCompeticio = true;
                    guanyador = competicio.nomGuanyador();
                }


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
                boolean perdedor = false;

                //TODO funcio per fer que si perd a la final surti que ha perdut
                /*try {
                    if(competicio.nomGuanyador().equals(login)){
                       perdedor = false;
                    }
                }catch (IndexOutOfBoundsException e){
                    perdedor = true;
                }*/

            if(!finalCompeticio /*|| perdedor*/){
                try{
                    posicio = Integer.valueOf(info[3]);
                    do {
                        //Mostrar info de la batalla
                        menu.Registrat(totalfase, fase, (int) competicio.getPuntuacioRapero(login), competicio.getBatallaActual()+1, battleType, contrincant);
                        try{
                            opcio = menu.demanaOpcio();
                        }catch (InputMismatchException exception){
                            menu.display("Opcion introduced not corresponding to the menu");
                            opcio = menu.demanaOpcio();
                        }
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
                        try{
                            opcio = menu.demanaOpcio();
                        }catch (InputMismatchException exception){
                            menu.display("Opcion introduced not corresponding to the menu");
                            opcio = menu.demanaOpcio();
                        }

                        if (opcio != 1 && opcio != 2 && opcio != 3 && opcio != 4) {
                            menu.display("Number introduced not corresponding to the menu");
                        }
                        else if (opcio ==1){
                            menu.display("Number introduced is desactivated");
                        }

                    } while (opcio != 2 && opcio != 3 && opcio != 4);

                }
            }else{
                fase = fase-1;
                do {
                    //Mostrar info de la batalla
                    menu.Registrat(totalfase, fase, puntuacioLouser, 6, battleType, guanyador);
                    try{
                        opcio = menu.demanaOpcio();
                    }catch (InputMismatchException exception){
                        menu.display("Opcion introduced not corresponding to the menu");
                        opcio = menu.demanaOpcio();
                    }

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
                            competicio.preFase(login);


                        }else{
                            //Si encara he de fer batalles fer-les
                            Random rand = new Random();
                            int coin = rand.nextInt(2);
                            makeBattle(posicio, contrincant, 0, coin);
                            Thread.sleep(2000);
                            makeBattle(posicio, contrincant, 1, coin);
                            competicio.setBatallaActual(competicio.getBatallaActual() + 1);
                            showrankingnext = true;
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
                        menu.showRanking(nom, puntuacions, login, competicio.getNumFases()-competicio.getFaseActual());
                        break;
                    case 3:
                        //Create profile
                        menu.createProfile();
                        break;
                    case 4:
                        //Leave competition
                        //String guanyador = "";
                        while(fase<=totalfase){
                            try{
                                if((competicio.getBatallaActual() + 1)==1){
                                    info = competicio.simularBatalles(login);
                                }else{
                                    for(int i=0; i<2; i++){
                                        info = competicio.simularBatalles(login);
                                    }
                                }
                            }catch (IndexOutOfBoundsException e){
                                guanyador = competicio.nomGuanyador();
                                finalCompeticio = true;
                                menu.leaveCompetition(guanyador);
                                break;
                            }

                            competicio.nextPhase();

                            competicio.preFase(login);

                        }
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

    private void simularCompeticioRestant(){
        for (int i = competicio.getFaseActual()-1; i < competicio.numFases()-1; i++) {
            if((competicio.getBatallaActual() + 1)==1){
                competicio.simularBatalles("");
            }else{
                for(int j=0; j<2;j++){
                    competicio.simularBatalles("");
                }
            }
        }
    }
}
