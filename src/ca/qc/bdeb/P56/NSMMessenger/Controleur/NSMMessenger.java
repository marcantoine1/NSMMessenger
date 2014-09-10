/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger.Controleur;

import ca.qc.bdeb.P56.NSMMessenger.IClient;
import ca.qc.bdeb.P56.NSMMessenger.NSMClient;
import ca.qc.bdeb.P56.NSMMessenger.Vue.Login;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
/**
 *
 * @author 1150275
 */
public class NSMMessenger {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
try {
    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
} catch (Exception e) {
    // If Nimbus is not available, you can set the GUI to another look and feel.
}
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
