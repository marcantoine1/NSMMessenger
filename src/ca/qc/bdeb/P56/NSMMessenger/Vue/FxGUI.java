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
        currentStage=primaryStage;
         afficherPageLogin();
            primaryStage.setTitle("Page de login");
            primaryStage.show();


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
 void afficherPageLogin(){
    login=(PageLogin)changerFenetre("PageLogin.fxml");
  login.setGui(this);
}
void afficherChat(){
    changerFenetre("chat.fxml");
}
    private Object changerFenetre(String fenetre) {
        Parent root = null;
        FXMLLoader fichier = new FXMLLoader(FxGUI.class.getResource(fenetre));
        try {

            root = (Parent) fichier.load();
            Scene scene = currentStage.getScene();
            if (scene == null) {
                scene = new Scene(root);
                currentStage.setScene(scene);
            } else {
                currentStage.getScene().setRoot(root);
            }
        } catch (IOException e) {
            Logger.getLogger(FxGUI.class.getName()).log(Level.SEVERE, null, e);
        }
        currentStage.sizeToScene();
return fichier.getController();
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
