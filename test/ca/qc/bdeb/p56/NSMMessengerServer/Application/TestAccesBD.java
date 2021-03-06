package ca.qc.bdeb.p56.NSMMessengerServer.Application;

/**
 * Created by Martin on 2014-09-22.
 */
import ca.qc.bdeb.P56.NSMMessengerServer.Application.AccesBd;
import ca.qc.bdeb.P56.NSMMessengerServer.Application.Authentificateur;
import ca.qc.bdeb.P56.NSMMessengerServer.Application.Utilisateur;
import org.junit.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * @author 1150275
 */
public class TestAccesBD {

    private static final String nom_bd_test = "dbTest";
    private static AccesBd baseDonnee;
    private Connection connection;

    public TestAccesBD() {
    }

    @BeforeClass
    public static void setUpClass() {
        baseDonnee = new AccesBd(nom_bd_test);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        truncateTable();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreerTable() {
        assertTrue(baseDonnee.tableExiste());
    }

    @Test
    public void testCreerTableContact() {
        assertTrue(baseDonnee.tableContactExiste());
    }

    @Test
    public void insererUnUtilisateur() {
        Utilisateur user = new Utilisateur("User", "pass", "test@test.ca", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        assertTrue(baseDonnee.insererUtilisateur(user));
    }

    @Test
    public void trouverUnUtilisateur() {
        Utilisateur user = new Utilisateur("Username", "Password", "Courriel", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        baseDonnee.insererUtilisateur(user);
        assertTrue(user.equals(baseDonnee.chercherUtilisateur("Username")));
    }

    @Test
    public void effacerUnUtilisateur() {
        Utilisateur u = new Utilisateur("a", "b", "c", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        baseDonnee.insererUtilisateur(u);
        baseDonnee.deleteUtilisateur(u);
        assertNull(baseDonnee.chercherUtilisateur(u.getUsername()));
    }

    @Test
    public void mettreAJourUnUtilisateur() {
        Utilisateur u = new Utilisateur("a", "b", "c", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        baseDonnee.insererUtilisateur(u);
        baseDonnee.updateUtilisateur(u, new Utilisateur("c", "b", "a", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg"));
        assertNotNull(baseDonnee.chercherUtilisateur("c"));
        assertNull(baseDonnee.chercherUtilisateur("a"));
    }

    @Test
    public void effacerUnUtilisateurExistantPasPlantePas() {
        baseDonnee.deleteUtilisateur(new Utilisateur("a", "b", "c", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg"));
    }

    @Test
    public void updaterUnUtilisateurExistantPasPlantePas() {
        baseDonnee.updateUtilisateur(new Utilisateur("a", "b", "c", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg"),
                new Utilisateur("c", "b", "a", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg"));

    }

    @Test
    public void insererContact() {
        ArrayList<String> listeContacts = new ArrayList<String>();
        Utilisateur u = new Utilisateur("a", "b", "c", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        Utilisateur user = new Utilisateur("Bob", "pass", "test@test.ca", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        baseDonnee.insererUtilisateur(u);
        baseDonnee.insererUtilisateur(user);
        baseDonnee.insererContact(u.getUsername(), user.getUsername());
        listeContacts = baseDonnee.chercherListeContact(u.getUsername());
        assertEquals("Bob", listeContacts.get(0));
    }

    @Test
    public void testIsContact() {
        ArrayList<String> listeContacts = new ArrayList<String>();
        Utilisateur u = new Utilisateur("a", "b", "c", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        Utilisateur user = new Utilisateur("Bob", "pass", "test@test.ca", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        baseDonnee.insererUtilisateur(u);
        baseDonnee.insererUtilisateur(user);
        baseDonnee.insererContact(u.getUsername(), user.getUsername());
        assertTrue(baseDonnee.isContact(u.getUsername(), user.getUsername()));
    }

    @Test
    public void effacerContact() {
        ArrayList<String> listeContacts = new ArrayList<String>();
        Utilisateur u = new Utilisateur("a", "b", "c", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        Utilisateur user = new Utilisateur("Bob", "pass", "test@test.ca", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        baseDonnee.insererUtilisateur(u);
        baseDonnee.insererUtilisateur(user);
        baseDonnee.insererContact(u.getUsername(), user.getUsername());
        baseDonnee.deleteContact(u.getUsername(), user.getUsername());
        listeContacts = baseDonnee.chercherListeContact(u.getUsername());
        assertEquals(0, listeContacts.size());
    }

    @Test
    public void chercherContactUtilisateur() {
        ArrayList<String> listeContacts = new ArrayList<String>();
        Utilisateur u = new Utilisateur("a", "b", "c", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        Utilisateur user = new Utilisateur("Bob", "pass", "test@test.ca", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        baseDonnee.insererUtilisateur(u);
        baseDonnee.insererUtilisateur(user);
        baseDonnee.insererContact(u.getUsername(), user.getUsername());
        listeContacts = baseDonnee.chercherListeContact(u.getUsername());
        assertEquals("Bob", listeContacts.get(0));
    }

    @Test
    public void effacerContactPasExistantPlantePas() {
        Utilisateur u = new Utilisateur("a", "b", "c", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        Utilisateur user = new Utilisateur("Bob", "pass", "test@test.ca", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        baseDonnee.insererUtilisateur(u);
        baseDonnee.insererUtilisateur(user);
        baseDonnee.deleteContact(u.getUsername(), user.getUsername());
    }

    @Test
    public void truncateVideBienLaTable() {
        Utilisateur user = new Utilisateur("User", "pass", "test@test.ca", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        baseDonnee.insererUtilisateur(user);
        truncateTable();
        assertNull(baseDonnee.chercherUtilisateur("User"));
    }

    @Test
    public void testChangerImageURL() {
        Utilisateur user = new Utilisateur("bobinet", "Password", "Courriel", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        baseDonnee.insererUtilisateur(user);
        Utilisateur user2 = new Utilisateur("bobinet", "Password", "Courriel", 12, "nomFamille", "prenom", "homme", "http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/4/11/1397210130748/Spring-Lamb.-Image-shot-2-011.jpg");
        baseDonnee.updateUtilisateur(user, user2);
        Utilisateur u = baseDonnee.chercherUtilisateur(user.getUsername());
        assertEquals("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/4/11/1397210130748/Spring-Lamb.-Image-shot-2-011.jpg", u.getImage());
    }

    private boolean initialiserConnection() {
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            Logger.getLogger(Authentificateur.class.getName()).log(Level.SEVERE, "Le driver sqlite n'est pas installé!");
            return false;
        }
        connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + nom_bd_test);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    private void truncateTable() {
        if (initialiserConnection()) {
            PreparedStatement stmt = null;
            try {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement("DELETE FROM UTILISATEUR");
                int nbMAJ = stmt.executeUpdate();
                stmt.close();
                connection.commit();
            } catch (SQLException ex) {
                System.out.println("Erreur dans la requete SQL");
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TestAccesBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
