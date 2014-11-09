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
public class ProfileRequest {
    public String utilisateurRecherche;
    public String utilisateurRecherchant;
    public ProfileRequest(){
        
    }

    public String getUtilisateurRecherche() {
        return utilisateurRecherche;
    }

    public String getUtilisateurRecherchant() {
        return utilisateurRecherchant;
    }
   public ProfileRequest(String utilisateurRecherche, String utilisateurRecherchant){
       this.utilisateurRecherchant = utilisateurRecherchant;
       this.utilisateurRecherche = utilisateurRecherche;
        
    }
}
