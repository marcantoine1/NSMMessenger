/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Authentificateur;
import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Utilisateur;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author 1150275
 */
public class TestAuthentificateur {

    public TestAuthentificateur() {
    }
    static Authentificateur TestAuthentificateur;

    @BeforeClass
    public static void setUpClass() {
        TestAuthentificateur = Authentificateur.getInstanceAuthentificateur();
        TestAuthentificateur.setNomBd("dbTest.db");
        TestAuthentificateur.creerUtilisateur("coolGuillaume", "sexyahri123", "guillaumesamurai@hotmail.ca",12,"nomFamille","prenom","homme");
        TestAuthentificateur.creerUtilisateur("coolRobert", "sexyahri123", "robertfroid@hotmail.ca",12,"nomFamille","prenom","homme");
        TestAuthentificateur.creerUtilisateur("coolAndré", "sexyahri123", "andresuper@hotmail.ca",12,"nomFamille","prenom","homme");
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
    public void AuthentificateurEstToujoursLeMeme() {
        assertEquals(TestAuthentificateur, Authentificateur.getInstanceAuthentificateur());
    }
    @Test
    public void AuthentifierUnUtilisateur() {
        assertTrue(TestAuthentificateur.authentifierUtilisateur("coolGuillaume", "sexyahri123"));
        
    }
    @Test
    public void TesterTrouverUnUtilisateur(){
        assertTrue(TestAuthentificateur.chercherUtilisateur("coolGuillaume"));
    }
}
