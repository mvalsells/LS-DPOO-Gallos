import java.util.Scanner;



public class Menu {
    private Scanner scanner; // agafem dades menu

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void mostraMenu(Competicio competicio){
        //Nom
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to competition: ");
        sb.append(competicio.getName());
        System.out.println(sb.toString());
        //Start
        sb = new StringBuilder();
        sb.append("Starts on ");
        sb.append(competicio.getStartDate());
        System.out.println(sb.toString());
        //End
        sb.append("Ends on ");
        sb.append(competicio.getEndDate());
        System.out.println(sb.toString());
        //Phases
        sb = new StringBuilder();
        sb.append("Phases: ");
        sb.append(competicio.numFase());
        System.out.println(sb.toString());
        //Participants
        sb = new StringBuilder();
        sb.append("Currently: ");
        sb.append(competicio.numParticipants());
        sb.append(" participants");
        System.out.println(sb.toString());

        if(!competicio.haComençat()){
            System.out.println("\nCompetition hasn't started yet. Do you want to:");
            System.out.println("\t1. Register");
            System.out.println("\t2. Leave");

        }else{
            System.out.println("\nCompetition started. Do you want to:");
            System.out.println("\t1. Login");
            System.out.println("\t2. Leave");
        }


    }



    public int demanaOpcio(){
        System.out.print("\nChoose an option: ");
        int opcio = scanner.nextInt();
        scanner.nextLine();
        return opcio;
    }

    /*public void executaOpcio(){

    }

    public void executaMenu(){

    }*/

    // Mètode per a mostrar un missatge per pantalla
    public void display(String s) {
        System.out.println(s);
    }
}
