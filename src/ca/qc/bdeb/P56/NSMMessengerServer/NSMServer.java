/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerServer;

import ca.qc.bdeb.P56.NSMMessengerCommunication.*;
import com.esotericsoftware.kryo.Kryo;
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
    public Server server;
    
    public HashMap<Integer, ConnectionUtilisateur> connections = new HashMap<>();;
    
    public NSMServer()
    {
        server = new Server();
        
        Communication.initialiserKryo(server.getKryo());
        server.addListener(new Listener(){
            @Override
            public void disconnected(Connection connection)
            {
                connections.remove(connection.getID());
            }
            
            @Override
            public void received (Connection connection, Object object)
            {
                if(object instanceof LoginRequest)
                {
                    LoginRequest login = (LoginRequest) object;
                    //todo: authentifier
                    connections.put(connection.getID(), new ConnectionUtilisateur(connection, login.username));
                    server.sendToTCP(connection.getID(), new LoginResponse(LoginResponse.ACCEPTED));
                }
                
                if(object instanceof Message)
                {
                    //verification du user
                    Message message = (Message) object;
                    if(connections.containsKey(connection.getID()) && connections.get(connection.getID()).username.equals(message.user))
                        server.sendToAllTCP(object);
                }
            }
        });

        partirServeur();
        liaisonPort();

    }

    private void partirServeur() {
        server.start();
        Logger.getLogger(NSMServer.class.getName()).log(Level.FINEST, "Serveur démarré");
    }

    private void liaisonPort() {
        try {
            server.bind(Communication.PORT, Communication.PORT+1);
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
