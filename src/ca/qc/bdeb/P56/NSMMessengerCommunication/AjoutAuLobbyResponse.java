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
public class AjoutAuLobbyResponse {
    String reponse;
    public AjoutAuLobbyResponse(){
        
    }            
    public AjoutAuLobbyResponse(String reponse){
        this.reponse = reponse;
    }

    public String getReponse() {
        return reponse;
    }
}
