package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author Marc-Antoine
 */
public class ContactEffacerRequest {
    String userDemandant;
    String userDemander;
    public ContactEffacerRequest(){
        
    }
    public ContactEffacerRequest(String userDemandant, String userDemander){
        this.userDemandant = userDemandant;
        this.userDemander = userDemander;
    }

    public String getUserDemandant() {
        return userDemandant;
    }

    public String getUserDemander() {
        return userDemander;
    }
    
}
