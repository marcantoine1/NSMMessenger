package ca.qc.bdeb.P56.NSMMessengerServer;

import ca.qc.bdeb.P56.NSMMessengerCommunication.*;

import static ca.qc.bdeb.P56.NSMMessengerCommunication.Communication.initialiserKryo;

import ca.qc.bdeb.P56.NSMMessengerCommunication.LobbyAction.Action;
import ca.qc.bdeb.P56.NSMMessengerServer.Application.Authentificateur;

import static ca.qc.bdeb.P56.NSMMessengerServer.Application.Authentificateur.getInstanceAuthentificateur;

import ca.qc.bdeb.P56.NSMMessengerServer.Application.Utilisateur;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

import static java.lang.System.exit;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.*;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.*;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author 1150580
 */
public class NSMServer {

    public static final String INITIALLOBBY = "Général", INITIALLOBBY2 = "Divers";

    public static void main(String[] args) {
        NSMServer s = new NSMServer();
    }

    private ProfileResponse profil = new ProfileResponse();

    private final HashMap<String, Integer> userID = new HashMap<>();
    public final HashMap<Integer, ConnectionUtilisateur> connections = new HashMap<>();
    public final HashMap<String, Lobby> lobbies = new HashMap<>();
    //todo singleton
    private final Server server;
    private final Authentificateur authentificateur = getInstanceAuthentificateur();

    private NSMServer(String nomBd) {
        this();
        authentificateur.setNomBd(nomBd);
    }

    public NSMServer() {
        server = new Server();
        initialiserKryo(server.getKryo());

        partirServeur();
        liaisonPort();

        lobbies.put(INITIALLOBBY, new Lobby(INITIALLOBBY));
        lobbies.put(INITIALLOBBY2, new Lobby(INITIALLOBBY2));

        server.addListener(new ServerListener());

        System.out.println("Je suis un serveur");
    }

    public String[] getLobbyNames() {
        ArrayList<Lobby> lobbyList = new ArrayList<>(lobbies.values());

        String[] lobbyNames = new String[lobbyList.size()];

        for (int i = 0; i < lobbyList.size(); i++) {
            lobbyNames[i] = lobbyList.get(i).name;
        }
        return lobbyNames;
    }

    Connection utilisateurConnecte(String username) {
        for (ConnectionUtilisateur cu : connections.values()) {
            if (cu.username.equals(username)) {
                return cu.connection;
            }
        }
        return null;
    }

    public void stop() {
        server.stop();
        server.close();
    }

    public void start() {
        server.start();
    }

    private void partirServeur() {
        server.start();
        Logger.getLogger(NSMServer.class.getName()).log(Level.FINEST, "Serveur démarré");
    }

    private void liaisonPort() {
        try {
            server.bind(Communication.PORT, Communication.PORT + 1);
        } catch (IOException ex) {
            Logger.getLogger(NSMServer.class.getName()).log(Level.SEVERE, "Ne peut pas se lier au port: " + Communication.PORT, ex);
            exit(1);
        }
    }

    private void sendToAllInLobbyExcept(Lobby lobby, int except, Object o) {
        for (int i : lobby.getUsers()) {
            if (i != except) {
                server.sendToTCP(i, o);
            }
        }
    }

    private void sendToAllInLobby(Lobby lobby, Object o) {
        for (int i : lobby.getUsers()) {
            server.sendToTCP(i, o);
        }
    }

    public synchronized void removeUserFromLobby(Lobby lobby, String username) {
        if (userID.containsKey(username)) {
            removeUserFromLobby(lobby, userID.get(username), username);
        }
    }

    public synchronized void removeUserFromLobby(Lobby lobby, int id) {
        if (connections.get(id) != null) {
            removeUserFromLobby(lobby, id, connections.get(id).username);
        } else {
            removeUserFromLobby(lobby, id, "");
        }
    }

    public synchronized void removeUserFromLobby(Lobby lobby, int id, String username) {

        if (lobby.userInLobby(id)) {
            lobby.removeUser(id);
            for (int i : lobby.getUsers()) {
                server.sendToTCP(i, new NotificationUtilisateurConnecte(username, lobby.name, false));
            }
        }

    }

    public synchronized void reset() {
        for (Connection c : server.getConnections()) {
            c.close();
        }
        lobbies.clear();
        connections.clear();
        lobbies.put(INITIALLOBBY, new Lobby(INITIALLOBBY));
        lobbies.put(INITIALLOBBY2, new Lobby(INITIALLOBBY2));
    }

    public ProfileResponse getProfil() {
        return profil;
    }

    public void setProfil(ProfileResponse profil) {
        this.profil = profil;
    }

    private class ServerListener extends Listener {

        @Override
        public void disconnected(Connection connection) {
            disconnectUser(connection);
        }

        @Override
        public void received(Connection connection, Object object) {
            if (object instanceof LoginRequest) {
                gererLogin(connection, (LoginRequest) object);
            } else if (object instanceof CreationRequest) {
                gererCreationCompte(connection, (CreationRequest) object);
            } else if (object instanceof ca.qc.bdeb.P56.NSMMessengerCommunication.Message) {
                gererRequeteMessage(connection, (ca.qc.bdeb.P56.NSMMessengerCommunication.Message) object);
            } else if (object instanceof LobbyAction) {
                gererLobbyAction(connection, (LobbyAction) object);
            } else if (object instanceof CreateLobby) {
                gererCreateLobby(connection, (CreateLobby) object);
            } else if (object instanceof ProfileRequest) {
                gererRechercheProfil(connection, (ProfileRequest) object);
            } else if (object instanceof ContactRequest) {
                gererCreationContact(connection, (ContactRequest) object);
            } else if (object instanceof ContactEffacerRequest) {
                gererEffacerContact(connection, (ContactEffacerRequest) object);
            } else if (object instanceof ListeContactRequest) {
                gererListeContact(connection, (ListeContactRequest) object);
            } else if (object instanceof UtilisateurModifier) {
                updaterUtilisateur((UtilisateurModifier) object);
            } else if (object instanceof LogoutRequest) {
                disconnectUser(connection);
            } else if (object instanceof PasswordRetrieveRequest) {
                regenererPassword((PasswordRetrieveRequest) object, connection);
            } else if (object instanceof AjoutAuLobbyRequest) {
                demanderAjouterAuLobby(connection, (AjoutAuLobbyRequest) object);
            } else if (object instanceof ImageRequest) {
                demanderImage(connection, (ImageRequest) object);
            }

        }

        private void demanderImage(Connection connection, ImageRequest u) {
            Utilisateur utilisateur;
            if ((utilisateur = authentificateur.chercherUtilisateur(u.getUtilisateurRecherche())) != null) {
                ImageReponse r = new ImageReponse(utilisateur.getImage());
                connection.sendTCP(r);
            }
            else {
                connection.sendTCP(new ImageReponse("src/ca/qc/bdeb/P56/Ressource/placeHolder.png"));
            }
        }

        private void demanderAjouterAuLobby(Connection connection, AjoutAuLobbyRequest lr) {
            if (authentificateur.chercherUtilisateur(lr.getUtilisateurDemande()) != null) {
                /*authentificateur.creerContact(cr.getUtilisateurDemandant(), cr.getUtilisateurDemander());
                ListeContactResponse lcr = new ListeContactResponse();
                lcr.setListeContact(authentificateur.chercherListeContact(cr.getUtilisateurDemandant()));
                connection.sendTCP(lcr);
                /*else if (lobbyAction.action == Action.JOIN) {
                    lobbies.get(lobbyAction.lobby).addUser(connection.getID());
                    NotificationUtilisateurConnecte utilisateurConnectant
                            = new NotificationUtilisateurConnecte(connections.get(connection.getID()).username, lobbyAction.lobby, true);

                 sendToAllInLobbyExcept(lobbies.get(lobbyAction.lobby), connection.getID(), utilisateurConnectant);

                 connection.sendTCP(creerListeUtilisateurs(lobbyAction.lobby));
                 }*/
                if (verifierConnecte(lr.getUtilisateurDemande())) {
                    int IDUtilisateurDemande = userID.get(lr.getUtilisateurDemande());
                    if (!lobbies.get(lr.getNomLobby()).getUsers().contains(IDUtilisateurDemande)) {
                        server.sendToTCP(connections.get(userID.get(lr.getUtilisateurDemande())).connection.getID(), new AjoutLobbyPopUp(lr.getUtilisateurDemandant(), lr.getNomLobby()));
                        server.sendToTCP(connections.get(userID.get(lr.getUtilisateurDemandant())).connection.getID(), new AjoutAuLobbyResponse("Votre demande a été envoyé avec succès"));
                    } else {
                        server.sendToTCP(connections.get(userID.get(lr.getUtilisateurDemandant())).connection.getID(), new AjoutAuLobbyResponse("Utilisateur déjà dans le lobby"));
                    }
                }
            } else {
                connection.sendTCP(new AjoutAuLobbyFailed());
            }
        }

        private void regenererPassword(PasswordRetrieveRequest utilisateur, Connection connection) {
            String string = RandomStringUtils.random(12, true, true);
            Utilisateur u = authentificateur.chercherUtilisateur(utilisateur.getUsername());
            if (u != null) {
                boolean success = envoyerEmail(new String[]{u.getCourriel()}, "Changement de mot de passe", "Votre nouveau mot de passe est: " + string, connection);
                if (success == true) {
                    authentificateur.updaterUtilisateur(u, string);
                }
            } else {
                connection.sendTCP(new ErreurUsagerInvalide());
            }
        }

        private boolean envoyerEmail(String[] destinataires, String sujet, String contenu, Connection connection) {
            boolean success = true;
            String from = "nsmmessengergenie@gmail.com";
            String pass = "sexyahri123";
            Properties props = System.getProperties();
            String host = "smtp.gmail.com";
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.connectiontimeout", 1000);
            props.put("mail.smtp.timeout", 1000);
            Session session = Session.getDefaultInstance(props);
            MimeMessage mail = new MimeMessage(session);

            try {
                mail.setFrom(new InternetAddress(from));
                InternetAddress[] toAddress = new InternetAddress[destinataires.length];

                // To get the array of addresses
                for (int i = 0; i < destinataires.length; i++) {
                    toAddress[i] = new InternetAddress(destinataires[i]);
                }

                for (int i = 0; i < toAddress.length; i++) {
                    mail.addRecipient(javax.mail.Message.RecipientType.TO, toAddress[i]);
                }

                mail.setSubject(sujet);
                mail.setText(contenu);
                Transport transport = session.getTransport("smtp");
                transport.connect(host, from, pass);
                transport.sendMessage(mail, mail.getAllRecipients());
                transport.close();
            } catch (AddressException ae) {
                ae.printStackTrace();
                success = false;
                connection.sendTCP(new ErreurEnvoieEmail());
            } catch (MessagingException me) {
                me.printStackTrace();
                connection.sendTCP(new ErreurEnvoieEmail());
                success = false;
            }
            return success;
        }

        private void updaterUtilisateur(UtilisateurModifier util) {
            authentificateur.updaterUtilisateur(util);
        }

        private void gererListeContact(Connection connection, ListeContactRequest liste) {
            ListeContactResponse lr = new ListeContactResponse();
            lr.setListeContact(authentificateur.chercherListeContact(liste.getUsername()));
            connection.sendTCP(lr);
        }

        private void gererLogin(Connection connection, LoginRequest login) {
            if (authentificateur.authentifierUtilisateur(login.username, login.password)) {
                Connection utilisateurConnecte = utilisateurConnecte(login.username);
                if (utilisateurConnecte != null) {
                    disconnectUser(utilisateurConnecte);
                }
                connections.put(connection.getID(), new ConnectionUtilisateur(connection, login.username));
                userID.put(login.username, connection.getID());

                connection.sendTCP(new LoginResponse(LoginResponse.ReponseLogin.ACCEPTED));

                connection.sendTCP(new AvailableLobbies(getLobbyNames()));

                creerEtEnvoyerListeConnectes();

                LobbyAction lobbyActionInitial = new LobbyAction();
                lobbyActionInitial.action = Action.JOIN;
                lobbyActionInitial.lobby = INITIALLOBBY;
                gererLobbyAction(connection, lobbyActionInitial);
            } else {
                connection.sendTCP(new LoginResponse(LoginResponse.ReponseLogin.REFUSED));
            }
        }

        private void creerEtEnvoyerListeConnectes() {
            ConnectionResponse listeConnectes = creerListeConnectes();
            EnvoyerListeConnectes(listeConnectes);
        }

        private ConnectionResponse creerListeConnectes() {
            ConnectionResponse listeConnectes = new ConnectionResponse();
            for (ConnectionUtilisateur c : connections.values()) {
                listeConnectes.ajouterUtilisateur(connections.get(c.connection.getID()).username);
            }
            return listeConnectes;
        }

        private void EnvoyerListeConnectes(ConnectionResponse listeConnectes) {
            for (ConnectionUtilisateur c : connections.values()) {
                c.connection.sendTCP(listeConnectes);
            }
        }

        private void gererCreationContact(Connection connection, ContactRequest cr) {
            if (authentificateur.chercherUtilisateur(cr.getUtilisateurDemander()) != null) {
                authentificateur.creerContact(cr.getUtilisateurDemandant(), cr.getUtilisateurDemander());
                ListeContactResponse lcr = new ListeContactResponse();
                lcr.setListeContact(authentificateur.chercherListeContact(cr.getUtilisateurDemandant()));
                connection.sendTCP(lcr);
            } else {
                connection.sendTCP(new ContactResponseFailed());
            }
        }

        private void gererEffacerContact(Connection connection, ContactEffacerRequest cer) {
            authentificateur.effacerContact(cer.getUserDemandant(), cer.getUserDemander());
            ListeContactResponse lcr = new ListeContactResponse();
            lcr.setListeContact(authentificateur.chercherListeContact(cer.getUserDemandant()));
            connection.sendTCP(lcr);
        }

        private void gererCreationCompte(Connection connection, CreationRequest creation) {
            if (authentificateur.creerUtilisateur(creation.username, creation.password,
                    creation.courriel, creation.age, creation.nom, creation.prenom, creation.sexe, creation.image)) {
                connection.sendTCP(new CreationResponse(CreationResponse.ReponseCreation.ACCEPTED, creation.username, creation.password));
            } else {
                connection.sendTCP(new CreationResponse(CreationResponse.ReponseCreation.EXISTING_USERNAME, null, null));
            }
        }

        private void gererRequeteMessage(Connection connection, ca.qc.bdeb.P56.NSMMessengerCommunication.Message message) {
            //verification du user et du lobby
            if (connections.containsKey(connection.getID()) && connections.get(connection.getID()).username.equals(message.user)
                    && lobbies.get(message.lobby).userInLobby(connection.getID())) {
                sendToAllInLobby(lobbies.get(message.lobby), message);
            }
        }

        private void gererLobbyAction(Connection connection, LobbyAction lobbyAction) {
            if (lobbies.containsKey(lobbyAction.lobby)) {
                if (lobbyAction.action == Action.LEAVE) {
                    removeUserFromLobby(lobbies.get(lobbyAction.lobby), connection.getID(), connections.get(connection.getID()).username);

                } else if (lobbyAction.action == Action.JOIN) {
                    lobbies.get(lobbyAction.lobby).addUser(connection.getID());
                    NotificationUtilisateurConnecte utilisateurConnectant
                            = new NotificationUtilisateurConnecte(connections.get(connection.getID()).username, lobbyAction.lobby, true);

                    sendToAllInLobbyExcept(lobbies.get(lobbyAction.lobby), connection.getID(), utilisateurConnectant);

                    connection.sendTCP(creerListeUtilisateurs(lobbyAction.lobby));
                }
            }
        }

        private synchronized void gererCreateLobby(Connection connection, CreateLobby createLobby) {
            if (!lobbies.containsKey(createLobby.name)) {
                lobbies.putIfAbsent(createLobby.name, new Lobby(createLobby.name));
                server.sendToAllTCP(new AvailableLobbies(getLobbyNames()));

                LobbyAction joinLobby = new LobbyAction();
                joinLobby.action = Action.JOIN;
                joinLobby.lobby = createLobby.name;
                gererLobbyAction(connection, joinLobby);
            }
        }

        private LobbyJoinedNotification creerListeUtilisateurs(String lobby) {
            LobbyJoinedNotification liste = new LobbyJoinedNotification();
            for (int i : lobbies.get(lobby).getUsers()) {
                liste.listeUtilisateurs.add(connections.get(i).username);
            }
            liste.nom = lobbies.get(lobby).getName();
            return liste;

        }

        private void disconnectUser(Connection c) {
            int id = c.getID();
            if (c.isConnected()) {
                c.close();
            }

            for (Lobby lobby : lobbies.values()) {
                if (lobby.userInLobby(id)) {
                    removeUserFromLobby(lobby, id);
                }
            }

            connections.remove(id);
            creerEtEnvoyerListeConnectes();
        }

        private void gererRechercheProfil(Connection connection, ProfileRequest profileRequest) {
            if (profileRequest.getUtilisateurRecherchant().equals(profileRequest.getUtilisateurRecherche())) {
                SelfProfileResponse pr = new SelfProfileResponse();
                Utilisateur u = authentificateur.chercherUtilisateur(profileRequest.getUtilisateurRecherchant());
                pr.setUsername(u.getUsername());
                pr.setPrenom(u.getPrenom());
                pr.setNom(u.getNom());
                pr.setAge(u.getAge());
                pr.setCourriel(u.getCourriel());
                pr.setSexe(u.getSexe());
                pr.setMotDePasse(u.getUnsecuredPassword());
                pr.setImage(u.getImage());
                connection.sendTCP(pr);
            } else {
                Utilisateur u = authentificateur.chercherUtilisateur(profileRequest.utilisateurRecherche);
                if (u != null) {
                    ProfileResponse pResponse = new ProfileResponse(u.getUsername(), u.getCourriel(), u.getNom(),
                            u.getPrenom(), u.getSexe(), u.getAge(), authentificateur.isContact(profileRequest.utilisateurRecherchant,
                            profileRequest.utilisateurRecherche), verifierConnecte(u.getUsername()), u.getImage());
                    setProfil(pResponse);
                    server.sendToTCP(connection.getID(), pResponse);
                }
            }
        }

        private boolean verifierConnecte(String username) {
            return connections.get(userID.get(username)) != null;
        }
    }
}
