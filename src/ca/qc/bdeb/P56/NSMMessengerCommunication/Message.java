/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author John
 */
public class Message {
    String user;
    String message;
    
    public Message(String user, String message)
    {
        this.user = user;
        this.message = message;
    }   
}
