/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.p56.NSMGUITest;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.InfoLogin;
import ca.qc.bdeb.P56.NSMMessenger.IClient;
import ca.qc.bdeb.P56.NSMMessenger.NSMClient;
import ca.qc.bdeb.P56.NSMMessenger.Vue.ChatGUI;
import ca.qc.bdeb.P56.NSMMessenger.Vue.ChatPrimitif;
import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileRequest;
import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileResponse;
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.AccesBd;
import ca.qc.bdeb.P56.NSMMessengerServer.NSMServer;
import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author 1260973
 */
public class TestProfil {

    static NSMServer server;
    static NSMClient client;
    static ChatPrimitif chat;
    static ChatGUI gui;

    public TestProfil() {
    }

    @BeforeClass
    public static void setUpClass() {
        server = new NSMServer();
        client = new NSMClient();
        gui = new ChatGUI(null);
        gui.ajouterObservateur(null);
        chat = new ChatPrimitif(gui);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        client.connect();
    }

    @After
    public void tearDown() {
        client.disconnect();
    }

    @Test
    public void testRecevoirInformation() {
        login(client, "coolGuillaume", "sexyahri123");
        ProfileResponse profil = new ProfileResponse("coolGuillaume", "test@test.test", "nomFamille", "prenom", "homme", 12);
        client.sendProfileRequest("coolGuillaume");
        waitForServer(100);
        assertEquals(profil.getCourriel(), server.getProfil().getCourriel());
        assertEquals(profil.getAge(), server.getProfil().getAge());
        assertEquals(profil.getNom(), server.getProfil().getNom());
        assertEquals(profil.getPrenom(), server.getProfil().getPrenom());
        assertEquals(profil.getSexe(), server.getProfil().getSexe());
        assertEquals(profil.getUsername(), server.getProfil().getUsername());
    }
    public void login(IClient client, String username, String password) {
        InfoLogin il = new InfoLogin();
        il.username = username;
        il.password = password;
        client.login(il);
        waitForServer(100);
    }

    private void waitForServer(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }
}
