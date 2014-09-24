/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Controleur;

import ca.qc.bdeb.P56.NSMMessenger.IClient;
import ca.qc.bdeb.P56.NSMMessenger.NSMClient;
import ca.qc.bdeb.P56.NSMMessenger.Vue.ChatGUI;
import ca.qc.bdeb.P56.NSMMessenger.Vue.IVue;
import ca.qc.bdeb.P56.NSMMessengerCommunication.CreationResponse;
import ca.qc.bdeb.P56.NSMMessengerCommunication.LoginResponse;
import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.P56.NSMMessengerServer.LobbyODT;
import ca.qc.bdeb.mvc.Observateur;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author 1150275
 */
public class NSMMessenger implements Observateur {

    public enum Observation {

        MESSAGERECU, LOGIN, CREATION, REPONSELOGIN, REPONSECREATION, 
        ENVOIMESSAGE, UPDATELOBBIES, JOINLOBBY, LEAVELOBBY
    }

    IClient client;
    IVue gui;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
    // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        NSMMessenger m = new NSMMessenger();

    }

    public NSMMessenger() {
        client = new NSMClient(this);
        client.connect();
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
                client.joinLobby((int) o);
                break;
            case LEAVELOBBY:
                client.leaveLobby((int) o);
            break;
            case UPDATELOBBIES:
                if(gui != null)
                    gui.updateLobbies((LobbyODT[])o);
                break;
            case MESSAGERECU:
                Message message = (Message) o;
                if(gui!= null)
                    gui.ajouterMessage(message.lobby, message.user, message.message);
                break;
            case REPONSELOGIN:
                switch(((LoginResponse) o).response)
                {
                    case ACCEPTED:
                        gui.lancerChat();
                        break;
                    case REFUSED:
                        gui.showLoginError();
                        break;
                }
                break;
            case REPONSECREATION:
                switch(((CreationResponse) o).response)
                {
                    case ACCEPTED:
                        gui.showAccountCreated();
                        break;
                    case EXISTING_USERNAME:
                        gui.showUsernameError();
                        break;
                }
                break;
            case LOGIN:
                client.login((InfoLogin) o);
                break;
            case CREATION:
                client.creerCompte((InfoCreation) o);
                break;
                
            case ENVOIMESSAGE:
                client.sendMessage((Message) o);
                break;
        }
    }

}
