/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ca.qc.bdeb.P56.NSMMessenger.Controleur.InfoLogin;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import ca.qc.bdeb.P56.NSMMessenger.NSMClient;
import ca.qc.bdeb.P56.NSMMessenger.Vue.ChatGUI;
import ca.qc.bdeb.P56.NSMMessenger.Vue.ChatPrimitif;
import ca.qc.bdeb.P56.NSMMessengerServer.NSMServer;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author 1255389
 */
public class TestChatPrimitif {
    static NSMServer server;
    static NSMClient client;
    static ChatPrimitif chat;
    static ChatGUI gui;
    public TestChatPrimitif() {
        
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
    public void verifierText(){
        InfoLogin il = new InfoLogin();
        il.username = "test";
        il.password = "test";
        client.login(il);
        chat.getChat().setText("test");
        assertEquals(chat.getChat().getText(),"test");
    }
    @Test
    @Ignore
    public void verifierTextRecu(){
        InfoLogin il = new InfoLogin();
        il.username = "coolGuillaume";
        il.password = "sexyahri123";
        client.login(il);
        chat.getChat().setText("test");
        chat.getButton().doClick();
        assertEquals("coolGuillaume: test",chat.getLblChat().getText());
        
    }
    
    @Test
    @Ignore
    public void verifierTexteVide(){
        chat.getChat().setText("test");
        chat.getButton().doClick();
        assertEquals("",chat.getChat().getText());
    }
}
