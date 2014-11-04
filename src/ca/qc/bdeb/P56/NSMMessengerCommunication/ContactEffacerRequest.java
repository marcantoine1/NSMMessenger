/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author Marc-Antoine
 */
public class ContactEffacerRequest {
    String userDemandant;
    String userDemander;
    public ContactEffacerRequest(){
        
    }
    public ContactEffacerRequest(String userDemandant, String userDemander){
        this.userDemandant = userDemandant;
        this.userDemander = userDemander;
    }

    public String getUserDemandant() {
        return userDemandant;
    }

    public String getUserDemander() {
        return userDemander;
    }
    
}
