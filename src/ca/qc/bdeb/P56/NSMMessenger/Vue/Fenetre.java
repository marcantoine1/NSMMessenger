/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Vue;

/**
 * @author 1150580
 */
public abstract class Fenetre {

    FxGUI gui;
    boolean fenetreCourante;

    public abstract String getPathFXML();

    public abstract String getTitre();

    public void setFenetreCourante(boolean fenetreCourante) {
        this.fenetreCourante = fenetreCourante;
    }

    public boolean getFenetreCourante() {
        return this.fenetreCourante;
    }

    public void setGui(FxGUI gui) {
        this.gui = gui;
    }

}
