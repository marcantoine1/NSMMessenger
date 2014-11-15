/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author Guillaume
 */
public class SelfProfileResponse {
    String Nom;
    String Prenom;
    int Age;
    String Courriel;
    String MotDePasse;
    String Username;
    String sexe;
    String image;
    public SelfProfileResponse(){

    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getUsername() {
        return Username;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public void setCourriel(String Courriel) {
        this.Courriel = Courriel;
    }
    public void setSexe(String sexe){
        this.sexe = sexe;
    }

    public void setMotDePasse(String MotDePasse) {
        this.MotDePasse = MotDePasse;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public int getAge() {
        return Age;
    }

    public String getCourriel() {
        return Courriel;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }
    public String getSexe(){
        return sexe;
    }
}
