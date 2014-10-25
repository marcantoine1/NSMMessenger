/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger.Vue;

/**
 *
 * @author John
 */
public abstract class Fenetre {
    
    FxGUI gui;

    public abstract String getPathFXML();

    public abstract String getTitre();

    public void setGui(FxGUI gui) {
        this.gui = gui;
    }
    
    
    
}
