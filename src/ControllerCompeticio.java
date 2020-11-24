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
        menu.welcome(competicio.getName(), competicio.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), competicio.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), competicio.getNumFases(), competicio.getNumParticipants(), competicio.nomGuanyador(), competicio.estat());
        if (competicio.haAcabat()) {
            System.exit(0);
        }
        int opcio = 0;

        while (opcio != 2 && opcio != 4) {
            //mostra menu i demana opcio
            opcio = menu.demanaOpcio();
            switch (opcio) {
                case 1:
                    if (!competicio.haComencat()) {
                        registrarUsuari();
                    } else {
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
                                        menu.doBattle(0);
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

    public void registrarUsuari() throws IOException {
        boolean primercop = true;
        Boolean[] estat = new Boolean[3];
        ArrayList<String> dadesUsuari = new ArrayList<>();
        do {
            if (primercop){
                dadesUsuari = menu.demanaInfoUser();
                primercop = false;
            } else {
                dadesUsuari = menu.demanaInfoUser(dadesUsuari);
            }

            String realName = dadesUsuari.get(0);
            String stageName = dadesUsuari.get(1);
            String birthInput = dadesUsuari.get(2);
            String nationality = dadesUsuari.get(3);
            String nivell = dadesUsuari.get(4);
            int level = Integer.parseInt(nivell);
            String photo = dadesUsuari.get(5);
            float puntuacio = 0;

            if (validarFecha(birthInput)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate birthLocalDate = LocalDate.parse(birthInput, formatter);
                String birth = birthLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                estat = competicio.registreUsuari(realName, stageName, birth, nationality, level, photo, puntuacio);
            } else {
                estat = 2;
            }
            menu.resultatRegistre(estat);
            switch (estat) {
                case 1:
                    dadesUsuari.set(1, null);
                    break;
                case 2:
                    dadesUsuari.set(2, null);
                    break;
            }
        } while (estat != 0 && estat != 3);

//        return estat;
            /* Llegenda return
            0 -> Dades correctes i guardat al JSON
            1 -> Nom artístic ja existeix
            2 -> Data neixament no valida
            3 -> País no existeix
            */
    }
}
