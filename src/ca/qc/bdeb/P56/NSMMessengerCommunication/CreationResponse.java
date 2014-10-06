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
public class CreationResponse {
    
    public enum ReponseCreation {ACCEPTED, EXISTING_USERNAME, ERROR }
    
    public ReponseCreation response;
    public String username;
    public String password;
    
    public CreationResponse(){}
    public CreationResponse(ReponseCreation i, String username, String password)
    {
        response = i;
        this.username = username;
        this.password = password;
    }
}
