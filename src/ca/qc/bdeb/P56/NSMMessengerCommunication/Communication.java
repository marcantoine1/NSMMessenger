/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

import ca.qc.bdeb.P56.NSMMessengerServer.LobbyODT;
import com.esotericsoftware.kryo.Kryo;
import java.util.ArrayList;


/**
 *
 * @author 1150580
 */
public class Communication {
    public static final int PORT = 31447;
    
    public static void initialiserKryo(Kryo kryo)
    {
        kryo.register(LoginRequest.class);
        kryo.register(Message.class);
        kryo.register(LoginResponse.class);
        kryo.register(CreationResponse.class);
        kryo.register(CreationRequest.class);
        kryo.register(CreationResponse.ReponseCreation.class);
        kryo.register(LoginResponse.ReponseLogin.class);
        kryo.register(LobbyAction.class);
        kryo.register(LobbyAction.Action.class);
        kryo.register(LobbyODT.class);
        kryo.register(LobbyODT[].class);
        kryo.register(AvailableLobbies.class);
        kryo.register(NotificationUtilisateurConnecte.class);
        kryo.register(NotificationUtilisateurDeconnecte.class);
        kryo.register(ListeUtilisateursLobby.class);
        kryo.register(ArrayList.class);
        kryo.register(String.class);
    }
}
