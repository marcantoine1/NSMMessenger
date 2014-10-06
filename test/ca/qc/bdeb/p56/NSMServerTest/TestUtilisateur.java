package ca.qc.bdeb.p56.NSMServerTest;


import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Utilisateur;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.fail;
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
    private static ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<>();
 
    public TestUtilisateur() {
    }
    @BeforeClass
    public static void setUpClass() {
        listeUtilisateurs.add(new Utilisateur("patninja", "poire","testMail@mail.com",12,"nomFamille","prenom","homme"));
        listeUtilisateurs.add(new Utilisateur("RobertPatinson", "pomme","testMail@mail.com",12,"nomFamille","prenom","homme"));
        listeUtilisateurs.add(new Utilisateur("AndreGarsOuFille", "Grenade","testMail@mail.com",12,"nomFamille","prenom","homme"));
        listeUtilisateurs.add(new Utilisateur("DisBeaucoupDesAffaires", "banane","testMail@mail.com",12,"nomFamille","prenom","homme"));
        listeUtilisateurs.add(new Utilisateur("ette", "asperge","testMail@mail.com",12,"nomFamille","prenom","homme"));
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
        Assert.assertEquals("patninja", listeUtilisateurs.get(0).getUsername());
    }
    @Test
    public void testGetUnsecuredPassword() {
       Assert.assertEquals("poire", listeUtilisateurs.get(0).getUnsecuredPassword()); 
    }
    @Test
    public void testGetId() {
       Assert.assertEquals(1, listeUtilisateurs.get(0).getId()); 
    }
    @Test
    public void testGetCourriel() {
       Assert.assertEquals("testMail@mail.com", listeUtilisateurs.get(0).getCourriel()); 
    }
    @Test
    public void lesIdSontUniques(){
        ArrayList<Integer> listeIdUtilises = new ArrayList<>();
        for(Utilisateur u : listeUtilisateurs){
            if(listeIdUtilises.contains(u.getId())){
                fail();
            }
            else{
                listeIdUtilises.add(u.getId());
            }
                
        }
    }
}
