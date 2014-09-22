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
import ca.qc.bdeb.P56.NSMMessenger.IClient;
import ca.qc.bdeb.P56.NSMMessenger.NSMClient;
import ca.qc.bdeb.P56.NSMMessengerServer.ConnectionUtilisateur;
import ca.qc.bdeb.P56.NSMMessengerServer.NSMServer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

    public void login(IClient client, String username, String password) {
        InfoLogin il = new InfoLogin();
        il.username = username;
        il.password = password;
        client.login(il);
    }

    @Test
    public void testLogin() {
        try {
            login(client, "coolGuillaume", "sexyahri123");
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
            login(client, "coolGuillaume", "sexyahri123");
            Thread.sleep(100);
            client.sendMessage(1, "test");
            Thread.sleep(100);

            assertTrue(client.messages.contains("coolGuillaume: test"));
        } catch (InterruptedException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testerJoinLobby() {
        login(client, "coolGuillaume", "sexyahri123");
        client.joinLobby(2);
        try{Thread.sleep(100);} catch(Exception e){}
        assertEquals(1, server.lobbies.get(2).getUsers().size());
    }
    
    @Test
    public void testerLeaveLobby() {
        login(client, "coolGuillaume", "sexyahri123");
        client.leaveLobby(1);
        try{Thread.sleep(100);} catch(Exception e){}
        assertEquals(0, server.lobbies.get(1).getUsers().size());
    }
    
    @Test
    public void testerMessageLobby()
    {
        login(client, "coolGuillaume", "sexyahri123");
        
        
        NSMClient client2 = new NSMClient();
        client2.connect();
        
        //todo: rentrer un autre utilisateur dans l'authentificateur
        login(client2, "", "");
        
        client2.joinLobby(2);
        client2.sendMessage(2, "TestLobby");
        assertEquals(false, client.messages.contains("TestLobby"));
        client.joinLobby(2);
        client2.sendMessage(2, "LobbyTest");
        assertEquals(true, client.messages.contains("LobbyTest"));
        client.leaveLobby(2);
    }
    public void testerCreerUnCompte() {
        InfoCreation nouveauCompte = new InfoCreation();
        nouveauCompte.email = "abc@hotmail.ca";
        nouveauCompte.password = "abc";
        nouveauCompte.username = "Testeur";
        InfoLogin login = new InfoLogin();
        client.creerCompte(nouveauCompte);
        login(client, "Testeur","abc");
        try{
        Thread.sleep(100);}
        catch(Exception e){
            
        }
        assertEquals(1, server.connections.size());
        assertEquals("Testeur", server.connections.values().toArray(new ConnectionUtilisateur[server.connections.size()])[0].username);
    }

}
