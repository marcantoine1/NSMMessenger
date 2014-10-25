package ca.qc.bdeb.p56.NSMGUITest;

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
    static ChatGUI gui;
    static ChatPrimitif chat;
    public TestChatPrimitif() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        //TODO: test de gui, utiliser le gui au lieu du client
        server = new NSMServer();
        client = new NSMClient();
        gui = new ChatGUI();
        chat = gui.chat;
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
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
        //assertEquals("coolGuillaume: test",chat.getLblChat().getText());
        
    }
    
    @Test
    @Ignore
    public void verifierTexteVide(){
        chat.getChat().setText("test");
        chat.getButton().doClick();
        assertEquals("",chat.getChat().getText());
    }
}
