/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerServer.Modele;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author 1150275
 */
public class Authentificateur {
    //Encryption dans les methode de xml du password
    private DocumentBuilderFactory xmlFactory;
    DocumentBuilder builder;
    public void Authentificateur() throws ParserConfigurationException{
        xmlFactory = DocumentBuilderFactory.newInstance();
        builder = xmlFactory.newDocumentBuilder();
    }
    public boolean creerUtilisateur(String utilisateur, String motDePasse){
        return false;
    }
    public boolean authentifierUtilisateur(String utilisateur, String motDePasse){
        return false;
    }
    private void userToXml(){
        
    } 
    private void passwordToXml(){
        
    }
    private void userFromXml(){
        
    }
    private void passwordFromXml(){
        
    }
    
    
}
