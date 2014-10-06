/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.InfoCreation;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.InfoLogin;
import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.mvc.Observable;
import java.net.InetAddress;

/**
 *
 * @author 1150580
 */
public interface IClient extends Observable{
    public void sendMessage(Message message);
    public void login(InfoLogin il);
    public int connect();
    public void creerCompte(InfoCreation ic);
    public void joinLobby(int lobby);
    public void leaveLobby(int lobby);
    public void creerLobby(String name);

    public void changerIp(String inetAddress);

    public void sendProfileRequest(String string);
}
