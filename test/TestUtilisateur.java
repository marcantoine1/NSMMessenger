
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Utilisateur;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1150275
 */
public class TestUtilisateur {
    Utilisateur Patrick;
    public TestUtilisateur() {
        Patrick = new Utilisateur("patninja", "poire");
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
    
    @Test
    public void testGetUsername() {
        Assert.assertEquals("patninja", Patrick.getUsername());
    }
    @Test
    public void testGetUnsecuredPassword() {
       Assert.assertEquals("poire", Patrick.getUnsecuredPassword()); 
    }
}
