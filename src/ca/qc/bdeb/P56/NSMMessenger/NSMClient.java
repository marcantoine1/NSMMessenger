/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import ca.qc.bdeb.P56.NSMMessengerCommunication.*;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 1150580
 */
public class NSMClient implements IClient {
    
    public Client client;
    public String username = "";
    
    //temporaire, ne pas oublier de changer le test
    public String messages = "";
    
    public NSMClient(){
        client = new Client();
        Communication.initialiserKryo(client.getKryo());
        
        client.addListener(new Listener(){
            @Override
            public void received (Connection connection, Object object)
            {
                if(object instanceof Message)
                {
                    Message message = (Message)object;
                    //todo: afficher message
                    messages += "\n" + message.user + ": " + message.message;
                }
                
                if(object instanceof LoginResponse)
                {
                    switch(((LoginResponse)object).response)
                    {   
                        case LoginResponse.ACCEPTED:
                            JOptionPane.showMessageDialog(null, "Connection réussie!");
                            break;
                        case LoginResponse.REFUSED:
                            JOptionPane.showMessageDialog(null, "Votre nom d'utilisateur ou mot de passe est invalide");
                            break;
                    }                   
                }
                if(object instanceof CreationResponse){
                    switch(((CreationResponse)object).response){
                        case CreationResponse.ACCEPTED:
                            JOptionPane.showMessageDialog(null, "Création de compte réussie!");
                            break;
                        case CreationResponse.ERROR:
                            JOptionPane.showMessageDialog(null, "Erreur dans la création du compte");
                            break;
                        case CreationResponse.EXISTING_USERNAME:
                            JOptionPane.showMessageDialog(null, "Le nom d'utilisateur existe déja!");
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void sendMessage(String s) {
        client.sendTCP(new Message(username, s));
    }

    @Override
    public void login(String user, String password) {
        this.username = user;
        client.sendTCP(new LoginRequest(user, password));
    }

    public void disconnect() {
        client.close();
        client.stop();
    }
    
    @Override
    public int connect() {
        try{
            client.start();
            client.connect(5000, InetAddress.getLocalHost(), Communication.PORT, Communication.PORT+1);
            return 0;
        } catch (IOException ex) {
            Logger.getLogger(NSMMessenger.class.getName()).log(Level.SEVERE, 
                    "connection impossible", ex);
            return 1;
        }
    }
    @Override
    public void creerCompte(String user, String password, String courriel){
        client.sendTCP(new CreationRequest(user, password, courriel));
    }
}
