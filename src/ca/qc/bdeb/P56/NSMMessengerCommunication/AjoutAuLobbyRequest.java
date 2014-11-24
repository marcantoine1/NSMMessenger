/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author Guillaume
 */
public class AjoutAuLobbyRequest {
        
    String utilisateurDemande;
    String utilisateurDemandant;
    String nomLobby;
    public AjoutAuLobbyRequest(){
    }
    public AjoutAuLobbyRequest(String utilisateurDemandant,String utilisateurDemande, String nomLobby){
        this.utilisateurDemandant = utilisateurDemandant;
        this.utilisateurDemande = utilisateurDemande;
        this.nomLobby = nomLobby;
    }

    public String getNomLobby() {
        return nomLobby;
    }

    public String getUtilisateurDemande() {
        return utilisateurDemande;
    }

    public String getUtilisateurDemandant() {
        return utilisateurDemandant;
    }
    
    
}
