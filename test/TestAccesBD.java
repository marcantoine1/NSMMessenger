
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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;

/**
 * @author 1150275
 */
public class TestAccesBD {

    private AccesBd baseDonnee;
    private final String nom_bd_test = "dbTest.db";
    private Connection connection;

    public TestAccesBD() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testerConnectionBasedeDonnees() {
        baseDonnee = new AccesBd(nom_bd_test);
        assertTrue(baseDonnee.connectionEtablie());
    }

    @Test
    public void testCreerTable() {
        baseDonnee = new AccesBd(nom_bd_test);
        assertTrue(baseDonnee.tableExiste("UTILISATEUR"));

    }

    @Test
    public void uneSeuleBaseDeDonneeCreee() {
        //AccesBd bd1 = new AccesBd(true);
        //AccesBd bd2 = new AccesBd(true);

    }

    @Test
    public void insererUnUtilisateur() {
        baseDonnee = new AccesBd(nom_bd_test);
        Utilisateur user = new Utilisateur("User", "pass", "test@test.ca");
        int id = baseDonnee.insererUtilisateur(user);
        assertTrue(user.equals(baseDonnee.chercherUtilisateur("test@test.ca")));
        baseDonnee.close();

    }

    @Test
    public void trouverUnUtilisateur() {
        baseDonnee = new AccesBd(nom_bd_test);

    }

    @Test
    public void effacerUnUtilisateur() {

    }

    @Test
    public void mettreAJourUnUtilisateur() {

    }

    @Test
    public void effacerUnUtilisateurExistantPasPlantePas() {

    }

    @Test
    public void updaterUnUtilisateurExistantPasPlantePas() {

    }

    @Test
    public void truncateVideBienLaTable() {
        AccesBd baseDonnee = new AccesBd(nom_bd_test);
        Utilisateur user = new Utilisateur("User", "pass", "test@test.ca");
        int id = baseDonnee.insererUtilisateur(user);
        truncateTable();
        assertFalse(user.equals(baseDonnee.chercherUtilisateur("test@test.ca")));
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
        baseDonnee = new AccesBd(nom_bd_test);
        if (initialiserConnection()) {
            PreparedStatement stmt = null;
            try {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement("DELETE FROM UTILISATEUR");
                int nbMAJ = stmt.executeUpdate();
                connection.commit();
                connection.close();
            } catch (SQLException ex) {
            }
        }
    }

}
