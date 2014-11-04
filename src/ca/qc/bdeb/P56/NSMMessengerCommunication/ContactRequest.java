/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author Marc-Antoine
 */
public class ContactRequest {
    String utilisateurDemander;
    String utilisateurDemandant;
    public ContactRequest(){
    }
    public ContactRequest(String utilisateurDemandant,String utilisateurDemander){
        this.utilisateurDemandant = utilisateurDemandant;
        this.utilisateurDemander = utilisateurDemander;
    }

    public String getUtilisateurDemander() {
        return utilisateurDemander;
    }

    public String getUtilisateurDemandant() {
        return utilisateurDemandant;
    }
    
    
    
}
