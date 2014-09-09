/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1150580
 */

import ca.qc.bdeb.P56.NSMMessenger.NSMClient;
    import ca.qc.bdeb.P56.NSMMessenger.NSMMessenger;
import ca.qc.bdeb.P56.NSMMessengerServer.ConnectionUtilisateur;
    import ca.qc.bdeb.P56.NSMMessengerServer.NSMServer;
import java.util.logging.Level;
import java.util.logging.Logger;
    import org.junit.After;
    import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
    import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
    import org.junit.Before;
    import org.junit.BeforeClass;
import org.junit.Test;

public class TestConnection {
    
    NSMServer server;
    NSMClient client;
    
    public TestConnection(){
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
    }
    
     @AfterClass
    public static void tearDownClass() 
    {
    }
    
    @Before
    public void setUp() 
    {
        server = new NSMServer();
        client = new NSMClient();
        client.connect();
    }
    
    @After
    public void tearDown() 
    {
        server = null;
        client = null;
    }
    
    @Test
    public void testConnection()
    {
        assertEquals(1, server.server.getConnections().length);
        assertEquals(true, client.client.isConnected());
    }
    
    @Test
    public void testLogin()
    {
        try {
            
            client.login("admin", "password");
            Thread.sleep(100);
        
            assertEquals(1, server.connections.size());
            assertEquals("admin", server.connections.values().toArray(new ConnectionUtilisateur[server.connections.size()])[0].username);
            
         } catch (InterruptedException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testMessage()
    {
        try {
            client.login("admin", "password");
            Thread.sleep(100);
            client.sendMessage("test");
            Thread.sleep(100);
        
            assertTrue(client.messages.contains("admin: test"));
        } catch (InterruptedException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
