package ca.qc.bdeb.p56.NSMMessengerServer.Application;

import ca.qc.bdeb.P56.NSMMessengerServer.Application.Encrypteur;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;

/**
 * Created by Martin on 2014-11-11.
 */
public class TestEncrypteur {
    private  final String cle="859E381543769334";
    private final String texteAEncrypter="TextedeTest";
    public TestEncrypteur(){

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
    public void testEncrypter() {
        assertFalse(texteAEncrypter.equals(Encrypteur.encrypter(texteAEncrypter, cle)));
    }
    @Test
    public void testDecrypter(){
        String messageEncrypte=Encrypteur.encrypter(texteAEncrypter,cle);

        assertEquals(texteAEncrypter,Encrypteur.decrypter(messageEncrypte,cle));
    }
}
