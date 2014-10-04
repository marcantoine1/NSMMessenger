/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

import ca.qc.bdeb.P56.NSMMessengerServer.Modele.AccesBd;
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Utilisateur;

/**
 *
 * @author Marc-Antoine
 */
public class ProfileRequest {
    AccesBd acc = new AccesBd("NSMDatabase");
   public ProfileRequest(){
        
    }
    public Utilisateur getUtilisateur(String nom){
       return acc.chercherUtilisateur(nom);
    }
}
