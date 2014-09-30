/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessengerCommunication.LobbyJoinedNotification;
import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.P56.NSMMessengerCommunication.NotificationUtilisateurConnecte;
import ca.qc.bdeb.P56.NSMMessengerServer.LobbyDTO;
import ca.qc.bdeb.mvc.Observable;
import java.util.ArrayList;

/**
 *
 * @author 1150580
 */
public interface IVue extends Observable{
    
     public void updateLobbies(LobbyDTO[] lobbies);
    
    public void ajouterMessage(Message message);
    
    public void notifierNouvelleConnection(NotificationUtilisateurConnecte utilConnecte);
    public void lancerChat();
    
    public void afficherCreationCompte();

    public void showAccountCreated();

    public void showUsernameError();
    
    public void showLoginError();
    
    public void lobbyJoined(ArrayList<String> utilisateurs, String nom);
    
}
