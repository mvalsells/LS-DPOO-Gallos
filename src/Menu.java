import java.util.Scanner;



public class Menu {
    private Scanner scanner; // agafem dades menu

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void mostraMenu(){
        System.out.println("\tWelcome to competition:");
        System.out.println("\tStarts on");
        System.out.println("\tEnds on");
        System.out.println("\tPhases:");
        System.out.println("\tCurrently: participants");

        //if !start on
        System.out.println("\t\tCompetition hasn't started yet. Do you want to:");
        System.out.println("\t\t1. Register");
        System.out.println("\t2. Leave");

        //if start on
        System.out.println("\t\tCompetition started. Do you want to:");
        System.out.println("\t\t1. Login");
        System.out.println("\t2. Leave");

    }

    public int demanaOpcio(){
        System.out.print("Choose an option");
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
