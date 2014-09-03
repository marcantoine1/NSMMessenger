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
    import ca.qc.bdeb.P56.NSMMessengerServer.NSMServer;
    import org.junit.After;
    import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
    import static org.junit.Assert.assertNotNull;
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
        assertEquals(server.server.getConnections().length, 1);
    }
    
}
