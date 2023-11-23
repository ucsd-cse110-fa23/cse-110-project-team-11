/**
 * Password.java encrypts the password based on the user input from
 * the app (most likely a textArea).
 * 
 * When user account is created, app should create User object,
 * which then calls this class to encrypt the textArea password
 * to then assign back at the User account.
 */
package pantryPal.client.UserAccount;

import java.math.BigInteger;  
import java.nio.charset.StandardCharsets;  
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;

public class Password {
    /**
     * encrypts the password from account creation
     * @param password
     * @return encryped password to be sent to MongoDB
     */
    public String encryptPassword (String password) {
        String encryptedPassword = "";
        try {
            encryptedPassword = toHexString(getSHA(password));
        } catch (Exception e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);  
        }
        return encryptedPassword;
    }

    /**
     * Helper method that hashes the string into SHA-256 hashing algorithm
     * @param input password
     * @return hashed information in bytes
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSHA (String input) throws NoSuchAlgorithmException {  
        /* MessageDigest instance for hashing using SHA256 */  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
  
        /**
         * digest() method called to calculate message digest of an input and
         * return array of byte
         */
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    }

    /**
     * converts hashed code into "readable" hexcode
     * @param hash hashed value
     * @return
     */
    public static String toHexString (byte[] hash) {  
        // Convert byte array of hash into digest
        BigInteger number = new BigInteger(1, hash);  
  
        // Convert the digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros  
        while (hexString.length() < 32) {  
            hexString.insert(0, '0');  
        }  
  
        return hexString.toString();  
    }  
}
