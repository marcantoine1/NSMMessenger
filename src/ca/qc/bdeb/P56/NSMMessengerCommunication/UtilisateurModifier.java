package ca.qc.bdeb.P56.NSMMessengerCommunication;


/**
 *
 * @author Marc-Antoine
 */
public class UtilisateurModifier {
    String[] ancien;
    String[] nouveau;
    public UtilisateurModifier(){
    }
    public UtilisateurModifier(String[]ancien,String nouveau[]){
        this.ancien = ancien;
        this.nouveau = nouveau;
    }

    public String[] getAncien() {
        return ancien;
    }

   

    public String[] getNouveau() {
        return nouveau;
    }

    
}
