/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessengerServer;

import ca.qc.bdeb.P56.NSMMessengerCommunication.*;
import ca.qc.bdeb.P56.NSMMessengerCommunication.LobbyAction.Action;
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Authentificateur;
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

    public HashMap<Integer, ConnectionUtilisateur> connections = new HashMap<>();
    public HashMap<Integer, Lobby> lobbies = new HashMap<>();
    //todo singleton
    private Server server;
    private Authentificateur authentificateur = Authentificateur.getInstanceAuthentificateur();

    ;
    public NSMServer(String nomBd){
        this();
        authentificateur.setNomBd(nomBd);
    }
    public NSMServer() {
        server = new Server();
        Communication.initialiserKryo(server.getKryo());

        partirServeur();
        liaisonPort();

        server.addListener(new ServerListener());
        
        lobbies.put(1, new Lobby(1, "main"));
        lobbies.put(2, new Lobby(2, "deuxieme"));

    }

    public static void main(String[] args) {

        System.out.println("Je suis un serveur");
        NSMServer s = new NSMServer();

    }

    public Connection utilisateurConnecté(String username) {
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
    
    private class ServerListener extends Listener{
            @Override
            public void connected(Connection connection) {
                connection.sendTCP(new Message(1, "Serveur", "Bienvenue!"));
            }

        @Override
        public void received(Connection connection, Object object) {
            
            if (object instanceof LoginRequest) {
                LoginRequest login = (LoginRequest) object;
                if (authentificateur.authentifierUtilisateur(login.username, login.password)) {

                    Connection utilisateurConnecté = utilisateurConnecté(login.username);
                    if (utilisateurConnecté != null) 
                        disconnectUser(utilisateurConnecté);
                    
                    connections.put(connection.getID(), new ConnectionUtilisateur(connection, login.username));
                    lobbies.get(1).addUser(connection.getID());
                    connection.sendTCP(new LoginResponse(LoginResponse.ReponseLogin.ACCEPTED));
                    connection.sendTCP(new AvailableLobbies(lobbies));
                } else {
                    connection.sendTCP(new LoginResponse(LoginResponse.ReponseLogin.REFUSED));
                }
            } else if (object instanceof CreationRequest) {
                CreationRequest creation = (CreationRequest) object;
                if (authentificateur.creerUtilisateur(creation.username, creation.password, creation.courriel)) {
                    connection.sendTCP(new CreationResponse(CreationResponse.ReponseCreation.ACCEPTED));
                } else {
                    connection.sendTCP(new CreationResponse(CreationResponse.ReponseCreation.EXISTING_USERNAME));
                }
            } else if (object instanceof Message) {
                //verification du user et du lobby
                Message message = (Message) object;
                if (connections.containsKey(connection.getID()) && connections.get(connection.getID()).username.equals(message.user) && 
                        lobbies.get(message.lobby).userInLobby(connection.getID())) {
                    sendMessage(message);
                }
            } else if (object instanceof LobbyAction) {
                LobbyAction lobbyAction = (LobbyAction) object;
                if(lobbies.containsKey(lobbyAction.lobby))
                if(lobbyAction.action == Action.LEAVE)
                {
                    lobbies.get(lobbyAction.lobby).removeUser(connection.getID());
                    
                } else if(lobbyAction.action == Action.JOIN)
                { 
                    lobbies.get(lobbyAction.lobby).addUser(connection.getID());
                }
            }
        }
        
        private void sendMessage(Message message)
        {
            for(int i : lobbies.get(message.lobby).getUsers())
                server.sendToTCP(i, message);
        }
        
        private void disconnectUser(Connection c)
        {
            //todo : envoi message de deconnection
            if(c.isConnected())
                c.close();
            int id = c.getID();
            connections.remove(id);
            for(Lobby lobby : lobbies.values())
                lobby.removeUser(id);
        }
    }
}

