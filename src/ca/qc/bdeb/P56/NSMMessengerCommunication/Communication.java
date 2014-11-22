package ca.qc.bdeb.P56.NSMMessengerCommunication;

import com.esotericsoftware.kryo.Kryo;
import java.util.ArrayList;



public class Communication {
    public static final int PORT = 31447;
    
    public static void initialiserKryo(Kryo kryo){
        kryo.register(LoginRequest.class);
        kryo.register(Message.class);
        kryo.register(LoginResponse.class);
        kryo.register(CreationResponse.class);
        kryo.register(CreationRequest.class);
        kryo.register(CreationResponse.ReponseCreation.class);
        kryo.register(LoginResponse.ReponseLogin.class);
        kryo.register(LobbyAction.class);
        kryo.register(LobbyAction.Action.class);
        kryo.register(AvailableLobbies.class);
        kryo.register(NotificationUtilisateurConnecte.class);
        kryo.register(LobbyJoinedNotification.class);
        kryo.register(ArrayList.class);
        kryo.register(CreateLobby.class);
        kryo.register(ProfileRequest.class);
        kryo.register(ProfileResponse.class);
        kryo.register(String.class);
        kryo.register(String[].class);
        kryo.register(ContactRequest.class);
        kryo.register(ContactEffacerRequest.class);
        kryo.register(ListeContactResponse.class);
        kryo.register(ListeContactRequest.class);
        kryo.register(ConnectionResponse.class);
        kryo.register(SelfProfileResponse.class);        
        kryo.register(ContactResponseFailed.class);
        kryo.register(UtilisateurModifier.class);
        kryo.register(LogoutRequest.class);
    }
}
