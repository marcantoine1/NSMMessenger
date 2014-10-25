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
import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileResponse;
import ca.qc.bdeb.P56.NSMMessengerServer.NSMServer;
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
    static ChatGUI gui;

    public TestProfil() {
    }

    @BeforeClass
    public static void setUpClass() {
        
        //TODO: test de gui, utiliser le gui au lieu du client
        server = new NSMServer();
        client = new NSMClient();
        gui = new ChatGUI();
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
    public void testRecevoirInformationServeur() {
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
    @Test
    public void testRecevoirInformationClient(){
                login(client, "coolGuillaume", "sexyahri123");
        ProfileResponse profil = new ProfileResponse("coolGuillaume", "test@test.test", "nomFamille", "prenom", "homme", 12);
        client.sendProfileRequest("coolGuillaume");
        waitForServer(100);
        assertEquals(profil.getCourriel(), client.getResponse().getCourriel());
        assertEquals(profil.getAge(), client.getResponse().getAge());
        assertEquals(profil.getNom(), client.getResponse().getNom());
        assertEquals(profil.getPrenom(), client.getResponse().getPrenom());
        assertEquals(profil.getSexe(), client.getResponse().getSexe());
        assertEquals(profil.getUsername(), client.getResponse().getUsername());
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
