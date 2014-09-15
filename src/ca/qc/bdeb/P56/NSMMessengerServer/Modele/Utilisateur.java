/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerServer.Modele;

import java.io.Serializable;

/**
 * @author 1150275
 */
public class Utilisateur implements Serializable {
    private String Username;
    private String UnsecuredPassword;
    private String courriel;
    private int userId;
    private static int lastId;


    public Utilisateur(String Username, String UnsecuredPassword, String courriel) {
        this.Username = Username;
        this.UnsecuredPassword = UnsecuredPassword;
        this.courriel = courriel;
        userId = lastId + 1;
        lastId++;
    }

    public String getUsername() {
        return Username;
    }

    public String getUnsecuredPassword() {
        return UnsecuredPassword;
    }

    public String getCourriel() {
        return courriel;
    }

    public int getId() {
        return userId;
    }

    public void setUsername(String Username) {
        UnsupportedOperationException();
    }

    public void setUnsecuredPassword(String UnsecuredPassword) {
        UnsupportedOperationException();
    }

    private void UnsupportedOperationException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
