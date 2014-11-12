/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessengerServer.Application;

import java.io.Serializable;

/**
 * @author 1150275
 */
public class Utilisateur implements Serializable {

    private final String Username;
    private final String UnsecuredPassword;
    private final String courriel;
    private final int userId;
    private static int lastId;
    private final int age;
    private final String nom;
    private final String prenom;
    private final String sexe;

    public Utilisateur(String Username, String UnsecuredPassword, String courriel, int age, String nom, String prenom, String sexe) {
        this.Username = Username;
        this.UnsecuredPassword = UnsecuredPassword;
        this.courriel = courriel;
        this.age = age;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
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

    public int getAge() {
        return age;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Utilisateur) {
            Utilisateur utilisateurCompare = (Utilisateur) obj;
            if (utilisateurCompare.getUsername().equals(this.getUsername())
                    && utilisateurCompare.getUnsecuredPassword().equals(this.getUnsecuredPassword())
                    && utilisateurCompare.getNom().equals(this.getNom())
                    && utilisateurCompare.getPrenom().equals(this.getPrenom())
                    && utilisateurCompare.getAge() == (this.getAge())
                    && utilisateurCompare.getSexe().equals(this.getSexe())) {
                return true;
            }
        }
        return false;
    }

    public void setUsername(String Username) {
        UnsupportedOperationException();
    }

    public void setUnsecuredPassword(String UnsecuredPassword) {
       throw new UnsupportedOperationException();
    }

    private void UnsupportedOperationException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
