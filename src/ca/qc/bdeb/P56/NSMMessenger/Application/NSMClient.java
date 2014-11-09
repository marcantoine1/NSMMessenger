/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Application;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger.Observation;
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

/**
 * @author 1150580
 */
public class NSMClient implements IClient {

    public Client client;
    public String username = "";
    private String ipAdress = "localhost";
    //logs des messages, seulement utilisé pour les tests en ce moment
    public String messages = "";
    public ProfileResponse pr = new ProfileResponse();
    public ListeContactResponse lc = new ListeContactResponse();
    private ArrayList<String> listeConnectes;
    
    private ArrayList<Observateur> observateurs = new ArrayList<>();

    public NSMClient() {
        init();
    }

    public NSMClient(Observateur o) {
        ajouterObservateur(o);
        init();
    }

    public void init() {
        client = new Client();
        Communication.initialiserKryo(client.getKryo());
        client.addListener(new ClientListener());
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

    public void disconnect() {
        client.close();
        client.stop();
    }

    @Override
    public int connect() {
        try {

            client.start();
            client.connect(5000, validerAdresseIp(), Communication.PORT, Communication.PORT + 1);
            return 0;
        } catch (IOException ex) {
            return 1;
        } catch (IllegalArgumentException ex) {
            return 1;
        }
    }

    private InetAddress validerAdresseIp() {
        if (ipAdress.equals("localhost")) {
            try {
                return InetAddress.getLocalHost();
            } catch (UnknownHostException ex) {

            }
        } else {
            try {
                return InetAddress.getByName(ipAdress);
            } catch (UnknownHostException ex) {
            }
        }
        return null;
    }

    @Override
    public void creerCompte(InfoCreation ic) {
        client.sendTCP(new CreationRequest(ic.username, ic.password, ic.email, ic.age, ic.nom, ic.prenom, ic.sexe));
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
                for (String s : ((LobbyJoinedNotification) object).listeUtilisateurs)
                    messages += s + " ";
                aviserObservateurs(Observation.LISTEUTILISATEURSLOBBY, object);
            } else if (object instanceof ProfileResponse) {
                setResponse((ProfileResponse) object);
                aviserObservateurs(Observation.PROFILERESPONSE, object);
            } else if (object instanceof ListeContactResponse) {
                setListeContact((ListeContactResponse) object);
            }else if (object instanceof ConnectionResponse){
                envoyerListeConnectes(((ConnectionResponse)object).getUtilisateurs());
                
            }else if (object instanceof SelfProfileResponse){
                aviserObservateurs(Observation.SELFPROFILERESPONSE,object);
                envoyerListeConnectes(((ConnectionResponse)object).getUtilisateurs());  
            }
            else if (object instanceof ContactResponseFailed){
                aviserObservateurs(Observation.CONTACTRESPONSEFAILED, object);
            }
        }
    }
public void envoyerListeConnectes(ArrayList<String> connectes){
    aviserObservateurs(Observation.CONNECTIONRESPONSE,connectes);
}
    public void setListeContact(ListeContactResponse lc) {
        this.lc = lc;
        aviserObservateurs(Observation.CONTACTRESPONSE,lc);
    }
    public ListeContactResponse getListeContact(){
        return this.lc;
    }

    public ProfileResponse getResponse() {
        return this.pr;
    }

    public void setResponse(ProfileResponse o) {
        this.pr = o;
    }
}
