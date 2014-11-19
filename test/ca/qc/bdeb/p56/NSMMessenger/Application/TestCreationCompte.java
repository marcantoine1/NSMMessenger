/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.p56.NSMMessenger.Application;

import ca.qc.bdeb.P56.NSMMessengerServer.Application.Authentificateur;
import ca.qc.bdeb.P56.NSMMessengerServer.ConnectionUtilisateur;
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
public class TestCreationCompte {

    static NSMServer server;
    static ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient client;

    public TestCreationCompte() {
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

    @Test
    public void testerCreerUnCompte() {
        ca.qc.bdeb.P56.NSMMessenger.Application.InfoCreation nouveauCompte = new ca.qc.bdeb.P56.NSMMessenger.Application.InfoCreation();
        nouveauCompte.email = "abc@hotmail.ca";
        nouveauCompte.password = "abc";
        nouveauCompte.username = "Testeur";
        nouveauCompte.age = 12;
        nouveauCompte.nom = "nom";
        nouveauCompte.prenom = "prenom";
        nouveauCompte.sexe = "homme";
        nouveauCompte.image = "http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/4/11/1397210130748/Spring-Lamb.-Image-shot-2-011.jpg";
        client.creerCompte(nouveauCompte);
        login(client, "Testeur", "abc");
        waitForServer();
        assertEquals(1, server.connections.size());
        assertEquals("Testeur", server.connections.values().toArray(new ConnectionUtilisateur[server.connections.size()])[0].username);
    }
}
