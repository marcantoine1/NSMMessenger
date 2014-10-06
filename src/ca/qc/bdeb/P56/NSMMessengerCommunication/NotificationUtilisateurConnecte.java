/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author patrick
 */
public class NotificationUtilisateurConnecte {
    public boolean connecte;
    public String username;
    public String lobby;
    
    public NotificationUtilisateurConnecte(){}
    public NotificationUtilisateurConnecte(String username, String lobby, boolean connecte){
        this.username = username;
        this.lobby = lobby;
        this.connecte = connecte;
    }
}
