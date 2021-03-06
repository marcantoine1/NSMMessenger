package ca.qc.bdeb.P56.NSMMessenger.Controleur;

import ca.qc.bdeb.P56.NSMMessenger.Application.IClient;
import ca.qc.bdeb.P56.NSMMessenger.Application.InfoCreation;
import ca.qc.bdeb.P56.NSMMessenger.Application.InfoLogin;
import ca.qc.bdeb.P56.NSMMessenger.Application.JukeBox;
import ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient;
import ca.qc.bdeb.P56.NSMMessenger.Utils.Fichiers;
import ca.qc.bdeb.P56.NSMMessenger.Vue.IVue;
import ca.qc.bdeb.P56.NSMMessengerCommunication.*;
import ca.qc.bdeb.mvc.Observateur;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import static java.util.logging.Logger.getLogger;

/**
 * @author 1150275
 */
public class NSMMessenger implements Observateur {

    private final IClient client;
    private final IVue gui;

    public NSMMessenger(IVue gui) {
        this.gui = gui;
        client = new NSMClient(this);

        gui.ajouterObservateur(this);
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
                        JukeBox.play("login");
                        assert gui != null;
                        gui.afficherChat();
                        client.sendListeContactRequest();
                        break;
                    case REFUSED:
                        assert gui != null;
                        gui.showLoginError();
                        Fichiers.supprimerFichier(NSMClient.PATH_LOGIN);
                        break;
                    case ERROR:
                        Fichiers.supprimerFichier(NSMClient.PATH_LOGIN);
                        break;
                }
                break;
            case REPONSECREATION:
                switch (((CreationResponse) o).response) {
                    case ACCEPTED:
                        CreationResponse response = (CreationResponse) o;
                        gui.afficherCompteCreer();
                        try {
                            sleep(300);
                        } catch (InterruptedException ex) {
                            getLogger(NSMMessenger.class.getName()).log(Level.SEVERE, null, ex);
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
                if (gui.isProfilAffiche()) {
                    gui.updateProfil((ProfileResponse) o);
                } else {
                    gui.afficherProfil((ProfileResponse) o);
                }
                break;
            case TESTERCONNECTION:
                if (client.connect() == 1) {
                    gui.showIpError();
                } else {
                    gui.showIpValidated();
                }

                break;
            case CONTACTREQUEST:
                client.sendContactRequest((String) o);
                client.sendListeContactRequest();
                break;
            case CONTACTEFFACERREQUEST:
                client.sendContactEffacerRequest((String) o);
                client.sendListeContactRequest();
                break;
            case LISTECONTACTREQUEST:
                client.sendListeContactRequest();
                break;
            case CONTACTRESPONSE:
                gui.setContacts(((ListeContactResponse) o).getListeContact());
                break;
            case CONNECTIONRESPONSE:
                gui.setConnectes((ArrayList<String>) o);
                break;
            case SELFPROFILERESPONSE:
                if (gui.isProfilAffiche()) {
                    gui.updateSelfProfil((SelfProfileResponse) o);
                } else {
                    gui.afficherSelfProfil((SelfProfileResponse) o);
                }
                break;
            case CONTACTRESPONSEFAILED:
                gui.showContactError();
                break;
            case UTILISATEURMODIFIER:
                client.sendUtilisateurModifier((UtilisateurModifier) o);
                break;
            case LOGOUTREQUEST:
                client.sendLogoutRequest();
                gui.afficherPageLogin();
                break;
            case RETRIEVEPASSWORDREQUEST:
                client.sendGenererPassword((String) o);
                break;
            case REQUESTAJOUTAULOBBY:
                client.sendAjoutAuLobbyRequest((AjoutLobbyInfo) o);
                break;
            case AJOUTLOBBYPOPUP:
                AjoutLobbyPopUp popUp = (AjoutLobbyPopUp) o;
                gui.afficherPopUpLobby(popUp);
                break;
            case AJOUTAULOBBYRESPONSE:
                AjoutAuLobbyResponse reponse = (AjoutAuLobbyResponse) o;
                gui.afficherDemandeJoindreLobbyEnvoye(reponse.getReponse());
                break;
            case ERREUREMAILINVALIDE:
                gui.showEmailError();
                break;
            case ERREURUSAGERINVALIDE:
                gui.showUsagerError();
                break;
            case CHARGERSOUVENUS:
                gui.mettreAJourUtilisateurs((List<String>) o);
                break;
            case SAUVEGARDERLOGIN:
                Fichiers.sauvegarderFichier(NSMClient.PATH_LOGIN, o.toString());
                break;
            case SUPPRIMERLOGIN:
                Fichiers.supprimerFichier(NSMClient.PATH_LOGIN);
                break;
            case LOGINDEMARRAGE:
                client.connecteDemarrage();
                break;
            case SONREQUEST:
                JukeBox.play("messageRecu");
            case DEMARRAGE_UTILISATEURS:
                client.chargerUtilisateurs();
                break;
            case IMAGEREQUEST:
                if(client.connect()==0) {

                    client.demanderImage((String) o);
                }else{
                    gui.showIpError();
                }
                break;
            case IMAGERECUE:

                    gui.mettreAJourImage(((ImageReponse) o).getImage());
                break;
            case SAUVEGARDER_SOUVENU:
                client.ecrireSouvenu((String)o);

        }
    }

    public enum Observation {

        MESSAGERECU, LOGIN, CREATION, REPONSELOGIN, REPONSECREATION,
        ENVOIMESSAGE, UPDATELOBBIES, JOINLOBBY, LEAVELOBBY, UTILISATEURCONNECTE,
        CREERLOBBY, LISTEUTILISATEURSLOBBY, LOBBYJOINED, ADRESSEIPCHANGEE,
        PROFILEREQUEST, PROFILERESPONSE, TESTERCONNECTION, CONTACTREQUEST,
        CONTACTEFFACERREQUEST, LISTECONTACTRESPONSE, LISTECONTACTREQUEST,
        CONTACTRESPONSE, CONNECTIONRESPONSE, SELFPROFILERESPONSE,
        CONTACTRESPONSEFAILED, UTILISATEURMODIFIER, LOGOUTREQUEST, RETRIEVEPASSWORDREQUEST,
        REQUESTAJOUTAULOBBY, RESPONSEAJOUTLOBBY, AJOUTLOBBYPOPUP, AJOUTAULOBBYRESPONSE,
        ERREURUSAGERINVALIDE, ERREUREMAILINVALIDE, SAUVEGARDERLOGIN, SUPPRIMERLOGIN, LOGINDEMARRAGE,
        DEMARRAGE_UTILISATEURS, IMAGEREQUEST, IMAGERECUE, CHARGERSOUVENUS,SONREQUEST,SAUVEGARDER_SOUVENU
    }

}
