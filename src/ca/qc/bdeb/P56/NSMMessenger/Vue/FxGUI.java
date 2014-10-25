/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.P56.NSMMessengerCommunication.NotificationUtilisateurConnecte;
import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileResponse;
import ca.qc.bdeb.mvc.Observateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author John
 */
public class FxGUI extends Application implements IVue {

    private ArrayList<Observateur> observateurs = new ArrayList<>();
    Stage currentStage;
    PageLogin login;
    Chat chat;
    
    public FxGUI()
    {
    }
    
    public FxGUI(Observateur observer)
    {
        ajouterObservateur(observer);
        launch();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //TODO: changer le creation compte pour la page de login
         FXMLLoader fxmlLoader =new FXMLLoader(PageLogin.class.getResource("PageLogin.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            login = (PageLogin)fxmlLoader.getController();
            login.build();   
            Scene scene = new Scene(root);
            primaryStage.setTitle("Page de login");
            primaryStage.setScene(scene);
            primaryStage.show();
            login.setGui(this);
            currentStage = primaryStage;

    }

    @Override
    public void updateLobbies(String[] lobbies) {
        if(chat != null)
            chat.updateLobbies(lobbies);
    }

    @Override
    public void ajouterMessage(Message message) {
        if(chat != null)
            chat.ajouterMessage(message.lobby, message.user, message.message);
    }

    @Override
    public void notifierNouvelleConnection(NotificationUtilisateurConnecte utilConnecte) {
        if(chat != null)
            chat.notifierConnectionClient(utilConnecte.lobby, utilConnecte.username);
    }
    
    @Override
    public void lobbyJoined(ArrayList<String> utilisateurs, String nom) {
        if(chat != null)
            chat.lobbyJoined(utilisateurs, nom);
    }


    @Override
    public void lancerChat() {
        try {
            currentStage.close();
            currentStage = new Stage();
            
            FXMLLoader fxmlLoader =new FXMLLoader(Chat.class.getResource("chat.fxml"));
            chat = (Chat)fxmlLoader.getController();
            chat.build();   
            Parent root = (Parent)fxmlLoader.load();
            Scene scene = new Scene(root);
            currentStage.setTitle("NSM Messenger");
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FxGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void afficherCreationCompte() {
        try {
            currentStage.close();
            currentStage = new Stage();
            
            FXMLLoader fxmlLoader =new FXMLLoader(Chat.class.getResource("CreationCompte.fxml"));
            //compte = (CreationCompte)fxmlLoader.getController();
           // compte.build();   
            Parent root = (Parent)fxmlLoader.load();
            Scene scene = new Scene(root);
            currentStage.setTitle("Créer un compte");
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FxGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    //TODO: la page de login
    @Override
    public void showAccountCreated() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showUsernameError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showLoginError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showIpError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afficherProfil(ProfileResponse profileResponse) {
        //TODO: affiche le profil dans le chat
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
            obs.changementEtat(e,o);
    }
    
}
