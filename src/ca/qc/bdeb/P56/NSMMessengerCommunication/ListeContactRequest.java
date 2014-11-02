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
public class ListeContactRequest {
    String username;
    public ListeContactRequest(){
        
    }
    public ListeContactRequest(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
