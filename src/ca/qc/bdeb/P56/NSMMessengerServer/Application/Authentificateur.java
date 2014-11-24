package ca.qc.bdeb.P56.NSMMessengerServer.Application;

import ca.qc.bdeb.P56.NSMMessengerCommunication.UtilisateurModifier;

import java.util.ArrayList;

/**
 * @author 1150275
 */
public class Authentificateur {

    private final String LOCATION_BD = "NSMDatabase";
    private AccesBd accesBd = new AccesBd(LOCATION_BD);
    private static final Authentificateur instanceAuthentificateur = new Authentificateur();
    private static final String CLE = "859E381543769334";

    private Authentificateur() {
        //Protection contre l'instanciation multiple du singleton. Merci à Stéphane Lévesque
        if (Authentificateur.instanceAuthentificateur != null) {
            throw new IllegalStateException("N'essayez pas d'instancier cette classe par réflection.");
        }
    }

    public synchronized static Authentificateur getInstanceAuthentificateur() {
        return Authentificateur.instanceAuthentificateur;

    }

    public boolean creerUtilisateur(String utilisateur, String motDePasse, String courriel, int age, String nom, String prenom, String sexe,String image) {
        return accesBd.chercherUtilisateur(utilisateur) == null && accesBd.insererUtilisateur(new Utilisateur(utilisateur, Encrypteur.encrypter(motDePasse, CLE), courriel, age, nom, prenom, sexe,image));
    }

    public void updaterUtilisateur(UtilisateurModifier util) {
        Utilisateur utilNouveau;
        Utilisateur utilAncien = new Utilisateur(util.getAncien()[0], util.getAncien()[1], util.getAncien()[2], Integer.parseInt(util.getAncien()[3]),
                util.getAncien()[4], util.getAncien()[5], util.getAncien()[6],util.getAncien()[7]);
        if (util.getAncien()[1].equals(util.getNouveau()[1])) {

            utilNouveau = new Utilisateur(util.getNouveau()[0], util.getNouveau()[1], util.getNouveau()[2], Integer.parseInt(util.getNouveau()[3]),
                    util.getNouveau()[4], util.getNouveau()[5], util.getNouveau()[6],util.getNouveau()[7]);
        } else {
            utilNouveau = new Utilisateur(util.getNouveau()[0], Encrypteur.encrypter(util.getNouveau()[1], CLE), util.getNouveau()[2], Integer.parseInt(util.getNouveau()[3]),
                    util.getNouveau()[4], util.getNouveau()[5], util.getNouveau()[6],util.getNouveau()[7]);
        }

        accesBd.updateUtilisateur(utilAncien, utilNouveau);
    }
    public void updaterUtilisateur(Utilisateur u,String motDePasse){
        Utilisateur nouveau = new Utilisateur(u.getUsername(), Encrypteur.encrypter(motDePasse,CLE), 
                u.getCourriel(), u.getAge(), u.getNom(), u.getPrenom(), u.getSexe(), u.getImage());
        accesBd.updateUtilisateur(u, nouveau);
    }
    public boolean authentifierUtilisateur(String username, String motDePasse) {
        Utilisateur u;
        return (u = accesBd.chercherUtilisateur(username)) != null && Encrypteur.decrypter(u.getUnsecuredPassword(), CLE).equals(motDePasse);
    }

    public boolean utilisateurExiste(String username) {
        return accesBd.chercherUtilisateur(username) != null;
    }

    public Utilisateur chercherUtilisateur(String username) {
        return accesBd.chercherUtilisateur(username);
    }

    public boolean creerContact(String utilisateurDemandant, String utilisateurDemander) {
        return accesBd.insererContact(utilisateurDemandant, utilisateurDemander);
    }

    public void effacerContact(String utilisateurDemandant, String utilisateurDemander) {
        accesBd.deleteContact(utilisateurDemandant, utilisateurDemander);
    }

    public ArrayList<String> chercherListeContact(String utilisateur) {
        return accesBd.chercherListeContact(utilisateur);
    }

    public boolean isContact(String utilisateur, String contact) {
        return accesBd.isContact(utilisateur, contact);
    }

    public void setNomBd(String nomBd) {
        accesBd = new AccesBd(nomBd);
    }
}
