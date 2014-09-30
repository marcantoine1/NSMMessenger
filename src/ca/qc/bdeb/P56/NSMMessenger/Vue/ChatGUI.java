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
import ca.qc.bdeb.mvc.Observateur;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author 1150580
 */
public class ChatGUI implements IVue{
    
    ArrayList<Observateur> observateurs = new ArrayList<>();
     
    ChatPrimitif chat;
    CompteUtilisateur creationCompte;
    Login login;
    
    public ChatGUI(Observateur observer)
    {
        ajouterObservateur(observer);
        login = new Login(this);
    }
    
    public void ajouterMessage(Message message)
    {
        if(chat != null)
            chat.ajouterMessage(message.lobby, message.user, message.message);
    }
    
    public void lancerChat()
    {
        login.setVisible(false);
        chat = new ChatPrimitif(this);
        chat.setVisible(true);
    }
    
    public void retourLogin()
    {
        if(creationCompte != null)
        {
            creationCompte.dispose();
            creationCompte = null;
        }
        if(chat != null)
        {
            chat.dispose();
            chat = null;
        }
        
        if(login != null)
            login.setVisible(true);
    }
    
    @Override
    public void updateLobbies(LobbyDTO[] lobbies)
    {
        if(chat != null)
            chat.updateLobbies(lobbies);
    }
    @Override
    public void lobbyJoined(ArrayList<String> utilisateurs, String nom){
        if(chat != null)
            chat.lobbyJoined(utilisateurs, nom);
    }
   
    
    @Override
    public void afficherCreationCompte()
    {
        login.setVisible(false);
        creationCompte = new CompteUtilisateur(this);
    }

    @Override
    public void showAccountCreated() {
        retourLogin();
        JOptionPane.showMessageDialog(login, "Compte créé!" );
    }

     @Override
    public void showUsernameError() {
        JOptionPane.showMessageDialog(creationCompte, "Erreur: nom d'utilisateur existe déja.");
    }
    
    
     @Override
    public void showLoginError() {
        JOptionPane.showMessageDialog(login, "Erreur: nom d'utilisateur ou mot de passe incorrect.");
    }
    
    @Override
    public void ajouterObservateur(Observateur o) {
        observateurs.add(o);
    }

    @Override
    public void retirerObservateur(Observateur o) {
        observateurs.remove(o);
    }

    @Override
    public void aviserObservateurs() {
        for(Observateur obs : observateurs)
            obs.changementEtat();
    }

    @Override
    public void aviserObservateurs(Enum<?> e, Object o) {
        for(Observateur obs : observateurs)
            obs.changementEtat(e, o);
    }

    @Override
    public void notifierNouvelleConnection(NotificationUtilisateurConnecte utilConnecte) {
        if(chat != null)
            chat.notifierConnectionClient(utilConnecte.idLobby, utilConnecte.username);
        chat.nouvelUtilisateurLobby(null, null);
    }

    
}
