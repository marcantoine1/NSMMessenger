package ca.qc.bdeb.P56.NSMMessengerCommunication;


/**
 *
 * @author 1150580
 */
public class LoginResponse {
    
    public ReponseLogin response;
    
    public LoginResponse(){
    }
    public LoginResponse(ReponseLogin r){
        response = r;
    }

    public enum ReponseLogin {

        ACCEPTED, REFUSED, ERROR
    }
}
