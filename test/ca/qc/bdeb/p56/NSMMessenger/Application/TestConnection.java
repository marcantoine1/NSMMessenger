package ca.qc.bdeb.p56.NSMMessenger.Application;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1150580
 */
import ca.qc.bdeb.P56.NSMMessenger.Application.IClient;
import ca.qc.bdeb.P56.NSMMessenger.Application.InfoCreation;
import ca.qc.bdeb.P56.NSMMessenger.Application.InfoLogin;
import ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient;
import ca.qc.bdeb.P56.NSMMessengerCommunication.ConnectionResponse;
import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileResponse;
import ca.qc.bdeb.P56.NSMMessengerCommunication.UtilisateurModifier;
import ca.qc.bdeb.P56.NSMMessengerServer.Application.Authentificateur;
import ca.qc.bdeb.P56.NSMMessengerServer.ConnectionUtilisateur;
import ca.qc.bdeb.P56.NSMMessengerServer.NSMServer;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestConnection {

    static NSMServer server;
    static NSMClient client;
    static NSMClient client2;

    public static final String  LOBBYTEST = "Test";
    
    
    public TestConnection() {
    }

    @BeforeClass
    public static void setUpClass() {
        server = new NSMServer();
        Authentificateur.getInstanceAuthentificateur().creerUtilisateur("coolGuillaume", "sexyahri123", "test@test.test",12,"nomFamille","prenom","homme","http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        Authentificateur.getInstanceAuthentificateur().creerUtilisateur("coolGuillaume2", "sexyahri1234", "test2@test.test",12,"nomFamille","prenom","homme", "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        client = new NSMClient();
        client2 = new NSMClient();
    }

    @AfterClass
    public static void tearDownClass() {
        server.stop();
    }

    @Before
    public void setUp() throws InterruptedException {
        waitForServer(100);
        client.connect();
        client2.connect();
        waitForServer(100);
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
        if(client2.client.isConnected()){
            try{
                client2.disconnect();
            }
            catch(Exception e){
                //le client est deja deconnecte
            }
        }
        server.reset();
        waitForServer(100);
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
        client.changerIp("127.0.0.1");
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        assertEquals(1, server.connections.size());
        assertEquals("coolGuillaume", server.connections.values().toArray(new ConnectionUtilisateur[server.connections.size()])[0].username);
    }
    @Test
    public void testMauvaisLogin()
    {
        login(client, "cosertye54546", "sexyrtyr43w634");
        waitForServer(100);
        assertEquals(0, server.connections.size());
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
    public void testListeConnecter(){
        login(client,"coolGuillaume","sexyahri123");
        waitForServer(100);
        login(client2,"coolGuillaume2","sexyahri1234");
        waitForServer(100);
         ConnectionResponse listeConnectes = new ConnectionResponse();
            for (ConnectionUtilisateur c : server.connections.values()) {
                listeConnectes.ajouterUtilisateur(server.connections.get(c.connection.getID()).username);
            }
            ConnectionResponse listeConnectes2 = new ConnectionResponse();
            listeConnectes2.ajouterUtilisateur("coolGuillaume");
            listeConnectes2.ajouterUtilisateur("coolGuillaume2");
           assertEquals(listeConnectes2.utilisateurs,listeConnectes.utilisateurs);
    }

}
