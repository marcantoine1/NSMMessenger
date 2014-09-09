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
public class LoginResponse {
    public static final int ACCEPTED = 0, REFUSED = 1, ERROR = 2;
    
    public int response;
    
    public LoginResponse(){}
    public LoginResponse(int i)
    {
        response = i;
    }
}
