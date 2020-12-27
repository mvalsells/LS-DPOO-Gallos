import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase sirve para mostrar el menú princial el cual le servirá al usuario para identificar tanto el estado de la competición,
 * si su registro ha estado de forma satisfactoria o no, en que fase se encuentra, su puntuación y rival, el ranking, y por supuesto
 * la posibilidad, si aun puede, de poner sus versos o por otro lado abandonar la competicion.
 *
 * @author Marc Valsells y Albert Clarimón.
 * @version 10/12/2020.
 */
public class Menu {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    //Campos de la clase
    private final Scanner scanner; // agafem dades menu

    /**
     * Constructor del menu
     */
    public Menu() {
        this.scanner = new Scanner(System.in);
    }//Cierre del constructor

    /**
     * Método que nos permite mostrar la información de la competición, si ha empezado o ha acbado o que dia empieza. Tambien,
     * nos indica, si ha acbado, el nombre del ganado. Por otro lado, nos muestra las diferentes opciones a elegir, ya sea registrarse,
     * login o leave competition.
     *
     * @param name            define el nombre de la competición.
     * @param startDate       define la fecha inicial de la competición.
     * @param endDate         define la fecha final de la competición.
     * @param numFases        define el número de fases de la competición.
     * @param numParticipants define el número de participantes que actualmente hay en la competición.
     * @param nomGuanyador    indica el nombre del ganador si la competición ha finalizado.
     * @param estat           nos indica en que estado se encuentra la competición ya sea por empezar, empezada o finalizada.
     */

    public void welcome(String name, String startDate, String endDate, int numFases, int numParticipants, String nomGuanyador, int estat) {
        //Nom
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to competition: ");
        sb.append(name);
        System.out.println(sb.toString());
        //Start
        sb = new StringBuilder();
        sb.append("Starts on ");
        sb.append(startDate);
        System.out.println(sb.toString());
        //End
        sb = new StringBuilder();
        sb.append("Ends on ");
        sb.append(endDate);
        System.out.println(sb.toString());
        //Phases
        sb = new StringBuilder();
        sb.append("Phases: ");
        sb.append(numFases);
        System.out.println(sb.toString());
        //Participants
        sb = new StringBuilder();
        sb.append("Currently: ");
        sb.append(numParticipants);
        sb.append(" participants");
        System.out.println(sb.toString());

        switch (estat) {
            case 0:
                //No ha començat
                System.out.println("\nCompetition hasn't started yet. Do you want to:");
                System.out.println("\t1. Register");
                System.out.println("\t2. Leave");
                break;
            case 1:
                System.out.println("\nCompetition started. Do you want to:");
                System.out.println("\t1. Login");
                System.out.println("\t2. Leave");
                break;
            case 2:
                sb = new StringBuilder();
                System.out.println("Competition has ended!");
                sb.append("The winner is: ");
                sb.append(nomGuanyador);
                System.out.println(sb.toString());
                pressEnterToContinue();
                break;
            default:
                System.out.println("Valor d'estat inesperat!!!!, Espero 0, 1 o 2");
        }
    }//Cierre del método

    /**
     * Método que pide por pantalla una opción a escoger y debuelve la opción.
     *
     * @return El número de la opción que el usuario ha escrito por pantalla.
     */

    public int demanaOpcio() {
        do {
            System.out.print("\nChoose an option: ");
            String input = scanner.nextLine();
            int opcio = 0;
            try {
                opcio = Integer.parseInt(input);

            } catch (NumberFormatException e) {
                opcio = 5;
            }
            return opcio;
        } while (true);
    }//Cierre del método

    /**
     * Método que sirve al momento de registrar el usuario, para que él pueda poner sus datos.
     *
     * @return El array donde figura toda su documentación para la competición.
     */

    public ArrayList<String> demanaInfoUser() {
        ArrayList<String> userData = new ArrayList<>();
        mostrarLiniaSeparadora();
        System.out.print("- Full name: ");
        userData.add(scanner.nextLine());
        System.out.print("- Artistic name: ");
        userData.add(scanner.nextLine());
        System.out.print("- Birth date (dd/MM/YYYY): ");
        userData.add(scanner.nextLine());
        System.out.print("- Country: ");
        userData.add(scanner.nextLine());
        System.out.print("- Level: ");
        userData.add(scanner.nextLine());
        System.out.print("- Photo URL: ");
        userData.add(scanner.nextLine());
        return userData;
    }// Cierre del método

    /**
     * Método que se ejecuta si la información introducida por el usuario es incorrecta, de esa manera el participante solo habrá que
     * reescribir la información incorrecta, la correcta ya se mostrará por pantalla.
     *
     * @param userData Array on conte la informació del usuari, si un String es null vol dir que s'ha de tornar a escriure la infomració.
     * @return El array ja modificat del usauri.
     */

    public ArrayList<String> demanaInfoUser(ArrayList<String> userData) {
        mostrarLiniaSeparadora();
        System.out.print("- Full name: ");
        System.out.println(userData.get(0));

        if (userData.get(1) == null) {
            System.err.print("- Artistic name: ");
            userData.set(1, scanner.nextLine());
        } else {
            System.out.print("- Artistic name: ");
            System.out.println(userData.get(1));
        }

        if (userData.get(2) == null) {
            System.err.print("- Birth date (dd/MM/YYYY): ");
            userData.set(2, scanner.nextLine());
        } else {
            System.out.print("- Birth date (dd/MM/YYYY): ");
            System.out.println(userData.get(2));
        }
        System.out.print("- Country: ");
        System.out.println(userData.get(3));

        if (userData.get(4) == null) {
            System.err.print("- Level: ");
            userData.set(4, scanner.nextLine());
        } else {
            System.out.print("- Level: ");
            System.out.println(userData.get(4));
        }

        if (userData.get(5) == null) {
            System.err.print("- Photo URL: ");
            userData.set(5, scanner.nextLine());
        } else {
            System.out.print("- Photo URL: ");
            System.out.println(userData.get(5));
        }
        return userData;
    }//Cierre del método

    /**
     * Método que nos indica si el usuario se ha registrado correctamente o por lo contrario que errores hemos cometido al inscribirnos.
     *
     * @param estat El parámetro estat nos sirve para poder identificar el error cometido.
     */

    public void resultatRegistre(Boolean[] estat) {

        /* Llegenda
        estat[0] -> Nom artístic OK/NO OK
        estat[1] -> Data neixament OK/NO OK
        estat[2] -> País acceptat OK/No OK
        estat[3] -> Totes les dades OK/NO OK
        */

        if (estat[3]) {
            System.out.println("Registration completed!");
            mostrarLiniaSeparadora();
        } else {
            if (!estat[0]) {
                System.err.println("A rapper with this artistic name already exists");
            }
            if (!estat[1]) {
                System.err.println("Birth date is invalid");
            }
            if (!estat[2]) {
                System.err.println("Country is not accepted in this competition");
            }
            if(!estat[4]){
                System.err.println("Level is invalid");
            }
        }
    }//Cierre del método

    /**
     * Método para obtención del aristic name del usuario.
     *
     * @return El nombre introducido por pantalla.
     */
    public String obtenirLogin() {
        System.out.print("Enter your artistic name: ");
        return scanner.nextLine();
    }//Cierre del método

    /**
     * Método que nos muestra por pantalla una frase indicandonos que el usuario no esta en la lista de participantes.
     *
     * @param login El parámetro login corresponde al nombre artístico del usuario.
     */
    public void noRegistrat(String login) {
        StringBuilder sb = new StringBuilder();
        sb.append("Yo' bro, there's no \"");
        sb.append(login);
        sb.append("\" in ma' list.");
        System.out.println(sb.toString());
        mostrarLiniaSeparadora();
        pressEnterToContinue();
    }

    /**
     * Método que nos muestra la tanto la información de la fase actual, al igual que sus respectivas opciones, como la información de si hemos ganado o perdido.
     *
     * @param totalFase  El parámetro totalFase indica el número total de fases.
     * @param fase       El parámetro fase indica la fase actual en la que nos encontramos.
     * @param score      El parámetro score indica la puntuación actual que tiene el usuario.
     * @param numBattle  El parámetro numBattle indica el número de batallas que en el que nos encotnramos.
     * @param battleType El parámetro battleType indica el tipo de batalla que realizaremos.
     * @param rival      El parámetro rival nos muestra nuestro siguiente oponente.
     * @param perdedor   El parámetro perdedor, nos sirve para poder identificar si el usuario ha perdido o no en la fase final.
     */

    public void registrat(int totalFase, int fase, int score, int numBattle, String battleType, String rival, boolean perdedor) {
        if (numBattle != 5 && numBattle != 6) {
            mostrarLiniaSeparadora();
            StringBuilder sb = new StringBuilder();
            sb.append("Phases: ");
            sb.append(fase);
            sb.append("/");
            sb.append(totalFase);
            sb.append(" | Score:");
            sb.append(score);
            if (!(numBattle == 3)) {
                sb.append(" | Battle ");
                sb.append(numBattle);
                sb.append("/2:");
                sb.append(battleType);
                sb.append(" | Rival: ");
                sb.append(rival);
            } else {
                sb.append(" | Waiting to go to the next phase");
            }

            System.out.println(sb.toString());
            mostrarLiniaSeparadora();
            if (!(numBattle == 3)) {
                System.out.println("1. Start the battle ");
            } else {
                System.out.println("1. Go to the next phase");
            }
        } else if (numBattle != 6) {
            mostrarLiniaSeparadora();
            StringBuilder sb = new StringBuilder();
            sb.append("Phases: ");
            sb.append(fase);
            sb.append("/");
            sb.append(totalFase);
            sb.append(" | Score:");
            sb.append(score);
            sb.append(" |  You've lost kid, I'm sure you'll do better next time...");
            System.out.println(sb.toString());
            mostrarLiniaSeparadora();

            System.out.println("1. Go to the next phase   (desactivated)");
        } else if (!perdedor) {
            mostrarLiniaSeparadora();
            StringBuilder sb = new StringBuilder();
            sb.append("END");
            sb.append(" | Score:");
            sb.append(score);
            sb.append(" |  You've lost kid, I'm sure you'll do better next time...");
            System.out.println(sb.toString());
            mostrarLiniaSeparadora();

            System.out.println("1. Go to the next phase   (desactivated)");
        } else {
            mostrarLiniaSeparadora();
            StringBuilder sb = new StringBuilder();
            sb.append("END");
            sb.append(" | Score:");
            sb.append(score);
            sb.append(" |  You've win");
            System.out.println(sb.toString());
            mostrarLiniaSeparadora();

            System.out.println("1. Go to the next phase   (desactivated)");
        }

        System.out.println("2. Show ranking ");
        System.out.println("3. Create profile ");
        System.out.println("4. Leave competition ");

    }//Cierre del método

    /**
     * Este método nos sirve para poder poner una líniea discontínua para separar diferentes prints.
     */

    private void mostrarLiniaSeparadora() {
        System.out.println("--------------------------------------------------------------------");
    }//Cierre del método

    /**
     * Método que se usa al momento en el que el usuario hace una batalla, aquí se motrará tanto quien empieza el combate, las frases del adversario, y los versos
     * escritos por pantalla del usuario.
     *
     * @param coin           El parámetro coin sirve para saber quien empieza la batalla.
     * @param topic          El parámetro topic nos idica que tema es el tratado en la batalla.
     * @param contrincant    El parámetro comtrincant nos muestra nuestro rival.
     * @param parrafada      El parámetro parrafada nos muestra los versos del adversario.
     * @param monedaLlancada El parámetro monedaLlançada nos permite saber si la moneda ha sido lanzada una vez o no, para determinar el orden de la batalla.
     * @return La estrofa escrita por el usuario.
     * @throws InterruptedException Nos sirve para controlar si hay un error en el tiempo.
     */

    public String doBattle(int coin, String topic, String contrincant, String parrafada, boolean monedaLlancada) throws InterruptedException {
        mostrarLiniaSeparadora();
        System.out.print("Topic: ");
        System.out.println(topic);
        StringBuilder sb;
        StringBuilder estrofaLogin = new StringBuilder();

        if (!monedaLlancada) {
            System.out.println("\nA coin is tossed in the air and...");
            Thread.sleep(1000);
        }
        switch (coin) {
            case 0:
                //Primer contrincant
                sb = new StringBuilder();
                sb.append(contrincant);
                sb.append(" your turn! Verse Drop it!\n\n");
                sb.append(contrincant);
                sb.append(":\n\n");
                sb.append(parrafada);
                sb.append("\n\nYour turn!\n");
                sb.append("Enter your verse:\n");
                System.out.println(sb.toString());

                //Llegir 4 versos
                estrofaLogin = new StringBuilder();
                for (int i = 0; i < 3; i++) {
                    estrofaLogin.append(scanner.nextLine());
                    estrofaLogin.append(",");
                }
                estrofaLogin.append(scanner.nextLine());
                estrofaLogin.append(".");
                break;


            case 1:
                //Primer login
                sb = new StringBuilder();
                sb.append("\nYour turn! Verse Drop it!\n");
                sb.append("Enter your verse:\n");
                System.out.println(sb.toString());

                //Llegir 4 estrofes
                estrofaLogin = new StringBuilder();
                for (int i = 0; i < 3; i++) {
                    estrofaLogin.append(scanner.nextLine());
                    estrofaLogin.append(",");
                }
                estrofaLogin.append(scanner.nextLine());
                estrofaLogin.append(".");

                sb = new StringBuilder();
                sb.append(contrincant);
                sb.append(":\n");
                sb.append("Your turn!\n\n");
                sb.append(parrafada);
                System.out.println(sb.toString());
                break;
        }
        return estrofaLogin.toString();
    }//Cierre del método


    /**
     * Método que nos muestra el ranking actual de la competición, al igual que la diferenciación de cuales están por ahora clasificados y cuales no.
     *
     * @param noms          El parámetro noms nos dice el nombre del rapero.
     * @param scores        El parámetro scores nos muestra la puntuación del rapero.
     * @param login         El parámetro login nos muestra donde se encuentra el login.
     * @param fasesRestants El parámetro fasesRestants nos permite diferenciar en cuantos raperos estarán clasificados y cuantos no.
     */
    public void showRanking(ArrayList<String> noms, ArrayList<Integer> scores, String login, int fasesRestants) {
        System.out.println("-------------------------------------");
        System.out.println("Pos. | Name | Score");
        System.out.println("-------------------------------------");

        StringBuilder colorNormal = new StringBuilder();
        StringBuilder colorVermell = new StringBuilder();

        if (fasesRestants == 2) {
            //La meitat veremell
            for (int i = 0; i < noms.size() / 2; i++) {
                colorNormal.append(i + 1);
                colorNormal.append(" ");
                colorNormal.append(noms.get(i));
                colorNormal.append(" - ");
                colorNormal.append(scores.get(i));
                if (noms.get(i).equals(login)) {
                    colorNormal.append(" <-- You");
                }
                colorNormal.append("\n");
            }

            for (int i = (noms.size() / 2); i < noms.size(); i++) {
                colorVermell.append(i + 1);
                colorVermell.append(" ");
                colorVermell.append(noms.get(i));
                colorVermell.append(" - ");
                colorVermell.append(scores.get(i));
                if (noms.get(i).equals(login)) {
                    colorVermell.append(" <-- You");
                }
                colorVermell.append("\n");
            }
        } else {
            //Tots menys els dos primer vermells
            for (int i = 0; i < 2; i++) {
                colorNormal.append(i + 1);
                colorNormal.append(" ");
                colorNormal.append(noms.get(i));
                colorNormal.append(" - ");
                colorNormal.append(scores.get(i));
                colorNormal.append("\n");
            }

            for (int i = 2; i < noms.size(); i++) {
                colorVermell.append(i + 1);
                colorVermell.append(" ");
                colorVermell.append(noms.get(i));
                colorVermell.append(" - ");
                colorVermell.append(scores.get(i));
                colorVermell.append("\n");
            }
        }

        System.out.print((colorNormal.toString()));
        System.out.println(ANSI_RED + (colorVermell.toString()) + ANSI_RESET);

        mostrarLiniaSeparadora();
        pressEnterToContinue();
    }//Cierre del método

    /**
     * Este método se usará mas adelante en la fase 4, el cual permitirá al usaurio crear su perfil.
     */

    public void createProfile() {
        System.out.println("Yo' bro, you're not ready for this yet");
        pressEnterToContinue();
    }//Cierre del método

    /**
     * Método que muestra cual es el ganador de la competición si el usuario abandona la competición.
     *
     * @param guanyador El parámetro guanyador nos muestra el ganador de la competición
     * @throws InterruptedException El parámetro InterruptedException sirve para identificar un problema con el tiempo
     */
    public void leaveCompetition(String guanyador) throws InterruptedException {
        System.out.println("And the winer is...");
        //Ponemos a "Dormir" el programa durante los ms que queremos
        for (int i = 3; i > 0; i--) {
            Thread.sleep(1500);
            System.out.println(i);
        }
        Thread.sleep(1000);
        System.out.println(guanyador);
        System.out.println();
        pressEnterToContinue();
    }//Cierre del método

    // Mètode per a mostrar un missatge per pantalla

    /**
     * Método para mostrar un mensaje por pantalla
     *
     * @param s El parámetro s sirve para saber que es lo que se debe mostrar por pantalla
     */

    public void display(String s) {
        System.out.println(s);
    }//Cierre del método
    public void displayError(String s) {
        System.err.println(s);
    }//Cierre del método

    /**
     * Método que se usa cada vez que el usuario deba salir de una pagina, deberá oprimir la tecla enter
     */
    private void pressEnterToContinue() {
        System.out.print("Press enter to continue");
        scanner.nextLine();
    }//Cierre del método

    public String askForRapper() {
        System.out.print("Enter the name of the rapper: ");
        return scanner.nextLine();
    }
}//Cierre de la clase Menú
