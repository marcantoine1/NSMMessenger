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
    public String utilisateurRecherche;
    public String utilisateurRecherchant;
    public ProfileRequest(){
        
    }
   public ProfileRequest(String utilisateurRecherche, String utilisateurRecherchant){
       this.utilisateurRecherchant = utilisateurRecherchant;
       this.utilisateurRecherche = utilisateurRecherche;
        
    }
}
