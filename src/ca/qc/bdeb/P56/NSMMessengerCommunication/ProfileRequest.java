package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author Marc-Antoine
 */
public class ProfileRequest {
    public String utilisateurRecherche;
    public String utilisateurRecherchant;
    public ProfileRequest(){
        
    }

    public ProfileRequest(String utilisateurRecherche, String utilisateurRecherchant) {
        this.utilisateurRecherchant = utilisateurRecherchant;
        this.utilisateurRecherche = utilisateurRecherche;
        
    }

    public String getUtilisateurRecherche() {
        return utilisateurRecherche;
    }
   public String getUtilisateurRecherchant(){
       return utilisateurRecherchant;
   }
}
