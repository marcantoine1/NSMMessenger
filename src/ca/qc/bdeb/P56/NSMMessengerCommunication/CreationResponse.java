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
    public static final int ACCEPTED = 0, EXISTING_USERNAME = 1, ERROR = 2;
    
    public int response;
    
    public CreationResponse(){}
    public CreationResponse(int i)
    {
        response = i;
    }
}
