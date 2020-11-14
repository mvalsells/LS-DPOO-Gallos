import java.util.Scanner;



public class Menu {
    private Scanner scanner; // agafem dades menu

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void mostraMenu(Competicio competicio){
        System.out.println("Welcome to competition: " + competicio.getName());
        System.out.println("Starts on " + competicio.getStartDate());
        System.out.println("Ends on " + competicio.getEndDate());
        System.out.println("Phases: " + competicio.getPhases().size());
        System.out.println("Currently: "+ competicio.getRappers().size()+ " participants");

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
        System.out.print("\nChoose an option");
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
