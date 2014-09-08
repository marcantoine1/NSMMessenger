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
    private static ArrayList<Utilisateur> listeUtilisateurs;
    private final static String LOCATION_LISTE_UTILISATEURS_TEST = "ressources/tests/listeUtilisateursTest.ser";
    static Authentificateur TestAuthentificateur;

    @BeforeClass
    public static void setUpClass() {
        TestAuthentificateur = Authentificateur.getInstanceAuthentificateur(LOCATION_LISTE_UTILISATEURS_TEST);
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
    public void laListeDeserialiseeEstLaMemeQueCelleSerialisee() {
        TestAuthentificateur.demarrerAuthentificateur();
        ArrayList<Utilisateur> listeAComparer = TestAuthentificateur.getListeUtilisateurs();
        assertEquals(1, listeAComparer.size());
        assertTrue(comparerListeUtilisateurs(listeAComparer, listeUtilisateurs));
        TestAuthentificateur.sauvegarderEtFermerAuthentificateur();
    }
    @Test
    public void OnNePeutPasDemarrerDeuxAuthentificateur() {
        
    }

    @Test
    public void testSerializeUserList() {

    }

    private static void creerListeTestSerialisee() {
        ObjectOutputStream ecrivainObjet = null;

        try {
            final FileOutputStream fichier = new FileOutputStream(LOCATION_LISTE_UTILISATEURS_TEST);
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

    private boolean comparerListeUtilisateurs(ArrayList<Utilisateur> listeUtilisateur1, ArrayList<Utilisateur> listeUtilisateur2) {
        if (listeUtilisateur1.size() == listeUtilisateur2.size()) {
            for (int i = 0; i < listeUtilisateur1.size(); i++) {
                Utilisateur utilisateur1 = listeUtilisateur1.get(i);
                Utilisateur utilisateur2 = listeUtilisateur2.get(i);
                if (!utilisateur1.getUsername().equals(utilisateur2.getUsername())) {
                    return false;
                }
                if (!utilisateur1.getUnsecuredPassword().equals(utilisateur2.getUnsecuredPassword())) {
                    return false;
                }
                if(utilisateur1.getId() != utilisateur2.getId()){
                    return false;
                }
            }
        }
        return true;
    }
}
