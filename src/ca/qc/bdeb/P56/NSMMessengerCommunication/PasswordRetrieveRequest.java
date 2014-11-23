package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author Marc-Antoine
 */
public class PasswordRetrieveRequest {
    String username;
    public PasswordRetrieveRequest(){
        
    }
    public PasswordRetrieveRequest(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
