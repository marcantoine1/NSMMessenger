package ca.qc.bdeb.p56.NSMCommunicationTest;

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
import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.P56.NSMMessengerServer.ConnectionUtilisateur;
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Authentificateur;
import ca.qc.bdeb.P56.NSMMessengerServer.NSMServer;
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

    public static String PREMIERLOBBY = "Général", DEUXIEMELOBBY = "Divers", TROISIEMELOBBY = "Test";
    
    
    public TestConnection() {
    }

    @BeforeClass
    public static void setUpClass() {
        server = new NSMServer();
        Authentificateur.getInstanceAuthentificateur().creerUtilisateur("coolGuillaume", "sexyahri123", "test@test.test",12,"nomFamille","prenom","homme");
        Authentificateur.getInstanceAuthentificateur().creerUtilisateur("coolGuillaume2", "sexyahri1234", "test2@test.test",12,"nomFamille","prenom","homme");
        client = new NSMClient();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws InterruptedException {
        waitForServer(100);
        client.connect();
        waitForServer(100);
    }

    @After
    public void tearDown() {
        if(client.client.isConnected())
        {
        try{
            client.disconnect();
            waitForServer(100);
        }catch (Exception e){
           //Le client est déja déconnecté
        }
        }
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
        waitForServer(100);
    }

    @Test
    public void testLogin() {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        assertEquals(1, server.connections.size());
        assertEquals("coolGuillaume", server.connections.values().toArray(new ConnectionUtilisateur[server.connections.size()])[0].username);
    }

    private void waitForServer(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }

    @Test
    public void testDisconnect() {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        client.disconnect();
        waitForServer(100);
        assertEquals(0, server.connections.size());
    }
    
    @Test
    public void testDisconnectLobby()
    {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        client.disconnect();
        waitForServer(100);
        assertEquals(0, server.lobbies.get(PREMIERLOBBY).getUsers().size());
    }

    @Test
    public void testMessage() {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        client.sendMessage(new Message("Général", "test"));
        waitForServer(100);
        assertTrue(client.messages.contains("coolGuillaume: test"));
    }

    @Test
    public void testerJoinLobby() {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        client.joinLobby(DEUXIEMELOBBY);
        waitForServer(100);
        assertEquals(1, server.lobbies.get(DEUXIEMELOBBY).getUsers().size());
    }

    @Test
    public void testerLeaveLobby() {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        client.leaveLobby(PREMIERLOBBY);
        waitForServer(100);
        assertEquals(0, server.lobbies.get(PREMIERLOBBY).getUsers().size());
    }

    @Test
    public void testerMessageLobby() {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        client.leaveLobby(PREMIERLOBBY);
        
        NSMClient client2 = new NSMClient();
        client2.connect();
        
        waitForServer(100);
        login(client2, "coolGuillaume2", "sexyahri1234");
        waitForServer(100);
        client2.sendMessage(new Message(PREMIERLOBBY, "TestLobby"));
        waitForServer(100);
        client.joinLobby(PREMIERLOBBY);
        waitForServer(100);
        client2.sendMessage(new Message(PREMIERLOBBY, "LobbyTest"));
        waitForServer(100);
        assertEquals(true, client.messages.contains("coolGuillaume2: LobbyTest"));    
        client2.disconnect();
    }
    @Test
    public void testCreerLobby()
    {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        client.creerLobby(TROISIEMELOBBY);
        waitForServer(100);
        assertTrue(server.lobbies.containsKey(TROISIEMELOBBY));
    }
    @Test
    public void testerCreerUnCompte() {
        InfoCreation nouveauCompte = new InfoCreation();
        nouveauCompte.email = "abc@hotmail.ca";
        nouveauCompte.password = "abc";
        nouveauCompte.username = "Testeur";
        nouveauCompte.age = 12;
        nouveauCompte.nom = "nom";
        nouveauCompte.prenom = "prenom";
        nouveauCompte.sexe = "homme";
        client.creerCompte(nouveauCompte);
        login(client, "Testeur", "abc");
        waitForServer(100);
        assertEquals(1, server.connections.size());
        assertEquals("Testeur", server.connections.values().toArray(new ConnectionUtilisateur[server.connections.size()])[0].username);
    }
    @Test
    public void testerNotificationUtilisateurConnecte(){
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        NSMClient client2 = new NSMClient();
        client.joinLobby(DEUXIEMELOBBY);
        waitForServer(100);
        client2.connect();
        login(client2, "coolGuillaume2", "sexyahri1234");
        waitForServer(100);
        client2.joinLobby(DEUXIEMELOBBY);
        waitForServer(100);
        assertTrue(client.messages.contains("coolGuillaume2 à rejoint le canal."));
        client2.disconnect();
    }
    @Test
    public void testUtilisateurRecoitLaListeDesUtilisateursEnRejoignantLobby(){
        login(client, "coolGuillaume", "sexyahri123");
        client.joinLobby(DEUXIEMELOBBY);
        waitForServer(100);
        NSMClient client2 = new NSMClient();
        client2.connect();
        login(client2, "coolGuillaume2", "sexyahri1234");
        client2.joinLobby(DEUXIEMELOBBY);
        waitForServer(100);
        assertTrue(client2.messages.contains("utilisateurs : coolGuillaume"));
    }


}
