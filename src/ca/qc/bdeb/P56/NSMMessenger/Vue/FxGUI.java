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
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author John
 */
public class FxGUI extends Application implements IVue {

    private ArrayList<Observateur> observateurs = new ArrayList<>();
    Stage currentStage;
    PageLogin login;
    Chat chat;
    CreationCompte compte;
    

    public static void main(String args[])
    {
        launch();
    }
    
    public FxGUI() {
        new NSMMessenger(this);
    }

    
    @Override
    public void updateLobbies(String[] lobbies) {
        if (chat != null)
            Platform.runLater(new Runnable() {

            @Override
            public void run() {
                chat.updateLobbies(lobbies);      }
            });
    }

    @Override
    public void ajouterMessage(Message message) {
        if (chat != null)
            Platform.runLater(new Runnable() {

            @Override
            public void run() {
                chat.ajouterMessage(message.lobby, message.user, message.message);  }
            });
            
    }

    @Override
    public void notifierNouvelleConnection(NotificationUtilisateurConnecte utilConnecte) {
        if (chat != null)
            Platform.runLater(new Runnable() {
            @Override
            public void run() {
                chat.notifierConnectionClient(utilConnecte.lobby, utilConnecte.username);  }
            });
            
    }

    @Override
    public void lobbyJoined(ArrayList<String> utilisateurs, String nom) {
        if (chat != null)
            Platform.runLater(new Runnable() {

            @Override
            public void run() {
                chat.lobbyJoined(utilisateurs, nom); }
            });
            
    }


    @Override
    public void afficherCreationCompte() {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                compte = (CreationCompte) changerFenetre("CreationCompte.fxml"); }
            };
        Platform.runLater(runnable);
        try {
            FXUtilities.runAndWait(runnable);
        } catch (InterruptedException ex) {
            Logger.getLogger(FxGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(FxGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        compte.setGui(this);
        currentStage.setTitle("Créer un compte");
        currentStage.show();
    }

    @Override
    public void afficherPageLogin() {
        login = (PageLogin) changerFenetre("PageLogin.fxml");
        login.setGui(this);
        currentStage.setTitle("Page de login");
        currentStage.show();
    }

    @Override
    public synchronized void afficherChat() {
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                chat = (Chat) changerFenetre("chat.fxml");
                chat.build();
         }};
        try {
            FXUtilities.runAndWait(runnable);
        } catch (InterruptedException ex) {
            Logger.getLogger(FxGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(FxGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        chat.setGUI(this);
        currentStage.setTitle("NSM Messenger");
        currentStage.show();
    }

    //TODO: Pas un objet...
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
        System.out.println("");
    }

    @Override
    public void retirerObservateur(Observateur o) {
        observateurs.remove(o);
    }

    @Override
    public void aviserObservateurs() {
        for (Observateur obs : observateurs)
            obs.changementEtat();
    }

    @Override
    public void aviserObservateurs(Enum<?> e, Object o) {
        for (Observateur obs : observateurs)
            obs.changementEtat(e, o);
    }

    @Override
    public void start(Stage stage) throws Exception {
        currentStage = stage;
        afficherPageLogin();
    }

}
