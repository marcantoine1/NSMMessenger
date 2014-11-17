/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.p56.NSMMessenger.Application;

import ca.qc.bdeb.P56.NSMMessengerServer.Application.Authentificateur;
import ca.qc.bdeb.P56.NSMMessengerServer.NSMServer;
import static ca.qc.bdeb.p56.NSMMessenger.Application.TestConnection.client;
import static ca.qc.bdeb.p56.NSMMessenger.Application.TestCreationCompte.server;
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
public class TestContact {

    static NSMServer server;
    static ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient client;

    public TestContact() {
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
    public void testDemandeContact(){
        login(client,"coolGuillaume","sexyahri123");
        client.sendContactRequest("coolGuillaume2");
        waitForServer(100);
        assertTrue(client.getListeContact().getListeContact().contains("coolGuillaume2"));
        client.sendContactEffacerRequest("coolGuillaume2");
    }
    @Test
    public void testEffacerContact(){
        login(client,"coolGuillaume","sexyahri123");
         client.sendContactRequest("coolGuillaume2");
        client.sendContactEffacerRequest("coolGuillaume2");
        waitForServer(100);
        assertFalse(client.getListeContact().getListeContact().contains("coolGuillaume2"));
    }
        @Test
    public void testContactInvalide(){
        login(client,"coolGuillaume","sexyahri123");
        waitForServer(100);
        client.sendContactRequest("NEPASCREERUNUTILISATEURAVECCENOM");
        for (int i = 0; i < client.getListeContact().getListeContact().size(); i++) {
             assertFalse((client.getListeContact().getListeContact().get(i).equals("NEPASCREERUNUTILISATEURAVECCENOM")));
        }
       
    }
}
