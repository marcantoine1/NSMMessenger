package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author 1150580
 */
public class LoginRequest {
    public String username;
    public String password;
    
    
    public LoginRequest(){
    }
    public LoginRequest(String user, String pass){
        username = user;
        password = pass;
    } 
}
