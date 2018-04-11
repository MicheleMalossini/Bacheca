/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MM
 */
public class Utils {
    private static MessageDigest digester;
 
    /**
     * 
     * @param pass1 prima password della form login
     * @param pass2 seconda password della form login
     * @return ritorna true se le due password corrispondono,false se non
     */
    public static boolean checkPassword(char [] pass1,char[] pass2){
        String password1=new String(pass1);
        String password2=new String(pass2);
        if (password1.equals(password2))
            return true;
        return false;  
    }
    /**
     * 
     * @param pass password presa dalla forma login
     * @return una string corrispondente alla pass criptata in MD5
     */
    public static String crypt(char[] pass){
        try {
           digester=MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String str=new String(pass);
        
         if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }

        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            }
            else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();  
    }
    
}
