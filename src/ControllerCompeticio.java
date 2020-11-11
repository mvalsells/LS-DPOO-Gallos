

public class ControllerCompeticio {

        private Competicio competició;        // Model de dades
        private  Menu menu;              // Menu, interfície gràfica

        public ControllerCompeticio (Competicio competició, Menu menu) {
            this.competició = competició;
            this.menu = menu;

        }

        public void executaMenu(){
                int opcio = -1;

                while(opcio != 2){
                     //mostra menu i demana opcio
                     menu.mostraMenu();
                     opcio = menu.demanaOpcio();

                     switch (opcio){
                             case 1:
                                     System.out.println("\t\uD83D\uDE18");
                                     break;
                             case 2:
                                     System.out.println("\t\uD83C\uDDFA\uD83C\uDDF8");
                                     break;
                             default:
                                     menu.display("Entra una opció correcta!");
                                     break;
                     }



                }
        }

        public void registraUsuari(){}

        public void numFase(){}

        public void numParticipants(){}

        public void haAcabat(){}

        public void faseActual(){}

        public void haComençat(){

        }

}
