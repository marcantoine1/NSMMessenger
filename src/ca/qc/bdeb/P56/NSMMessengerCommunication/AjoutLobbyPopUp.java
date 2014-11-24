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
public class AjoutLobbyPopUp {
    String usernameDemandant;
    String lobby;
    public AjoutLobbyPopUp(){
        
    }
    public AjoutLobbyPopUp(String usernameDemandant,String lobby){
    this.lobby = lobby;
    this.usernameDemandant = usernameDemandant;
    }

    public String getUsernameDemandant() {
        return usernameDemandant;
    }

    public String getLobby() {
        return lobby;
    }
}
