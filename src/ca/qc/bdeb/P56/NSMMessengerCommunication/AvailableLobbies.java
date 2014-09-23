/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

import ca.qc.bdeb.P56.NSMMessengerServer.Lobby;
import ca.qc.bdeb.P56.NSMMessengerServer.LobbyODT;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author 1150580
 */
public class AvailableLobbies {
    public LobbyODT[] lobbies;
    
    public AvailableLobbies()
    {}
    public AvailableLobbies(HashMap<Integer, Lobby> lobbiesMap)
    {
        lobbies = LobbyODT.fromHashmap(lobbiesMap);
    }
}
