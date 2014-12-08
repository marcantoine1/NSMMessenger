package ca.qc.bdeb.p56.NSMMessenger.Application;

import ca.qc.bdeb.P56.NSMMessengerServer.Application.Authentificateur;
import ca.qc.bdeb.P56.NSMMessengerServer.Application.Encrypteur;
import ca.qc.bdeb.P56.NSMMessengerServer.Application.Utilisateur;
import ca.qc.bdeb.P56.NSMMessengerServer.NSMServer;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Marc-Antoine
 */
public class TestLogin {

    static NSMServer server;
    static ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient client;
    public static final String LOBBYTEST = "Test";
    static Authentificateur TestAuthentificateur;
    private final String cle = "859E381543769334";

    public TestLogin() {

    }

    @BeforeClass
    public static void setUpClass() {
        server = new NSMServer();
        TestAuthentificateur = Authentificateur.getInstanceAuthentificateur();
        Authentificateur.getInstanceAuthentificateur().creerUtilisateur("coolGuillaume", "sexyahri123", "test@test.test", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        Authentificateur.getInstanceAuthentificateur().creerUtilisateur("coolGuillaume2", "sexyahri1234", "test2@test.test", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        Authentificateur.getInstanceAuthentificateur().creerUtilisateur("a5", "sexyahri123", "test@test.test", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        client = new ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient();
    }

    @AfterClass
    public static void tearDownClass() {
        server.stop();
    }

    @Before
    public void setUp() throws InterruptedException {
        waitForServer();
        client.connect();
    }

    @After
    public void tearDown() {
        if (client.client.isConnected()) {
            try {
                client.disconnect();
            } catch (Exception e) {
                //Le client est déja déconnecté
            }
        }
        server.reset();
        waitForServer();
    }

    private void waitForServer() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("probleme de thread");
        }
    }

    public void login(ca.qc.bdeb.P56.NSMMessenger.Application.IClient client, String username, String password) {
        ca.qc.bdeb.P56.NSMMessenger.Application.InfoLogin il = new ca.qc.bdeb.P56.NSMMessenger.Application.InfoLogin();
        il.username = username;
        il.password = password;
        client.login(il);
        waitForServer();
    }
    
    //Ce test va passer lorsqu'il est possible d'envoyer des emails
    @Test
    public void testMotDePasseDifferent() {
        client.sendGenererPassword("a5");
        waitForServer();
        Utilisateur u = TestAuthentificateur.chercherUtilisateur("a5");
        assertFalse(Encrypteur.decrypter(u.getUnsecuredPassword(), cle).equals(Encrypteur.encrypter("sexyahri123", cle)));
    }
    
    //Ce test va passer lorsque le email est capable d'etre envoyer sinon une exception va être lancer
    @Test 
    public void testEnvoyerEmailFail(){
         client.sendGenererPassword("a5");
        waitForServer();
       
    }
    //Ce test va passer lorsque le email est capable d'etre envoyer sinon une exception va être lancer
    @Test 
    public void testEnvoyerEmailAdresseFail(){
         client.sendGenererPassword("coolGuillaume");
        waitForServer();
    }
}
