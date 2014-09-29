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
public class LobbyDTO {
    public int id;
    public String name;
    
    public LobbyDTO()
    {
        
    }
    
    private LobbyDTO(Lobby lobby)
    {
        id = lobby.id;
        name = lobby.name;
    }
    
    public static LobbyDTO[] fromHashmap(HashMap<Integer, Lobby> lobbiesMap)
    {
        ArrayList<LobbyDTO> lobbies = new ArrayList<>();
        
        for(Lobby lobby : lobbiesMap.values())
        {
            lobbies.add(new LobbyDTO(lobby));
        }
        LobbyDTO[] dummy = new LobbyDTO[lobbies.size()];
        return lobbies.toArray(dummy);
    }
}
