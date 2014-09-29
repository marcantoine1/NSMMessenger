
/**
 * Created by Martin on 2014-09-22.
 */
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.AccesBd;
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Authentificateur;
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Utilisateur;
import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        Utilisateur user = new Utilisateur("User", "pass", "test@test.ca",12,"nomFamille","prenom","homme");
        assertTrue(baseDonnee.insererUtilisateur(user));
    }

    @Test
    public void trouverUnUtilisateur() {
        Utilisateur user = new Utilisateur("Username","Password","Courriel",12,"nomFamille","prenom","homme");
        baseDonnee.insererUtilisateur(user);
        assertTrue(user.equals(baseDonnee.chercherUtilisateur("Username")));
    }

    @Test
    public void effacerUnUtilisateur() {
        Utilisateur u = new Utilisateur("a","b","c",12,"nomFamille","prenom","homme");
        baseDonnee.insererUtilisateur(u);
        baseDonnee.deleteUtilisateur(u);
        assertNull(baseDonnee.chercherUtilisateur(u.getUsername()));
    }
    @Test
    public void mettreAJourUnUtilisateur() {
        Utilisateur u = new Utilisateur("a","b","c",12,"nomFamille","prenom","homme");
        baseDonnee.insererUtilisateur(u);
        baseDonnee.updateUtilisateur(u, new Utilisateur("c","b","a",12,"nomFamille","prenom","homme"));
        assertNotNull(baseDonnee.chercherUtilisateur("c"));
        assertNull(baseDonnee.chercherUtilisateur("a"));    
    }

    @Test
    public void effacerUnUtilisateurExistantPasPlantePas() {
        baseDonnee.deleteUtilisateur(new Utilisateur("a","b","c",12,"nomFamille","prenom","homme"));
    }

    @Test
    public void updaterUnUtilisateurExistantPasPlantePas() {
        baseDonnee.updateUtilisateur(new Utilisateur("a","b","c",12,"nomFamille","prenom","homme"),new Utilisateur("c","b","a",12,"nomFamille","prenom","homme"));
        
    }

    @Test
    public void truncateVideBienLaTable() {
        Utilisateur user = new Utilisateur("User", "pass", "test@test.ca",12,"nomFamille","prenom","homme");
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
