/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.service;

/**
 *
 * @author MM
 */
public class Utils {
    
    public boolean checkPassword(String password,String c_password){
        if (password.equals(c_password))
            return true;
        return false;  
    }
    
}
