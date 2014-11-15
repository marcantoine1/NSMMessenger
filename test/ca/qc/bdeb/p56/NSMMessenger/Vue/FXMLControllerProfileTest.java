/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.p56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Vue.FXMLControllerProfil;
import javafx.scene.control.Button;
import org.junit.*;

/**
 *
 * @author Ordinatron
 */
public class FXMLControllerProfileTest {
    @Rule 
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
    Button btnAddRemove;
    @BeforeClass    
    public static void setUpClass() {        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        FXMLControllerProfil profil = new FXMLControllerProfil();
        btnAddRemove = profil.getBtnAddRemove();
        
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void testModificationImageBouton(){
        
    }

}
