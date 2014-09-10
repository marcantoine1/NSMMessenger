/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessengerServer;

import ca.qc.bdeb.P56.NSMMessengerCommunication.*;
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

    //todo singleton
    private Server server;
    private Authentificateur authentificateur;
    public HashMap<Integer, ConnectionUtilisateur> connections = new HashMap<>();

    ;
    
    public NSMServer() {
        server = new Server();
        authentificateur = Authentificateur.getInstanceAuthentificateur();
        Communication.initialiserKryo(server.getKryo());

        partirServeur();
        liaisonPort();

        server.addListener(new Listener() {

            @Override
            public void connected(Connection connection) {
                connection.sendTCP(new Message("Serveur", "Bienvenue!"));
            }

            @Override
            public void disconnected(Connection connection) {
                if (connections.containsKey(connection.getID())) {
                    connections.remove(connection.getID());
                }
            }

            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof LoginRequest) {
                    LoginRequest login = (LoginRequest) object;
                    if (authentificateur.authentifierUtilisateur(login.username, login.password)) {

                        Connection utilisateurConnecté = utilisateurConnecté(login.username);
                        if (utilisateurConnecté != null) {
                            //todo : envoi message de deconnection
                            utilisateurConnecté.close();
                            connections.remove(utilisateurConnecté.getID());
                        }

                        connections.put(connection.getID(), new ConnectionUtilisateur(connection, login.username));
                        connection.sendTCP(new LoginResponse(LoginResponse.ACCEPTED));
                    } else {
                        connection.sendTCP(new LoginResponse(LoginResponse.REFUSED));
                    }

                } else if (object instanceof CreationRequest) {
                    CreationRequest creation = (CreationRequest) object;
                    if (authentificateur.creerUtilisateur(creation.username, creation.password, creation.courriel)) {
                        connection.sendTCP(new CreationResponse(CreationResponse.ACCEPTED));
                    } else {
                        connection.sendTCP(new CreationResponse(CreationResponse.EXISTING_USERNAME));
                    }
                } else if (object instanceof Message) {
                    //verification du user
                    Message message = (Message) object;
                    if (connections.containsKey(connection.getID()) && connections.get(connection.getID()).username.equals(message.user)) {
                        server.sendToAllTCP(object);
                    }
                }
            }
        });

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
        authentificateur.demarrerAuthentificateur();
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

    public static void main(String[] args) {

        System.out.println("Je suis un serveur");
        NSMServer s = new NSMServer();

    }
}
