/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 * @author 1150580
 */
public class Message {
    public String user;
    public String message;

    public Message() {
    }



    public Message(String user, String message) {
        this.user = user;
        this.message = message;
    }
}
