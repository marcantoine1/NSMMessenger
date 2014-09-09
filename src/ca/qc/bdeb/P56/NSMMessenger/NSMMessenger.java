/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.ControleurLogin;
import ca.qc.bdeb.P56.NSMMessenger.Vue.Login;
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Authentificateur;

/**
 *
 * @author 1150275
 */
public class NSMMessenger {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            System.out.println("Dufour26");
            System.out.println("Grégoire");
            System.out.println("Bye");
            System.out.println("Boudreau");
            System.out.println("Dube");
            //TODO FIX MVC
            IClient client = new NSMClient();
            Login login = new Login(client);
            int connectionResult = client.connect();
            
            
    }
    
}
