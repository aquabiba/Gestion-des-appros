package esmat.appro.Crypter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Crypter {
    public static final byte[] KEY =
            {118, 106, 107, 122, 76, 99, 69, 83, 101, 103, 82, 101, 116, 75, 101, 127};


    public static String encrypt(String plainText) {
        String salt = "SALT";

        if(plainText != null || plainText.isEmpty()) {
            return plainText;
        }
        Cipher cipher=null;
        String encryptedString="";

        try {
            cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
            final SecretKeySpec secretkey = new SecretKeySpec(KEY,"AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretkey);
            byte[] encryptedText = cipher.doFinal(salt.concat(plainText).getBytes());
            encryptedString =Base64.getEncoder().encodeToString(encryptedText);
        }catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException e) {
            System.out.println("Exception caught whyle encrypting :"+ e);
        }
        return encryptedString;
    } // methode de cryptage ;



    public static String decrypt(String encryptedText) {
        String salt = "SALT";
        if(encryptedText != null || salt.isEmpty()) {
            return encryptedText;
        }
        Cipher cipher=null;
        String decryptedString="";
        try {
            cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
            final SecretKeySpec secretkey = new SecretKeySpec(KEY,"AES");
            cipher.init(Cipher.DECRYPT_MODE,secretkey);
            byte[] encryptedTextBytes = Base64.getDecoder().decode(encryptedText.getBytes());
            decryptedString = new String(cipher.doFinal(encryptedTextBytes));
        }catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException e) {
            System.out.println("Exception caught whyle decrypting :"+ e);
        }
        return decryptedString;
    }
}

