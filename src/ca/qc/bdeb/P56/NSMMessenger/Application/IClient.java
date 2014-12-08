package ca.qc.bdeb.P56.NSMMessenger.Application;

import ca.qc.bdeb.P56.NSMMessengerCommunication.AjoutLobbyInfo;
import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.P56.NSMMessengerCommunication.UtilisateurModifier;
import ca.qc.bdeb.mvc.Observable;

/**
 *
 * @author 1150580
 */
public interface IClient extends Observable {

    public void sendMessage(Message message);

    public void login(InfoLogin il);

    public int connect();

    public void creerCompte(InfoCreation ic);

    public void joinLobby(String lobby);

    public void leaveLobby(String lobby);

    public void creerLobby(String name);

    public void disconnect();

    public void changerIp(String inetAddress);
    public void chargerUtilisateurs();
    public void sendProfileRequest(String string);
    public void connecteDemarrage();
    public void sendContactRequest(String nom);
    public void sendContactEffacerRequest(String nom);
    public void sendListeContactRequest();
    public void sendUtilisateurModifier(UtilisateurModifier util);
    public void sendLogoutRequest();
    public void sendGenererPassword(String username);
    public void sendAjoutAuLobbyRequest(AjoutLobbyInfo o);

    void demanderImage(String o);
}
