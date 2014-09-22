package ca.qc.bdeb.P56.NSMMessengerServer.Modele;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Martin on 2014-09-22.
 */
public class AccesBd {

    private Connection connection;
    private String nomBD;
    private final String COLONNE_NOM_UTILISATEUR = "NOM_UTILISATEUR",
            COLONNE_MOT_DE_PASSE = "MOT_DE_PASSE",
            COLONNE_COURRIEL = "COURRIEL",
            NOM_TABLE_UTILISATEUR = "UTILISATEUR";

    public AccesBd(String nomBD) {
        this.nomBD = nomBD;
        if (initialiserConnection(nomBD)) {
            if (!tableExiste(NOM_TABLE_UTILISATEUR)) {
                creerTable();
            }
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesBd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean initialiserConnection(String nomBd) {
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            Logger.getLogger(Authentificateur.class.getName()).log(Level.SEVERE, "Le driver sqlite n'est pas installé!");
            return false;
        }
        connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:sqlite:" + nomBd);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public Utilisateur chercherUtilisateur(String courriel) {
        Utilisateur userTrouve;
        if (initialiserConnection(nomBD)) {
            PreparedStatement stmt = null;
            try {
                connection.setAutoCommit(false);
                String selectSQL = "SELECT * FROM UTILISATEUR WHERE COURRIEL = ?";
                stmt = connection.prepareStatement(selectSQL);
                stmt.setString(1,courriel);
                ResultSet rs = stmt.executeQuery();
                userTrouve = new Utilisateur
                (rs.getString(COLONNE_NOM_UTILISATEUR),
                rs.getString(COLONNE_MOT_DE_PASSE),
                rs.getString(COLONNE_COURRIEL));               
                stmt.close();
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                return null;
            }
            return userTrouve;
        }
        return null;
    }

    public int insererUtilisateur(Utilisateur user) {
        int id;
        if (initialiserConnection(nomBD)) {
            PreparedStatement stmt = null;
            try {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement("INSERT INTO UTILISATEUR ("
                        + COLONNE_NOM_UTILISATEUR + "," 
                        + COLONNE_MOT_DE_PASSE + "," 
                        + COLONNE_COURRIEL 
                        +  ") values (?, ?, ?)");
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getUnsecuredPassword());
                stmt.setString(3, user.getCourriel());
                id = stmt.executeUpdate();
                stmt.close();
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                return -1;
            }
            return id;
        }
        return -1;
    }

    public void deleteUtilisateur(Utilisateur user) {

    }

    public int updateUtilisateur(Utilisateur u) {
        return 0;
    }

    private void creerTable() {
        try {
            Statement requete = connection.createStatement();
            String create = "CREATE TABLE " + NOM_TABLE_UTILISATEUR
                    + "(ID INTEGER PRIMARY KEY  AUTOINCREMENT, "
                    + COLONNE_NOM_UTILISATEUR + " TEXT NOT NULL, "
                    + COLONNE_MOT_DE_PASSE + " TEXT NOT NULL, "
                    + COLONNE_COURRIEL + " TEXT NOT NULL)";
            requete.executeUpdate(create);
            requete.close();
        } catch (Exception e) {

        }
    }

    //TO_DO Code_smell while
    public Boolean tableExiste(String table) {
        Boolean existe = false;
        try {
            initialiserConnection(nomBD);
            Statement requete = connection.createStatement();
            String select = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + table + "';";
            ResultSet resultat = requete.executeQuery(select);

            while (resultat.next()) {
                existe = true;
            }
            resultat.close();
            requete.close();
            connection.close();
        } catch (Exception e) {

        }
        return existe;
    }

    public boolean connectionEtablie() {
        return connection != null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection = null;
    }
}
