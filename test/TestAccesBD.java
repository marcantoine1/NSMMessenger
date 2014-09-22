/**
 * Created by Martin on 2014-09-22.
 */
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.AccesBd;
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Authentificateur;
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Utilisateur;
import org.junit.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author 1150275
 */
public class TestAccesBD {
    private AccesBd baseDonnee;

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
        baseDonnee=new AccesBd();
       assertTrue(baseDonnee.connectionEtablie());
    }

    @Test
    public void testCreerTable()
    {
        baseDonnee=new AccesBd();
        assertTrue(baseDonnee.tableExiste("UTILISATEUR"));
    }
    @Test
    public void uneSeuleBaseDeDonneeCreee(){

    }
    @Test
    public void insererUnUtilisateur(){


    }
    @Test
    public void trouverUnUtilisateur()
    {

    }
    @Test
    public void effacerUnUtilisateur(){

    }
    @Test
    public void mettreAJourUnUtilisateur(){

    }
    @Test
    public void effacerUnUtilisateurExistantPasPlantePas(){

    }
    @Test
    public void updaterUnUtilisateurExistantPasPlantePas(){

    }


}
