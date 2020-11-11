package sample;

import java.awt.*;

public class ControllerCompetició {
    private Competició competició;        // Model de dades
    private Menu menu;              // Menu, interfície gràfica

    public ControllerCompetició (Competició competició, Menu menu) {
        this.competició = competició;
        this.menu = menu;
    }

    public void registraUsuari(){}

    public void numFase(){}

    public void numParticipants(){}

    public void haAcabat(){}

    public void faseActual(){}

    public void haComençat(){}
}
