/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Entities;

/**
 *
 * @author MM
 */
public class User {
    String codice;
    String name;
    String surname;
    String email;
    String password;

    public User(String codice,String name, String surname,String email, String password) {
        this.codice=codice;
        this.name = name;
        this.surname = surname;
        this.email=email;
        this.password = password;
    }
    
     public User(String name, String surname,String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email=email;
        this.password = password;
    }

    public String getCodice() {
        return codice;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }
    
    
    
}
