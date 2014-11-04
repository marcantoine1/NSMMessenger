/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessengerCommunication;

import java.util.ArrayList;

/**
 *
 * @author 1255389
 */
public class ConnectionResponse {

    public ArrayList<String> utilisateurs;

    public ArrayList<String> getUtilisateurs() {
        return utilisateurs;
    }

    public ConnectionResponse() {
        utilisateurs = new ArrayList<String>();
    }

    public void ajouterUtilisateur(String nom) {
        utilisateurs.add(nom);
    }

}
