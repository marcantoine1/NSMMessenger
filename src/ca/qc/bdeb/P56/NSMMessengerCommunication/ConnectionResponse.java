package ca.qc.bdeb.P56.NSMMessengerCommunication;

import java.util.ArrayList;

/**
 *
 * @author 1255389
 */
public class ConnectionResponse {

    public ArrayList<String> utilisateurs;

    public ConnectionResponse() {
        utilisateurs = new ArrayList<String>();
    }

    public ArrayList<String> getUtilisateurs() {
        return utilisateurs;
    }

    public void ajouterUtilisateur(String nom) {
        utilisateurs.add(nom);
    }

}
