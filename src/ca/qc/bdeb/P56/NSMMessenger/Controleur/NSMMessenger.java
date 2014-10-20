/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Controleur;

import ca.qc.bdeb.P56.NSMMessenger.IClient;
import ca.qc.bdeb.P56.NSMMessenger.NSMClient;
import ca.qc.bdeb.P56.NSMMessenger.Vue.Chat;
import ca.qc.bdeb.P56.NSMMessenger.Vue.ChatGUI;
import ca.qc.bdeb.P56.NSMMessenger.Vue.IVue;
import ca.qc.bdeb.P56.NSMMessenger.Vue.Profil;
import ca.qc.bdeb.P56.NSMMessengerCommunication.CreationResponse;
import ca.qc.bdeb.P56.NSMMessengerCommunication.LobbyJoinedNotification;
import ca.qc.bdeb.P56.NSMMessengerCommunication.LoginResponse;
import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.P56.NSMMessengerCommunication.NotificationUtilisateurConnecte;
import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileResponse;
import ca.qc.bdeb.mvc.Observateur;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * @author 1150275
 */
public class NSMMessenger extends Application  implements Observateur {

    public enum Observation {

        MESSAGERECU, LOGIN, CREATION, REPONSELOGIN, REPONSECREATION,
        ENVOIMESSAGE, UPDATELOBBIES, JOINLOBBY, LEAVELOBBY, UTILISATEURCONNECTE,
        CREERLOBBY, LISTEUTILISATEURSLOBBY, LOBBYJOINED, ADRESSEIPCHANGEE, PROFILEREQUEST,
        PROFILERESPONSE
    }

    private final IClient client;
    private final IVue gui;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel("Nimbus");
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        launch(args);
    }
    public NSMMessenger() {
        client = new NSMClient(this);
        gui = new ChatGUI(this);
    }

    @Override
    public void changementEtat() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void changementEtat(Enum<?> e, Object o) {
        Observation obs = (Observation) e;
        switch (obs) {
            case JOINLOBBY:
                client.joinLobby((String) o);
                break;
            case LEAVELOBBY:
                client.leaveLobby((String) o);
                break;
            case UPDATELOBBIES:
                if (gui != null) {
                    gui.updateLobbies((String[]) o);
                }
                break;
            case MESSAGERECU:
                if (gui != null) {
                    gui.ajouterMessage((Message) o);
                }
                break;
            case UTILISATEURCONNECTE:
                if (gui != null) {
                    gui.notifierNouvelleConnection((NotificationUtilisateurConnecte) o);
                }
                break;
            case REPONSELOGIN:
                switch (((LoginResponse) o).response) {
                    case ACCEPTED:
                        assert gui != null;
                        gui.lancerChat();
                        break;
                    case REFUSED:
                        assert gui != null;
                        gui.showLoginError();
                        break;
                    case ERROR:
                        break;
                }
                break;
            case REPONSECREATION:
                switch (((CreationResponse) o).response) {
                    case ACCEPTED:
                        CreationResponse response = (CreationResponse) o;
                        gui.showAccountCreated();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(NSMMessenger.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        InfoLogin infoLog = new InfoLogin();
                        infoLog.username = response.username;
                        infoLog.password = response.password;
                        client.login(infoLog);
                        break;
                    case EXISTING_USERNAME:
                        gui.showUsernameError();
                        break;
                }
                break;
            case LOGIN:
                if (client.connect() == 0) {
                    client.login((InfoLogin) o);
                } else {
                    gui.showIpError();
                }
                break;
            case CREATION:
                if (client.connect() == 0) {
                    client.creerCompte((InfoCreation) o);
                } else {
                    gui.showIpError();
                }
                break;

            case ENVOIMESSAGE:
                client.sendMessage((Message) o);
                break;
            case CREERLOBBY:
                client.creerLobby((String) o);
                break;
            case LISTEUTILISATEURSLOBBY:
                LobbyJoinedNotification ljn = (LobbyJoinedNotification) o;
                gui.lobbyJoined(ljn.listeUtilisateurs, ljn.nom);
                break;
            case ADRESSEIPCHANGEE:
                client.changerIp((String) o);
                break;
            case PROFILEREQUEST:
                client.sendProfileRequest((String) o);
                break;
            case PROFILERESPONSE:
                gui.afficherProfil((ProfileResponse) o);
                break;
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
       FXMLLoader fxmlLoader =new FXMLLoader(Chat.class.getResource("chat.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            Chat chat = (Chat)fxmlLoader.getController();
            chat.build();   
            Scene scene = new Scene(root);
            primaryStage.setTitle("NSM Messenger");
            primaryStage.setScene(scene);
            primaryStage.show();

//        FXMLLoader fxmlLoader =new FXMLLoader(Profil.class.getResource("Profil.fxml"));
//            Parent root = (Parent)fxmlLoader.load();
//            Profil profil=(Profil)fxmlLoader.getController();
//            profil.build();
//            Scene scene= new Scene(root);
//            primaryStage.setTitle("Page de profil");
//            primaryStage.setScene(scene);
//            primaryStage.setResizable(false);
//            primaryStage.show();

    }

}
