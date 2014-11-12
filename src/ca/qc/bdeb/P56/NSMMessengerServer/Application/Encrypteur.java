package ca.qc.bdeb.P56.NSMMessengerServer.Application;









import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            return new String(Base64.encode(cipher.doFinal(texteAEncrypter.getBytes())));
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
            byte[] encrypte= Base64.decode(messageEncrypte.getBytes());
            byte[] decryption=cipher.doFinal(encrypte);
            return new String(decryption);
        } catch (Exception e) {
            Logger.getLogger(Authentificateur.class.getName()).log(Level.SEVERE, e.getMessage());
            return null;
        }

    }
}
