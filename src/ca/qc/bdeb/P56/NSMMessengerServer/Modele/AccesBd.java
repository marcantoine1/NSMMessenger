package ca.qc.bdeb.P56.NSMMessengerServer.Modele;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Martin on 2014-09-22.
 */
public class AccesBd {

    public static final String DRIVER_MANQUANT = "Le driver n'est pas installé.";
    public static final String BASE_DE_DONNÉES_INACCESSIBLE = "Base de données inaccessible";
    private Connection connection;
    private String nomBD;
    private final String COLONNE_NOM_UTILISATEUR = "NOM_UTILISATEUR",
            COLONNE_MOT_DE_PASSE = "MOT_DE_PASSE",
            COLONNE_COURRIEL = "COURRIEL",
            NOM_TABLE_UTILISATEUR = "UTILISATEUR"
            ,COLONNE_NOM = "NOM",
            COLONNE_PRENOM = "PRENOM"
            ,COLONNE_AGE = "AGE"
            ,COLONNE_SEXE = "SEXE";
            

    public AccesBd(String nomBD) {
        this.nomBD = nomBD;
        if (initialiserConnection(nomBD)) {
            if (!tableExiste(NOM_TABLE_UTILISATEUR)) {
                creerTable();
            }
        }
    }

    private Boolean initialiserConnection(String nomBd) {
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            ecrireMessageErreur(Level.SEVERE, DRIVER_MANQUANT);
            return false;
        }
        connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:sqlite:" + nomBd);
        } catch (SQLException e) {
            ecrireMessageErreur(Level.SEVERE, BASE_DE_DONNÉES_INACCESSIBLE);
            return false;
        }
        return true;
    }

    private void ecrireMessageErreur(Level severite, String message) {
        Logger.getLogger(AccesBd.class.getName()).log(severite, null, message);
    }

    public Utilisateur chercherUtilisateur(String username) {
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
                        rs.getString(COLONNE_SEXE));
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

    public boolean insererUtilisateur(Utilisateur user) {
        boolean succes = true;
        if (initialiserConnection(nomBD)) {
            PreparedStatement stmt = null;
            try {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement("INSERT INTO UTILISATEUR ("
                        + COLONNE_NOM_UTILISATEUR + ","
                        + COLONNE_MOT_DE_PASSE + ","
                        + COLONNE_COURRIEL + ","
                        + COLONNE_AGE + "," +COLONNE_NOM + ","
                        + COLONNE_PRENOM + ","
                        +COLONNE_SEXE +") values (?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getUnsecuredPassword());
                stmt.setString(3, user.getCourriel());
                stmt.setInt(4, user.getAge());
                stmt.setString(5, user.getNom());
                stmt.setString(6, user.getPrenom());
                stmt.setString(7, user.getSexe());
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

    public void deleteUtilisateur(Utilisateur user) {

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

    public void updateUtilisateur(Utilisateur u, Utilisateur nouvellesDonnees) {
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
                            + COLONNE_SEXE + " = ?"
                            + " where " + COLONNE_NOM_UTILISATEUR + " = ?");
                    statement.setString(1, nouvellesDonnees.getUsername());
                    statement.setString(2, nouvellesDonnees.getUnsecuredPassword());
                    statement.setString(3, nouvellesDonnees.getCourriel());
                    statement.setInt(4, nouvellesDonnees.getAge());
                    statement.setString(5,nouvellesDonnees.getNom());
                    statement.setString(6, nouvellesDonnees.getPrenom());
                    statement.setString(7, nouvellesDonnees.getSexe());
                    statement.setString(8,u.getUsername());
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

    private void creerTable() {
        try {
            initialiserConnection(nomBD);
            Statement requete = connection.createStatement();
            /*String create = "CREATE TABLE " + NOM_TABLE_UTILISATEUR
                    + "(ID_UTILISATEUR INTEGER PRIMARY KEY  AUTOINCREMENT, "
                    + COLONNE_NOM_UTILISATEUR + " TEXT NOT NULL, "
                    + COLONNE_MOT_DE_PASSE + " TEXT NOT NULL, "
                    + COLONNE_COURRIEL + " TEXT NOT NULL,)"
                    + COLONNE_PRENOM + " TEXT NOT NULL,"
                    + COLONNE_NOM + " TEXT NOT NULL,"
                    + COLONNE_AGE + " INTEGER NOT NULL,"
                    + COLONNE_SEXE + " TEXT NOT NULL";*/
            String create = "CREATE TABLE " + NOM_TABLE_UTILISATEUR
                    + "(ID_UTILISATEUR INTEGER PRIMARY KEY  AUTOINCREMENT, "
                    + COLONNE_NOM_UTILISATEUR + " TEXT NOT NULL, "
                    + COLONNE_MOT_DE_PASSE + " TEXT NOT NULL, "
		    + COLONNE_PRENOM + " TEXT NOT NULL, "
	            + COLONNE_NOM + " TEXT NOT NULL, "
	            + COLONNE_SEXE + " TEXT NOT NULL, "
	            + COLONNE_AGE + " INTEGER NOT NULL, "
                    + COLONNE_COURRIEL + " TEXT NOT NULL)";
            requete.executeUpdate(create);
            requete.close();
            connection.commit();
            close();
        } catch (Exception e) {
            ecrireMessageErreur(Level.SEVERE,e.getMessage());
        }
    }

    //TO_DO Code_smell while
    public Boolean tableExiste(String table) {
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
        } catch (Exception e) {
            ecrireMessageErreur(Level.INFO,e.getMessage());
        }
        close();
        return existe;
    }

    public boolean connectionEtablie() {
        return connection != null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ecrireMessageErreur(Level.INFO,ex.getMessage());
        }
        connection = null;
    }
}
