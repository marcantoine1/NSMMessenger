/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.p56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Application.JukeBox;
import ca.qc.bdeb.P56.NSMMessenger.Vue.FXMLControllerPageLogin;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author Ordinatron
 */
public class FXMLControllerLoginTest{
     @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
     FXMLControllerPageLogin login;
     Label lblCreationCompte;
     ComboBox cmbUtilisateur;
     PasswordField txtMotDePasse;
     TextField txtIp;
     Button btnConnecter;
     Button btnTester;
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    //FIXME: Faut regler les sons car ils marchent mal
    @Before
    public void setUp() {
        JukeBox.init();
        JukeBox.load("../../Ressources/Sounds/BackgroundMusic.wav", "BackgroundMusic");
        JukeBox.load("../../Ressources/Sounds/NSM.wav", "NSM");
        login = new FXMLControllerPageLogin();
        login.jouerSon();
        lblCreationCompte = login.getLblCreerCompte();
        cmbUtilisateur = login.getCmbUtilisateur();
        txtMotDePasse = login.getMotDePasse();
        txtIp = login.getTxtIp();
        btnConnecter = login.getBtnConnecter();
        btnTester = login.getBtnTester();
    }
    
    @After
    public void tearDown() {
        JukeBox.stop("BackgroundMusic");
        JukeBox.stop("NSM");
        try{
            Thread.currentThread().join(1000);
        }catch(InterruptedException e){

        }

    }
    

    
    @Test
    public void testSonNSM() {
       assertEquals(true, JukeBox.isPlaying("NSM"));
    }
    
    @Test
    public void testSonBackgroundMusic() {
        assertTrue(JukeBox.isPlaying("BackgroundMusic"));
    }
        
    @Test
    public void testSonPlayStop() {
        login.playStopClick();
        assertEquals(false, JukeBox.isPlaying("NSM"));
        assertEquals(false, JukeBox.isPlaying("BackgroundMusic"));
        login.playStopClick();
         try {
             Thread.sleep(1000);
         }
         catch (InterruptedException ex) {
             Logger.getLogger(FXMLControllerLoginTest.class.getName()).log(Level.SEVERE, null, ex);
         }
        assertEquals(true, JukeBox.isPlaying("NSM"));
        assertEquals(true, JukeBox.isPlaying("BackgroundMusic"));
    }
}
