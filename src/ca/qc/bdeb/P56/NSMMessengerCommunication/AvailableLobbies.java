/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

import ca.qc.bdeb.P56.NSMMessengerServer.Lobby;
import ca.qc.bdeb.P56.NSMMessengerServer.LobbyDTO;

import java.util.HashMap;


/**
 *
 * @author 1150580
 */
public class AvailableLobbies {
    public LobbyDTO[] lobbies;
    
    public AvailableLobbies()
    {}
    public AvailableLobbies(HashMap<String, Lobby> lobbiesMap)
    {
        lobbies = LobbyDTO.fromHashmap(lobbiesMap);
    }
}
