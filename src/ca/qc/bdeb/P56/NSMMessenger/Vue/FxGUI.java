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
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author John
 */
public class FxGUI extends Application implements IVue {

    private ArrayList<Observateur> observateurs = new ArrayList<>();
    Stage currentStage;
    FXMLControllerPageLogin login = new FXMLControllerPageLogin();
    FXMLControllerChat chat = new FXMLControllerChat();
    FXMLControllerCreationCompte compte = new FXMLControllerCreationCompte();

    public static void main(String args[]) {
        launch();
    }

    public FxGUI() {
        new NSMMessenger(this);
    }

    @Override
    public void updateLobbies(String[] lobbies) {

        FXUtilities.runAndWait(() -> {
            chat.updateLobbies(lobbies);
        });
    }

    @Override
    public void ajouterMessage(Message message) {

        Platform.runLater(() -> {
            chat.ajouterMessage(message.lobby, message.user, message.message);
        });

    }

    @Override
    public void notifierNouvelleConnection(NotificationUtilisateurConnecte utilConnecte) {

        Platform.runLater(() -> {
            chat.notifierConnectionClient(utilConnecte.lobby, utilConnecte.username);
        });

    }

    @Override
    public void lobbyJoined(ArrayList<String> utilisateurs, String nom) {

        Platform.runLater(() -> {
            chat.lobbyJoined(utilisateurs, nom);
        });

    }

    @Override
    public void afficherCreationCompte() {
        
       
        FXUtilities.runAndWait(() -> {
            compte = (FXMLControllerCreationCompte) changerFenetre(compte);
            compte.build();
        });
    }

    @Override
    public void afficherPageLogin() {
        login = (FXMLControllerPageLogin) changerFenetre(login);
    }

    @Override
    public synchronized void afficherChat() {
        
        FXUtilities.runAndWait(() -> {
            chat = (FXMLControllerChat) changerFenetre(chat);
            chat.build();
        });

    }

    private Fenetre changerFenetre(Fenetre fenetre) {
        Parent root = null;
        FXMLLoader fichier = new FXMLLoader(FxGUI.class.getResource(fenetre.getPathFXML()));
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
        currentStage.setTitle(fenetre.getTitre());
        currentStage.show();

        fenetre = (Fenetre) fichier.getController();
        fenetre.setGui(this);
        return fenetre;
    }

    @Override
    public void afficherCompteCreer() {
        afficherChat();
    }

    @Override
    public void showUsernameError() {
        
        FXUtilities.runAndWait(() -> {
            Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Création de compte impossible");
                a.setContentText("Votre nom de compte n'est pas valide ou est déjà utilisé.");
                a.initOwner(currentStage);
                a.initModality(Modality.WINDOW_MODAL);
                a.setHeaderText(null);
                a.setGraphic(null);
                a.show();
        });
        
        
    }

  @Override
    public void showLoginError() {  
        FXUtilities.runAndWait(() -> {
        Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Login impossible");
                a.setContentText("Erreur: nom d'utilisateur ou mot de passe incorrect.");
                a.initOwner(currentStage);
                a.initModality(Modality.APPLICATION_MODAL);
                a.setHeaderText(null);
                a.setGraphic(null);
                a.show();
        });
        
    }

    @Override
    public void showIpError() {
        FXUtilities.runAndWait(() -> {
        Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Connection impossible");
                a.setContentText("Adresse IP invalide ou serveur indisponible.");
                a.initOwner(currentStage);
                a.initModality(Modality.APPLICATION_MODAL);
                a.setHeaderText(null);
                a.setGraphic(null);
                a.show();
        });
    }

    @Override
    public void afficherProfil(ProfileResponse profileResponse) {
        FXUtilities.runAndWait(() -> {
            Stage profilStage = new Stage();
            FXMLControllerProfil profil = new FXMLControllerProfil();
            
            FXMLLoader fichier = new FXMLLoader(FxGUI.class.getResource(profil.getPathFXML()));
            try {
                Parent root = fichier.load();
                Scene scene = new Scene(root);
                profilStage.setScene(scene);
            }
            catch(IOException e) {
                Logger.getLogger(FxGUI.class.getName()).log(Level.SEVERE, null, e);
            }
            profilStage.initModality(Modality.WINDOW_MODAL);
            profilStage.setResizable(false);
            profilStage.initOwner(currentStage);
            profilStage.sizeToScene();
            profilStage.setTitle("Profil de " + profileResponse.username);
            profilStage.show();
            profil = (FXMLControllerProfil) fichier.getController();
            profil.setProfil(profileResponse);
            profil.build();
            profil.setGui(this);
        });
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
        for (Observateur obs : observateurs) {
            obs.changementEtat();
        }
    }

    @Override
    public void aviserObservateurs(Enum<?> e, Object o) {
        for (Observateur obs : observateurs) {
            obs.changementEtat(e, o);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        currentStage = stage;
        stage.setResizable(false);
        afficherPageLogin();
    }

}
