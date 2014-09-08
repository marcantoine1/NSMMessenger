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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1150580
 */
public class NSMServer {
    public static final int PORT = 54123;
    //todo singleton
    public Server server;
    
    public NSMServer()
    {
        server = new Server();
        
        Kryo kryo = server.getKryo();
        kryo.register(LoginRequest.class);

        partirServeur();
        liaisonPort();

        server.addListener(new Listener(){
            @Override
            public void received (Connection connection, Object object)
            {
                if(object instanceof LoginRequest)
                {
                    //todo: login
                    server.sendToTCP(connection.getID(), new LoginResponse(LoginResponse.ACCEPTED));
                }
                
                if(object instanceof Message)
                {
                    server.sendToAllTCP(object);
                }
            }
        });
    }

    private void partirServeur() {
        server.start();
        Logger.getLogger(NSMServer.class.getName()).log(Level.FINEST, "Serveur démarré");
    }

    private void liaisonPort() {
        try {
            server.bind(PORT);
        } catch (IOException ex) {
            Logger.getLogger(NSMServer.class.getName()).log(Level.SEVERE, "Ne peut pas se lier au port: " + PORT, ex);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        
        System.out.println("Je suis un serveur");
        NSMServer s = new NSMServer();
       
    }
}
