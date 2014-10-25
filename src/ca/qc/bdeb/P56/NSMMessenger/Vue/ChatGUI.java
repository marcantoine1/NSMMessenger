/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger.Vue;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.P56.NSMMessengerCommunication.NotificationUtilisateurConnecte;
import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileResponse;
import ca.qc.bdeb.mvc.Observateur;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


/**
 *
 * @author 1150580
 */
public class ChatGUI implements IVue{
    
    private ArrayList<Observateur> observateurs = new ArrayList<>();
     
    public ChatPrimitif chat;
    private CompteUtilisateur creationCompte;
    private Login login;
    
    public static void main(String args[])
    {
        new ChatGUI();
    }
    
    public ChatGUI()
    {
        new NSMMessenger(this);
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel("Nimbus");
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        afficherPageLogin();
    }
    
    @Override
    public void ajouterMessage(Message message)
    {
        if(chat != null)
            chat.ajouterMessage(message.lobby, message.user, message.message);
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
    public void updateLobbies(String[] lobbies)
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
    public void showIpError(){
        JOptionPane.showMessageDialog(login, "Erreur: Adresse du serveur invalide");
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
        {
            if(utilConnecte.connecte)
                chat.notifierConnectionClient(utilConnecte.lobby, utilConnecte.username);
            else
                chat.notifierDeconnectionClient(utilConnecte.lobby, utilConnecte.username);
        }
    }
    
    
    void showIpError(String text) {
        JOptionPane.showMessageDialog(login, text + "n'est pas une adresse ip valide!");
    }

    @Override
    public void afficherProfil(ProfileResponse profileResponse) {
        chat.afficherProfil(profileResponse);
    }

    @Override
    public void afficherPageLogin() {
        login = new Login(this);
        login.setVisible(true);
    }

    @Override
    public void afficherChat() {
       login.setVisible(false);
       chat = new ChatPrimitif(this);
       chat.setVisible(true);
    }




    
}
