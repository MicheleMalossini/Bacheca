
package com.sm.bachecarest.model;

public class User {
    
    String cod_alfanum;
    String nome;
    String cognome;

    public User(String cod_alfanum, String nome, String cognome) {
        this.cod_alfanum = cod_alfanum;
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getCod_alfanum() {
        return cod_alfanum;
    }

    public void setCod_alfanum(String cod_alfanum) {
        this.cod_alfanum = cod_alfanum;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    
}
