package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author 1150275
 */
public class CreationResponse {
    
    
    public ReponseCreation response;
    public String username;
    public String password;
    
    public CreationResponse(){
    }
    public CreationResponse(ReponseCreation i, String username, String password){
        response = i;
        this.username = username;
        this.password = password;
    }

    public enum ReponseCreation {

        ACCEPTED, EXISTING_USERNAME, ERROR
    }
}
