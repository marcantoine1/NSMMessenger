/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessengerServer;

import ca.qc.bdeb.P56.NSMMessengerCommunication.*;
import ca.qc.bdeb.P56.NSMMessengerCommunication.LobbyAction.Action;
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Authentificateur;
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Utilisateur;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1150580
 */
public class NSMServer {

    public static final String INITIALLOBBY = "Général", INITIALLOBBY2 = "Divers";
    
    private final HashMap<String, Integer> userID = new HashMap<>();
    public final HashMap<Integer, ConnectionUtilisateur> connections = new HashMap<>();
    public final HashMap<String, Lobby> lobbies = new HashMap<>();
    //todo singleton
    private final Server server;
    private final Authentificateur authentificateur = Authentificateur.getInstanceAuthentificateur();

    private NSMServer(String nomBd) {
        this();
        authentificateur.setNomBd(nomBd);
    }

    public NSMServer() {
        server = new Server();
        Communication.initialiserKryo(server.getKryo());

        partirServeur();
        liaisonPort();

        server.addListener(new ServerListener());

        gererCreateLobby(new CreateLobby(INITIALLOBBY));
        gererCreateLobby(new CreateLobby(INITIALLOBBY2));

    }

    private synchronized void gererCreateLobby(CreateLobby createLobby) {
        if(!lobbies.containsKey(createLobby.name))
        {
            lobbies.putIfAbsent(createLobby.name, new Lobby(createLobby.name));
            server.sendToAllTCP(new AvailableLobbies(lobbies));
        }
    }

    public static void main(String[] args) {

        System.out.println("Je suis un serveur");
        NSMServer s = new NSMServer();

    }

    Connection utilisateurConnecté(String username) {
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
            System.exit(1);
        }
    }

    public void removeUserFromLobby(Lobby lobby, String username) {
        if(userID.containsKey(username))
            removeUserFromLobby(lobby, userID.get(username), username);
    }

    public void removeUserFromLobby(Lobby lobby, int id) {
        if(connections.get(id) != null)
            removeUserFromLobby(lobby, id, connections.get(id).username);
        else
            removeUserFromLobby(lobby, id, "");
    }

    public void removeUserFromLobby(Lobby lobby, int id, String username) {

        if (lobby.userInLobby(id)) {
            lobby.removeUser(id);
            for (int i : lobby.getUsers()) {
                server.sendToTCP(i, new NotificationUtilisateurConnecte(username, lobby.name, false));
            }
        }

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
            } else if (object instanceof Message) {
                gererRequeteMessage(connection, (Message) object);
            } else if (object instanceof LobbyAction) {
                gererLobbyAction(connection, (LobbyAction) object);
            } else if (object instanceof CreateLobby) {
                gererCreateLobby((CreateLobby) object);
            } else if (object instanceof ProfileRequest) {
                gererRechercheProfil(connection, (ProfileRequest) object);
            }
        }

        private void gererLogin(Connection connection, LoginRequest login) {
            if (authentificateur.authentifierUtilisateur(login.username, login.password)) {
                Connection utilisateurConnecté = utilisateurConnecté(login.username);
                if (utilisateurConnecté != null) {
                    disconnectUser(utilisateurConnecté);
                }
                connections.put(connection.getID(), new ConnectionUtilisateur(connection, login.username));
                userID.put(login.username, connection.getID());

                connection.sendTCP(new LoginResponse(LoginResponse.ReponseLogin.ACCEPTED));

                connection.sendTCP(new AvailableLobbies(lobbies));

                LobbyAction lobbyActionInitial = new LobbyAction();
                lobbyActionInitial.action = Action.JOIN;
                lobbyActionInitial.lobby = INITIALLOBBY;
                gererLobbyAction(connection, lobbyActionInitial);
            } else {
                connection.sendTCP(new LoginResponse(LoginResponse.ReponseLogin.REFUSED));
            }
        }

        private void gererCreationCompte(Connection connection, CreationRequest creation) {
            if (authentificateur.creerUtilisateur(creation.username, creation.password,
                    creation.courriel, creation.age, creation.nom, creation.prenom, creation.sexe)) {
                connection.sendTCP(new CreationResponse(CreationResponse.ReponseCreation.ACCEPTED, creation.username, creation.password));
            } else {
                connection.sendTCP(new CreationResponse(CreationResponse.ReponseCreation.EXISTING_USERNAME, null, null));
            }
        }

        private void gererRequeteMessage(Connection connection, Message message) {
            //verification du user et du lobby
            if (connections.containsKey(connection.getID()) && connections.get(connection.getID()).username.equals(message.user)
                    && lobbies.get(message.lobby).userInLobby(connection.getID())) {
                sendMessage(message);
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
                    server.sendToAllExceptTCP(connection.getID(), utilisateurConnectant);
                    connection.sendTCP(creerListeUtilisateurs(lobbyAction.lobby));
                }
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

        private void sendMessage(Message message) {
            for (int i : lobbies.get(message.lobby).getUsers()) {
                server.sendToTCP(i, message);
            }
        }

        private void disconnectUser(Connection c) {
            int id = c.getID();
            if (c.isConnected()) {
                c.close();
            }
            
            for (Lobby lobby : lobbies.values()) {
                if(lobby.userInLobby(id))
                    removeUserFromLobby(lobby, id);
            }
            connections.remove(id);
        }

        private void gererRechercheProfil(Connection connection, ProfileRequest profileRequest) {
            Utilisateur u = authentificateur.chercherUtilisateur(profileRequest.utilisateurRecherche);
            if (u != null) {
                ProfileResponse pResponse = new ProfileResponse(u.getUsername(), u.getCourriel(), u.getNom(), u.getPrenom(), u.getSexe(), u.getAge());
                server.sendToTCP(connection.getID(), pResponse);
                        
            }
        }
    }
}
