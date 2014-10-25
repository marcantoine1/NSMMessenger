/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.P56.NSMMessengerCommunication.NotificationUtilisateurConnecte;
import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileResponse;
import ca.qc.bdeb.mvc.Observable;
import java.util.ArrayList;

/**
 *
 * @author 1150580
 */
public interface IVue extends Observable{
    
    public void updateLobbies(String[] lobbies);
    
    public void ajouterMessage(Message message);
    
    public void notifierNouvelleConnection(NotificationUtilisateurConnecte utilConnecte);
    

    
    public void afficherCreationCompte();

    public void afficherPageLogin();
    public void afficherChat();
    public void showAccountCreated();

    public void showUsernameError();
    
    public void showLoginError();
    
    public void lobbyJoined(ArrayList<String> utilisateurs, String nom);

    public void showIpError();

    public void afficherProfil(ProfileResponse profileResponse);
    
}
