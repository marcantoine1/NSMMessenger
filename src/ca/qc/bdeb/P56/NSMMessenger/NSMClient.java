/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger;

import com.esotericsoftware.kryonet.Client;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1150580
 */
public class NSMClient {
    
    public Client client;
    
    public NSMClient(){
        try{
            client = new Client();
            client.connect(5000, InetAddress.getLocalHost(), 54123);
        } catch (IOException ex) {
            Logger.getLogger(NSMMessenger.class.getName()).log(Level.SEVERE, 
                    "connection impossible", ex);
            System.out.println("Connection impossible");
            
        }
    }
}
