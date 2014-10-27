/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Application;

import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.mvc.Observable;

/**
 *
 * @author 1150580
 */
public interface IClient extends Observable {

    public void sendMessage(Message message);

    public void login(InfoLogin il);

    public int connect();

    public void creerCompte(InfoCreation ic);

    public void joinLobby(String lobby);

    public void leaveLobby(String lobby);

    public void creerLobby(String name);

    public void disconnect();

    public void changerIp(String inetAddress);

    public void sendProfileRequest(String string);
}
