package ca.qc.bdeb.p56.NSMMessengerServer.Application;

import ca.qc.bdeb.P56.NSMMessengerServer.Application.Authentificateur;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author 1150275
 */
public class TestAuthentificateur {

    private final String cle = "859E381543769334";

    public TestAuthentificateur() {
    }
    static Authentificateur TestAuthentificateur;

    @BeforeClass
    public static void setUpClass() {
        TestAuthentificateur = Authentificateur.getInstanceAuthentificateur();
        TestAuthentificateur.setNomBd("dbTest");
        TestAuthentificateur.creerUtilisateur("coolGuillaume", "sexyahri123", "guillaumesamurai@hotmail.ca", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        TestAuthentificateur.creerUtilisateur("coolRobert", "sexyahri123", "robertfroid@hotmail.ca", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        TestAuthentificateur.creerUtilisateur("coolAndré", "sexyahri123", "andresuper@hotmail.ca", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
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
//TODO: test utilisateur insere

    @Test
    public void AuthentificateurEstToujoursLeMeme() {
        assertEquals(TestAuthentificateur, Authentificateur.getInstanceAuthentificateur());
    }

    @Test
    public void testCreerUtilisateurSucces() {
        TestAuthentificateur.creerUtilisateur("eee", "eee", "guillaasdumesamurai@hotmail.ca", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        assertTrue(TestAuthentificateur.chercherUtilisateur("eee") != null);
    }

    @Test
    public void testCreerUtilisateurEchec() {
        assertTrue(TestAuthentificateur.chercherUtilisateur("12413125135") == null);
    }

    @Test
    public void AuthentifierUnUtilisateur() {
        assertTrue(TestAuthentificateur.authentifierUtilisateur("coolGuillaume", "sexyahri123"));

    }

    @Test
    public void TesterTrouverUnUtilisateur() {
        assertTrue(TestAuthentificateur.utilisateurExiste("coolGuillaume"));
    }
}
