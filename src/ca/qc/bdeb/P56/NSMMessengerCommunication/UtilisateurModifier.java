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
    Utilisateur ancien;
    Utilisateur nouveau;
    public UtilisateurModifier(){
    }
    public UtilisateurModifier(Utilisateur ancien,Utilisateur nouveau){
        this.ancien = ancien;
        this.nouveau = nouveau;
    }

    public Utilisateur getAncien() {
        return ancien;
    }

   

    public Utilisateur getNouveau() {
        return nouveau;
    }

    
}
