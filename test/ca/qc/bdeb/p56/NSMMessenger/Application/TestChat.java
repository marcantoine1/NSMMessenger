package ca.qc.bdeb.p56.NSMMessenger.Application;

import ca.qc.bdeb.P56.NSMMessengerCommunication.AjoutLobbyInfo;
import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.P56.NSMMessengerServer.Application.Authentificateur;
import ca.qc.bdeb.P56.NSMMessengerServer.NSMServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 1255389
 */
public class TestChat {

    static NSMServer server;
    static ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient client;

    public TestChat() {
    }

    @BeforeClass
    public static void setUpClass() {
        server = new NSMServer();
        Authentificateur.getInstanceAuthentificateur().creerUtilisateur("coolGuillaume", "sexyahri123", "test@test.test", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        Authentificateur.getInstanceAuthentificateur().creerUtilisateur("coolGuillaume2", "sexyahri1234", "test2@test.test", 12, "nomFamille", "prenom", "homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        client = new ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient();
    }

    @AfterClass
    public static void tearDownClass() {
        server.stop();
    }

    @Before
    public void setUp() throws InterruptedException {
        waitForServer(100);
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
        waitForServer(100);
    }

    private void waitForServer(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println("Erreur dans le thread");
        }
    }

    public void login(ca.qc.bdeb.P56.NSMMessenger.Application.IClient client, String username, String password) {
        ca.qc.bdeb.P56.NSMMessenger.Application.InfoLogin il = new ca.qc.bdeb.P56.NSMMessenger.Application.InfoLogin();
        il.username = username;
        il.password = password;
        client.login(il);
        waitForServer(100);
    }

    @Test
    public void testMessage() {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(1000);
        client.sendMessage(new Message("Général", "test"));
        waitForServer(1000);
        assertTrue(client.messages.contains("coolGuillaume: test"));
    }

    @Test
    public void testerNotificationUtilisateurConnecte() {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient client2 = new ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient();
        client.joinLobby(NSMServer.INITIALLOBBY2);
        waitForServer(100);
        client2.connect();
        login(client2, "coolGuillaume2", "sexyahri1234");
        waitForServer(100);
        client2.joinLobby(NSMServer.INITIALLOBBY2);
        waitForServer(100);
        assertTrue(client.messages.contains("coolGuillaume2 à rejoint le canal."));
        client2.disconnect();
    }

    @Test
    public void testUtilisateurRecoitLaListeDesUtilisateursEnRejoignantLobby() {
        login(client, "coolGuillaume", "sexyahri123");
        client.joinLobby(NSMServer.INITIALLOBBY2);
        waitForServer(100);
        ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient client2 = new ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient();
        client2.connect();
        login(client2, "coolGuillaume2", "sexyahri1234");
        client2.joinLobby(NSMServer.INITIALLOBBY2);
        waitForServer(100);
        assertTrue(client2.messages.contains("utilisateurs : coolGuillaume"));
    }
    
    @Test
    public void testInviterUtilisateurAuLobby() {
        login(client, "coolGuillaume", "sexyahri123");
        client.joinLobby(NSMServer.INITIALLOBBY);
        waitForServer(100);
        ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient client2 = new ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient();
        client2.connect();
        login(client2, "coolGuillaume2", "sexyahri1234");
        client2.joinLobby(NSMServer.INITIALLOBBY);
        
        // Le client1 join le lobby 2
        client.joinLobby(NSMServer.INITIALLOBBY2);
        client.sendAjoutAuLobbyRequest(new AjoutLobbyInfo(client2.username, NSMServer.INITIALLOBBY2));
        client2.joinLobby(NSMServer.INITIALLOBBY2);
        waitForServer(100);
        
        // Vérifie si le nombre d'utilisateurs dans le lobby est 2
        assertTrue(client.messages.contains("coolGuillaume2 à rejoint le canal."));
        assertEquals(server.lobbies.get(NSMServer.INITIALLOBBY2).getUsers().size(), 2);
    }
}
