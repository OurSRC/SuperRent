package ControlObjects;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Elitward
 */
public class SecurityCtrl {

    private final String PASSWORD_SALT = "src";     //this is a simply text to be mix with password, to against rainbow table

    static public String toMD5(String text) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecurityCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        md.update(text.getBytes());
        byte byteData[] = md.digest();

        //convert the byte to hex format method 2
        StringBuffer hexString;
        hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        System.out.println("Digest(in hex format):: " + hexString.toString());
        return hexString.toString();
    }

    static public String digestPassword(String passwd) {
        return toMD5("PASSWORD_SALT" + passwd);
    }

    // 8-byte Salt
    final static byte[] salt = {
        (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
        (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
    };
    // Iteration count
    final static int iterationCount = 19;


    /**
     * 
     * @param secretKey Key used to encrypt data
     * @param plainText Text input to be encrypted
     * @return Returns encrypted text
     * 
     */
    static public String encrypt(String secretKey, String plainText) {
        try {
            //Key generation for enc and desc
            KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            
            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            
            //Enc process
            Cipher ecipher;
            ecipher = Cipher.getInstance(key.getAlgorithm());
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            String charSet="UTF-8";
            byte[] in = plainText.getBytes(charSet);
            byte[] out = ecipher.doFinal(in);
            String encStr=new sun.misc.BASE64Encoder().encode(out);
            return encStr;
        } catch (Exception ex) {
            Logger.getLogger(SecurityCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     /**     
     * @param secretKey Key used to decrypt data
     * @param encryptedText encrypted text input to decrypt
     * @return Returns plain text after decryption
     */
    static public String decrypt(String secretKey, String encryptedText) {
        try {
            //Key generation for enc and desc
            KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            
            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            
            //Decryption process; same key will be used for decr
            Cipher dcipher;
            dcipher=Cipher.getInstance(key.getAlgorithm());
            dcipher.init(Cipher.DECRYPT_MODE, key,paramSpec);
            byte[] enc = new sun.misc.BASE64Decoder().decodeBuffer(encryptedText);
            byte[] utf8 = dcipher.doFinal(enc);
            String charSet="UTF-8";
            String plainStr = new String(utf8, charSet);
            return plainStr;
        } catch (Exception ex) {
            Logger.getLogger(SecurityCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
}
