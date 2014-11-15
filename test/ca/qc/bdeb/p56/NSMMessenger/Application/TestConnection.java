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
    public void testDisconnectLobby()
    {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        client.disconnect();
        waitForServer(100);
        assertEquals(0, server.lobbies.get(NSMServer.INITIALLOBBY).getUsers().size());
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
        client.joinLobby(NSMServer.INITIALLOBBY2);
        waitForServer(100);
        assertEquals(1, server.lobbies.get(NSMServer.INITIALLOBBY2).getUsers().size());
    }

    @Test
    public void testerLeaveLobby() {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        client.leaveLobby(NSMServer.INITIALLOBBY);
        waitForServer(100);
        assertEquals(0, server.lobbies.get(NSMServer.INITIALLOBBY).getUsers().size());
    }

    @Test
    public void testerMessageLobby() {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        client.leaveLobby(NSMServer.INITIALLOBBY);
        
        NSMClient client2 = new NSMClient();
        client2.connect();
        
        waitForServer(100);
        login(client2, "coolGuillaume2", "sexyahri1234");
        waitForServer(100);
        client2.sendMessage(new Message(NSMServer.INITIALLOBBY, "TestLobby"));
        waitForServer(100);
        client.joinLobby(NSMServer.INITIALLOBBY);
        waitForServer(100);
        client2.sendMessage(new Message(NSMServer.INITIALLOBBY, "LobbyTest"));
        waitForServer(100);
        assertEquals(true, client.messages.contains("coolGuillaume2: LobbyTest"));    
        client2.disconnect();
    }
    @Test
    public void testCreerLobby()
    {
        login(client, "coolGuillaume", "sexyahri123");
        waitForServer(100);
        client.creerLobby(LOBBYTEST);
        waitForServer(100);
        assertTrue(server.lobbies.containsKey(LOBBYTEST));
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
    public void testUtilisateurRecoitLaListeDesUtilisateursEnRejoignantLobby(){
        login(client, "coolGuillaume", "sexyahri123");
        client.joinLobby(NSMServer.INITIALLOBBY2);
        waitForServer(100);
        NSMClient client2 = new NSMClient();
        client2.connect();
        login(client2, "coolGuillaume2", "sexyahri1234");
        client2.joinLobby(NSMServer.INITIALLOBBY2);
        waitForServer(100);
        assertTrue(client2.messages.contains("utilisateurs : coolGuillaume"));
    }
    @Test
    public void testRecevoirInformationServeur() {
        login(client, "coolGuillaume", "sexyahri123");
        ProfileResponse profil = new ProfileResponse("eee", "eee@eee.ca", "eee", "eee",
                "Homme", 13,false,"http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        client.sendProfileRequest("eee");
        waitForServer(100);
        assertEquals(profil.getCourriel(), server.getProfil().getCourriel());
        assertEquals(profil.getAge(), server.getProfil().getAge());
        assertEquals(profil.getNom(), server.getProfil().getNom());
        assertEquals(profil.getPrenom(), server.getProfil().getPrenom());
        assertEquals(profil.getSexe(), server.getProfil().getSexe());
        assertEquals(profil.getUsername(), server.getProfil().getUsername());
    }
    @Test
    public void testRecevoirInformationClient(){
                login(client, "coolGuillaume", "sexyahri123");
       ProfileResponse profil = new ProfileResponse("eee", "eee@eee.ca", "eee", "eee",
                "Homme", 13,false, "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        client.sendProfileRequest("eee");
        waitForServer(100);
        assertEquals(profil.getCourriel(), client.getResponse().getCourriel());
        assertEquals(profil.getAge(), client.getResponse().getAge());
        assertEquals(profil.getNom(), client.getResponse().getNom());
        assertEquals(profil.getPrenom(), client.getResponse().getPrenom());
        assertEquals(profil.getSexe(), client.getResponse().getSexe());
        assertEquals(profil.getUsername(), client.getResponse().getUsername());
    }
    @Test
    public void testDemandeContact(){
        login(client,"coolGuillaume","sexyahri123");
        client.sendContactRequest("eee");
        waitForServer(100);
        assertTrue(client.getListeContact().getListeContact().contains("eee"));
        client.sendContactEffacerRequest("eee");
    }
    @Test
    public void testEffacerContact(){
        login(client,"coolGuillaume","sexyahri123");
         client.sendContactRequest("eee");
        client.sendContactEffacerRequest("eee");
        waitForServer(100);
        assertFalse(client.getListeContact().getListeContact().contains("eee"));
    }
    @Test
    public void testListeConnecter(){
        login(client,"coolGuillaume","sexyahri123");
        waitForServer(100);
        login(client2,"bob","bob");
        waitForServer(100);
         ConnectionResponse listeConnectes = new ConnectionResponse();
            for (ConnectionUtilisateur c : server.connections.values()) {
                listeConnectes.ajouterUtilisateur(server.connections.get(c.connection.getID()).username);
            }
            ConnectionResponse listeConnectes2 = new ConnectionResponse();
            listeConnectes2.ajouterUtilisateur("coolGuillaume");
            listeConnectes2.ajouterUtilisateur("bob");
           assertEquals(listeConnectes.utilisateurs, listeConnectes2.utilisateurs);
    }
    @Test
    public void testContactInvalide(){
        login(client,"eee","eee");
        waitForServer(100);
        client.sendContactRequest("NEPASCREERUNUTILISATEURAVECCENOM");
        for (int i = 0; i < client.getListeContact().getListeContact().size(); i++) {
             assertFalse((client.getListeContact().getListeContact().get(i).equals("NEPASCREERUNUTILISATEURAVECCENOM")));
        }
       
    }
}
