
package com.sm.bachecarest.model;

import java.util.Date;

public class Message {
    
    int id;
    String contenuto;
    Date date_time;
    int n_like;
    String cod_utente;

    public Message(int id, String contenuto, Date date_time, int n_like, String cod_utente) {
        this.id = id;
        this.contenuto = contenuto;
        this.date_time = date_time;
        this.n_like = n_like;
        this.cod_utente = cod_utente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public int getN_like() {
        return n_like;
    }

    public void setN_like(int n_like) {
        this.n_like = n_like;
    }

    public String getCod_utente() {
        return cod_utente;
    }

    public void setCod_utente(String cod_utente) {
        this.cod_utente = cod_utente;
    }
    
}
