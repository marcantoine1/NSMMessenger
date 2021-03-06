package ca.qc.bdeb.P56.NSMMessengerCommunication;

import java.util.ArrayList;

/**
 * @author Marc-Antoine
 */
public class ListeContactResponse {
    ArrayList<String> listeContact;

    public ListeContactResponse() {
        listeContact = new ArrayList<>();
    }

    public ArrayList<String> getListeContact() {
        return listeContact;
    }

    public boolean isContact(String nom) {
        return listeContact.contains(nom);
    }

    public void setListeContact(ArrayList<String> listeContact) {
        this.listeContact = listeContact;
    }

}
