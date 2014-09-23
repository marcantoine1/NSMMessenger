/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerServer;

import java.util.ArrayList;
import java.util.HashMap;

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
    
    public static LobbyODT[] fromHashmap(HashMap<Integer, Lobby> lobbiesMap)
    {
        ArrayList<LobbyODT> lobbies = new ArrayList<>();
        
        for(Lobby lobby : lobbiesMap.values())
        {
            lobbies.add(new LobbyODT(lobby));
        }
        
        return (LobbyODT[]) lobbies.toArray();
    }
}
