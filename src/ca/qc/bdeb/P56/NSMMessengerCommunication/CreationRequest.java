/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author 1150275
 */
public class CreationRequest {
    public String username;
    public String password;
    public String courriel;
    
    
    public CreationRequest(){}
    public CreationRequest(String user, String pass, String courriel)
    {
        this.username = user;
        this.password = pass;
        this.courriel = courriel;
    } 
}
