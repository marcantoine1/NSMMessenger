package ca.qc.bdeb.P56.NSMMessenger.Application;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger.Observation;
import ca.qc.bdeb.P56.NSMMessenger.Utils.Fichiers;
import ca.qc.bdeb.P56.NSMMessengerCommunication.*;
import ca.qc.bdeb.P56.NSMMessengerCommunication.LobbyAction.Action;
import ca.qc.bdeb.mvc.Observateur;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static ca.qc.bdeb.P56.NSMMessengerCommunication.Communication.initialiserKryo;
import static java.net.InetAddress.getByName;
import static java.net.InetAddress.getLocalHost;

public class NSMClient implements IClient {

    public Client client;
    public String username = "";
    private String ipAdress = "localhost";
    //logs des messages, seulement utilisé pour les tests en ce moment
    public String messages = "";
    public ProfileResponse pr = new ProfileResponse();
    public ListeContactResponse lc = new ListeContactResponse();
    private static final String PATH_SOUVENUS = "src/ca/qc/bdeb/P56/Ressources/Souvenus.txt";
    private ArrayList<Observateur> observateurs = new ArrayList<>();
    public static final String PATH_LOGIN = "src/ca/qc/bdeb/P56/Ressources/Login.txt";

    public NSMClient() {
        init();
    }

    public NSMClient(Observateur o) {
        ajouterObservateur(o);
        init();
    }

    public void init() {
        client = new Client();
        initialiserKryo(client.getKryo());
        client.addListener(new ClientListener());


    }

    public void chargerUtilisateurs() {
        List<String> utilisateurs = chargerSouvenus();
        if (utilisateurs != null) {
            aviserObservateurs(Observation.CHARGERSOUVENUS, utilisateurs);
        } else {
            utilisateurs = new ArrayList<String>();
            utilisateurs.add(" ");
            aviserObservateurs(Observation.CHARGERSOUVENUS, utilisateurs);
        }
    }

    public void connecteDemarrage() {
        List<String> connecte;
        connecte = Fichiers.chargerFichier(PATH_LOGIN);
        if ((connecte) != null) {
            String[] loginInfo = connecte.get(0).split(";");
            System.out.println(loginInfo[1]);
            InfoLogin log = new InfoLogin();
            log.username = loginInfo[0];
            log.password = loginInfo[1];
            if (connect() == 0) {
                login(log);
            }

        }
    }

    private List<String> chargerSouvenus() {
        return Fichiers.chargerFichier(PATH_SOUVENUS);
    }

    public void ecrireSouvenu(String nomUtilisateur) {
        Fichiers.sauvegarderFichier(PATH_SOUVENUS, nomUtilisateur);

    }

    @Override
    public void sendMessage(Message message) {
        message.user = username;
        client.sendTCP(message);
    }

    @Override
    public void login(InfoLogin il) {
        this.username = il.username;
        client.sendTCP(new LoginRequest(il.username, il.password));
    }

    @Override
    public void disconnect() {
        client.close();
        client.stop();
    }

    @Override
    public int connect() {
        try {

            client.start();
            client.connect(5_000, validerAdresseIp(), Communication.PORT, Communication.PORT + 1);
            return 0;
        } catch (IOException | IllegalArgumentException ex) {
            return 1;
        }
    }

    private InetAddress validerAdresseIp() {
        if (ipAdress.equals("localhost")) {
            try {
                return getLocalHost();
            } catch (UnknownHostException ex) {
                System.out.println("probleme de host");

            }
        } else {
            try {
                return getByName(ipAdress);
            } catch (UnknownHostException ex) {
                System.out.println("probleme de host");
            }
        }
        return null;
    }

    @Override
    public void creerCompte(InfoCreation ic) {
        client.sendTCP(new CreationRequest(ic.username, ic.password, ic.email, ic.age, ic.nom, ic.prenom, ic.sexe, ic.image));
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
    public void joinLobby(String lobby) {
        effectuerActionSurLobby(lobby, Action.JOIN);
    }

    @Override
    public void leaveLobby(String lobby) {
        effectuerActionSurLobby(lobby, Action.LEAVE);
    }

    private void effectuerActionSurLobby(String lobby, Action action) {
        LobbyAction lobbyAction = new LobbyAction();
        lobbyAction.action = action;
        lobbyAction.lobby = lobby;
        client.sendTCP(lobbyAction);
    }

    @Override
    public void creerLobby(String name) {
        client.sendTCP(new CreateLobby(name));
    }

    @Override
    public void changerIp(String inetAddress) {
        this.ipAdress = inetAddress;
    }

    @Override
    public void sendProfileRequest(String string) {
        ProfileRequest pr = new ProfileRequest(string, username);
        client.sendTCP(pr);
    }

    @Override
    public void sendContactRequest(String nom) {
        if (!nom.equalsIgnoreCase(username)) {
            ContactRequest cr = new ContactRequest(username, nom);
            client.sendTCP(cr);
        }

    }

    @Override
    public void sendContactEffacerRequest(String nom) {
        ContactEffacerRequest cer = new ContactEffacerRequest(username, nom);
        client.sendTCP(cer);
    }

    @Override
    public void sendListeContactRequest() {
        ListeContactRequest lr = new ListeContactRequest(username);
        client.sendTCP(lr);
    }

    @Override
    public void sendUtilisateurModifier(UtilisateurModifier util) {
        client.sendTCP(util);
    }

    @Override
    public void sendLogoutRequest() {
        LogoutRequest logout = new LogoutRequest();
        client.sendTCP(logout);
    }

    @Override
    public void sendGenererPassword(String username) {
        PasswordRetrieveRequest prr = new PasswordRetrieveRequest();
        prr.setUsername(username);
        connect();
        client.sendTCP(prr);
    }

    @Override
    public void sendAjoutAuLobbyRequest(AjoutLobbyInfo o) {
        if (!username.equalsIgnoreCase(o.getUtilisateurDemande())) {
            AjoutAuLobbyRequest lr = new AjoutAuLobbyRequest(username, o.getUtilisateurDemande(), o.getLobby());
            client.sendTCP(lr);
        }
    }

    @Override
    public void demanderImage(String o) {
        client.sendTCP(new ImageRequest(o));
    }

    private class ClientListener extends Listener {

        @Override
        public void received(Connection connection, Object object) {
            if (object instanceof Message) {
                Message message = (Message) object;
                aviserObservateurs(Observation.MESSAGERECU, object);
                messages += "\n" + message.user + ": " + message.message;
            } else if (object instanceof LoginResponse) {
                aviserObservateurs(Observation.REPONSELOGIN, object);
            } else if (object instanceof CreationResponse) {
                aviserObservateurs(Observation.REPONSECREATION, object);
            } else if (object instanceof AvailableLobbies) {
                aviserObservateurs(Observation.UPDATELOBBIES, ((AvailableLobbies) object).lobbies);
            } else if (object instanceof NotificationUtilisateurConnecte) {
                NotificationUtilisateurConnecte utilisateurConnecte = (NotificationUtilisateurConnecte) object;
                aviserObservateurs(Observation.UTILISATEURCONNECTE, object);
                messages += "\n" + utilisateurConnecte.username + " à rejoint le canal.";
            } else if (object instanceof LobbyJoinedNotification) {
                messages += "\n utilisateurs : ";
                for (String s : ((LobbyJoinedNotification) object).listeUtilisateurs) {
                    messages += s + " ";
                }
                aviserObservateurs(Observation.LISTEUTILISATEURSLOBBY, object);
            } else if (object instanceof ProfileResponse) {
                setResponse((ProfileResponse) object);
                aviserObservateurs(Observation.PROFILERESPONSE, object);
            } else if (object instanceof ListeContactResponse) {
                setListeContact((ListeContactResponse) object);
            } else if (object instanceof ConnectionResponse) {
                envoyerListeConnectes(((ConnectionResponse) object).getUtilisateurs());

            } else if (object instanceof SelfProfileResponse) {
                aviserObservateurs(Observation.SELFPROFILERESPONSE, object);
            } else if (object instanceof ContactResponseFailed) {
                aviserObservateurs(Observation.CONTACTRESPONSEFAILED, object);
            } else if (object instanceof AjoutLobbyPopUp) {
                aviserObservateurs(Observation.AJOUTLOBBYPOPUP, object);
            } else if (object instanceof AjoutAuLobbyResponse) {
                aviserObservateurs(Observation.AJOUTAULOBBYRESPONSE, object);
            } else if (object instanceof ErreurEnvoieEmail) {
                aviserObservateurs(Observation.ERREUREMAILINVALIDE, object);
            } else if (object instanceof ErreurUsagerInvalide) {
                aviserObservateurs(Observation.ERREURUSAGERINVALIDE, object);
            } else if (object instanceof ImageReponse) {
                aviserObservateurs(Observation.IMAGERECUE, object);
            }
        }

    }

    public void envoyerListeConnectes(ArrayList<String> connectes) {
        aviserObservateurs(Observation.CONNECTIONRESPONSE, connectes);
    }

    public void setListeContact(ListeContactResponse lc) {
        this.lc = lc;
        aviserObservateurs(Observation.CONTACTRESPONSE, lc);
    }

    public ListeContactResponse getListeContact() {
        return this.lc;
    }

    public ProfileResponse getResponse() {
        return this.pr;
    }

    public void setResponse(ProfileResponse o) {
        this.pr = o;
    }

}
