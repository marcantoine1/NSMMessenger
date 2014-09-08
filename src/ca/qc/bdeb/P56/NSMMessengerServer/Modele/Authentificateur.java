/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerServer.Modele;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author 1150275
 */
public class Authentificateur {
    /*
    *String de la location, dans le système de fichier, de la liste d'utilisateurs
    */
    private static String locationListeUtilisateurs;
    /*
    *Liste des utilisateurs.
    */
    private ArrayList<Utilisateur> listeUtilisateur;
    private static Authentificateur instanceAuthentificateur = new Authentificateur();
    private Authentificateur(){
        //Protection contre l'instanciation multiple du singleton. Merci à Stéphane Lévesque
        if (Authentificateur.instanceAuthentificateur != null) {
            throw new IllegalStateException("N'essayez pas d'instancier cette classe par réflection.");
        }
    }
    public synchronized static Authentificateur getInstanceAuthentificateur(String locationListeUtilisateurs){
        setLocationListe(locationListeUtilisateurs);
        return Authentificateur.instanceAuthentificateur;
        
    }
    public static void setLocationListe(String location){
        locationListeUtilisateurs = location;
    }
    public boolean creerUtilisateur(String utilisateur, String motDePasse){
        return false;
    }
    public boolean authentifierUtilisateur(String utilisateur, String motDePasse){
        return false;
    }
    public synchronized void demarrerAuthentificateur(){
        if(listeUtilisateur == null){
            listeUtilisateur = unserializeUserList(locationListeUtilisateurs);
        }
        else{
            throw new IllegalStateException("L'authentificateur est déja démarré!");
        }
    }
    public synchronized void sauvegarderEtFermerAuthentificateur(){
        
        if(listeUtilisateur != null){
            serializeUserList(locationListeUtilisateurs);
        }
        else{
            throw new IllegalStateException("L'authentificateur est déja fermé");
        }
    }
    public ArrayList<Utilisateur> getListeUtilisateurs(){
        return listeUtilisateur;
    }
    private ArrayList<Utilisateur> unserializeUserList(String cible)
    {
        ArrayList<Utilisateur> listeUtilisateurs = null;
        try
      {
         FileInputStream fileIn = new FileInputStream(cible);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         listeUtilisateurs = (ArrayList<Utilisateur>) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i)
      {
         i.printStackTrace();
      }catch(ClassNotFoundException c)
      {
         System.out.println("Cette liste d'utilisateurs n'existe pas!");
         c.printStackTrace();
      }
        return listeUtilisateurs;
    }
    private void serializeUserList(String cible)
    {
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
}

