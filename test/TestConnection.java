/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1150580
 */

import ca.qc.bdeb.P56.NSMMessenger.Controleur.InfoCreation;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.InfoLogin;
import ca.qc.bdeb.P56.NSMMessenger.NSMClient;
import ca.qc.bdeb.P56.NSMMessengerServer.ConnectionUtilisateur;
import ca.qc.bdeb.P56.NSMMessengerServer.NSMServer;
import org.junit.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestConnection {

    static NSMServer server;
    static NSMClient client;

    public TestConnection() {
    }

    @BeforeClass
    public static void setUpClass() {
        server = new NSMServer();
        client = new NSMClient();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws InterruptedException {
        client.connect();
    }

    @After
    public void tearDown() {
        client.disconnect();
    }

    @Test
    public void testConnection() {
        assertEquals(true, client.client.isConnected());
    }

    public void login(String username, String password) {
        InfoLogin il = new InfoLogin();
        il.username = username;
        il.password = password;
        client.login(il);
    }

    @Test
    public void testLogin() {
        try {
            login("coolGuillaume", "sexyahri123");
            Thread.sleep(100);

            assertEquals(1, server.connections.size());
            assertEquals("coolGuillaume", server.connections.values().toArray(new ConnectionUtilisateur[server.connections.size()])[0].username);

        } catch (InterruptedException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testMessage() {
        try {
            login("coolGuillaume", "sexyahri123");
            Thread.sleep(100);
            client.sendMessage("test");
            Thread.sleep(100);

            assertTrue(client.messages.contains("coolGuillaume: test"));
        } catch (InterruptedException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testerCreerUnCompte() {
        InfoCreation nouveauCompte = new InfoCreation();
        nouveauCompte.email = "abc@hotmail.ca";
        nouveauCompte.password = "abc";
        nouveauCompte.username = "Testeur";
        InfoLogin login = new InfoLogin();
        client.creerCompte(nouveauCompte);
        login("Testeur", "abc");
        try {
            Thread.sleep(100);
        } catch (Exception e) {

        }
        assertEquals(1, server.connections.size());
        assertEquals("Testeur", server.connections.values().toArray(new ConnectionUtilisateur[server.connections.size()])[0].username);
    }

}
