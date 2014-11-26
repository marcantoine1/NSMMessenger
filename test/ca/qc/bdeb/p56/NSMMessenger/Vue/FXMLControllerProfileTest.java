package ca.qc.bdeb.p56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Vue.FXMLControllerProfil;
import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileResponse;
import javafx.scene.control.Button;
import org.junit.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Ordinatron
 */
public class FXMLControllerProfileTest {

    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
    FXMLControllerProfil profil;
    ProfileResponse profilConnecte;
    ProfileResponse profilDeconnecte;
    Button btnInviterLobby;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        profilConnecte = new ProfileResponse("coolGuillaume", "courriel@courriel.com", "Nom", "Prénom", "Homme", 20, true, true, "http://upload.wikimedia.org/wikipedia/commons/f/fe/Sciurus_niger_(on_fence).jpg");
        profilDeconnecte = new ProfileResponse("coolGuillaume", "courriel@courriel.com", "Nom", "Prénom", "Homme", 20, true, false, "http://upload.wikimedia.org/wikipedia/commons/f/fe/Sciurus_niger_(on_fence).jpg");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testProfilConnecte() {
        profil = new FXMLControllerProfil(profilConnecte);
        profil.build();
        btnInviterLobby = profil.getBtnInviterLobby();
        assertFalse(btnInviterLobby.isDisabled());
    }
    
    @Test
    public void testProfilDeconnecte() {
        profil = new FXMLControllerProfil(profilDeconnecte);
        profil.build();
        btnInviterLobby = profil.getBtnInviterLobby();
        assertTrue(btnInviterLobby.isDisabled());
    }

}
