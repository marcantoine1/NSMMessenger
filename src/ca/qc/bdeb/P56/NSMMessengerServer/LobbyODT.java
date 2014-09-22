/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerServer;

/**
 *
 * @author 1150580
 */
public class LobbyODT {
    int id;
    String name;
    
    public LobbyODT(Lobby lobby)
    {
        id = lobby.id;
        name = lobby.name;
    }
}
