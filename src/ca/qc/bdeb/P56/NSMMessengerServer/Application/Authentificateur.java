/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessengerServer.Application;

import java.util.ArrayList;

/**
 * @author 1150275
 */
public class Authentificateur {

    private final String LOCATION_BD = "NSMDatabase";
    private AccesBd accesBd = new AccesBd(LOCATION_BD);
    private static final Authentificateur instanceAuthentificateur = new Authentificateur();

    private Authentificateur() {
        //Protection contre l'instanciation multiple du singleton. Merci à Stéphane Lévesque
        if (Authentificateur.instanceAuthentificateur != null) {
            throw new IllegalStateException("N'essayez pas d'instancier cette classe par réflection.");
        }
    }

    public synchronized static Authentificateur getInstanceAuthentificateur() {
        return Authentificateur.instanceAuthentificateur;

    }

    public boolean creerUtilisateur(String utilisateur, String motDePasse, String courriel, int age, String nom, String prenom, String sexe) {
        return accesBd.chercherUtilisateur(utilisateur) == null && accesBd.insererUtilisateur(new Utilisateur(utilisateur, motDePasse, courriel, age, nom, prenom, sexe));
    }

    public boolean authentifierUtilisateur(String username, String motDePasse) {
        Utilisateur u;
        return (u = accesBd.chercherUtilisateur(username)) != null && u.getUnsecuredPassword().equals(motDePasse);
    }

    public boolean utilisateurExiste(String username) {
        return accesBd.chercherUtilisateur(username) != null;
    }
    public Utilisateur chercherUtilisateur(String username){
        return accesBd.chercherUtilisateur(username);
    }
    public boolean creerContact(String utilisateurDemandant,String utilisateurDemander){
        return accesBd.insererContact(utilisateurDemandant, utilisateurDemander);
    }
    public void effacerContact(String utilisateurDemandant,String utilisateurDemander){
         accesBd.deleteContact(utilisateurDemandant, utilisateurDemander);
    }
    public ArrayList<String> chercherListeContact(String utilisateur){
       return accesBd.chercherListeContact(utilisateur);
    }
    public boolean isContact(String utilisateur,String contact){
        return accesBd.isContact(utilisateur,contact);
    }
    public void setNomBd(String nomBd) {
        accesBd = new AccesBd(nomBd);
    }
}
