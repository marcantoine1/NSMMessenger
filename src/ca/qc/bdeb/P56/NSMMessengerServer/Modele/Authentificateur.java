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
    /*
    *String de la location, dans le système de fichier, de la liste d'utilisateurs
    */
    private static String locationListeUtilisateurs = "ressources/listeUtilisateurs.ser";
    private static Authentificateur instanceAuthentificateur = new Authentificateur();
    /*
    *Liste des utilisateurs.
    */
    private ArrayList<Utilisateur> listeUtilisateur;

    private Authentificateur() {
        //Protection contre l'instanciation multiple du singleton. Merci à Stéphane Lévesque
        if (Authentificateur.instanceAuthentificateur != null) {
            throw new IllegalStateException("N'essayez pas d'instancier cette classe par réflection.");
        }
    }

    public synchronized static Authentificateur getInstanceAuthentificateur() {
        return Authentificateur.instanceAuthentificateur;

    }

    public static void setLocationListe(String location) {
        locationListeUtilisateurs = location;
    }

    public boolean creerUtilisateur(String utilisateur, String motDePasse, String courriel) {
        boolean utilisateurExistant = chercherUtilisateur(utilisateur);
        if (!utilisateurExistant) {
            listeUtilisateur.add(new Utilisateur(utilisateur, motDePasse, courriel));
            return true;
        }
        return false;
    }

    public boolean authentifierUtilisateur(String utilisateur, String motDePasse) {
        for (Utilisateur u : listeUtilisateur) {
            if (u.getUsername().equals(utilisateur)) {
                if (u.getUnsecuredPassword().equals((motDePasse))) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public synchronized void demarrerAuthentificateur() {
        if (listeUtilisateur == null) {
            listeUtilisateur = unserializeUserList(locationListeUtilisateurs);
        } else {
            System.out.println("L'authentificateur est déja démarré!");
        }
    }

    public synchronized void sauvegarderEtFermerAuthentificateur() {

        if (listeUtilisateur != null) {
            serializeUserList(locationListeUtilisateurs);
        } else {
            throw new IllegalStateException("L'authentificateur est déja fermé");
        }
    }

    public ArrayList<Utilisateur> getListeUtilisateurs() {
        return listeUtilisateur;
    }

    public boolean chercherUtilisateur(String username) {
        for (Utilisateur u : listeUtilisateur) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Utilisateur> unserializeUserList(String cible) {
        ArrayList<Utilisateur> listeUtilisateurs = null;
        try {
            FileInputStream fileIn = new FileInputStream(cible);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            listeUtilisateurs = (ArrayList<Utilisateur>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Cette liste d'utilisateurs n'existe pas!");
            listeUtilisateurs = new ArrayList<>();
            c.printStackTrace();
        }
        return listeUtilisateurs;
    }

    private void serializeUserList(String cible) {
        ObjectOutputStream ecrivainObjet = null;
        try {
            final FileOutputStream fichier = new FileOutputStream(cible);
            ecrivainObjet = new ObjectOutputStream(fichier);
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        }
        try {
            if (ecrivainObjet != null) {
                ecrivainObjet.writeObject(cible);
                ecrivainObjet.flush();
                ecrivainObjet.close();
            }
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

    public Boolean intialiserBasedeDonnee() {
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            Logger.getLogger(Authentificateur.class.getName()).log(Level.SEVERE, "Le driver sqlite n'est pas installé!");
            return false;
        }


        Connection connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:sqlite:NSMDonnees.db");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}

