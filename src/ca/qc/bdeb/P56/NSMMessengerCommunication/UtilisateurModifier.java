/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

import ca.qc.bdeb.P56.NSMMessengerServer.Application.Utilisateur;

/**
 *
 * @author Marc-Antoine
 */
public class UtilisateurModifier {
    String[] ancien;
    String[] nouveau;
    public UtilisateurModifier(){
    }
    public UtilisateurModifier(String[]ancien,String nouveau[]){
        this.ancien = ancien;
        this.nouveau = nouveau;
    }

    public String[] getAncien() {
        return ancien;
    }

   

    public String[] getNouveau() {
        return nouveau;
    }

    
}
