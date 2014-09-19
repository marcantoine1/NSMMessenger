/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 * @author 1150275
 */
public class CreationResponse {

    public ReponseCreation response;

    public CreationResponse() {
    }

    public CreationResponse(ReponseCreation i) {
        response = i;
    }

    public enum ReponseCreation {ACCEPTED, EXISTING_USERNAME, ERROR}
}
