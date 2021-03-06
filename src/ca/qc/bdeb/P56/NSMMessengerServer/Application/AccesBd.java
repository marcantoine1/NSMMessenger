package ca.qc.bdeb.P56.NSMMessengerServer.Application;

import java.sql.Connection;
import static java.sql.DriverManager.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import static java.util.logging.Logger.getLogger;

/**
 * Created by Martin on 2014-09-22.
 */
public class AccesBd {

    private static final String DRIVER_MANQUANT = "Le driver n'est pas installé.";
    private static final String BASE_DE_DONNEES_INACCESSIBLE = "Base de données inaccessible";
    private Connection connection;
    private final String nomBD;
    private final String COLONNE_NOM_UTILISATEUR = "NOM_UTILISATEUR";
    private final String COLONNE_MOT_DE_PASSE = "MOT_DE_PASSE";
    private final String COLONNE_COURRIEL = "COURRIEL";
    private final String NOM_TABLE_UTILISATEUR = "UTILISATEUR";
    private final String COLONNE_NOM = "NOM";
    private final String COLONNE_PRENOM = "PRENOM";
    private final String COLONNE_AGE = "AGE";
    private final String COLONNE_SEXE = "SEXE";
    private final String COLONNE_NOM_UTILISATEUR_CONTACT = "NOM_UTILISATEUR_CONTACT";
    private final String COLONNE_NOM_CONTACT = "NOM_CONTACT";
    private final String NOM_TABLE_CONTACT = "CONTACT";
    private final String COLONNE_IMAGE = "IMAGE";
            

    public AccesBd(String nomBD) {
        this.nomBD = nomBD;
        if (initialiserConnection(nomBD)) {
            if (!tableExiste()) {
                creerTable();
            }
            if(!tableContactExiste()){
                creerTableContact();
            }
        }
    }

    private synchronized Boolean  initialiserConnection(String nomBd) {
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            ecrireMessageErreur(Level.SEVERE, DRIVER_MANQUANT);
            return false;
        }
        connection = null;
        try {

            connection = getConnection("jdbc:sqlite:" + nomBd);
        } catch (SQLException e) {
            ecrireMessageErreur(Level.SEVERE, BASE_DE_DONNEES_INACCESSIBLE);
            return false;
        }
        return true;
    }

    private void ecrireMessageErreur(Level severite, String message) {
        getLogger(AccesBd.class.getName()).log(severite, null, message);
    }

    public synchronized Utilisateur chercherUtilisateur(String username) {
        Utilisateur userTrouve;
        if (initialiserConnection(nomBD)) {
            PreparedStatement stmt = null;
            try {
                connection.setAutoCommit(false);
                String selectSQL = "SELECT * FROM " + NOM_TABLE_UTILISATEUR + " WHERE " + COLONNE_NOM_UTILISATEUR + " = ?";
                stmt = connection.prepareStatement(selectSQL);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                userTrouve = new Utilisateur(rs.getString(COLONNE_NOM_UTILISATEUR),
                        rs.getString(COLONNE_MOT_DE_PASSE),
                        rs.getString(COLONNE_COURRIEL),
                        rs.getInt(COLONNE_AGE),
                        rs.getString(COLONNE_NOM),
                        rs.getString(COLONNE_PRENOM),
                        rs.getString(COLONNE_SEXE),
                        rs.getString(COLONNE_IMAGE));
                stmt.close();
            } catch (SQLException e) {
                userTrouve = null;
                ecrireMessageErreur(Level.INFO,e.getMessage());
            }
            close();
            return userTrouve;
        }
        return null;
    }
    public synchronized boolean isContact(String username,String contactPotentiel) {
        boolean isContact = false;
        if (initialiserConnection(nomBD)) {
            PreparedStatement stmt = null;
            try {
                connection.setAutoCommit(false);
                String selectSQL = "SELECT " + COLONNE_NOM_CONTACT + " FROM "
                        + NOM_TABLE_CONTACT + " WHERE " +
                        COLONNE_NOM_UTILISATEUR_CONTACT + " = ?" + " AND " + COLONNE_NOM_CONTACT + " = ?";
                stmt = connection.prepareStatement(selectSQL);
                stmt.setString(1, username);
                stmt.setString(2, contactPotentiel);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    isContact = true;
                }
                stmt.close();
            } catch (SQLException e) {

                ecrireMessageErreur(Level.INFO, e.getMessage());
            }
            close();

        }
        return isContact;
    }
    public synchronized ArrayList<String> chercherListeContact(String username){
        ArrayList<String> listeContact = new ArrayList<String>();
        if (initialiserConnection(nomBD)) {
            PreparedStatement stmt = null;
            try {
                connection.setAutoCommit(false);
                String selectSQL = "SELECT " +COLONNE_NOM_CONTACT+" FROM " 
                        + NOM_TABLE_CONTACT + " WHERE " + 
                        COLONNE_NOM_UTILISATEUR_CONTACT + " = ?";
                stmt = connection.prepareStatement(selectSQL);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    listeContact.add(rs.getString(1));
                }
                stmt.close();
            } catch (SQLException e) {
                listeContact = null;
                ecrireMessageErreur(Level.INFO,e.getMessage());
            }
            close();
            return listeContact;
        }
        return null;
    }
    public synchronized boolean insererUtilisateur(Utilisateur user) {
        boolean succes = true;
        if (initialiserConnection(nomBD)) {
            PreparedStatement stmt = null;
            try {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement("INSERT INTO " +NOM_TABLE_UTILISATEUR+" ("
                        + COLONNE_NOM_UTILISATEUR + ","
                        + COLONNE_MOT_DE_PASSE + ","
                        + COLONNE_COURRIEL + ","
                        + COLONNE_AGE + "," 
                        + COLONNE_NOM + ","
                        + COLONNE_PRENOM + ","
                        + COLONNE_SEXE + ","
                        + COLONNE_IMAGE + ") values (?, ?, ?, ?, ?, ?, ?, ?)");
                remplirTable(user, stmt);
                stmt.executeUpdate();
                stmt.close();
                connection.commit();
            } catch (SQLException e) {
                succes = false;
                ecrireMessageErreur(Level.INFO,e.getMessage());
            }
            close();
        }
        return succes;
    }
     public synchronized boolean insererContact(String user, String contact ) {
        boolean succes = true;
        if (initialiserConnection(nomBD)) {
            PreparedStatement stmt = null;
            try {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement("INSERT INTO " + NOM_TABLE_CONTACT 
                        +" ("
                        + COLONNE_NOM_UTILISATEUR_CONTACT + ","
                        + COLONNE_NOM_CONTACT 
                        +") values (?, ?)");
                stmt.setString(1, user);
                stmt.setString(2, contact);
                stmt.executeUpdate();
                stmt.close();
                connection.commit();
            } catch (SQLException e) {
                succes = false;
                ecrireMessageErreur(Level.INFO,e.getMessage());
            }
            close();
        }
        return succes;
    }

    private synchronized void remplirTable(Utilisateur user, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getUnsecuredPassword());
        stmt.setString(3, user.getCourriel());
        stmt.setInt(4, user.getAge());
        stmt.setString(5, user.getNom());
        stmt.setString(6, user.getPrenom());
        stmt.setString(7, user.getSexe());
        stmt.setString(8, user.getImage());
    }
    
    public synchronized void deleteUtilisateur(Utilisateur user) {

        if (initialiserConnection(nomBD)) {
            PreparedStatement statement = null;
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement("delete from " + NOM_TABLE_UTILISATEUR + " where " + COLONNE_NOM_UTILISATEUR + " = ?");
                statement.setString(1, user.getUsername());
                statement.executeUpdate();
                statement.close();
                connection.commit();
            } catch (SQLException ex) {
                ecrireMessageErreur(Level.INFO,ex.getMessage());
            }
            close();
        }
    }
    public synchronized void deleteContact(String user, String contact) {

        if (initialiserConnection(nomBD)) {
            PreparedStatement statement = null;
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement("delete from " + NOM_TABLE_CONTACT + " where " + COLONNE_NOM_UTILISATEUR_CONTACT + " = ? "
                        + "and " + COLONNE_NOM_CONTACT + " = ?");
               statement.setString(1, user);
               statement.setString(2, contact);
                statement.executeUpdate();
                statement.close();
                connection.commit();
            } catch (SQLException ex) {
                ecrireMessageErreur(Level.INFO,ex.getMessage());
            }
            close();
        }
    }

    public synchronized void updateUtilisateur(Utilisateur u, Utilisateur nouvellesDonnees) {
        if (chercherUtilisateur(u.getUsername()) != null) {
            if (initialiserConnection(nomBD)) {
                PreparedStatement statement = null;
                try {
                    connection.setAutoCommit(false);
                    statement = connection.prepareStatement("update " + NOM_TABLE_UTILISATEUR
                            + " set " + COLONNE_NOM_UTILISATEUR + " = ?,"
                            + COLONNE_MOT_DE_PASSE + " = ?,"
                            + COLONNE_COURRIEL + " = ?,"
                            + COLONNE_AGE + " = ?,"
                            + COLONNE_NOM + " = ?,"
                            + COLONNE_PRENOM + " = ?,"
                            + COLONNE_SEXE + " = ?,"
                            + COLONNE_IMAGE + " = ?"
                            + " where " + COLONNE_NOM_UTILISATEUR + " = ?");
                    remplirTable(nouvellesDonnees, statement);
                    statement.setString(9,u.getUsername());
                    statement.executeUpdate();
                    statement.close();
                    connection.commit();
                } catch (SQLException ex) {
                    ecrireMessageErreur(Level.INFO,ex.getMessage());
                }
                close();
            }
        }
    }

    private synchronized void creerTable() {
        try {
            initialiserConnection(nomBD);
            Statement requete = connection.createStatement();
            String create = "CREATE TABLE " + NOM_TABLE_UTILISATEUR
                    + "(ID_UTILISATEUR INTEGER PRIMARY KEY  AUTOINCREMENT, "
                    + COLONNE_NOM_UTILISATEUR + " TEXT NOT NULL, "
                    + COLONNE_MOT_DE_PASSE + " TEXT NOT NULL, "
		    + COLONNE_PRENOM + " TEXT NOT NULL, "
	            + COLONNE_NOM + " TEXT NOT NULL, "
	            + COLONNE_SEXE + " TEXT NOT NULL, "
	            + COLONNE_AGE + " INTEGER NOT NULL, "
                    + COLONNE_COURRIEL + " TEXT NOT NULL,"
                    + COLONNE_IMAGE + " TEXT NOT NULL)";
            requete.executeUpdate(create);
            requete.close();
            connection.commit();
            close();
        } catch (SQLException e) {
            ecrireMessageErreur(Level.SEVERE,e.getMessage());
        }
    }
    private synchronized void creerTableContact() {
        try {
            initialiserConnection(nomBD);
            Statement requete = connection.createStatement();
            String COLONNE_ID_LIAISON = "ID_LIAISON";
            String FK_Contact = " FK_NOM_CONTACT";
            String FK_Utilisateur = " FK_NOM_UTILISATEUR";
            String createTableContact = "CREATE TABLE " + NOM_TABLE_CONTACT +
                    "(" + COLONNE_ID_LIAISON + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLONNE_NOM_UTILISATEUR_CONTACT + " TEXT NOT NULL,"
                    + COLONNE_NOM_CONTACT + " TEXT NOT NULL,"
                    + "CONSTRAINT" + FK_Utilisateur 
                    + " FOREIGN KEY (" + COLONNE_NOM_UTILISATEUR_CONTACT + ")"
                    + "REFERENCES " + NOM_TABLE_UTILISATEUR
                    + "(" +COLONNE_NOM_UTILISATEUR + "),"
                    + "CONSTRAINT" + FK_Contact 
                    + " FOREIGN KEY (" + COLONNE_NOM_CONTACT + ")"
                    + "REFERENCES " + NOM_TABLE_UTILISATEUR
                    + "(" +COLONNE_NOM_UTILISATEUR + "))";
            requete.executeUpdate(createTableContact);
            requete.close();
            connection.commit();
            close();
        } catch (SQLException e) {
            ecrireMessageErreur(Level.SEVERE,e.getMessage());
        }
    }
    
    public synchronized Boolean tableExiste() {
        Boolean existe = false;
        try {
            initialiserConnection(nomBD);
            Statement requete = connection.createStatement();
            String select = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + NOM_TABLE_UTILISATEUR + "';";
            ResultSet resultat = requete.executeQuery(select);

            while (resultat.next()) {
                existe = true;
            }
            resultat.close();
            requete.close();
            
        } catch (SQLException e) {
            ecrireMessageErreur(Level.INFO,e.getMessage());
        }
        close();
        return existe;
    }

    public synchronized Boolean tableContactExiste() {
        Boolean existe = false;
        try {
            initialiserConnection(nomBD);
            Statement requete = connection.createStatement();
            String select = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + NOM_TABLE_CONTACT + "';";
            ResultSet resultat = requete.executeQuery(select);

            while (resultat.next()) {
                existe = true;
            }
            resultat.close();
            requete.close();
        } catch (SQLException e) {
            ecrireMessageErreur(Level.INFO,e.getMessage());
        }
        close();
        return existe;
    }

    public synchronized boolean connectionEtablie() {
        return connection != null;
    }

    void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ecrireMessageErreur(Level.INFO,ex.getMessage());
        }
        connection = null;
    }
}
