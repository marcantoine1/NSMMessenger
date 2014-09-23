
/**
 * Created by Martin on 2014-09-22.
 */
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.AccesBd;
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Authentificateur;
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Utilisateur;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author 1150275
 */
public class TestAccesBD {

    private static final String nom_bd_test = "dbTest.db";
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

    //Refaire ce test la, il fait pas la bonne chose
    @Test
    @Ignore
    public void testerConnectionBasedeDonnees() {
        assertTrue(baseDonnee.connectionEtablie());

    }

    @Test
    public void testCreerTable() {
        assertTrue(baseDonnee.tableExiste("UTILISATEUR"));
    }

    @Test
    public void insererUnUtilisateur() {
        Utilisateur user = new Utilisateur("User", "pass", "test@test.ca");
        baseDonnee.insererUtilisateur(user);
        assertTrue(user.equals(baseDonnee.chercherUtilisateur("User")));
    }

    @Test
    public void trouverUnUtilisateur() {
        Utilisateur user = new Utilisateur("Username","Password","Courriel");
        baseDonnee.insererUtilisateur(user);
        assertTrue(user.equals(baseDonnee.chercherUtilisateur("Username")));
    }

    @Test
    public void effacerUnUtilisateur() {
        Utilisateur u = new Utilisateur("a","b","c");
        baseDonnee.insererUtilisateur(u);
        baseDonnee.deleteUtilisateur(u);
        assertNull(baseDonnee.chercherUtilisateur(u.getUsername()));
    }
    @Test
    public void mettreAJourUnUtilisateur() {
        Utilisateur u = new Utilisateur("a","b","c");
        baseDonnee.insererUtilisateur(u);
        baseDonnee.updateUtilisateur(u, new Utilisateur("c","b","a"));
        assertNotNull(baseDonnee.chercherUtilisateur("c"));
        assertNull(baseDonnee.chercherUtilisateur("a"));    
    }

    @Test
    public void effacerUnUtilisateurExistantPasPlantePas() {
        baseDonnee.deleteUtilisateur(new Utilisateur("a","b","c"));
    }

    @Test
    public void updaterUnUtilisateurExistantPasPlantePas() {
        baseDonnee.updateUtilisateur(new Utilisateur("a","b","c"),new Utilisateur("c","b","a"));
        
    }

    @Test
    public void truncateVideBienLaTable() {
        Utilisateur user = new Utilisateur("User", "pass", "test@test.ca");
        baseDonnee.insererUtilisateur(user);
        truncateTable();
        assertNull(baseDonnee.chercherUtilisateur("User"));
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
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TestAccesBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
