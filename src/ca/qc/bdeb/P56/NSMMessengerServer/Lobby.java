package ca.qc.bdeb.P56.NSMMessengerServer;

import java.util.ArrayList;
import static java.util.Collections.synchronizedList;
import java.util.List;

/**
 *
 * @author 1150580
 */
public class Lobby {
    final String name;
    private final List<Integer> users = synchronizedList(new ArrayList<Integer>());
    
    public Lobby(String name){
        this.name = name;
    }

    public synchronized void addUser(int id){
        if(!users.contains(id)) {
            users.add(id);
        }
    }
    
    public synchronized void removeUser(int id){
        users.remove((Integer) id);
    }
    
    public synchronized boolean userInLobby(int id){
        return users.contains(id);
    }
    
    public synchronized List<Integer> getUsers(){
        return users;
    }
    public String getName(){
        return this.name;
    }
}
