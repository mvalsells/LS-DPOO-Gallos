import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner scanner; // agafem dades menu

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void welcome(Competicio competicio) throws IOException {
        //Nom
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to competition: ");
        sb.append(competicio.getName());
        System.out.println(sb.toString());
        //Start
        sb = new StringBuilder();
        sb.append("Starts on ");
        sb.append(competicio.getStartDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        System.out.println(sb.toString());
        //End
        sb = new StringBuilder();
        sb.append("Ends on ");
        sb.append(competicio.getEndDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        System.out.println(sb.toString());
        //Phases
        sb = new StringBuilder();
        sb.append("Phases: ");
        sb.append(competicio.numFases());
        System.out.println(sb.toString());
        //Participants
        sb = new StringBuilder();
        sb.append("Currently: ");
        sb.append(competicio.numParticipants());
        sb.append(" participants");
        System.out.println(sb.toString());

        if(!competicio.haComencat()){
            System.out.println("\nCompetition hasn't started yet. Do you want to:");
            System.out.println("\t1. Register");
            System.out.println("\t2. Leave");

        } else if (!competicio.haAcabat()){
            System.out.println("\nCompetition started. Do you want to:");
            System.out.println("\t1. Login");
            System.out.println("\t2. Leave");
        } else {
            sb = new StringBuilder();
            System.out.println("Competition has ended!");
            sb.append("The winner is: ");
            sb.append(competicio.nomGuanyador());
            System.out.println(sb.toString());
            System.out.println("Press enter to exit");
            scanner.nextLine(); //No ser perquè s'han de fer 2 enters
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
        switch (estat) {
            case 0:
                System.out.println("Registration completed!\n--------------------------------------------------");
                System.out.println("Press enter to go back to the main menu");
                scanner.nextLine();
                break;
            case 1:
                System.out.println("A rapper with this artistic name already exists");
                break;
            case 2:
                System.out.println("Birth date has an invalid format");
                break;
            case 3:
                System.out.println("Country does not exist in our system");
                break;
            case 4:
                System.out.println("Photo URL has an invalid format");
                break;
            default:
                System.out.println("The registration process returned an unexpected status error");
                break;
        }
    }

    // Mètode per a mostrar un missatge per pantalla
    public void display(String s) {
        System.out.println(s);
    }
}
