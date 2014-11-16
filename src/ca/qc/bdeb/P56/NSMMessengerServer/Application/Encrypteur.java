package ca.qc.bdeb.P56.NSMMessengerServer.Application;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Martin on 2014-11-11.
 */
public class Encrypteur {

    public static String encrypter(String texteAEncrypter, String cle) {
        Cipher cipher;
        Key CleSecurite = new SecretKeySpec(cle.getBytes(), "AES");
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, CleSecurite);
            return new String(new Base64().encode(cipher.doFinal(texteAEncrypter.getBytes())));
        } catch (Exception e) {
            Logger.getLogger(Authentificateur.class.getName()).log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static String decrypter(String messageEncrypte, String cle) {
        Cipher cipher;
        Key CleSecurite = new SecretKeySpec(cle.getBytes(), "AES");
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, CleSecurite);
            byte[] encrypte= new Base64().decode(messageEncrypte.getBytes());
            byte[] decryption=cipher.doFinal(encrypte);
            return new String(decryption);
        } catch (Exception e) {
            Logger.getLogger(Authentificateur.class.getName()).log(Level.SEVERE, e.getMessage());
            return null;
        }

    }
}
