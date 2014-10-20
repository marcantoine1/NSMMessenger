/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author patrick
 */
public class ProfileResponse {
    public String username;
    public String courriel;
    public String nom;
    public String prenom;
    public String sexe;
    public int age;
    public ProfileResponse(){
        
    }
    public ProfileResponse(String username, String courriel, String nom, String prenom, String sexe, int age) {
        this.username = username;
        this.courriel = courriel;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getCourriel() {
        return courriel;
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

    public int getAge() {
        return age;
    }
    
    
}

