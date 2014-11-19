/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.p56.NSMMessenger.Application;

import ca.qc.bdeb.P56.NSMMessenger.Application.InfoLogin;
import ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient;
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
public class TestLobby {

    static NSMServer server;
    static NSMClient client;
     public static final String  LOBBYTEST = "Test";
    public TestLobby() {
        
    }

    @BeforeClass
    public static void setUpClass() {
         server = new NSMServer();
        Authentificateur.getInstanceAuthentificateur().creerUtilisateur("coolGuillaume", "sexyahri123", "test@test.test",12,"nomFamille","prenom","homme","http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        Authentificateur.getInstanceAuthentificateur().creerUtilisateur("coolGuillaume2", "sexyahri1234", "test2@test.test",12,"nomFamille","prenom","homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        client = new NSMClient();
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
        if(client.client.isConnected())
        {
        try{
            client.disconnect();
        }catch (Exception e){
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
        InfoLogin il = new ca.qc.bdeb.P56.NSMMessenger.Application.InfoLogin();
        il.username = username;
        il.password = password;
        client.login(il);
        waitForServer();
    }
     @Test
    public void testerJoinLobby() {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer();
        client.joinLobby(NSMServer.INITIALLOBBY2);
        waitForServer();
        assertEquals(1, server.lobbies.get(NSMServer.INITIALLOBBY2).getUsers().size());
    }

    @Test
    public void testerLeaveLobby() {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer();
        client.leaveLobby(NSMServer.INITIALLOBBY);
        waitForServer();
        assertEquals(0, server.lobbies.get(NSMServer.INITIALLOBBY).getUsers().size());
    }

    @Test
    public void testerMessageLobby() {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer();
        client.leaveLobby(NSMServer.INITIALLOBBY);
        
        ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient client2 = new ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient();
        client2.connect();
        
        waitForServer();
        login(client2, "coolGuillaume2", "sexyahri1234");
        waitForServer();
        client2.sendMessage(new Message(NSMServer.INITIALLOBBY, "TestLobby"));
        waitForServer();
        client.joinLobby(NSMServer.INITIALLOBBY);
        waitForServer();
        client2.sendMessage(new Message(NSMServer.INITIALLOBBY, "LobbyTest"));
        waitForServer();
        assertEquals(true, client.messages.contains("coolGuillaume2: LobbyTest"));    
        client2.disconnect();
    }
    @Test
    public void testCreerLobby()
    {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer();
        client.creerLobby(LOBBYTEST);
        waitForServer();
        assertTrue(server.lobbies.containsKey(LOBBYTEST));
    }
     @Test
    public void testDisconnectLobby()
    {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer();
        client.disconnect();
        waitForServer();
        assertEquals(0, server.lobbies.get(NSMServer.INITIALLOBBY).getUsers().size());
    }
}
