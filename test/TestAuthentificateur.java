/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ca.qc.bdeb.P56.NSMMessengerServer.Modele.Utilisateur;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
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
    private static ArrayList<Utilisateur> listeUtilisateurs;
    @BeforeClass
    public static void setUpClass() {
        listeUtilisateurs = new ArrayList<>();
        listeUtilisateurs.add(new Utilisateur("coolGuillaume", "sexyahri123"));
        creerListeTestSerialisee();
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
    public void testUnserializeUserList() {

    }
    @Test
    public void testSerializeUserList() {

    }
    
    private static void creerListeTestSerialisee() {
        ObjectOutputStream ecrivainObjet = null;

        try {
            final FileOutputStream fichier = new FileOutputStream("ressources/tests/listeUtilisateursTest.ser");
            ecrivainObjet = new ObjectOutputStream(fichier);
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ecrivainObjet != null) {
                    ecrivainObjet.writeObject(listeUtilisateurs);
                    ecrivainObjet.flush();
                    ecrivainObjet.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
