package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessengerCommunication.AjoutLobbyPopUp;
import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.P56.NSMMessengerCommunication.NotificationUtilisateurConnecte;
import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileResponse;
import ca.qc.bdeb.P56.NSMMessengerCommunication.SelfProfileResponse;
import ca.qc.bdeb.mvc.Observable;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 1150580
 */
public interface IVue extends Observable {

    public void updateLobbies(String[] lobbies);

    public void ajouterMessage(Message message);

    public void setContacts(ArrayList<String> contacts);

    public void notifierNouvelleConnection(NotificationUtilisateurConnecte utilConnecte);

    public void afficherCreationCompte();

    public void afficherPageLogin();

    public void afficherChat();

    public boolean isProfilAffiche();

    public void afficherCompteCreer();

    public void showUsernameError();

    public void showLoginError();

    public void lobbyJoined(ArrayList<String> utilisateurs, String nom);

    public void showIpError();

    public void showIpValidated();

    public void updateProfil(ProfileResponse profil);
    public void mettreAJourUtilisateurs(List<String> utilisateurs);
    public void afficherProfil(ProfileResponse profileResponse);
    
    public void updateSelfProfil(SelfProfileResponse selfProfil);
    
    public void afficherSelfProfil(SelfProfileResponse selfProfil);

    public void setConnectes(ArrayList<String> utilisateurs);
    
    public void showContactError();
    
    public void afficherPopUpLobby(AjoutLobbyPopUp o);
    
    public void afficherDemandeJoindreLobbyEnvoye(String message);
    public void showEmailError();
    public void showUsagerError();

    void mettreAJourImage(String image);
}
