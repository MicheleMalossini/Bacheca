/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Entities;

import java.sql.Date;
import java.sql.Date;

/**
 *
 * @author MM
 */
public class Comment {
    int id;
    String contenuto;
    int n_like;
    int cod_utente;
    int id_m;

    public Comment(String contenuto, int cod_utente, int id_m) {
        this.contenuto = contenuto;
        this.n_like = 0;
        this.cod_utente = cod_utente;
        this.id_m = id_m;
    }

    public Comment(int id, String contenuto, int n_like, int cod_utente, int id_m) {
        this.id = id;
        this.contenuto = contenuto;
        this.n_like = n_like;
        this.cod_utente = cod_utente;
        this.id_m = id_m;
    }
    
    public int getID() {
        return id;
    }

    public String getContent() {
        return contenuto;
    }

    public int getLike() {
        return n_like;
    }

    public int getUserID() {
        return cod_utente;
    }

    public int getMessageID() {
        return id_m;
    }

    
    
}
