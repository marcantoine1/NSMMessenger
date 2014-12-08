package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 * Created by Martin on 2014-11-26.
 */
public class ImageRequest {
    public String utilisateurRecherche;

    public String getUtilisateurRecherche() {
        return utilisateurRecherche;
    }
public ImageRequest(){

}
    public ImageRequest(String utilisateurRecherche) {

        this.utilisateurRecherche = utilisateurRecherche;
    }
}
