/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger;

/**
 *
 * @author 1150580
 */
public interface IClient {
    public void sendMessage(String s);
    public void login(String user, String password);
    public int connect();
}
