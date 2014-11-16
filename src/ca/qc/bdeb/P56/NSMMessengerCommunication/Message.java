package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author 1150580
 */
public class Message {
    public String user;
    public String message;
    public String lobby;
    
    public Message(){
    }
    
    public Message(String lobby, String message){
        this.lobby = lobby;
        this.message = message;
    }
    public Message(String lobby, String message, String user){
        this.lobby = lobby;
        this.user = user;
        this.message = message;
    }   
}
