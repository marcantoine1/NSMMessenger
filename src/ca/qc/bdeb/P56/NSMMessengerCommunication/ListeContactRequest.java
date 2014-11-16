package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author Marc-Antoine
 */
public class ListeContactRequest {
    String username;
    public ListeContactRequest(){
        
    }
    public ListeContactRequest(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
