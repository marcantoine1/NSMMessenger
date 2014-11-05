/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.p56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Vue.FXMLControllerPageLogin;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author Ordinatron
 */
public class FXMLControllerLoginTest{
     @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
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
    
    @Before
    public void setUp() {
        FXMLControllerPageLogin login = new FXMLControllerPageLogin();
        lblCreationCompte = login.getLblCreerCompte();
        cmbUtilisateur = login.getCmbUtilisateur();
        txtMotDePasse = login.getMotDePasse();
        txtIp = login.getTxtIp();
        btnConnecter = login.getBtnConnecter();
        btnTester = login.getBtnTester();
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void testChatOuvert (){
        cmbUtilisateur.setPromptText("bob");
        txtMotDePasse.setText("bob");
        btnConnecter.getOnAction().handle(new ActionEvent());
        assertEquals("","");
    }
}
