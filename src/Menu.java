import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner; // agafem dades menu

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

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
                System.out.println("Press enter to exit");
                scanner.nextLine(); //No ser perquè s'han de fer 2 enters
                break;
            default:
                System.out.println("Valor d'estat inesperat!!!!, Espero 0, 1 o 2");
        }
    }

    public int demanaOpcio() throws InputMismatchException {
        System.out.print("\nChoose an option: ");
        int opcio = scanner.nextInt();
        scanner.nextLine();
        return opcio;
    }

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
    }

    public ArrayList<String> demanaInfoUser(ArrayList<String> userData) {
        mostrarLiniaSeparadora();
        System.out.print("- Full name: ");
        System.out.println(userData.get(0));
        System.out.print("- Artistic name: ");
        if (userData.get(1)==null) {
            userData.set(1, scanner.nextLine());
        } else {
            System.out.println(userData.get(1));
        }
        System.out.print("- Birth date (dd/MM/YYYY): ");
        if (userData.get(2)==null) {
            userData.set(2, scanner.nextLine());
        } else {
            System.out.println(userData.get(2));
        }
        System.out.print("- Country: ");
        System.out.println(userData.get(3));
        System.out.print("- Level: ");
        if (userData.get(4)==null) {
            userData.set(4, scanner.nextLine());
        } else {
            System.out.println(userData.get(4));
        }
        System.out.print("- Photo URL: ");
        if (userData.get(5)==null) {
            userData.set(5, scanner.nextLine());
        } else {
            System.out.println(userData.get(5));
        }
        return userData;
    }

    public void resultatRegistre(Boolean [] estat) {

        /* Llegenda
        estat[0] -> Nom artístic OK/NO OK
        estat[1] -> Data neixament OK/NO OK
        estat[2] -> País acceptat OK/No OK
        estat[3] -> Totes les dades OK/NO OK
        */

        if (estat[3]) {
            System.out.println("Registration completed!");
        } else {
            if (!estat[0]) {
                System.out.println("A rapper with this artistic name already exists");
            }
            if (!estat[1]) {
                System.out.println("Birth date is invalid");
            }
            if (!estat[2]) {
                System.out.println("Country is not accepted in this competition");
            }
        }
    }

    //Obtenir Login
    public String obtenirLogin() {
        System.out.print("Enter your artistic name: ");
        return scanner.nextLine();
    }

    //Login no registrat
    public void noRegistrat(String login) {
        StringBuilder sb = new StringBuilder();
        sb.append("Yo' bro, there's no \"");
        sb.append(login);
        sb.append("\" in ma' list.");
        System.out.println(sb.toString());
        enterMainMenu();
    }

    //login si registrat
    public void Registrat(int totalFase, int fase, int score, int numBattle, String battleType, String rival) {
        /*StringBuilder sb = new StringBuilder();
        sb.append("Yo' bro, there's no \"");
        sb.append(fase);
        sb.append("\" in ma' list.");
        System.out.println(sb.toString());*/
        if(numBattle!=5 && numBattle !=6){
            mostrarLiniaSeparadora();
            StringBuilder sb = new StringBuilder();
            sb.append("Phases: ");
            sb.append(fase);
            sb.append("/");
            sb.append(totalFase);
            sb.append(" | Score:");
            sb.append(score);
            if (!(numBattle==3)) {
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
            if (!(numBattle==3)) {
                System.out.println("1. Start the battle ");
            } else {
                System.out.println("1. Go to the next phase");
            }
        }else if(numBattle != 6){
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
        }else {
            mostrarLiniaSeparadora();
            StringBuilder sb = new StringBuilder();
            sb.append("END");
            sb.append(" | Score:");
            sb.append(score);
            sb.append(" |  You've win, I'm sure you'll win the next time!!!");
            System.out.println(sb.toString());
            mostrarLiniaSeparadora();

            System.out.println("1. Go to the next phase   (desactivated)");
        }

        System.out.println("2. Show ranking ");
        System.out.println("3. Create profile ");
        System.out.println("4. Leave competition ");

    }

    private void mostrarLiniaSeparadora() {
        System.out.println("--------------------------------------------------------------------");
    }

    public String doBattle(int coin, String topic, String contrincant, String parrafada, boolean monedaLlancada) throws InterruptedException {
        mostrarLiniaSeparadora();
        System.out.print("Topic: ");
        System.out.println(topic);
        StringBuilder sb;
        StringBuilder estrofaLogin = new StringBuilder();

        if (!monedaLlancada){
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
                for (int i=0; i<3; i++ ){
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
                for (int i=0; i<3; i++ ){
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
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void showRanking(ArrayList<String> noms, ArrayList<Integer> scores, String login, int fasesRestants) {
        System.out.println("-------------------------------------");
        System.out.println("Pos. | Name | Score");
        System.out.println("-------------------------------------");

        StringBuilder colorNormal = new StringBuilder();
        StringBuilder colorVermell = new StringBuilder();

        if (fasesRestants==2){
            //La meitat veremell
            for (int i=0; i<noms.size()/2; i++) {
                colorNormal.append(i+1);
                colorNormal.append(" ");
                colorNormal.append(noms.get(i));
                colorNormal.append(" - ");
                colorNormal.append(scores.get(i));
                if(noms.get(i).equals(login)){
                    colorNormal.append(" <-- You");
                }
                colorNormal.append("\n");
            }

            for (int i=(noms.size()/2); i<noms.size(); i++){
                colorVermell.append(i+1);
                colorVermell.append(" ");
                colorVermell.append(noms.get(i));
                colorVermell.append(" - ");
                colorVermell.append(scores.get(i));
                if(noms.get(i).equals(login)){
                    colorVermell.append(" <-- You");
                }
                colorVermell.append("\n");
            }
        } else {
            //Tots menys els dos primer vermells
            for (int i=0; i<2; i++) {
                colorNormal.append(i+1);
                colorNormal.append(" ");
                colorNormal.append(noms.get(i));
                colorNormal.append(" - ");
                colorNormal.append(scores.get(i));
                colorNormal.append("\n");
            }

            for (int i=2; i<noms.size(); i++){
                colorVermell.append(i+1);
                colorVermell.append(" ");
                colorVermell.append(noms.get(i));
                colorVermell.append(" - ");
                colorVermell.append(scores.get(i));
                colorVermell.append("\n");
            }
        }

        System.out.print((colorNormal.toString()));
        System.out.println(ANSI_RED + (colorVermell.toString())+ANSI_RESET);

        mostrarLiniaSeparadora();
        scanner.nextLine();
    }

    public void createProfile() {
        System.out.println("Yo' bro, you're not ready for this yet");
        scanner.nextLine();
    }

    public void leaveCompetition(String guanyador) throws InterruptedException {
        System.out.println("And the winer is...");
        //Ponemos a "Dormir" el programa durante los ms que queremos
        for(int i=3; i>0; i--){
            Thread.sleep(1500);
            System.out.println(i);
        }
        Thread.sleep(1000);
        System.out.println(guanyador);
        scanner.nextLine();
    }

    private void enterMainMenu() {
        mostrarLiniaSeparadora();
        System.out.println("Press enter to go back to the main menu");
        scanner.nextLine();
    }

    // Mètode per a mostrar un missatge per pantalla
    public void display(String s) {
        System.out.println(s);
    }
}
