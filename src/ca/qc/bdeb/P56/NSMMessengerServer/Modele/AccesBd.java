package ca.qc.bdeb.P56.NSMMessengerServer.Modele;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Martin on 2014-09-22.
 */
public class AccesBd {

    private Connection connection;

    public AccesBd() {
        if (intialiserBasedeDonnee()) {
            if (!tableExiste("UTILISATEUR")) {
                creerTable();
            }

        } else {
            connection = null;
        }
    }

    public Boolean intialiserBasedeDonnee() {
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            Logger.getLogger(Authentificateur.class.getName()).log(Level.SEVERE, "Le driver sqlite n'est pas installé!");
            return false;
        }
        connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:sqlite:NSMDonnees.db");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public Utilisateur chercherUtilisateur(int id) {
        return null;
    }

    public boolean insererUtilisateur(Utilisateur user) {
        return false;
    }

    public void deleteUtilisateur(Utilisateur user) {

    }

    public int updateUtilisateur(Utilisateur u) {
        return 0;
    }

    private void creerTable() {
        try {
            Statement requete = connection.createStatement();
            String create = "CREATE TABLE UTILISATEUR"
                    + "(ID PRIMARY KEY NOT NULL, "
                    + "NOM_UTILISATEUR TEXT NOT NULL, "
                    + "MOT_DE_PASSE TEXT NOT NULL, "
                    + "COURRIEL TEXT NOT NULL)";
            requete.executeUpdate(create);
            requete.close();
        } catch (Exception e) {

        }
    }

    //TO_DO Code_smell while
    public Boolean tableExiste(String table) {
        Boolean existe = false;
        try {
            Statement requete = connection.createStatement();
            String select = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + table + "';";
            ResultSet resultat = requete.executeQuery(select);

            while (resultat.next()) {
                existe = true;
            }
            resultat.close();
            requete.close();
        } catch (Exception e) {

        }
        return existe;
    }

    public boolean connectionEtablie() {
        return connection != null;
    }
}
