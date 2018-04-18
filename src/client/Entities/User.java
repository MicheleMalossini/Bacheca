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
    String cod_alfanum;
    String nome;
    String cognome;
    String mail;
    String password;

    public User(String cod_alfanum,String nome, String cognome,String mail, String password) {
        this.cod_alfanum=cod_alfanum;
        this.nome = nome;
        this.cognome = cognome;
        this.mail=mail;
        this.password = password;
    }
    
     public User(String nome, String cognome,String mail, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail=mail;
        this.password = password;
    }

    public String getCodice() {
        return cod_alfanum;
    }

    public String getEmail() {
        return mail;
    }

    public String getName() {
        return nome;
    }

    public String getSurname() {
        return cognome;
    }

    public String getPassword() {
        return password;
    }
    
    
    
}
