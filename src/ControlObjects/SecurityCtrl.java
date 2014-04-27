package ControlObjects;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    static public String digestPassword(String passwd){
        return toMD5("PASSWORD_SALT" + passwd);
    }
}
