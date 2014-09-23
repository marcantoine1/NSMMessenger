/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerServer.Modele;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 1150275
 */
public class Authentificateur {
    private final String LOCATION_BD = "NSMDatabase.db";
    private AccesBd accesBd = new AccesBd(LOCATION_BD);
    private static Authentificateur instanceAuthentificateur = new Authentificateur();

    private Authentificateur() {
        //Protection contre l'instanciation multiple du singleton. Merci à Stéphane Lévesque
        if (Authentificateur.instanceAuthentificateur != null) {
            throw new IllegalStateException("N'essayez pas d'instancier cette classe par réflection.");
        }
    }

    public synchronized static Authentificateur getInstanceAuthentificateur() {
        return Authentificateur.instanceAuthentificateur;

    }
    public boolean creerUtilisateur(String utilisateur, String motDePasse, String courriel) {
        if (accesBd.chercherUtilisateur(courriel) == null) {
            accesBd.insererUtilisateur(new Utilisateur(utilisateur, motDePasse, courriel));
            return true;
        }
        return false;
    }

    public boolean authentifierUtilisateur(String username, String motDePasse) {
        Utilisateur u;
        if((u = accesBd.chercherUtilisateur(username)) != null){
            if(u.getUnsecuredPassword().equals(motDePasse)){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    public boolean chercherUtilisateur(String username) {
        if(accesBd.chercherUtilisateur(username) != null){
            return true;
        }
        return false;
    }
    public void setNomBd(String nomBd){
        accesBd = new AccesBd(nomBd);
    }
}

