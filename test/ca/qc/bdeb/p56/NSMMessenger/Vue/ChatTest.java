/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.p56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Vue.Chat;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author patrick
 */
public class ChatTest {
    Chat instance;
    public ChatTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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

    /**
     * Test of updateLobbies method, of class Chat.
     */
    @Test
    @Ignore
    public void testUpdateLobbies() throws IOException {
        System.out.println("updateLobbies");
        String[] lobbies = {"test", "o", "sterone"};
        //Quand l'implémentation dans chatgui serat établie, aller y chercher le chat
        instance.updateLobbies(lobbies);
        assertEquals(3, instance.getListeLobbyClient().getChildrenUnmodifiable().size());
        
    }
}
