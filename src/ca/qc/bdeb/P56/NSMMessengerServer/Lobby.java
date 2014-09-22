/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerServer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author John
 */
public class Lobby {
    int id;
    String name;
    private final ArrayList<Integer> users = new ArrayList<>();
    
    public Lobby(int id, String name)
    {
        this.name = name;
    }
    
    public void addUser(int id)
    {
        if(!users.contains(id))
            users.add(id);
    }
    
    public void removeUser(int id)
    {
        users.remove((Object) id);
    }
    
    public boolean userInLobby(int id)
    {
        return users.contains(id);
    }
    
    public List<Integer> getUsers()
    {
        return users;
    }
}
