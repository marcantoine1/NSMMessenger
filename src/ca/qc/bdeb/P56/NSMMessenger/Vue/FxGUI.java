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
    PageLogin login = new PageLogin();
    Chat chat = new Chat();
    CreationCompte compte = new CreationCompte();

    public static void main(String args[]) {
        launch();
    }

    public FxGUI() {
        new NSMMessenger(this);
    }

    @Override
    public void updateLobbies(String[] lobbies) {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                chat.updateLobbies(lobbies);
            }
        });
    }

    @Override
    public void ajouterMessage(Message message) {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                chat.ajouterMessage(message.lobby, message.user, message.message);
            }
        });

    }

    @Override
    public void notifierNouvelleConnection(NotificationUtilisateurConnecte utilConnecte) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                chat.notifierConnectionClient(utilConnecte.lobby, utilConnecte.username);
            }
        });

    }

    @Override
    public void lobbyJoined(ArrayList<String> utilisateurs, String nom) {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                chat.lobbyJoined(utilisateurs, nom);
            }
        });

    }

    @Override
    public void afficherCreationCompte() {
        
       
        
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                compte = (CreationCompte) changerFenetre(compte);
                compte.build();
            }
        };
        Platform.runLater(runnable);
        FXUtilities.runAndWait(runnable);
    }

    @Override
    public void afficherPageLogin() {
        login = (PageLogin) changerFenetre(login);
    }

    @Override
    public synchronized void afficherChat() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                chat = (Chat) changerFenetre(chat);
                chat.build();
            }
        };
        FXUtilities.runAndWait(runnable);

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
    public void showAccountCreated() {
        afficherChat();
    }

    @Override
    public void showUsernameError() {
        
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Création de compte impossible");
                a.setContentText("Votre nom de compte n'est pas valide ou est déjà utilisé.");
                a.initOwner(currentStage);
                a.initModality(Modality.WINDOW_MODAL);
                a.setHeaderText(null);
                a.setGraphic(null);
                a.show();
            }
        };
        Platform.runLater(runnable);
        FXUtilities.runAndWait(runnable);
        
        
    }

  @Override
    public void showLoginError() {
        
         Runnable runnable = new Runnable() {

            @Override
            public void run() {
                Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Login impossible");
                a.setContentText("Erreur: nom d'utilisateur ou mot de passe incorrect.");
                a.initOwner(currentStage);
                a.initModality(Modality.APPLICATION_MODAL);
                a.setHeaderText(null);
                a.setGraphic(null);
                a.show();
            }
        };
        Platform.runLater(runnable);
        FXUtilities.runAndWait(runnable);
        
    }

    @Override
    public void showIpError() {
        Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Connection impossible");
                a.setContentText("Adresse IP invalide ou serveur indisponible.");
                a.initOwner(currentStage);
                a.initModality(Modality.APPLICATION_MODAL);
                a.setHeaderText(null);
                a.setGraphic(null);
                a.show();
    }

    @Override
    public void afficherProfil(ProfileResponse profileResponse) {
        //TODO : Transformer cette fenêtre en "PopOver", elle ne doit pas fermer les autres fenetres
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                Profil profil = new Profil();
                profil = (Profil) changerFenetre(profil);
                profil.setProfil(profileResponse);
                profil.build();
            }
        };
        Platform.runLater(runnable);
        FXUtilities.runAndWait(runnable);
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
        afficherPageLogin();
    }

}
