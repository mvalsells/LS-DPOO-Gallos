import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner scanner; // agafem dades menu

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

        switch (estat){
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

    public int demanaOpcio(){
        System.out.print("\nChoose an option: ");
        int opcio = scanner.nextInt();
        scanner.nextLine();
        return opcio;
    }

    public ArrayList<String> demanaInfoUser() {
        ArrayList<String> userData = new ArrayList<>();
        System.out.println("--------------------------------------------------");
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

    public void resultatRegistre (int estat) {
        System.out.println();
        switch (estat) {
            case 0:
                System.out.println("Registration completed!");
                break;
            case 1:
                System.out.println("A rapper with this artistic name already exists");
                break;
            case 2:
                System.out.println("Birth date is invalid");
                break;
            case 3:
                System.out.println("Country is not accepted in this competition");
                break;
            default:
                System.out.println("The registration process returned an unexpected estat number");
                break;
        }
        enterMainMenu();
    }

    //Obtenir Login
    public String obtenirLogin(){
        System.out.print("Enter your artistic name: ");
        return scanner.nextLine();
    }
    //Login no registrat
    public void noRegistrat(String login){
        StringBuilder sb = new StringBuilder();
        sb.append("Yo' bro, there's no \"");
        sb.append(login);
        sb.append("\" in ma' list.");
        System.out.println(sb.toString());
        enterMainMenu();
    }

    //login si registrat
    public void Registrat(int totalFase, int fase){
        /*StringBuilder sb = new StringBuilder();
        sb.append("Yo' bro, there's no \"");
        sb.append(fase);
        sb.append("\" in ma' list.");
        System.out.println(sb.toString());*/
        System.out.println("--------------------------------------------------");
        StringBuilder sb = new StringBuilder();
        sb.append("Phases: ");
        sb.append(fase);
        sb.append("/");
        sb.append(totalFase);

        System.out.println(sb.toString());
        System.out.println("--------------------------------------------------");

        System.out.print("\n1. Start the battle ");
        System.out.print("\n2. Show ranking ");
        System.out.print("\n3. Create profile ");
        System.out.print("\n4. Leave competition ");

    }
    public void doBattle(int coin) throws InterruptedException {
        System.out.println("--------------------------------------------------");
        System.out.println("Topic:");


        System.out.println("\nA coin is tossed in the air and...");
        Thread.sleep(1000);
        switch (coin){
            case 0:
                System.out.println("your turn! Drop it!");
                System.out.println("\n\nYour turn!");
                System.out.println("Enter your verse:");
                scanner.next();
                scanner.nextLine();
                System.out.println("\n...");
                scanner.nextLine();

        }


    }
    public void showRanking(){
        System.out.println("---------------------------------");
        System.out.println("Pos.  |  Name  |  Score");
        System.out.println("---------------------------------");
        scanner.nextLine();
    }

    public void createProfile(){
        System.out.println("Yo' bro, you're not ready for this yet");
        scanner.nextLine();
    }

    public void leaveCompetition() throws InterruptedException {
        System.out.println("And the winer is...");
        //Ponemos a "Dormir" el programa durante los ms que queremos
        Thread.sleep(2*1000);
        System.out.println("albert");
        scanner.nextLine();
    }

    private void enterMainMenu(){
        System.out.println("--------------------------------------------------");
        System.out.println("Press enter to go back to the main menu");
        scanner.nextLine();
    }
    // Mètode per a mostrar un missatge per pantalla
    public void display(String s) {
        System.out.println(s);
    }
}
