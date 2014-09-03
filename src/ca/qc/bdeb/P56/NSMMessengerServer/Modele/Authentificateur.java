/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerServer.Modele;

import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author 1150275
 */
public class Authentificateur {

    public void Authentificateur() throws ParserConfigurationException{
        unserializeUserList();
    }
    public boolean creerUtilisateur(String utilisateur, String motDePasse){
        return false;
    }
    public boolean authentifierUtilisateur(String utilisateur, String motDePasse){
        return false;
    }
    public ArrayList<Utilisateur> unserializeUserList()
    {
        return null;
    }
    public ArrayList<Utilisateur> serializeUserList()
    {
        return null;
    }
    
    
}
