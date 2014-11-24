/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.p56.NSMMessenger.Application;

import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileResponse;
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
public class TestProfile {

    static NSMServer server;
    static ca.qc.bdeb.P56.NSMMessenger.Application.NSMClient client;

    public TestProfile() {
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
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Erreur dans le thread");
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
    public void testRecevoirInformationServeurProfile() {
        login(client, "coolGuillaume", "sexyahri123");
        ProfileResponse profil = new ProfileResponse("coolGuillaume2", "test2@test.test", "nomFamille", "prenom",
                "homme", 12, false,true, "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        client.sendProfileRequest("coolGuillaume2");
        waitForServer();
        assertEquals(profil.getCourriel(), server.getProfil().getCourriel());
        assertEquals(profil.getAge(), server.getProfil().getAge());
        assertEquals(profil.getNom(), server.getProfil().getNom());
        assertEquals(profil.getPrenom(), server.getProfil().getPrenom());
        assertEquals(profil.getSexe(), server.getProfil().getSexe());
        assertEquals(profil.getUsername(), server.getProfil().getUsername());
        assertEquals(profil.getImage(), client.getResponse().getImage());
    }

    @Test
    public void testRecevoirInformationClientProfile() {
        login(client, "coolGuillaume", "sexyahri123");
        ProfileResponse profil = new ProfileResponse("coolGuillaume2", "test2@test.test", "nomFamille", "prenom",
                "homme", 12, false,true, "http://cdn.crunchify.com/wp-content/uploads/2012/10/java_url.jpg");
        client.sendProfileRequest("coolGuillaume2");
        waitForServer();
        assertEquals(profil.getCourriel(), client.getResponse().getCourriel());
        assertEquals(profil.getAge(), client.getResponse().getAge());
        assertEquals(profil.getNom(), client.getResponse().getNom());
        assertEquals(profil.getPrenom(), client.getResponse().getPrenom());
        assertEquals(profil.getSexe(), client.getResponse().getSexe());
        assertEquals(profil.getUsername(), client.getResponse().getUsername());
        assertEquals(profil.getImage(), client.getResponse().getImage());
    }
}
