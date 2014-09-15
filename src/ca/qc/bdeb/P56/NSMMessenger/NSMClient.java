/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.InfoCreation;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.InfoLogin;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import ca.qc.bdeb.P56.NSMMessengerCommunication.*;
import ca.qc.bdeb.mvc.Observateur;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1150580
 */
public class NSMClient implements IClient {
    
    public Client client;
    public String username = "";
    
    //temporaire, ne pas oublier de changer le test
    public String messages = "";
    
    private ArrayList<Observateur> observateurs = new ArrayList<>();
    
    public NSMClient(){
        init();
    }
    
    public NSMClient(Observateur o)
    {
        ajouterObservateur(o);
        init();
    }
    
    public void init()
    {
        client = new Client();
        Communication.initialiserKryo(client.getKryo());
        
        client.addListener(new Listener(){
            @Override
            public void received (Connection connection, Object object)
            {
                if(object instanceof Message)
                {
                    Message message = (Message)object;
                    aviserObservateurs(NSMMessenger.Observation.MESSAGERECU, message.user + ": " + message.message);
                    messages += "\n" + message.user + ": " + message.message;
                }
                
                if(object instanceof LoginResponse)
                {
                    aviserObservateurs(NSMMessenger.Observation.REPONSELOGIN, object);              
                }
                if(object instanceof CreationResponse){
                     aviserObservateurs(NSMMessenger.Observation.REPONSECREATION, object);
                }
            }
        });
    }

    @Override
    public void sendMessage(String s) {
        client.sendTCP(new Message(username, s));
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
    public void creerCompte(InfoCreation ic){
        client.sendTCP(new CreationRequest(ic.username, ic.password, ic.email));
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
        for(Observateur obs : observateurs)
            obs.changementEtat();
    }

    @Override
    public void aviserObservateurs(Enum<?> e, Object o) {
        for(Observateur obs : observateurs)
            obs.changementEtat(e, o);
    }
}
